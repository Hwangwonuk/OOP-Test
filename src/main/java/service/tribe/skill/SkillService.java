package service.tribe.skill;

import skill.common.CommonSkill;
import skill.tribe.TribeSkill;
import skill.tribe.UltimateSkill;
import tribe.Tribe;

public interface SkillService {
    void useCommonSkill(Tribe tribe, CommonSkill commonSkill);

    void useTribeSkill(Tribe tribe, TribeSkill tribeSkill);

    void useUltimateSkill(Tribe tribe, UltimateSkill ultimateSkill);
}
