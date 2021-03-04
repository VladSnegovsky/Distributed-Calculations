import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TaskThread implements Runnable {
    final int[] line;
    final String name;
    private final int time;
    private final MyCyclicBarrier waitGroup;
    private TaskThread friend1;
    private TaskThread friend2;

    TaskThread(int[] _line, String _name, int _time, MyCyclicBarrier _waitGroup) {
        line = _line;
        time = _time;
        name = _name;
        waitGroup = _waitGroup;
    }

    void addFriends(TaskThread _friend1, TaskThread _friend2) {
        friend1 = _friend1;
        friend2 = _friend2;
    }

    int countCum(int[] _line) {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += _line[i];
        }
        return sum;
    }

    @Override
    public void run() {
        while (true) {
            Random random = new Random();
            System.out.println("Thread " + name + " started working[" + String.valueOf(time) + "sec]");

            int pos = random.nextInt(10);
            if (random.nextBoolean()) { line[pos] += 1; }
            else { line[pos] -= 1; }

            try { Thread.sleep(time* 1000L); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("Thread " + name + " is waiting.");
            waitGroup.await();
            int sum1 = countCum(line);
            int sum2 = countCum(friend1.line);
            int sum3 = countCum(friend2.line);
            System.out.println("Thread " + name + ":\n" +
                    name + " " + Arrays.toString(line) + "[" + String.valueOf(sum1) + "]\n" +
                    friend1.name + " " + Arrays.toString(friend1.line) + "[" + String.valueOf(sum2) + "]\n" +
                    friend2.name + " " + Arrays.toString(friend2.line) + "[" + String.valueOf(sum3) + "]\n");
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            if (sum1 == sum2 && sum2 == sum3) { break; }
        }
        System.out.println("Thread " + name + " stopped.");
    }
}
