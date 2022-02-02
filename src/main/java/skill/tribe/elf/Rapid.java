package skill.tribe.elf;

import tribe.Tribe;

import java.math.BigDecimal;

public class Rapid extends ElfUltimateSkill implements Runnable {

    private final Tribe tribe;
    private final long startMilliSeconds;
    private final BigDecimal previousAttackSpeedPercentage;

    public Rapid(Tribe tribe) {
        this.attackSpeedPercentage = new BigDecimal("5"); // 500%
        this.mpCost = BigDecimal.ONE;
        this.durationMillisecond = new BigDecimal("60000"); // 60초

        this.tribe = tribe;
        this.previousAttackSpeedPercentage = tribe.getTotalAttackSpeed();
        this.startMilliSeconds = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while(System.currentTimeMillis() - startMilliSeconds <= durationMillisecond.longValue()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tribe.setTotalAttackPower(this.previousAttackSpeedPercentage);
        System.out.println("라피드 종료 후 설정된 공격속도 : " + previousAttackSpeedPercentage);
    }
}
