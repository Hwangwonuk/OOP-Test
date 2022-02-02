
import monster.Slime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class MonsterTest {

    private Slime slime;
    private Slime deadSlime;

    @BeforeEach
    void beforeEach() {
        // given
        this.slime = new Slime(
                new BigDecimal(100), // 체력
                new BigDecimal(10), // 공격력
                new BigDecimal(1) // 방어력
        );

        this.deadSlime = new Slime(
                new BigDecimal(0), // 체력
                new BigDecimal(10), // 공격력
                new BigDecimal(1) // 방어력
        );
    }

    @Test
    @DisplayName("사망 몬스터는 몬스터 사망여부 검사시 true 반환")
    void isDeadTest() {
        // when
        boolean isDead = deadSlime.isDead();

        // then
        assertTrue(isDead);
    }

    @Test
    @DisplayName("반격 여부 검사시 에러없이 성공")
    void isDodgeTest() {
        // when + then
        Assertions.assertDoesNotThrow(
                () -> {
                    slime.isCounterAttack();
                }
        );
    }

    @Test
    @DisplayName("사망 몬스터는 생존여부 검사시 에러 발생")
    void throwExceptionValidateAliveTest() {
        // when + then
        Assertions.assertThrows(RuntimeException.class,
                () -> {
                    deadSlime.validateAlive();
                }
        );
    }

    @Test
    @DisplayName("피격")
    void takeDamageTest() {
        // when
        slime.takeDamage(BigDecimal.TEN);

        // then
        assertEquals(slime.getHp(), new BigDecimal("90"));
    }
}
