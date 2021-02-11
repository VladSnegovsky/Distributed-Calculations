import javax.swing.*;

public class Threads implements Runnable {
    int number;
    boolean is_running = true;

    JSlider slider;
    Thread thread;

    static String red(String str){
        return (char) 27 + "[31m" + str + (char) 27 + "[0m\n";
    }

    public void Run() {
        is_running = !is_running;
    }

    @Override
    public void run() {
        while (is_running) {
            synchronized (this){ slider.setValue(number); }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Threads(int number, JSlider slider) {
        this.thread = new Thread(this);
        this.number = number;
        this.slider = slider;

        this.thread.start();
    }

    public void increase_priority() {
        if (thread.getPriority() + 1 <= Thread.MAX_PRIORITY) {
            thread.setPriority(thread.getPriority() + 1);
            System.out.print(red("Priority of thread " + thread.getId() + " increased to " + Integer.toString(thread.getPriority()) + "!"));
        }
    }

    public void decrease_priority() {
        if (thread.getPriority() - 1 >= Thread.MIN_PRIORITY) {
            thread.setPriority(thread.getPriority() - 1);
            System.out.print(red("Priority of thread " + thread.getId() + " decreased to " + Integer.toString(thread.getPriority()) + "!"));
        }
    }
}
