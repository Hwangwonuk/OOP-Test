package weapon.orc.blunt;

import java.math.BigDecimal;

public class IronHammer extends Blunt {

    public IronHammer() {
        this.attackPowerPercentage = new BigDecimal("0.2");
        this.attackSpeedPercentage = new BigDecimal("-0.1");
    }

}
