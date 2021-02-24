public class Hairdresser implements Runnable{
    private final Salon salon;
    private boolean isSleeping;

    Hairdresser(Salon _salon) {
        salon = _salon;
        isSleeping = true;
    }

    @Override
    public void run() {
        while (true) {
            if (isSleeping) {
                try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            } else {
                if (salon.chair.tryAcquire()){
                    isSleeping = true;
                    salon.chair.release();
                }
            }
        }
    }

    void toWake() {
        if (isSleeping){
            isSleeping = false;
            System.out.println("Hairdresser freed chair.");
            salon.chair.release();
        }
    }
}
