package weapon.orc.blunt;

import tribe.TribeName;
import util.WeaponStatKeys;
import weapon.Weapon;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public abstract class Blunt implements Weapon {

    protected BigDecimal attackPowerPercentage;
    protected BigDecimal attackSpeedPercentage;

    @Override
    public Map<String, BigDecimal> getWeaponStat() {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put(WeaponStatKeys.ATTACK_POWER_PERCENTAGE, attackPowerPercentage);
        map.put(WeaponStatKeys.ATTACK_SPEED_PERCENTAGE, attackSpeedPercentage);
        return map;
    }

    @Override
    public TribeName getWeaponTribe() {
        return TribeName.ORC;
    }

}
