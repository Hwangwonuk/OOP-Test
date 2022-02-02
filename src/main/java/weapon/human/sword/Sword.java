package weapon.human.sword;

import tribe.TribeName;
import util.WeaponStatKeys;
import weapon.Weapon;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public abstract class Sword implements Weapon {

    protected BigDecimal attackPowerPercentage;

    @Override
    public Map<String, BigDecimal> getWeaponStat() {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put(WeaponStatKeys.ATTACK_POWER_PERCENTAGE, attackPowerPercentage);
        map.put(WeaponStatKeys.ATTACK_SPEED_PERCENTAGE, BigDecimal.ZERO);
        return map;
    }

    @Override
    public TribeName getWeaponTribe(){
        return TribeName.HUMAN;
    }

}
