import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TaskThread implements Runnable {
    final String[] line;
    private final String name;
    private final int time;
    private final CyclicBarrier waitGroup;

    TaskThread(String[] _line, String _name, int _time, CyclicBarrier _waitGroup) {
        line = _line;
        time = _time;
        name = _name;
        waitGroup = _waitGroup;
    }

    @Override
    public void run() {
        Random random = new Random();
        System.out.println("Thread " + name + " started working[" + String.valueOf(time) + "sec]");
        for (int i = 0; i < 10; i++) {
            if (random.nextBoolean()) {
                switch (line[i]) {
                    case "A" -> line[i] = "C";
                    case "C" -> line[i] = "A";
                    case "B" -> line[i] = "D";
                    case "D" -> line[i] = "B";
                }
            }
        }
        try { Thread.sleep(time* 1000L); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("Thread " + name + " is waiting.");
        try { waitGroup.await(); } catch (InterruptedException e) { e.printStackTrace(); } catch (BrokenBarrierException e) { e.printStackTrace(); }
        System.out.println("Thread " + name + " returned " + Arrays.toString(line));
    }
}
