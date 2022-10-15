import java.util.Scanner;

public abstract class Location {

   private Player player;
   private String name;
   private boolean selectCombatValue = false;

   public static Scanner input=new Scanner(System.in);


    public Location(Player player, String name) {
        this.player = player;
        this.name = name;
    }


    abstract boolean onLocation();

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSelectCombatValue(boolean selectCombatValue) {
        this.selectCombatValue = selectCombatValue;
    }
    public boolean isSelectCombatValue() {
        return selectCombatValue;
    }
}
