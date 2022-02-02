package skill.tribe.orc;

import skill.tribe.TribeSkill;
import util.SkillStatKeys;
import tribe.TribeName;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public abstract class OrcSkill implements TribeSkill {

    protected BigDecimal attackPowerPercentage;
    protected BigDecimal defencePowerPercentage;
    protected BigDecimal mpCost;

    @Override
    public Map<String, BigDecimal> getSkillStat() {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put(SkillStatKeys.ATTACK_POWER_PERCENTAGE, attackPowerPercentage);
        map.put(SkillStatKeys.DEFENCE_POWER_PERCENTAGE, defencePowerPercentage);
        map.put(SkillStatKeys.DODGE_PERCENTAGE, BigDecimal.ZERO);
        map.put(SkillStatKeys.MP_COST, mpCost);
        return map;
    }

    @Override
    public TribeName getSkillTribe() {
        return TribeName.ORC;
    }

}
