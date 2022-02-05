package skill.tribe.elf;

import skill.tribe.TribeSkill;
import tribe.TribeName;
import util.SkillStatKeys;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public abstract class ElfSkill implements TribeSkill {

    protected BigDecimal dodgePercentage;
    protected BigDecimal mpCost;

    @Override
    public Map<String, BigDecimal> getSkillStat() {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put(SkillStatKeys.ATTACK_POWER_PERCENTAGE, BigDecimal.ZERO);
        map.put(SkillStatKeys.DEFENCE_POWER_PERCENTAGE, BigDecimal.ZERO);
        map.put(SkillStatKeys.DODGE_PERCENTAGE, dodgePercentage);
        map.put(SkillStatKeys.MP_COST, mpCost);
        return map;
    }

    @Override
    public TribeName getSkillTribe() {
        return TribeName.ELF;
    }

}
