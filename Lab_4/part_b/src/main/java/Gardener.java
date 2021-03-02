public class Gardener implements Runnable{
    private Garden garden;
    private Thread thread;

    private int line;

    Gardener(Garden _garden) {
        garden = _garden;
        line = 0;
        thread = new Thread(this);
        thread.setName("Gardener");
        thread.start();
    }

    @Override
    public void run() {
        while (true){
            try {  Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

            while (!garden.lockReaderWriter(false, true, thread.getName())) { try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); } }
            while (garden.getLockR()) {try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }}

            garden.toWaterFlowers(line, thread.getName());

            garden.unlockWriter(thread.getName());

            line += 1;
            if (line == 10) { line = 0; }
        }
    }
}
