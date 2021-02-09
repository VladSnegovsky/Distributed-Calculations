import javax.swing.*;

public class Threads implements Runnable {
    Thread thread;
    JSlider slider;
    boolean is_running = true;
    int number;

    public Threads(int number, JSlider slider) {
        this.number = number;
        this.slider = slider;
        this.thread = new Thread(this);
        this.thread.start();
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

    public void lower_priority() { thread.setPriority(Thread.MIN_PRIORITY); }

    public void increase_priority() {
        thread.setPriority(Thread.MAX_PRIORITY);
    }
}
