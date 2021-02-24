public class Mediator implements Runnable{
    private final Table table;
    private boolean isWorking;
    private Thread thread;

    static String red(String str){ return (char) 27 + "[31m" + str + (char) 27 + "[0m"; }
    static String green(String str){ return (char) 27 + "[32m" + str + (char) 27 + "[0m"; }

    Mediator(Table _Table) {
        table = _Table;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run() {
        while (true){
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            if (!table.tobacco && !table.matches && !table.paper){
                while (!table.use.tryAcquire()){}

                int pos = (int) (Math.random() * 3);

                switch (pos) {
                    case 0 -> {
                        System.out.println(green("Mediator") + " put paper and matches on the table.");
                        table.paper = true;
                        table.matches = true;
                    }
                    case 1 -> {
                        System.out.println(green("Mediator") + " put tobacco and matches on the table.");
                        table.tobacco = true;
                        table.matches = true;
                    }
                    case 2 -> {
                        System.out.println(green("Mediator") + " put paper and tobacco on the table.");
                        table.tobacco = true;
                        table.paper = true;
                    }
                    default -> System.out.println(red("Mediator ERROR"));
                }

                table.use.release();
            }
        }
    }
}
