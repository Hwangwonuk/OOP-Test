import monster.Slime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.tribe.skill.SkillService;
import skill.common.Heal;
import skill.common.Steam;
import skill.tribe.elf.Elusion;
import skill.tribe.elf.Rapid;
import skill.tribe.human.Guard;
import skill.tribe.human.Invincible;
import skill.tribe.orc.Anger;
import skill.tribe.orc.Frenzy;
import tribe.Elf;
import tribe.Human;
import tribe.Orc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class SkillServiceTest {

    private SkillService skillService;

    private Human human;
    private Elf elf;
    private Orc orc;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        this.skillService = appConfig.skillService();

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
    }

    @Test
    @DisplayName("스킬 사용시 MP 소모")
    void consumeMpTest() {
        // when
        skillService.useCommonSkill(human, new Heal());

        // then
        assertEquals(human.getMp(), new BigDecimal("99"));
    }

    @Test
    @DisplayName("공통 스킬 - Heal")
    void healTest() {
        // when
        skillService.useCommonSkill(human, new Heal());

        // then
        assertEquals(human.getHp(), new BigDecimal("110.0"));
    }

    @Test
    @DisplayName("공통 스킬 - Steam")
    void steamTest() {
        // when
        skillService.useCommonSkill(human, new Steam());

        // then
        assertEquals(human.getTotalAttackPower(), new BigDecimal("12.0"));
    }

    @Test
    @DisplayName("휴먼 스킬 - Guard")
    void guardTest() {
        // when
        skillService.useTribeSkill(human, new Guard());

        // then
        assertEquals(human.getTotalDefencePower(), new BigDecimal("65.0"));
    }

    @Test
    @DisplayName("엘프 스킬 - Elusion")
    void elusionTest() {
        // when
        skillService.useTribeSkill(elf, new Elusion());

        // then
        assertEquals(elf.getTotalDodgePercentage(), new BigDecimal("39.0"));
    }

    @Test
    @DisplayName("오크 스킬 - Anger")
    void angerTest() {
        // when
        skillService.useTribeSkill(orc, new Anger());

        // then
        assertEquals(orc.getTotalAttackPower(), new BigDecimal("22.5"));
        assertEquals(orc.getTotalDefencePower(), new BigDecimal("45.0"));
    }

    @Test
    @DisplayName("휴먼 궁극 스킬 - Invincible")
    void invincibleTest() {
        // given
        Invincible invincible1 = new Invincible(human);
        BigDecimal previousDodgePercent = human.getTotalDodgePercentage();

        // when
        skillService.useUltimateSkill(human, invincible1);

        // then
        assertEquals(human.getTotalDodgePercentage(), new BigDecimal(Integer.MAX_VALUE).add(previousDodgePercent));
    }

    @Test
    @DisplayName("엘프 궁극 스킬 - Rapid")
    void rapidTest() {
        // given
        Rapid rapid = new Rapid(elf);

        // when
        skillService.useUltimateSkill(elf, rapid);

        // then
        assertEquals(elf.getTotalAttackSpeed(), new BigDecimal("72"));
    }

    @Test
    @DisplayName("오크 궁극 스킬 - Frenzy")
    void frenzyTest() {
        // given
        Frenzy frenzy = new Frenzy(orc);

        // when
        skillService.useUltimateSkill(orc, frenzy);

        // then
        assertEquals(orc.getTotalAttackPower(), new BigDecimal("90"));
    }
}
