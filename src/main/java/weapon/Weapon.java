package weapon;

import tribe.TribeName;

import java.math.BigDecimal;
import java.util.Map;

public interface Weapon {
    Map<String, BigDecimal> getWeaponStat();

    TribeName getWeaponTribe();
}
