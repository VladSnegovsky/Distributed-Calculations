import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

public class Main {
    static int countLetter(String[] arr1, String letter) {
        int answer = 0;
        for (int i = 0; i < 10; i++) {
            if (arr1[i].equals(letter)) {
                answer += 1;
            }
        }
        return answer;
    }

    static boolean checkAnswer(String[] arr1, String[] arr2, String[] arr3, String[] arr4) {
        int count = 0;
        int countA1 = countLetter(arr1, "A");
        int countB1 = countLetter(arr1, "B");
        int countA2 = countLetter(arr2, "A");
        int countB2 = countLetter(arr2, "B");
        int countA3 = countLetter(arr3, "A");
        int countB3 = countLetter(arr3, "B");
        int countA4 = countLetter(arr4, "A");
        int countB4 = countLetter(arr4, "B");
        if (countA1 == countB1) { count += 1; }
        if (countA2 == countB2) { count += 1; }
        if (countA3 == countB3) { count += 1; }
        if (countA4 == countB4) { count += 1; }
        System.out.println("Arr1 " + Arrays.toString(arr1) + "[" + String.valueOf(countA1) + ":" + String.valueOf(countB1) + "]");
        System.out.println("Arr2 " + Arrays.toString(arr2) + "[" + String.valueOf(countA2) + ":" + String.valueOf(countB2) + "]");
        System.out.println("Arr3 " + Arrays.toString(arr3) + "[" + String.valueOf(countA3) + ":" + String.valueOf(countB3) + "]");
        System.out.println("Arr4 " + Arrays.toString(arr4) + "[" + String.valueOf(countA4) + ":" + String.valueOf(countB4) + "]");
        return count >= 3;
    }

    public static void main(String[] args) {
        String[] arr1 = new String[]{"B", "A", "A", "C", "D", "D", "B", "C", "A", "D"};
        String[] arr2 = new String[]{"D", "B", "A", "C", "D", "A", "C", "C", "A", "B"};
        String[] arr3 = new String[]{"B", "C", "D", "B", "A", "D", "C", "A", "A", "B"};
        String[] arr4 = new String[]{"B", "D", "A", "C", "A", "D", "A", "B", "B", "C"};
        CyclicBarrier waitGroup = new CyclicBarrier(4);

        while (true) {
            TaskThread taskThread1 = new TaskThread(arr1, "Thread1", 1, waitGroup);
            TaskThread taskThread2 = new TaskThread(arr2, "Thread2", 7, waitGroup);
            TaskThread taskThread3 = new TaskThread(arr3, "Thread3", 3, waitGroup);
            TaskThread taskThread4 = new TaskThread(arr4, "Thread4", 5, waitGroup);

            Thread thread1 = new Thread(taskThread1);
            Thread thread2 = new Thread(taskThread2);
            Thread thread3 = new Thread(taskThread3);
            Thread thread4 = new Thread(taskThread4);

            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();

            try { thread1.join(); } catch (InterruptedException e) { e.printStackTrace(); }
            try { thread2.join(); } catch (InterruptedException e) { e.printStackTrace(); }
            try { thread3.join(); } catch (InterruptedException e) { e.printStackTrace(); }
            try { thread4.join(); } catch (InterruptedException e) { e.printStackTrace(); }

            arr1 = taskThread1.line;
            arr2 = taskThread2.line;
            arr3 = taskThread3.line;
            arr4 = taskThread4.line;

            if (checkAnswer(arr1, arr2, arr3, arr4)) { break; }
        }
    }
}
