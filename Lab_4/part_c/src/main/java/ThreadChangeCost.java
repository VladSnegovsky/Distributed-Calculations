public class ThreadChangeCost implements Runnable {
    private Graph graph;
    private Thread thread;
    private final String[] action = new String[]{"Lviv", "Kharkiv",
            "Kyiv", "London", "Los Angeles"};

    ThreadChangeCost(Graph graph) {
        this.graph = graph;
        thread = new Thread(this);
        thread.setName("ThreadChangeCost");
        thread.start();
    }

    @Override
    public void run() {
        int iter = 0;
        while (true) {
            try { Thread.sleep(1000); }
            catch (InterruptedException e) { e.printStackTrace(); }
            if (iter < 5){
                while (graph.lock.isWriteLocked()) { try { Thread.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); } }
                graph.lock.readLock().lock();
                City city = graph.getCity(action[iter], thread.getName());
                graph.lock.readLock().unlock();

                while (graph.lock.getReadLockCount() > 0) { try { Thread.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); } }
                while (!graph.lock.writeLock().tryLock()) {try { Thread.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); } }
                graph.changeCost(city, (int) (20 + Math.random() * 100), thread.getName());
                graph.lock.writeLock().unlock();
                iter += 1;
            }
        }
    }
}
