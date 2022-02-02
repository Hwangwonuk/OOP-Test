package tribe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public abstract class Tribe {

    private static final BigDecimal COOLDOWN_STANDARD_MILLISECOND = new BigDecimal("2000"); // 2초
    private static final BigDecimal DODGE_STANDARD_PERCENTAGE = new BigDecimal("100");

    private int level; // 레벨
    private BigDecimal hp; // 생명력
    private BigDecimal mp; // 마나
    private BigDecimal attackPower; // 공격력
    private BigDecimal attackSpeed; // 공격 속도
    private BigDecimal defencePower; // 방어력
    private BigDecimal dodgePercentage; // 회피율

    private BigDecimal weaponAttackPower; // 무기 공격력
    private BigDecimal weaponAttackSpeed; // 무기 공격속도

    private BigDecimal skillAttackPower; // 스킬 공격력
    private BigDecimal skillAttackSpeed; // 스킬 공격속도
    private BigDecimal skillDefencePower; // 스킬 방어력
    private BigDecimal skillDodgePercentage; // 스킬 회피력

    private BigDecimal totalAttackPower; // 총 공격력 = 공격력 + 무기로 추가된 공격력 + 스킬로 추가된 공격력
    private BigDecimal totalAttackSpeed; // 총 공격속도 = 공격속도 + 무기로 추가된 공격속도
    private BigDecimal totalDefencePower; // 총 방어력 = 방어력 + 스킬로 추가된 방어력
    private BigDecimal totalDodgePercentage; // 총 회피력 = 회피력 + 스킬로 추가된 회피력

    private Boolean isEquipWeapon = false; // 무기 착용 여부

    private long lastAttackMillisecondTime; // 직전 공격 시간

    protected Tribe(int level, BigDecimal hp, BigDecimal mp, BigDecimal attackPower, BigDecimal attackSpeed, BigDecimal defencePower, BigDecimal dodgePercentage) {
        this.level = level;
        this.hp = hp;
        this.mp = mp;
        this.attackPower = attackPower;
        this.attackSpeed = attackSpeed;
        this.defencePower = defencePower;
        this.dodgePercentage = dodgePercentage;

        this.weaponAttackPower = BigDecimal.ZERO;
        this.weaponAttackSpeed = BigDecimal.ZERO;

        this.skillAttackPower = BigDecimal.ZERO;
        this.skillAttackSpeed = BigDecimal.ZERO;
        this.skillDefencePower = BigDecimal.ZERO;
        this.skillDodgePercentage = BigDecimal.ZERO;

        this.totalAttackPower = attackPower;
        this.totalAttackSpeed = attackSpeed;
        this.totalDefencePower = defencePower;
        this.totalDodgePercentage = dodgePercentage;
    }

    public abstract TribeName getTribeName();

    public void setLevel(int level) { this.level = level; }

    public int getLevel() { return this.level; }

    public void setHp(BigDecimal hp) {
        this.hp = hp;
    }

    public BigDecimal getHp() {
        return this.hp;
    }

    public void setMp(BigDecimal mp) {
        this.mp = mp;
    }

    public BigDecimal getMp() {
        return this.mp;
    }

    public void setAttackPower(BigDecimal attackPower) {
        this.attackPower = attackPower;
    }

    public BigDecimal getAttackPower() {
        return this.attackPower;
    }

    public void setAttackSpeed(BigDecimal attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public BigDecimal getAttackSpeed() {
        return this.attackSpeed;
    }

    public void setDefencePower(BigDecimal defencePower) {
        this.defencePower = defencePower;
    }

    public BigDecimal getDefencePower() {
        return this.defencePower;
    }

    public void setDodgePercentage(BigDecimal dodgePercentage) {
        this.dodgePercentage = dodgePercentage;
    }

    public BigDecimal getDodgePercentage() {
        return this.dodgePercentage;
    }

    public void setWeaponAttackPower(BigDecimal weaponAttackPower) {
        this.weaponAttackPower = weaponAttackPower;
    }

    public BigDecimal getWeaponAttackPower() {
        return this.weaponAttackPower;
    }

    public void setWeaponAttackSpeed(BigDecimal weaponAttackSpeed) {
        this.weaponAttackSpeed = weaponAttackSpeed;
    }

    public BigDecimal getWeaponAttackSpeed() {
        return this.weaponAttackSpeed;
    }

    public void setSkillAttackPower(BigDecimal skillAttackPower) {
        this.skillAttackPower = skillAttackPower;
    }

    public BigDecimal getSkillAttackPower() {
        return this.skillAttackPower;
    }

    public void setSkillAttackSpeed(BigDecimal skillAttackSpeed) {
        this.skillAttackSpeed = skillAttackSpeed;
    }

    public BigDecimal getSkillAttackSpeed() {
        return this.skillAttackSpeed;
    }

    public void setSkillDefencePower(BigDecimal skillDefencePower) {
        this.skillDefencePower = skillDefencePower;
    }

    public BigDecimal getSkillDefencePower() {
        return this.skillDefencePower;
    }

    public void setSkillDodgePercentage(BigDecimal skillDodgePercentage) {
        this.skillDodgePercentage = skillDodgePercentage;
    }

    public BigDecimal getSkillDodgePercentage() {
        return this.skillDodgePercentage;
    }

    public void setTotalAttackSpeed(BigDecimal totalAttackSpeed) {
        this.totalAttackSpeed = totalAttackSpeed;
    }

    public BigDecimal getTotalAttackSpeed() {
        return totalAttackSpeed;
    }

    public void setTotalAttackPower(BigDecimal totalAttackPower) {
        this.totalAttackPower = totalAttackPower;
    }

    public BigDecimal getTotalAttackPower() {
        return totalAttackPower;
    }

    public void setTotalDefencePower(BigDecimal totalDefencePower) {
        this.totalDefencePower = totalDefencePower;
    }

    public BigDecimal getTotalDefencePower() {
        return totalDefencePower;
    }

    public void setTotalDodgePercentage(BigDecimal totalDodgePercentage) {
        this.totalDodgePercentage = totalDodgePercentage;
    }

    public BigDecimal getTotalDodgePercentage() {
        return totalDodgePercentage;
    }

    public void setEquipWeapon(Boolean equipWeapon) {
        isEquipWeapon = equipWeapon;
    }

    public Boolean getEquipWeapon() {
        return isEquipWeapon;
    }

    public void setLastAttackMillisecondTime(long lastAttackMillisecondTime) {
        this.lastAttackMillisecondTime = lastAttackMillisecondTime;
    }

    public long getLastAttackMillisecondTime() {
        return lastAttackMillisecondTime;
    }

    public Boolean validateAttackCoolDown() {
        // 공격 쿨다운 = COOLDOWN_STANDARD_MILLISECOND 값을 공격속도로 나눈 값
        if((System.currentTimeMillis() - getLastAttackMillisecondTime()) >
            COOLDOWN_STANDARD_MILLISECOND.divide(getTotalAttackSpeed(), RoundingMode.HALF_UP).longValue()) {
            return true;
        }

        System.out.println("직전 공격과의 시간차 : " + (System.currentTimeMillis() - getLastAttackMillisecondTime() + ", 공격속도에 따른 기준 쿨다운 : " + COOLDOWN_STANDARD_MILLISECOND.divide(getTotalAttackSpeed(), RoundingMode.HALF_UP).longValue()));
        return false;
    }

    public Boolean isDodge() {
        // 회피 확률 적용을 위해 캐릭터 회피율과 비교대상이 되는 랜덤값을 비교
        // 캐릭터 회피율이 랜덤값보다 높으면 회피 성공
        BigDecimal comparedDodgePercentage = new BigDecimal(new Random().nextInt(DODGE_STANDARD_PERCENTAGE.intValue()));
        System.out.println("캐릭터 회피율 : " + getTotalDodgePercentage() + ", 비교대상 회피율 : " + comparedDodgePercentage + ", 회피 성공 여부 : " + (getTotalDodgePercentage().compareTo(comparedDodgePercentage) > 0));
        return getTotalDodgePercentage().compareTo(comparedDodgePercentage) > 0;
    }

    public Boolean isDead() {
        return getHp().compareTo(BigDecimal.ZERO) <= 0;
    }

    public void validateAlive() {
        if(isDead())
            throw new RuntimeException("이미 죽은 캐릭터입니다");
    }

    public void levelUp() {
        this.level = getLevel() + 1;
    }

    public void takeDamage(BigDecimal damage) {
        this.hp = getHp().subtract(damage);
    }

}
