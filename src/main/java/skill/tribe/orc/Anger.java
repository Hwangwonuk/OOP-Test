package skill.tribe.orc;

import java.math.BigDecimal;

public class Anger extends OrcSkill{

    public Anger() {
        this.attackPowerPercentage = new BigDecimal("0.5");
        this.defencePowerPercentage = new BigDecimal("-0.1");
        this.mpCost = BigDecimal.ONE;
    }

}
