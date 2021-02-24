import java.util.concurrent.Semaphore;

public class Salon implements Runnable{
    final Hairdresser hairdresser;
    Semaphore chair;

    Salon() {
        hairdresser = new Hairdresser(this);
        chair = new Semaphore(1, true);
    }

    public void run() {
        new Thread(hairdresser).start();
        for (int i = 0; i < 10; i++){
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            new Thread(new Visitor(this, (int) (2 + Math.random() * 5) * 1000, i)).start();
        }
    }
}
