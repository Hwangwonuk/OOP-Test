package service.monster.attack;

import monster.Monster;
import tribe.Tribe;

public interface MonsterAttackService {
    void attack(Monster attacker, Tribe defender);
}
