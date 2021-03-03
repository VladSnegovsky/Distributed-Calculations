import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 3, 7, 8, 4, 3, 8, 1, 2, 6}; //43
        int[] arr2 = new int[]{5, 2, 9, 0, 3, 4, 5, 7, 3, 5}; //43
        int[] arr3 = new int[]{3, 9, 5, 7, 3, 0, 2, 6, 4, 4}; //43
        CyclicBarrier waitGroup = new CyclicBarrier(3);

        TaskThread taskThread1 = new TaskThread(arr1, "Thread1", 1, waitGroup);
        TaskThread taskThread2 = new TaskThread(arr2, "Thread2", 7, waitGroup);
        TaskThread taskThread3 = new TaskThread(arr3, "Thread3", 3, waitGroup);

        taskThread1.addFriends(taskThread2, taskThread3);
        taskThread2.addFriends(taskThread1, taskThread3);
        taskThread3.addFriends(taskThread1, taskThread2);

        Thread thread1 = new Thread(taskThread1);
        Thread thread2 = new Thread(taskThread2);
        Thread thread3 = new Thread(taskThread3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
