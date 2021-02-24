public class Visitor implements Runnable{
    private final Salon salon;
    private final int time;
    private final int ID;

    Visitor (Salon _salon, int _time, int num) {
        this.salon = _salon;
        this.time = _time;
        this.ID = num;
    }

    @Override
    public void run() {
        while (!salon.chair.tryAcquire()){
            salon.hairdresser.toWake();
        }
        System.out.println("Hairdresser started working. [" + String.valueOf(ID) + "]");
        System.out.println("Visitor cut his hair. [" + String.valueOf(ID) + "]");
        salon.chair.release();
    }
}
