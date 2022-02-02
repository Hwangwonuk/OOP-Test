package weapon.orc.blunt;

import java.math.BigDecimal;

public class ShortAxe extends Blunt {

    public ShortAxe() {
        this.attackPowerPercentage = new BigDecimal("0.1");
        this.attackSpeedPercentage = new BigDecimal("-0.05");
    }

}
