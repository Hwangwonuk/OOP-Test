package skill.common;

import util.SkillStatKeys;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public abstract class CommonSkill {

    protected BigDecimal attackPowerPercentage;
    protected BigDecimal hpPercentage;
    protected BigDecimal mpCost;

    public Map<String, BigDecimal> getSkillStat() {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put(SkillStatKeys.ATTACK_POWER_PERCENTAGE, attackPowerPercentage);
        map.put(SkillStatKeys.HP_PERCENTAGE, hpPercentage);
        map.put(SkillStatKeys.MP_COST, mpCost);
        return map;
    }

}
