public class ToolStore extends NormalLocation {
    public ToolStore(Player player) {
        super(player,"Store");
    }

    @Override
    public boolean onLocation() {

        System.out.println("-----------Welcome to Store------------");
       boolean showMenu = true;
        while (true){
            System.out.println("1.Silahlar");
            System.out.println("2.Zırhlar");
            System.out.println("3.Exit!");
            int selectCase=input.nextInt();
            while (selectCase <1 || selectCase > 3){

                System.out.println("Geçersiz değer, tekrar giriniz!!!");
                selectCase=input.nextInt();
            }
            switch (selectCase){
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Çıkış Yapıldı!!");
                    showMenu = false;
                    break;

            }
            return true;
        }

    }
    public  void printWeapon(){
        System.out.println("-------- Silahlar! --------");

        for (Weapon w : Weapon.weapons()){
            System.out.println(w.getId() + "--"
                    + w.getName() + "  Para : "
                    + w.getPrice() + "  Hasar : "
                    + w.getDamage());
        }

        System.out.println("0---Çıkış yap!!!");

    }

    public void buyWeapon(){

        System.out.println("Bir silah seçiniz : ");
        int selectWeaponID = input.nextInt();
        while (selectWeaponID <0 || selectWeaponID > Weapon.weapons().length){

            System.out.println("Geçersiz değer, tekrar giriniz!!!");
            selectWeaponID=input.nextInt();
        }

        if (selectWeaponID != 0){

        Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
        if (Weapon.getWeaponObjByID(selectWeaponID) != null){
            if (selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                System.out.println("Not enough money!!!");
            }
            else {

                //Satın alma
                System.out.println(selectedWeapon.getName() + " silahını satın aldınız!");
                int balance =this.getPlayer().getMoney() - selectedWeapon.getPrice();
                this.getPlayer().setMoney(balance);

                System.out.println("Kalan paranız : " + this.getPlayer().getMoney());
                System.out.println("Önceki silahınız : "  +  this.getPlayer().getInventory().getWeapon().getName());
                this.getPlayer().getInventory().setWeapon(selectedWeapon);
                System.out.println("Şimdiki silahınız : "  + this.getPlayer().getInventory().getWeapon().getName());

            }
        }
    }
    }

    public void printArmor(){
        System.out.println("-------- Zırhlar! --------");

        for (Armor a : Armor.armors()){
            System.out.println(a.getId() + "--"
                    + a.getName() + "  Para : "
                    + a.getPrice() + "  Zırh : "
                    + a.getBlock());
        }
        System.out.println("0---Çıkış yap!!!");

    }
    public void buyArmor() {

        System.out.println("Bir zırh seçiniz : ");
        int selectArmorID = input.nextInt();
        while (selectArmorID < 0 || selectArmorID > Armor.armors().length) {

            System.out.println("Geçersiz değer, tekrar giriniz!!!");
            selectArmorID = input.nextInt();

        }
        if (selectArmorID != 0){
            Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);
            if (selectedArmor != null){
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli paranız bulunmamaktadır!!!");
                }
                else {
                    //Satın alma
                    System.out.println(selectedArmor.getName() + " silahını satın aldınız!");
                    int balance =this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);

                    System.out.println("Kalan paranız : " + this.getPlayer().getMoney());
                    System.out.println("Önceki zırhınız : "  +  this.getPlayer().getInventory().getArmor().getName());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Şimdiki zırhınız : "  + this.getPlayer().getInventory().getArmor().getName());

                }
            }
        }
    }
}
