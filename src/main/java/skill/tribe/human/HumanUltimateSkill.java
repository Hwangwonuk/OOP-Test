package skill.tribe.human;

import skill.tribe.UltimateSkill;
import tribe.TribeName;
import util.SkillStatKeys;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public abstract class HumanUltimateSkill implements UltimateSkill {

    protected BigDecimal dodgePercentage;
    protected BigDecimal mpCost;
    protected BigDecimal durationMillisecond;

    @Override
    public Map<String, BigDecimal> getSkillStat() {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put(SkillStatKeys.ATTACK_POWER_PERCENTAGE, BigDecimal.ZERO);
        map.put(SkillStatKeys.DEFENCE_POWER_PERCENTAGE, BigDecimal.ZERO);
        map.put(SkillStatKeys.ATTACK_SPEED_PERCENTAGE, BigDecimal.ZERO);
        map.put(SkillStatKeys.DODGE_PERCENTAGE, dodgePercentage);
        map.put(SkillStatKeys.MP_COST, mpCost);
        map.put(SkillStatKeys.DURATION_MILLISECOND, durationMillisecond);
        return map;
    }

    @Override
    public TribeName getSkillTribe() {
        return TribeName.HUMAN;
    }

}
