import java.time.Period;
import java.util.concurrent.*;


public class Main {
    public static void main(String[] args) {
        lampord.LampordLock l = new lampord.LampordLock(3);
        int[] energy = new int[32];
        for (int i = 0; i < energy.length; i++){
            int tempEnergy = 20 + (int) (Math.random() * 80);
//            System.out.println("Energy " + String.valueOf(i) + ": is " + String.valueOf(tempEnergy));
            energy[i] = tempEnergy;
        }

        ListOfMonks monks = new ListOfMonks(energy, l);
//        for (int j = 0; j < monks.getSize(); j++){
//            System.out.println(monks.getMonksInfo(j));
//        }

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 3, 0L, TimeUnit.SECONDS, new SynchronousQueue<>());

        Competitions competition1 = new Competitions(monks, "First Group");
        Competitions competition2 = new Competitions(monks, "Second Group");
        Competitions competition3 = new Competitions(monks, "Third  Group");

//        executor.execute(competition1);
//        executor.execute(competition2);
//        executor.execute(competition3);

        l.register(competition1.thread);
        l.register(competition2.thread);
        l.register(competition3.thread);

        competition1.thread.start();
        competition2.thread.start();
        competition3.thread.start();

//        executor.shutdown();
    }
}
