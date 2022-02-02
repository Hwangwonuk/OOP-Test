package skill.common;

import java.math.BigDecimal;

public class Heal extends CommonSkill {

    public Heal(){
        this.hpPercentage = new BigDecimal("0.1");
        this.attackPowerPercentage = BigDecimal.ZERO;
        this.mpCost = BigDecimal.ONE;
    }
}
