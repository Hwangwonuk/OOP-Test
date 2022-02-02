import monster.Slime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.tribe.weapon.WeaponService;
import tribe.Elf;
import tribe.Human;
import tribe.Orc;
import weapon.elf.bow.IronBow;
import weapon.human.sword.LongSword;
import weapon.human.sword.ShortSword;
import weapon.orc.blunt.ShortAxe;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class WeaponServiceTest {

    private WeaponService weaponService;

    private Human human;
    private Elf elf;
    private Orc orc;
    private Slime slime;

    private Human deadHuman;
    private Slime deadSlime;
    private Human attackPowerMaxHuman;
    private Slime defencePowerMaxSlime;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        this.weaponService = appConfig.weaponService();

        // given
        this.human = new Human(
                1, // 레벨
                new BigDecimal(100), // 체력
                new BigDecimal(100), // 마력
                new BigDecimal(10), // 공격력
                new BigDecimal(12), // 공격속도
                new BigDecimal(50), // 방어력
                new BigDecimal(30) // 회피율
        );

        this.elf = new Elf(
                1, // 레벨
                new BigDecimal(100), // 체력
                new BigDecimal(100), // 마력
                new BigDecimal(10), // 공격력
                new BigDecimal(12), // 공격속도
                new BigDecimal(50), // 방어력
                new BigDecimal(30) // 회피율
        );

        this.orc = new Orc(
                1, // 레벨
                new BigDecimal(100), // 체력
                new BigDecimal(100), // 마력
                new BigDecimal(15), // 공격력
                new BigDecimal(12), // 공격속도
                new BigDecimal(50), // 방어력
                new BigDecimal(30) // 회피율
        );

        this.slime = new Slime(
                new BigDecimal(100), // 체력
                new BigDecimal(10), // 공격력
                new BigDecimal(1) // 방어력
        );

        this.deadHuman = new Human(
                1, // 레벨
                new BigDecimal(0), // 체력
                new BigDecimal(100), // 마력
                new BigDecimal(10), // 공격력
                new BigDecimal(12), // 공격속도
                new BigDecimal(50), // 방어력
                new BigDecimal(30) // 회피율
        );

        this.deadSlime = new Slime(
                new BigDecimal(0), // 체력
                new BigDecimal(10), // 공격력
                new BigDecimal(1) // 방어력
        );

        this.attackPowerMaxHuman = new Human(
                1, // 레벨
                new BigDecimal(100), // 체력
                new BigDecimal(100), // 마력
                new BigDecimal(Integer.MAX_VALUE), // 공격력
                new BigDecimal(12), // 공격속도
                new BigDecimal(50), // 방어력
                new BigDecimal(30) // 회피율
        );

        this.defencePowerMaxSlime = new Slime(
                new BigDecimal(100), // 체력
                new BigDecimal(10), // 공격력
                new BigDecimal(Integer.MAX_VALUE) // 방어력
        );
    }

    @Test
    @DisplayName("종족에 맞지 않는 무기 장착시 에러 발생")
    void throwExceptionValidateWeaponCompatibilityTest() {
        // when + then
        Assertions.assertThrows(RuntimeException.class,
                () -> {
                    weaponService.equipWeapon(human, new ShortAxe()); // Human이 Orc 무기 장착 시도
                }
        );
    }

    @Test
    @DisplayName("Human 공격 성공")
    void successHumanAttackTest() {
        //given
        weaponService.equipWeapon(human, new LongSword()); // 공격력 10% 증가 = 10 + 1 = 11

        //when
        weaponService.attack(human, slime);

        //then
        assertEquals(slime.getHp(), new BigDecimal("90.0"));
    }

    @Test
    @DisplayName("Elf 공격 성공")
    void successElfAttackTest() {
        //given
        weaponService.equipWeapon(elf, new IronBow());

        //when
        weaponService.attack(elf, slime);

        //then
        assertEquals(slime.getHp(), new BigDecimal("91"));
    }

    @Test
    @DisplayName("Orc 공격 성공")
    void successOrcAttackTest() {
        //given
        weaponService.equipWeapon(orc, new ShortAxe()); // 공격력 10% 증가 = 15 + 1.5 = 16.5

        //when
        weaponService.attack(orc, slime);

        //then
        assertEquals(slime.getHp(), new BigDecimal("84.5"));
    }

    @Test
    @DisplayName("캐릭터가 죽었는데 그 캐릭터가 공격을 시도한다면 에러 발생")
    void throwExceptionTribeValidateAliveTest() {
        //when + then
        Assertions.assertThrows(RuntimeException.class,
            () -> {
                weaponService.attack(deadHuman, slime);
            }
        );
    }

    @Test
    @DisplayName("몬스터가 이미 죽었는데 그 몬스터에게 공격을 시도한다면 에러 발생")
    void throwExceptionMonsterValidateAliveTest() {
        //when + then
        Assertions.assertThrows(RuntimeException.class,
                () -> {
                    weaponService.attack(human, deadSlime);
                }
        );
    }

    @Test
    @DisplayName("무기를 장착하지 않았을때 공격을 시도한다면 에러 발생")
    void throwExceptionValidateAttackReadinessByNoWeaponTest() {
        //when + then
        Assertions.assertThrows(RuntimeException.class,
                () -> {
                    weaponService.attack(human, slime);
                }
        );
    }

    @Test
    @DisplayName("아직 공격 쿨다운이 남아 있을때 공격을 시도한다면 에러 발생")
    void throwExceptionValidateAttackReadinessByCoolDownTest() {
        // given
        weaponService.equipWeapon(human, new ShortSword());

        //when + then
        Assertions.assertThrows(RuntimeException.class,
                () -> {
                    weaponService.attack(human, slime);
                    weaponService.attack(human, slime);
                }
        );
    }

    @Test
    @DisplayName("몬스터 방어력이 최종 데미지보다 높거나 같은 경우 데미지 0")
    void monsterDefencePowerOverpowerTribeAttackPowerTest() {
        // given
        weaponService.equipWeapon(human, new ShortSword());

        // when
        weaponService.attack(human, defencePowerMaxSlime);

        // then
        assertEquals(defencePowerMaxSlime.getHp(), new BigDecimal("100"));
    }

    @Test
    @DisplayName("공격 후 몬스터가 죽었다면 캐릭터 레벨업")
    void tribeLevelUpByMonsterDeadTest() {
        // given
        weaponService.equipWeapon(attackPowerMaxHuman, new ShortSword());

        // when
        weaponService.attack(attackPowerMaxHuman, slime);

        // then
        assertEquals(attackPowerMaxHuman.getLevel(), 2);
    }
}
