package skill.tribe.orc;

import tribe.Tribe;

import java.math.BigDecimal;

public class Frenzy extends OrcUltimateSkill implements Runnable {

    private final Tribe tribe;
    private final long startMilliSeconds;
    private final BigDecimal previousAttackPower;

    public Frenzy(Tribe tribe) {
        this.attackPowerPercentage = new BigDecimal("5"); // 500%
        this.mpCost = BigDecimal.ONE;
        this.durationMillisecond = new BigDecimal("60000"); // 60초

        this.tribe = tribe;
        this.previousAttackPower = tribe.getTotalAttackPower();
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
        tribe.setTotalAttackPower(this.previousAttackPower);
        System.out.println("프렌지 종료 후 설정된 공격력 : " + previousAttackPower);
    }
}
