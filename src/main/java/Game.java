import service.monster.attack.MonsterAttackService;
import service.tribe.skill.SkillService;
import service.tribe.weapon.WeaponService;
import monster.Slime;
import skill.common.Heal;
import skill.common.Steam;
import skill.tribe.elf.Elusion;
import skill.tribe.elf.Rapid;
import skill.tribe.human.Invincible;
import skill.tribe.orc.Anger;
import skill.tribe.orc.Frenzy;
import tribe.Elf;
import tribe.Human;
import tribe.Orc;
import weapon.orc.blunt.IronHammer;
import weapon.orc.blunt.ShortAxe;
import weapon.elf.bow.ShortBow;
import weapon.human.sword.LongSword;

import java.math.BigDecimal;

public class Game {
    public static void main(String[] args) throws InterruptedException {
        AppConfig appConfig = new AppConfig();
        WeaponService weaponService = appConfig.weaponService();
        SkillService skillService = appConfig.skillService();
        MonsterAttackService monsterAttackService = appConfig.monsterAttackService();

        System.out.println("\nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        System.out.println("휴먼 무기 착용 + 무기 공격 + 몬스터 반격 + 휴먼 회피 테스트\n");
        Human human1 = new Human(
                1, // 레벨
                new BigDecimal(100), // 체력
                new BigDecimal(100), // 마력
                new BigDecimal(10), // 공격력
                new BigDecimal(10), // 공격속도
                new BigDecimal(50), // 방어력
                new BigDecimal(30) // 회피율
        );

        Slime slime1 = new Slime(
                new BigDecimal(100), // 체력
                new BigDecimal(10), // 공격력
                new BigDecimal(1) // 방어력
        );

        System.out.println("\n착용전 공격력 : " + human1.getTotalAttackPower());
        System.out.println("착용전 공격속도 : " + human1.getTotalAttackSpeed());
        weaponService.equipWeapon(human1, new LongSword());
        System.out.println("착용후 공격력 : " + human1.getTotalAttackPower());
        System.out.println("착용후 공격속도 : " + human1.getTotalAttackSpeed());

        System.out.println("\n때리기 전 캐릭터 생명력 : " + human1.getHp());
        System.out.println("때리기 전 몬스터 생명력: " + slime1.getHp());
        weaponService.attack(human1, slime1);
        System.out.println("때린 후 캐릭터 생명력 : " + human1.getHp());
        System.out.println("때린 후 몬스터 생명력 : " + slime1.getHp());

        System.out.println("\nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        System.out.println("오크 스킬 사용 + 무기 착용 + 무기 공격 + 몬스터 반격 + 오크 회피 + 무기 교체 테스트\n");
        Orc orc1 = new Orc(
            1, // 레벨
            new BigDecimal(300), // 체력
            new BigDecimal(100), // 마력
            new BigDecimal(10), // 공격력
            new BigDecimal(10), // 공격속도
            new BigDecimal(5), // 방어력
            new BigDecimal(30) // 회피율
        );

        Slime slime2 = new Slime(
                new BigDecimal(100), // 체력
                new BigDecimal(10), // 공격력
                new BigDecimal(1) // 방어력
        );

        System.out.println("\n사용전 공격력 : " + orc1.getTotalAttackPower());
        System.out.println("사용전 방어력 : " + orc1.getTotalDefencePower());
        System.out.println("사용전 마나 : " + orc1.getMp());
        skillService.useTribeSkill(orc1, new Anger());
        System.out.println("사용후 공격력 : " + orc1.getTotalAttackPower());
        System.out.println("사용후 방어력 : " + orc1.getTotalDefencePower());
        System.out.println("사용후 마나 : " + orc1.getMp());

        System.out.println("\n착용전 공격력 : " + orc1.getTotalAttackPower());
        System.out.println("착용전 방어력 : " + orc1.getTotalDefencePower());
        System.out.println("착용전 공격속도 : " + orc1.getTotalAttackSpeed());
        weaponService.equipWeapon(orc1, new ShortAxe());
        System.out.println("착용후 공격력 : " + orc1.getTotalAttackPower());
        System.out.println("착용후 방어력 : " + orc1.getTotalDefencePower());
        System.out.println("착용후 공격속도 : " + orc1.getTotalAttackSpeed());

        System.out.println("\n때리기 전 캐릭터 생명력 : " + orc1.getHp());
        System.out.println("때리기 전 몬스터 생명력 : " + slime2.getHp());
        weaponService.attack(orc1, slime2);
        System.out.println("때린 후 캐릭터 생명력 : " + orc1.getHp());
        System.out.println("때린 후 몬스터 생명력 : " + slime2.getHp());

        System.out.println("\n교체전 공격력 : " + orc1.getTotalAttackPower());
        System.out.println("교체전 방어력 : " + orc1.getTotalDefencePower());
        System.out.println("교체전 공격속도 : " + orc1.getTotalAttackSpeed());
        weaponService.equipWeapon(orc1, new IronHammer());
        System.out.println("교체후 공격력 : " + orc1.getTotalAttackPower());
        System.out.println("교체후 방어력 : " + orc1.getTotalDefencePower());
        System.out.println("교체후 공격속도 : " + orc1.getTotalAttackSpeed());

        Thread.sleep(300); // 공격속도에 따른 쿨다운 대기를 위한 딜레이

        System.out.println("\n때리기 전 캐릭터 생명력 : " + orc1.getHp());
        System.out.println("때리기 전 몬스터 생명력 : " + slime2.getHp());
        weaponService.attack(orc1, slime2);
        System.out.println("때린 후 캐릭터 생명력 : " + orc1.getHp());
        System.out.println("때린 후 몬스터 생명력 : " + slime2.getHp());

        System.out.println("\nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        System.out.println("엘프 스킬 사용 + 무기 착용 + 무기 공격 + 몬스터 반격 + 엘프 회피 + 몬스터 공격 테스트\n");
        Elf elf1 = new Elf(
                1, // 레벨
                new BigDecimal(100), // 체력
                new BigDecimal(200), // 마력
                new BigDecimal(10), // 공격력
                new BigDecimal(10), // 공격속도
                new BigDecimal(5), // 방어력
                new BigDecimal(30) // 회피율
        );

        Slime slime3 = new Slime(
                new BigDecimal(100), // 체력
                new BigDecimal(10), // 공격력
                new BigDecimal(1) // 방어력
        );

        System.out.println("\n스킬 사용 전 회피율 : " + elf1.getTotalDodgePercentage());
        System.out.println("스킬 사용 전 마나 : " + elf1.getMp());
        skillService.useTribeSkill(elf1, new Elusion());
        System.out.println("스킬 사용 후 회피율 : " + elf1.getTotalDodgePercentage());
        System.out.println("스킬 사용 후 마나 : " + elf1.getMp());

        System.out.println("\n무기 착용 전 공격속도 : " + elf1.getTotalAttackSpeed());
        weaponService.equipWeapon(elf1, new ShortBow());
        System.out.println("무기 착용 후 공격속도 : " + elf1.getTotalAttackSpeed());

        System.out.println("\n캐릭터 공격력 : " + elf1.getTotalAttackPower());
        System.out.println("무기 공격 전 캐릭터 생명력 : " + elf1.getHp());
        System.out.println("무기 공격 전 몬스터 생명력 : " + slime3.getHp());
        weaponService.attack(elf1, slime3);
        System.out.println("무기 공격 후 캐릭터 생명력 : " + elf1.getHp());
        System.out.println("무기 공격 후 몬스터 생명력 : " + slime3.getHp());

        System.out.println("\n몬스터 공격력 : " + slime3.getAttackPower());
        System.out.println("캐릭터 방어력 : " + elf1.getTotalDefencePower());
        System.out.println("몬스터 공격 전 캐릭터 생명력 : " + elf1.getHp());
        monsterAttackService.attack(slime3, elf1);
        System.out.println("몬스터 공격 후 캐릭터 생명력 : " + elf1.getHp());

        System.out.println("\nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        System.out.println("공통 스킬 사용 테스트\n");
        Human human2 = new Human(
                1, // 레벨
                new BigDecimal(100), // 체력
                new BigDecimal(100), // 마력
                new BigDecimal(10), // 공격력
                new BigDecimal(10), // 공격속도
                new BigDecimal(50), // 방어력
                new BigDecimal(30) // 회피율
        );

        System.out.println("힐 사용 전 캐릭터 생명력 : " + human2.getHp());
        for (int i = 0; i < 5; i++) {
            skillService.useCommonSkill(human2, new Heal());
            System.out.println("힐 "+ (i + 1) + "회 사용 후 캐릭터 생명력 : " + human2.getHp());
        }

        System.out.println("스팀 사용 전 캐릭터 공격력 : " + human2.getTotalAttackPower());
        skillService.useCommonSkill(human2, new Steam());
        System.out.println("스팀 사용 후 캐릭터 공격력 : " + human2.getTotalAttackPower());


        System.out.println("\nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        System.out.println("궁극 스킬 사용 테스트\n");
        Human human3 = new Human(
                1, // 레벨
                new BigDecimal(100), // 체력
                new BigDecimal(100), // 마력
                new BigDecimal(10), // 공격력
                new BigDecimal(10), // 공격속도
                new BigDecimal(50), // 방어력
                new BigDecimal(30) // 회피율
        );

        System.out.println("인빈서블 사용 전 캐릭터 회피력 : " + human3.getTotalDodgePercentage());
        Invincible invincible1 = new Invincible(human3);
        skillService.useUltimateSkill(human3, invincible1);
        System.out.println("인빈서블 사용 후 캐릭터 회피력 : " + human3.getTotalDodgePercentage());
        invincible1.run();

        Elf elf2 = new Elf(
                1, // 레벨
                new BigDecimal(100), // 체력
                new BigDecimal(100), // 마력
                new BigDecimal(10), // 공격력
                new BigDecimal(12), // 공격속도
                new BigDecimal(50), // 방어력
                new BigDecimal(30) // 회피율
        );

        System.out.println("라피드 사용 전 캐릭터 공격속도 : " + elf2.getTotalAttackSpeed());
        Rapid rapid = new Rapid(elf2);
        skillService.useUltimateSkill(elf2, rapid);
        System.out.println("라피드 사용 후 캐릭터 공격속도 : " + elf2.getTotalAttackSpeed());
        rapid.run();

        Orc orc2 = new Orc(
                1, // 레벨
                new BigDecimal(100), // 체력
                new BigDecimal(100), // 마력
                new BigDecimal(15), // 공격력
                new BigDecimal(12), // 공격속도
                new BigDecimal(50), // 방어력
                new BigDecimal(30) // 회피율
        );

        System.out.println("프렌지 사용 전 캐릭터 공격력 : " + orc2.getTotalAttackPower());
        Frenzy frenzy1 = new Frenzy(orc2);
        skillService.useUltimateSkill(orc2, frenzy1);
        System.out.println("프렌지 사용 후 캐릭터 공격력 : " + orc2.getTotalAttackPower());
        frenzy1.run();
    }

}
