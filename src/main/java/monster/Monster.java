package monster;

import java.math.BigDecimal;
import java.util.Random;

public abstract class Monster {

    private BigDecimal hp; // 생명력
    private BigDecimal attackPower; // 공격력
    private BigDecimal defencePower; // 방어력

    private BigDecimal counterAttackPercentage = new BigDecimal("30"); // 반격확률

    protected Monster(BigDecimal hp, BigDecimal attackPower, BigDecimal defencePower) {
        this.hp = hp;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
    }

    public void setHp(BigDecimal hp) {
        this.hp = hp;
    }

    public BigDecimal getHp() {
        return this.hp;
    }

    public void setAttackPower(BigDecimal attackPower) {
        this.attackPower = attackPower;
    }

    public BigDecimal getAttackPower() {
        return this.attackPower;
    }

    public void setDefencePower(BigDecimal defencePower) {
        this.defencePower = defencePower;
    }

    public BigDecimal getDefencePower() {
        return this.defencePower;
    }

    public void setCounterAttackPercentage(BigDecimal counterAttackPercentage) {
        this.counterAttackPercentage = counterAttackPercentage;
    }

    public BigDecimal getCounterAttackPercentage() {
        return this.counterAttackPercentage;
    }

    public abstract BigDecimal attack();

    public abstract BigDecimal counterAttack();

    public Boolean isCounterAttack() {
        // 반격 확률 적용을 위해 반격 확률과 비교대상이 되는 랜덤값을 비교
        // 반격 확률값이 랜덤값보다 높으면 반격 성공
        BigDecimal comparedCounterAttackPercentage = new BigDecimal(new Random().nextInt(100));
        System.out.println("반격 확률 : " + getCounterAttackPercentage() + ", 비교대상 확률 : " + comparedCounterAttackPercentage + ", 반격 성공 여부 : " + (getCounterAttackPercentage().compareTo(comparedCounterAttackPercentage) > 0));
        return getCounterAttackPercentage().compareTo(comparedCounterAttackPercentage) > 0;
    }

    public Boolean isDead() {
        return getHp().compareTo(BigDecimal.ZERO) <= 0;
    }

    public void validateAlive() {
        if(isDead())
            throw new RuntimeException("이미 죽은 몬스터입니다");
        
    }

    public void takeDamage(BigDecimal damage) {
        this.hp = getHp().subtract(damage);
    }
}
