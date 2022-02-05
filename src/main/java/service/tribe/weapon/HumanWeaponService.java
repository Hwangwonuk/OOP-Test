package service.tribe.weapon;

import monster.Monster;
import tribe.Human;
import weapon.human.sword.Sword;

public interface HumanWeaponService {
    void equipWeapon(Human human, Sword sword);

    void attack(Human attacker, Monster defender);
}
