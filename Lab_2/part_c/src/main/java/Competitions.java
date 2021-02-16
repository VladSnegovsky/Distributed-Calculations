import java.util.ArrayList;

public class Competitions implements Runnable{
    private boolean isWorking;
    private final ListOfMonks Monks;
    Thread thread;

    Competitions(ListOfMonks _Monks, String name) {
        this.isWorking = true;
        this.Monks = _Monks;
        this.thread = new Thread(this);
        this.thread.setName(name);
    }

    int determineWinner(Monk firstMonk, Monk secondMonk) {
        return 0;
    }

    @Override
    public void run() {
        while (isWorking){
            if (!Monks.isWinner()) {
                ArrayList<Monk> rivals;
                rivals = Monks.getRivals(this.thread);

                if (rivals.size() == 2){
                    int winner = determineWinner(rivals.get(0), rivals.get(1));

                    System.out.println(this.thread.getName() + "::: " + rivals.get(winner).getName() + " wins " + rivals.get(1-winner).getName() + ".");
                    Monks.addWinner(this.thread, rivals.get(winner));
                }
            } else {
                Monks.announceWinner();
                isWorking = false;
            }
        }
    }
}
