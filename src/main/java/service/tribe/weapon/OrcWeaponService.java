package service.tribe.weapon;

import monster.Monster;
import tribe.Orc;
import weapon.orc.blunt.Blunt;

public interface OrcWeaponService {
    void equipWeapon(Orc orc, Blunt blunt);

    void attack(Orc attacker, Monster defender);
}
