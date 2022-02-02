package service.tribe.skill;

import skill.tribe.TribeSkill;
import skill.tribe.UltimateSkill;
import skill.common.CommonSkill;
import tribe.Tribe;
import util.SkillStatKeys;

import java.math.RoundingMode;

public class SkillServiceImpl implements SkillService {

    @Override
    public void useCommonSkill(Tribe tribe, CommonSkill commonSkill) {
        tribe.validateAlive();

        checkRemainMp(tribe, commonSkill);

        calculateCommonSkillHp(tribe, commonSkill);

        calculateCommonSkillAttackPower(tribe, commonSkill);

        calculateCommonSkillMp(tribe, commonSkill);
    }

    private void checkRemainMp(Tribe tribe, CommonSkill commonSkill) {
        if (tribe.getMp().compareTo(commonSkill.getSkillStat().get(SkillStatKeys.MP_COST)) < 0) {
            throw new RuntimeException("공통 스킬을 사용할 마나가 부족합니다");
        }
    }

    private void calculateCommonSkillHp(Tribe tribe, CommonSkill commonSkill) {
        tribe.setHp(
                tribe.getHp().add(
                        tribe.getHp().multiply(commonSkill.getSkillStat().get(SkillStatKeys.HP_PERCENTAGE))
                ).setScale(1, RoundingMode.CEILING)
        );
    }

    private void calculateCommonSkillAttackPower(Tribe tribe, CommonSkill commonSkill) {
        // 스킬로 인해 변경될 공격력 수치
        tribe.setSkillAttackPower(
                tribe.getAttackPower()
                        .multiply(commonSkill.getSkillStat().get(SkillStatKeys.ATTACK_POWER_PERCENTAGE))
        );

        // 총 공격력 = 기본 공격력 + 무기로 인해 변경될 수치 + 스킬로 인해 변경된 수치
        tribe.setTotalAttackPower(
                tribe.getAttackPower()
                        .add(tribe.getWeaponAttackPower())
                        .add(tribe.getSkillAttackPower())
        );
    }

    private void calculateCommonSkillMp(Tribe tribe, CommonSkill commonSkill) {
        tribe.setMp(
                tribe.getMp().subtract(
                        commonSkill.getSkillStat().get(SkillStatKeys.MP_COST)
                )
        );
    }

    @Override
    public void useTribeSkill(Tribe tribe, TribeSkill tribeSkill) {
        tribe.validateAlive();

        validateSkillCompatibility(tribe, tribeSkill);

        checkRemainMp(tribe, tribeSkill);

        calculateTribeSkillAttackPower(tribe, tribeSkill);

        calculateTribeSkillDefencePower(tribe, tribeSkill);

        calculateTribeSkillDodgePercentage(tribe, tribeSkill);

        calculateTribeSkillMp(tribe, tribeSkill);
    }

    private void validateSkillCompatibility(Tribe tribe, TribeSkill tribeSkill) {
        if (!tribeSkill.getSkillTribe().equals(tribe.getTribeName())){
            throw new RuntimeException("종족에 맞는 스킬을 사용해주세요.");
        }
    }

    private void checkRemainMp(Tribe tribe, TribeSkill tribeSkill) {
        if (tribe.getMp().compareTo(tribeSkill.getSkillStat().get(SkillStatKeys.MP_COST)) < 0) {
            throw new RuntimeException("종족 스킬을 사용할 마나가 부족합니다");
        }
    }

    private void calculateTribeSkillAttackPower(Tribe tribe, TribeSkill tribeSkill) {
        // 스킬로 인해 변경될 공격력 수치
        tribe.setSkillAttackPower(
                tribe.getAttackPower()
                        .multiply(tribeSkill.getSkillStat().get(SkillStatKeys.ATTACK_POWER_PERCENTAGE))
        );

        // 총 공격력 = 기본 공격력 + 무기로 인해 변경될 수치 + 스킬로 인해 변경된 수치
        tribe.setTotalAttackPower(
                tribe.getAttackPower()
                        .add(tribe.getWeaponAttackPower())
                        .add(tribe.getSkillAttackPower())
        );
    }

    private void calculateTribeSkillDefencePower(Tribe tribe, TribeSkill tribeSkill) {
        // 스킬로 인해 변경될 방어력 수치
        tribe.setSkillDefencePower(
                tribe.getDefencePower()
                        .multiply(tribeSkill.getSkillStat().get(SkillStatKeys.DEFENCE_POWER_PERCENTAGE))
        );

        // 총 방어력 = 기본 방어력 + 스킬로 인해 변경된 수치
        tribe.setTotalDefencePower(
                tribe.getDefencePower()
                        .add(tribe.getSkillDefencePower())
        );
    }

    private void calculateTribeSkillDodgePercentage(Tribe tribe, TribeSkill tribeSkill) {
        // 스킬로 인해 변경될 회피력 수치
        tribe.setSkillDodgePercentage(
                tribe.getDodgePercentage()
                        .multiply(tribeSkill.getSkillStat().get(SkillStatKeys.DODGE_PERCENTAGE))
        );

        // 총 회피력 = 기본 회피력 + 스킬로 인해 변경된 수치
        tribe.setTotalDodgePercentage(
                tribe.getDodgePercentage()
                        .add(tribe.getSkillDodgePercentage())
        );
    }

    private void calculateTribeSkillMp(Tribe tribe, TribeSkill tribeSkill) {
        tribe.setMp(
                tribe.getMp().subtract(
                        tribeSkill.getSkillStat().get(SkillStatKeys.MP_COST)
                )
        );
    }

    @Override
    public void useUltimateSkill(Tribe tribe, UltimateSkill ultimateSkill) {
        tribe.validateAlive();

        validateSkillCompatibility(tribe, ultimateSkill);

        checkRemainMp(tribe, ultimateSkill);

        calculateUltimateSkillDodgePercentage(tribe, ultimateSkill);

        calculateUltimateSkillAttackSpeed(tribe, ultimateSkill);

        calculateUltimateSkillAttackPower(tribe, ultimateSkill);

        calculateUltimateSkillMp(tribe, ultimateSkill);
    }

    private void validateSkillCompatibility(Tribe tribe, UltimateSkill ultimateSkill) {
        if (!ultimateSkill.getSkillTribe().equals(tribe.getTribeName())){
            throw new RuntimeException("종족에 맞는 스킬을 사용해주세요.");
        }
    }

    private void checkRemainMp(Tribe tribe, UltimateSkill ultimateSkill) {
        if (tribe.getMp().compareTo(ultimateSkill.getSkillStat().get(SkillStatKeys.MP_COST)) < 0) {
            throw new RuntimeException("궁극 스킬을 사용할 마나가 부족합니다");
        }
    }

    private void calculateUltimateSkillDodgePercentage(Tribe tribe, UltimateSkill ultimateSkill) {
        // 스킬로 인해 변경될 회피력 수치
        tribe.setSkillDodgePercentage(
                ultimateSkill.getSkillStat().get(SkillStatKeys.DODGE_PERCENTAGE)
        );

        // 총 회피력 = 기본 회피력 + 스킬로 인해 변경된 수치
        tribe.setTotalDodgePercentage(
                tribe.getDodgePercentage()
                        .add(tribe.getSkillDodgePercentage())
        );
    }

    private void calculateUltimateSkillAttackSpeed(Tribe tribe, UltimateSkill ultimateSkill) {
        // 스킬로 인해 변경될 공격속도 수치
        tribe.setSkillAttackSpeed(
                tribe.getAttackSpeed()
                        .multiply(ultimateSkill.getSkillStat().get(SkillStatKeys.ATTACK_SPEED_PERCENTAGE))
        );

        // 총 공격속도 = 기본 공격속도 + 무기로 인해 변경된 수치 + 스킬로 인해 변경된 수치
        tribe.setTotalAttackSpeed(
                tribe.getAttackSpeed()
                        .add(tribe.getWeaponAttackSpeed())
                        .add(tribe.getSkillAttackSpeed())
        );
    }

    private void calculateUltimateSkillAttackPower(Tribe tribe, UltimateSkill ultimateSkill) {
        // 스킬로 인해 변경될 공격력 수치
        tribe.setSkillAttackPower(
                tribe.getAttackPower()
                        .multiply(ultimateSkill.getSkillStat().get(SkillStatKeys.ATTACK_POWER_PERCENTAGE))
        );

        // 총 공격력 = 기본 공격력 + 무기로 인해 변경될 수치 + 스킬로 인해 변경된 수치
        tribe.setTotalAttackPower(
                tribe.getAttackPower()
                        .add(tribe.getWeaponAttackPower())
                        .add(tribe.getSkillAttackPower())
        );
    }

    private void calculateUltimateSkillMp(Tribe tribe, UltimateSkill ultimateSkill) {
        tribe.setMp(
                tribe.getMp().subtract(
                        ultimateSkill.getSkillStat().get(SkillStatKeys.MP_COST)
                )
        );
    }
}
