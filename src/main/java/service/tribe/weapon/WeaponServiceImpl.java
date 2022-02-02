package service.tribe.weapon;

import monster.Monster;
import tribe.Tribe;
import util.WeaponStatKeys;
import weapon.Weapon;

import java.math.BigDecimal;

public class WeaponServiceImpl implements WeaponService {

    @Override
    public void equipWeapon(Tribe tribe, Weapon weapon) {
        tribe.validateAlive(); // 캐릭터가 죽었으면 에러 발생

        validateWeaponCompatibility(tribe, weapon); // 종족에 맞는 무기 장착이 아니라면 에러 발생

        tribe.setEquipWeapon(true); // 무기 착용여부 true

        calculateWeaponAttackPower(tribe, weapon);

        calculateWeaponAttackSpeed(tribe, weapon);
    }

    private void validateWeaponCompatibility(Tribe tribe, Weapon weapon) {
        if (!weapon.getWeaponTribe().equals(tribe.getTribeName())){
            throw new RuntimeException("종족에 맞는 무기를 착용해주세요.");
        }
    }

    private void calculateWeaponAttackPower(Tribe tribe, Weapon weapon) {
        // 무기로 인해 변경될 공격력 수치
        tribe.setWeaponAttackPower(
                tribe.getAttackPower()
                        .multiply(weapon.getWeaponStat().get(WeaponStatKeys.ATTACK_POWER_PERCENTAGE))
        );

        // 총 공격력 = 기본 공격력 + 무기로 인해 변경될 수치 + 스킬로 인해 변경된 수치
        tribe.setTotalAttackPower(
                tribe.getAttackPower()
                        .add(tribe.getWeaponAttackPower())
                        .add(tribe.getSkillAttackPower())
        );
    }

    private void calculateWeaponAttackSpeed(Tribe tribe, Weapon weapon) {
        // 무기로 인해 변경될 공격속도 수치
        tribe.setWeaponAttackSpeed(
                tribe.getAttackSpeed()
                        .multiply(weapon.getWeaponStat().get(WeaponStatKeys.ATTACK_SPEED_PERCENTAGE))
        );

        // 총 공격속도 = 기본 공격속도 + 무기로 인해 변경될 수치 + 스킬로 인해 변경된 수치
        tribe.setTotalAttackSpeed(
                tribe.getAttackSpeed()
                        .add(tribe.getWeaponAttackSpeed())
        );
    }

    @Override
    public void attack(Tribe attacker, Monster defender) {
        attacker.validateAlive(); // 캐릭터가 죽었으면 에러 발생
        defender.validateAlive(); // 이미 몬스터가 죽었으면 에러 발생

        validateAttackReadiness(attacker); // 공격할 준비가 돼있지 않다면 에러 발생
        attacker.setLastAttackMillisecondTime(System.currentTimeMillis()); // 공격 쿨다운 계산을 위한 현재 공격시간 저장

        BigDecimal totalDamage = calculateDefenderTotalDamage(attacker, defender); // 최종 데미지

        if (totalDamage.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("몬스터 방어력이 최종 데미지보다 높거나 같습니다.");
        } else {
            defender.takeDamage(totalDamage); // 몬스터 피격

            if (defender.isDead()){ // 몬스터가 죽었는지 여부
                attacker.levelUp();
                System.out.println("캐릭터 레벨업! 레벨 : " + attacker.getLevel());
            } else if (defender.isCounterAttack() && !attacker.isDodge()){ // 몬스터가 반격했지만 캐릭터가 회피에 실패했다면
                if(isTakeDamageByCounterAttack(defender, attacker)) { // 몬스터 반격 스킬 공격력이 캐릭터 방어력보다 높다면
                    attacker.takeDamage(calculateTribeCounterAttackDamage(defender, attacker)); // 반격 성공
                } else {
                    System.out.println("캐릭터 방어력이 몬스터 반격 스킬 데미지보다 높거나 같습니다.");
                    System.out.println("* 캐릭터 방어력 : " + attacker.getTotalDefencePower());
                    System.out.println("* 몬스터 반격 스킬 데미지 : " + defender.getAttackPower().multiply(new BigDecimal("0.7")));
                }
            } else {
                System.out.println("반격실패 혹은 반격회피 성공");
            }
        }
    }

    private BigDecimal calculateDefenderTotalDamage(Tribe attacker, Monster defender) {
        return attacker.getTotalAttackPower().subtract(defender.getDefencePower());
    }

    private void validateAttackReadiness(Tribe attacker) {
        if (attacker.getEquipWeapon() == false) {
            throw new RuntimeException("무기가 없습니다.");
        }

        if (attacker.validateAttackCoolDown() == false) {
            throw new RuntimeException("아직 공격 쿨다운이 남아 있습니다.");
        }
    }

    private Boolean isTakeDamageByCounterAttack(Monster attacker, Tribe defender) {
        return attacker.getAttackPower().multiply(new BigDecimal("0.7")).compareTo(defender.getTotalDefencePower()) > 0;
    }

    private BigDecimal calculateTribeCounterAttackDamage(Monster attacker, Tribe defender) {
        return attacker.getAttackPower().multiply(new BigDecimal("0.7")).subtract(defender.getTotalDefencePower());
    }
}
