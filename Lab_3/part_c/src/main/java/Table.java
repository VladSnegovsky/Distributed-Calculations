import java.util.concurrent.Semaphore;

public class Table {
    Semaphore use;
    boolean tobacco;
    boolean paper;
    boolean matches;

    Table() {
        use = new Semaphore(1, true);
        tobacco = false;
        paper = false;
        matches = false;
    }
}
