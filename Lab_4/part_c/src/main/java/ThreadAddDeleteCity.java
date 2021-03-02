public class ThreadAddDeleteCity implements Runnable {
    private Graph graph;
    private Thread thread;

    private boolean toDo;
    private final String[] actionAdd = new String[]{"New York", "Manchester",
            "Barcelona"};
    private final String[] actionDelete = new String[]{"Lviv"};

    ThreadAddDeleteCity(Graph graph) {
        toDo = false;
        this.graph = graph;
        thread = new Thread(this);
        thread.setName("ThreadAddDeleteCity");
        thread.start();
    }

    @Override
    public void run() {
        int added = 0;
        int deleted = 0;
        while (true) {
            try { Thread.sleep(1000); }
            catch (InterruptedException e) { e.printStackTrace(); }
            if (added < 3 && deleted < 1) {
                if (toDo) {
                    while (graph.lock.isWriteLocked()) { try { Thread.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); } }
                    graph.lock.readLock().lock();
                    City city = graph.getCity(actionAdd[added], thread.getName());
                    graph.lock.readLock().unlock();

                    while (graph.lock.getReadLockCount() > 0) { try { Thread.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); } }
                    while (!graph.lock.writeLock().tryLock()) {try { Thread.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); } }
                    graph.addCity(city, thread.getName());
                    graph.lock.writeLock().unlock();
                    added += 1;
                    toDo = false;
                } else {
                    while (graph.lock.isWriteLocked()) { try { Thread.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); } }
                    graph.lock.readLock().lock();
                    City city = graph.getCity(actionDelete[deleted], thread.getName());
                    graph.lock.readLock().unlock();

                    while (graph.lock.getReadLockCount() > 0) { try { Thread.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); } }
                    while (!graph.lock.writeLock().tryLock()) {try { Thread.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); } }
                    graph.removeCity(city, thread.getName());
                    graph.lock.writeLock().unlock();
                    deleted += 1;
                    toDo = true;
                }
            }
        }
    }
}
