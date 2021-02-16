import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Forest forest = new Forest();
        BeeManager beeManager = new BeeManager(forest);
        beeManager.start_searching();
    }
}
