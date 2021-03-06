public class SmokerTobacco implements Runnable{
    private final Table table;
    private Thread thread;

    static String red(String str){ return (char) 27 + "[31m" + str + (char) 27 + "[0m"; }
    static String green(String str){ return (char) 27 + "[32m" + str + (char) 27 + "[0m"; }

    SmokerTobacco(Table _table) {
        table = _table;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true){
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            if (!table.tobacco && table.matches && table.paper){
                while (!table.use.tryAcquire()) {}

                System.out.println(green("SmokerTobacco") + " started smoking.");
                try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(green("SmokerTobacco") + " finished smoking.");

                table.matches = false;
                table.paper = false;

                table.use.release();
            }
        }
    }
}
