package skill.tribe.orc;

import skill.tribe.UltimateSkill;
import tribe.TribeName;
import util.SkillStatKeys;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public abstract class OrcUltimateSkill implements UltimateSkill {

    protected BigDecimal attackPowerPercentage;
    protected BigDecimal mpCost;
    protected BigDecimal durationMillisecond;

    @Override
    public Map<String, BigDecimal> getSkillStat() {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put(SkillStatKeys.ATTACK_POWER_PERCENTAGE, attackPowerPercentage);
        map.put(SkillStatKeys.DEFENCE_POWER_PERCENTAGE, BigDecimal.ZERO);
        map.put(SkillStatKeys.ATTACK_SPEED_PERCENTAGE, BigDecimal.ZERO);
        map.put(SkillStatKeys.DODGE_PERCENTAGE, BigDecimal.ZERO);
        map.put(SkillStatKeys.MP_COST, mpCost);
        map.put(SkillStatKeys.DURATION_MILLISECOND, durationMillisecond);
        return map;
    }

    @Override
    public TribeName getSkillTribe() {
        return TribeName.ORC;
    }

}
