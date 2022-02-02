package skill.common;

import java.math.BigDecimal;

public class Steam extends CommonSkill {

    public Steam(){
        this.hpPercentage = BigDecimal.ZERO;
        this.attackPowerPercentage = new BigDecimal("0.2");
        this.mpCost = BigDecimal.ONE;
    }
}
