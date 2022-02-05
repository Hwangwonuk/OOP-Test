import service.monster.attack.MonsterAttackService;
import service.monster.attack.MonsterAttackServiceImpl;
import service.tribe.skill.SkillService;
import service.tribe.skill.SkillServiceImpl;
import service.tribe.weapon.*;

public class AppConfig {
    public WeaponService weaponService() {
        return new WeaponServiceImpl();
    }

    public SkillService skillService() {
        return new SkillServiceImpl();
    }

    public MonsterAttackService monsterAttackService() {
        return new MonsterAttackServiceImpl();
    }

    public HumanWeaponService humanWeaponService() {
        return new HumanWeaponServiceImpl();
    }

    public ElfWeaponService elfWeaponService() {
        return new ElfWeaponServiceImpl();
    }

    public OrcWeaponService orcWeaponService() {
        return new OrcWeaponServiceImpl();
    }
}
