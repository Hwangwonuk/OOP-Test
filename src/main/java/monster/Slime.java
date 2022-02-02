package monster;

import java.math.BigDecimal;

public class Slime extends Monster {

    public Slime(
            BigDecimal hp,
            BigDecimal attackPower,
            BigDecimal defencePower
    ) {
        super(hp, attackPower, defencePower);
    }

    @Override
    public BigDecimal attack() {
        return this.getAttackPower();
    }

    @Override
    public BigDecimal counterAttack() {
        return this.getAttackPower().multiply(BigDecimal.valueOf(0.7));
    }

}
