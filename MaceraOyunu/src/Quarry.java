public class Quarry  extends BattleLoc{
    private Obstacle obstacle;
    public Quarry(Player player0) {
        super(player0,"Maden", Quarry.getobstacle(), "Savaş Ganimeti", 2);
    }


    public static Obstacle getobstacle() {
        return new Snake();
    }
}
