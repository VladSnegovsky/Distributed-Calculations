public class ThreadGetCost implements Runnable {
    private Graph graph;
    private Thread thread;
    private final String[] action = new String[]{"", ""};

    ThreadGetCost(Graph graph, String fCity, String sCity) {
        action[0] = fCity;
        action[1] = sCity;
        this.graph = graph;
        thread = new Thread(this);
        thread.setName("ThreadGetCost");
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try { Thread.sleep(5000); }
            catch (InterruptedException e) { e.printStackTrace(); }
            while (graph.lock.isWriteLocked()) { try { Thread.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); } }
            graph.lock.readLock().lock();
            City fCity = graph.getCity(action[0], thread.getName());
            graph.lock.readLock().unlock();

            while (graph.lock.isWriteLocked()) { try { Thread.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); } }
            graph.lock.readLock().lock();
            City sCity = graph.getCity(action[1], thread.getName());
            graph.lock.readLock().unlock();

            while (graph.lock.getReadLockCount() > 0) { try { Thread.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); } }
            while (!graph.lock.writeLock().tryLock()) {try { Thread.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); } }
            int cost = graph.findCostOfWay(fCity, sCity, thread.getName());
            graph.lock.writeLock().unlock();
            System.out.println("Cost between " + action[0] + " and " + action[1] + " is " +
                    String.valueOf(cost));
        }
    }
}
