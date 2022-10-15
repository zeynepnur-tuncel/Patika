import java.util.Scanner;

public class Game {
   private Scanner input = new Scanner(System.in);

    public void  start(){
        System.out.println("Welcome to Macere Oyunu!!!");
        System.out.println("Enter the Name: ");
        //String playerName = input.nextLine();
        Player player = new Player("Zeynep");
        System.out.println("Dear " + player.getName() + " welcome!!");
        System.out.println("hohoHOHOOHOHOOOOOOO");
        System.out.println("Select Character: ");
        player.selectChar();

        Location location=null;
        while(true){
             player.printInfo();
            System.out.println("Bölgeler!!!");
            System.out.println("1. Safe House");
            System.out.println("2. ToolStore");
            System.out.println("3. Cave-->Award:Food");
            System.out.println("4. Forest-->Award:Firewood");
            System.out.println("5. River-->Award:Water");
            System.out.println("0. EXIT");
            System.out.println("Select Location: ");
            int selectLoc= input.nextInt();
            switch (selectLoc){
                case 0:
                    location=null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location= new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                default:
                    System.out.println("Lütfen geçerli bir değer giriniz!!!");


            }
            if (location==null){
                System.out.println("Good bye!!!!");
                break;
            }
           if (!location.onLocation())
            {
                System.out.println("GAME OVER!!!");
                break;

            }

        }



    }
}
