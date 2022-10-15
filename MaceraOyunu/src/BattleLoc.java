import java.util.Random;

public abstract class BattleLoc extends Location {

    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    private boolean selectCombatValue;
    public BattleLoc(Player player, String name,Obstacle obstacle,String award,int maxObstacle) {
        super(player, name);
        this.obstacle=obstacle;
        this.award=award;
        this.maxObstacle=maxObstacle;
    }

    @Override
    public boolean onLocation(){
        System.out.println("Şuan buradasınız : " + this.getName());
        System.out.println("Dikkatli ol! Burada "+ this.getMaxObstacle() + " tane "
                + this.getObstacle().getName() + " yaşıyor");
        boolean continueEx = true;
        while(continueEx) {
            System.out.println("<S>avaş  veya <K>aç");
            String selectCase = Location.input.nextLine();
            selectCase = selectCase.toUpperCase();
            if (selectCase.equals("S")) {
                if (combat(getMaxObstacle())) {
                    break;
                } else {
                    System.out.println("Öldünüz!");
                    return false;
                }
            } else if (selectCase.equals("K")) {
                continueEx = false;
            }
        }
        return true;
    }
    public boolean combat(int obsNumber){
        Random random = new Random();
        for(int i = 1; i<=obsNumber; i++){
            this.getObstacle().setHealth(this.getObstacle().getOrjHealth());

            obstacleStats(i);
            playerStats();

            while (this.getPlayer().getHealth() > 0
                    && this.getObstacle().getHealth() > 0) {
                int priotiry = random.nextInt(2);
                System.out.println("<V>ur veya <K>aç");
                String selectCombat = input.nextLine().toUpperCase();

                if (selectCombat.equals("V")) {
                    if (priotiry == 0) {
                        System.out.println("Vurdunuz!" + " öncelik: " + (priotiry));
                        this.getObstacle().setHealth(this.getObstacle().getHealth()
                                - this.getPlayer().getTotalDamage());
                        afterHit(i);
                    }
                    else {
                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println(this.getObstacle().getName() + " size vurdu"  + " öncelik: " + (priotiry ));
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getTotalDamage();
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - this.getObstacle().getDamage());
                            afterHit(i);
                        }
                    }
                    this.setSelectCombatValue(true);
                }
                else if(selectCombat.equals("K")){
                    afterHit(i);
                    this.setSelectCombatValue(false);
                    //hala hayatta
                    return true;
                }
            }

        }


        if( this.getPlayer().getHealth() > this.getObstacle().getHealth()){
            System.out.println("Düşmanı yendiniz!");
            if(this.getObstacle().getName().equals("Yılan")){
                Player player = this.getPlayer();
                player.awardForQarry(this.getObstacle().getAward());
                //hala hayatta
                this.setSelectCombatValue(true);
                return true;
            }
            else {
                int totalGain = this.getObstacle().getAward() * getMaxObstacle();
                System.out.println(totalGain + " altın ödülünü kazandınız");
                System.out.println("Önceki paranız: " + this.getPlayer().getMoney());
                this.getPlayer().setMoney(this.getPlayer().getMoney() + totalGain);
                System.out.println("Güncel paranız : " + this.getPlayer().getMoney());
                System.out.println(this.getName() + " bölgesinde tüm düşmanları yendiniz !");
                System.out.println("Artık bu bölge temizlendi. ");
                System.out.println("isSelected değeri: " + isSelectCombatValue());
                //hala hayatta
                this.setSelectCombatValue(true);
                return true;
            }
        }else{
            //öldü
            return false;}
    }


    public int firstHit() {    // İlk kimin vuracağını temsil ediyor.
        Random r = new Random();
        return r.nextInt(2);
    }

    private void afterHit(int i) {
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        System.out.println(i + ". " +this.obstacle.getName() + " canı: "
                + this.obstacle.getHealth());
        System.out.println();
    }
    public boolean isWantWeapon(String name) {
        System.out.println("Tebrikler " + name + " kazandiniz almak istermisiniz");
        System.out.println("Suanki elinizde olan: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("1\tEvet");
        System.out.println("2\tHayir");
        System.out.println();
        int isYes = input.nextInt();
        input.nextLine();       // Bunu diger alıcagımız deger String oldugu ıcın karışmaması adına yapıyoruz.
        while (isYes < 1 || isYes > 2) {
            System.out.println("Yanlis secim tekrar deneyin");
            isYes = input.nextInt();            // Bunu diger alıcagımız deger String oldugu ıcın karışmaması adına yapıyoruz.
            input.nextLine();
        }
        if (isYes == 2) {
            return false;
        }
        return true;
    }

    public boolean isWantArmor(String name) {
        System.out.println("Tebrikler " + name + " kazandiniz almak istermisiniz");
        System.out.println("Suanki elinizde olan: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("1\tEvet");
        System.out.println("2\tHayir");
        System.out.println();
        int isYes = input.nextInt();
        input.nextLine();       // Bunu diger alıcagımız deger String oldugu ıcın karışmaması adına yapıyoruz.
        while (isYes < 1 || isYes > 2) {
            System.out.println("Yanlis secim tekrar deneyin");
            isYes = input.nextInt();
            input.nextLine();       // Bunu diger alıcagımız deger String oldugu ıcın karışmaması adına yapıyoruz.
        }
        if (isYes == 2) {
            return false;
        }
        return true;
    }

    public void locationAward() {       // Bölüm sonu ödülü alıp almadığı ve hangi ödülü aldıgını belli ediyoruz.
        if (this.getName().equals("Magara")) {
            this.getPlayer().getInventory().setFood(this.award);
        }
        if (this.getName().equals("Orman")) {
            this.getPlayer().getInventory().setFirewood(this.award);
        }
        if (this.getName().equals("Nehir")) {
            this.getPlayer().getInventory().setWater(this.award);
        }
        if (this.getName().equals("Maden")) {
            this.getPlayer().getInventory().setSnake(this.award);
        }
    }

    public void playerStats(){
        System.out.println("Oyuncu Değerleri: ");
        System.out.println("-----------------------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para :" + this.getPlayer().getMoney());

    }

    public void obstacleStats(int i){
        System.out.println(i + "." + this.getObstacle().getName() + "Değerleri");
        System.out.println("-----------------------------------");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Para :" + this.getObstacle().getAward());

    }
    public int randomObstacleNumber(){
       Random r =new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
    @Override
    public void setSelectCombatValue(boolean selectCombatValue) {
        this.selectCombatValue=selectCombatValue;
    }
}
