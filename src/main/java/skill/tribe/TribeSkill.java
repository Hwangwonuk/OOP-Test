package skill.tribe;

import tribe.TribeName;

import java.math.BigDecimal;
import java.util.Map;

public interface TribeSkill {
    Map<String, BigDecimal> getSkillStat();

    TribeName getSkillTribe();
}
