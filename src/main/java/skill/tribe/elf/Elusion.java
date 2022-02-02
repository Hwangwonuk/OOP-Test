package skill.tribe.elf;

import java.math.BigDecimal;

public class Elusion extends ElfSkill {

    public Elusion() {
        this.dodgePercentage = new BigDecimal("0.3");
        this.mpCost = BigDecimal.ONE;
    }
}
