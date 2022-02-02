package service.monster.attack;

import monster.Monster;
import tribe.Tribe;

import java.math.BigDecimal;

public class MonsterAttackServiceImpl implements MonsterAttackService {

    @Override
    public void attack(Monster attacker, Tribe defender) {
        attacker.validateAlive(); // 몬스터가 죽었으면 에러 발생
        defender.validateAlive(); // 이미 캐릭터가 죽었으면 에러 발생

        BigDecimal totalDamage = calculateDefenderTotalDamage(attacker, defender); // 최종 데미지

        if (totalDamage.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("캐릭터 방어력이 최종 데미지보다 높거나 같습니다.");
        } else {
            defender.takeDamage(totalDamage); // 캐릭터 피격
        }
    }

    private BigDecimal calculateDefenderTotalDamage(Monster attacker, Tribe defender) {
        return attacker.getAttackPower().subtract(defender.getTotalDefencePower());
    }
}
