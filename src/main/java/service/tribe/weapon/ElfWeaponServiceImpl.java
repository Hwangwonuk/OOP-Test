package service.tribe.weapon;

import monster.Monster;
import tribe.Elf;
import util.WeaponStatKeys;
import weapon.elf.bow.Bow;

import java.math.BigDecimal;

public class ElfWeaponServiceImpl implements ElfWeaponService{
    @Override
    public void equipWeapon(Elf elf, Bow bow) {
        elf.validateAlive(); // 캐릭터가 죽었으면 에러 발생

        /*        validateWeaponCompatibility(human, sword); // 종족에 맞는 무기 장착이 아니라면 에러 발생*/

        elf.setEquipWeapon(true); // 무기 착용여부 true

        calculateWeaponAttackPower(elf, bow);

        calculateWeaponAttackSpeed(elf, bow);
    }

    /* 종족별 ServiceImpl을 따로 만들었기 떄문에 필요없는 로직
    private void validateWeaponCompatibility(Human human, Sword sword) {
        if (!sword.getWeaponTribe().equals(human.getTribeName())){
            throw new RuntimeException("종족에 맞는 무기를 착용해주세요.");
        }
    }
    */

    private void calculateWeaponAttackPower(Elf elf, Bow bow) {
        // 무기로 인해 변경될 공격력 수치
        elf.setWeaponAttackPower(
                elf.getAttackPower()
                        .multiply(bow.getWeaponStat().get(WeaponStatKeys.ATTACK_POWER_PERCENTAGE))
        );

        // 총 공격력 = 기본 공격력 + 무기로 인해 변경될 수치 + 스킬로 인해 변경된 수치
        elf.setTotalAttackPower(
                elf.getAttackPower()
                        .add(elf.getWeaponAttackPower())
                        .add(elf.getSkillAttackPower())
        );
    }

    private void calculateWeaponAttackSpeed(Elf elf, Bow bow) {
        // 무기로 인해 변경될 공격속도 수치
        elf.setWeaponAttackSpeed(
                elf.getAttackSpeed()
                        .multiply(bow.getWeaponStat().get(WeaponStatKeys.ATTACK_SPEED_PERCENTAGE))
        );

        // 총 공격속도 = 기본 공격속도 + 무기로 인해 변경될 수치 + 스킬로 인해 변경된 수치
        elf.setTotalAttackSpeed(
                elf.getAttackSpeed()
                        .add(elf.getWeaponAttackSpeed())
        );
    }

    @Override
    public void attack(Elf attacker, Monster defender) {
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

    private BigDecimal calculateDefenderTotalDamage(Elf attacker, Monster defender) {
        return attacker.getTotalAttackPower().subtract(defender.getDefencePower());
    }

    private void validateAttackReadiness(Elf attacker) {
        if (attacker.getEquipWeapon() == false) {
            throw new RuntimeException("무기가 없습니다.");
        }

        if (attacker.validateAttackCoolDown() == false) {
            throw new RuntimeException("아직 공격 쿨다운이 남아 있습니다.");
        }
    }

    private Boolean isTakeDamageByCounterAttack(Monster attacker, Elf defender) {
        return attacker.getAttackPower().multiply(new BigDecimal("0.7")).compareTo(defender.getTotalDefencePower()) > 0;
    }

    private BigDecimal calculateTribeCounterAttackDamage(Monster attacker, Elf defender) {
        return attacker.getAttackPower().multiply(new BigDecimal("0.7")).subtract(defender.getTotalDefencePower());
    }
}
