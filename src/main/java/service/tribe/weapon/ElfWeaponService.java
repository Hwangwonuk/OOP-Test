package service.tribe.weapon;

import monster.Monster;
import tribe.Elf;
import weapon.elf.bow.Bow;

public interface ElfWeaponService {
    void equipWeapon(Elf elf, Bow bow);

    void attack(Elf attacker, Monster defender);
}
