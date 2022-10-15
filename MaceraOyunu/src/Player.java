import javax.xml.stream.Location;
import java.util.Random;
import java.util.Scanner;

public class Player {

    private int damage;
    private int health;
    private int orjHealth;

    public int getOrjHealth() {
        return orjHealth;
    }

    public void setOrjHealth(int orjHealth) {
        this.orjHealth = orjHealth;
    }

    private int money;
    private String charName;
    private String name;
    private Scanner input=new Scanner(System.in);
    private Inventory inventory;

    public Player(String name){
        this.name=name;
        this.inventory=new Inventory();

    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
  public int getTotalDamage(){

          return damage + this.getInventory().getWeapon().getDamage();


  }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void selectChar(){


        GameCharacter[] charlist ={new Samurai(), new Archer(), new Knight()};
        System.out.println("KARAKTERLER: ");
        System.out.println("---------------------------------");
        for (GameCharacter gameCharacter: charlist){
            System.out.println("Player Id: " +gameCharacter.getId()+
                    "\t Character:" +gameCharacter.getName()+
                    "\t Damage:" +gameCharacter.getDamage()+
                    "\t Health:" +gameCharacter.getHealth()+
                    "\t Money:" +gameCharacter.getMoney());

        }


        System.out.println("----------------------------------------------");
        System.out.println("Please enter the char: ");
        int selectChar =input.nextInt();
        switch (selectChar){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer() );
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }

        System.out.println("Character:" +this.getCharName()+
                "\t Damage:" +this.getDamage()+
                "\t Health:" +this.getHealth()+
                "\t Money:" +this.getMoney());
    }
    public  void printInfo(){
        System.out.println("Silahınız:" +this.getInventory().getWeapon().getName()+
                "\t Zırh:" +this.getInventory().getArmor().getName()+
                "\t Blocklama:" +this.getInventory().getArmor().getBlock()+
                "\t Damage:" +this.getTotalDamage()+
                "\t Health:" +this.getHealth()+
                "\t Money:" +this.getMoney());
    }


    public void initPlayer(GameCharacter gameCharacter){
    this.setDamage(gameCharacter.getDamage());
    this.setHealth(gameCharacter.getHealth());
    this.setOrjHealth(gameCharacter.getHealth());
    this.setMoney(gameCharacter.getMoney());
    this.setCharName(gameCharacter.getName());
    }
    public void awardForQarry(int possib){
        Weapon[] weapons =
                {

                        Weapon.getWeaponObjByID(3),
                        Weapon.getWeaponObjByID(3),
                        Weapon.getWeaponObjByID(2),
                        Weapon.getWeaponObjByID(2),
                        Weapon.getWeaponObjByID(2),
                        Weapon.getWeaponObjByID(1),
                        Weapon.getWeaponObjByID(1),
                        Weapon.getWeaponObjByID(1),
                        Weapon.getWeaponObjByID(1),
                        Weapon.getWeaponObjByID(1)
                };
        Armor[] armors =
                {
                        Armor.getArmorObjByID(3),
                        Armor.getArmorObjByID(3),
                        Armor.getArmorObjByID(2),
                        Armor.getArmorObjByID(2),
                        Armor.getArmorObjByID(2),
                        Armor.getArmorObjByID(1),
                        Armor.getArmorObjByID(1),
                        Armor.getArmorObjByID(1),
                        Armor.getArmorObjByID(1),
                        Armor.getArmorObjByID(1)
                };

        int money[] = {1,1,1,1,1,5,5,5,10,10};

        Random random = new Random();
        int possRn = possib;
        int percent;
        if(possRn < 3){
            percent = random.nextInt(10);
            Weapon weapon = weapons[percent];
            System.out.println("Tebrikler! Silah kazandınız.");
            System.out.println("Önceki silahınız: " + this.getInventory().getWeapon().getName());
            this.getInventory().setWeapon(weapon);
            System.out.println("Yeni silahınız: " + this.getInventory().getWeapon().getName());

        }
        else if(2<possRn  || possRn < 6){
            percent = random.nextInt(10);
            Armor armor = armors[percent];
            System.out.println("Terikler! Zırh kazandınız.");
            System.out.println("Önceki silahınız: " + this.getInventory().getArmor().getName());
            this.getInventory().setArmor(armor);
            System.out.println("Yeni silahınız: " + this.getInventory().getArmor().getName());
        }
        else if (5 < possRn || possRn<11){
            percent = random.nextInt(10);
            int gain = money[percent];
            System.out.println("Tebrikler! Altın kazandınız.");
            System.out.println("Önceki paranız: " + this.getMoney() + " altın");
            this.setMoney(this.getMoney() + gain);
            System.out.println("yeni paranız: " + getMoney() + " altın");
        }
        else{
            System.out.println("Malesef hiçbir şey kazanamadınız!");
        }
    }
}
