package skill.tribe.human;

import java.math.BigDecimal;

public class Guard extends HumanSkill {

    public Guard() {
        this.defencePowerPercentage = new BigDecimal("0.3");
        this.mpCost = BigDecimal.ONE;
    }
}
