import java.io.FileWriter;
import java.io.IOException;

public class Monitor2 implements Runnable{
    private Garden garden;
    private Thread thread;

    Monitor2(Garden _garden) {
        garden = _garden;
        thread = new Thread(this);
        thread.setName("Monitor2");
        thread.start();
    }

    @Override
    public void run() {
        while (true){
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

            garden.lockReaderWriter(true, false, thread.getName());
            while (garden.getLockW()) { try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); } }

            System.out.print("");
            String results = garden.getGardenInfo(thread.getName());
            System.out.println(results + "\n");

            garden.unlockReader(thread.getName());
        }
    }
}
