public class MyCyclicBarrier {
    private int parties;
    private final int num;

    MyCyclicBarrier(int parties) {
        this.parties = parties;
        this.num = parties;
    }

    synchronized void addPart() {
        parties--;
    }

    void await() {
        addPart();
        while (parties > 0) {
            try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        try { Thread.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
        parties += 1;
    }
}
