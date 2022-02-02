import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tribe.Human;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TribeTest {

    private Human human;
    private Human deadHuman;

    @BeforeEach
    void beforeEach() {
        // given
        this.human = new Human(
                1, // 레벨
                new BigDecimal(100), // 체력
                new BigDecimal(100), // 마력
                new BigDecimal(10), // 공격력
                new BigDecimal(12), // 공격속도
                new BigDecimal(5), // 방어력
                new BigDecimal(30) // 회피율
        );

        this.deadHuman = new Human(
                1, // 레벨
                new BigDecimal(0), // 체력
                new BigDecimal(100), // 마력
                new BigDecimal(10), // 공격력
                new BigDecimal(12), // 공격속도
                new BigDecimal(5), // 방어력
                new BigDecimal(30) // 회피율
        );
    }

    @Test
    @DisplayName("사망 캐릭터는 캐릭터 사망여부 검사시 true 반환")
    void isDeadTest() {
        // when
        boolean isDead = deadHuman.isDead();

        // then
        assertTrue(isDead);
    }

    @Test
    @DisplayName("회피 여부 검사시 에러없이 성공")
    void isDodgeTest() {
        // when + then
        Assertions.assertDoesNotThrow(
            () -> {
                human.isDodge();
            }
        );
    }

    @Test
    @DisplayName("사망 캐릭터는 생존여부 검사시 에러 발생")
    void throwExceptionValidateAliveTest() {
        // when + then
        Assertions.assertThrows(RuntimeException.class,
                () -> {
                    deadHuman.validateAlive();
                }
        );
    }

    @Test
    @DisplayName("레벨업")
    void levelUpTest() {
        // when
        human.levelUp();

        // then
        assertEquals(human.getLevel(), 2);
    }

    @Test
    @DisplayName("피격")
    void takeDamageTest() {
        // when
        human.takeDamage(BigDecimal.TEN);

        // then
        assertEquals(human.getHp(), new BigDecimal("90"));
    }
}
