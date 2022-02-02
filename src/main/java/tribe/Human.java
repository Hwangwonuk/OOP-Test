package tribe;

import java.math.BigDecimal;

public class Human extends Tribe {

    public Human(
            int level,
            BigDecimal hp,
            BigDecimal mp,
            BigDecimal attackPower,
            BigDecimal attackSpeed,
            BigDecimal defencePower,
            BigDecimal dodgePercentage
    ) {
        super(level, hp, mp, attackPower, attackSpeed, defencePower, dodgePercentage);
    }

    @Override
    public TribeName getTribeName() {
        return TribeName.HUMAN;
    }

}
