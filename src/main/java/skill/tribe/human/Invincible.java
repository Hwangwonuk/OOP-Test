package skill.tribe.human;

import tribe.Tribe;

import java.math.BigDecimal;

public class Invincible extends HumanUltimateSkill implements Runnable {

    private final Tribe tribe;
    private final long startMilliSeconds;
    private final BigDecimal previousDodgePercentage;

    public Invincible(Tribe tribe) {
        this.dodgePercentage = new BigDecimal(Integer.MAX_VALUE); // 회피율 최대치
        this.mpCost = BigDecimal.ONE;
        this.durationMillisecond = new BigDecimal("10000"); // 10초

        this.tribe = tribe;
        this.previousDodgePercentage = tribe.getTotalDodgePercentage();
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
        tribe.setTotalAttackPower(this.previousDodgePercentage);
        System.out.println("인빈서블 종료 후 회피력 : " + previousDodgePercentage);
    }
}
