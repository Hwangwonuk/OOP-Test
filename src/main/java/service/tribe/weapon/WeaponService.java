package service.tribe.weapon;

import monster.Monster;
import tribe.Tribe;
import weapon.Weapon;

public interface WeaponService {
    void equipWeapon(Tribe tribe, Weapon weapon);

    void attack(Tribe attacker, Monster defender);
}
