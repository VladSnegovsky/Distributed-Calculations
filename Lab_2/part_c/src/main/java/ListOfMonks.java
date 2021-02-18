import java.util.ArrayList;

public class ListOfMonks {
    private final ArrayList<Monk> participants;
    private boolean winnerAnnounced = false;
    private int first, last;

    lampord.LampordLock l;

    static private String red(String str){
        return (char) 27 + "[31m" + str + (char) 27 + "[0m";
    }

    ListOfMonks(int[] energy, lampord.LampordLock _l) {
//        this.first = 0;
//        this.last = energy.length - 1;
        this.l = _l;
        int tempHalf = energy.length/2;
        participants = new ArrayList<>();
        for (int i = 0; i < energy.length; i++){
            if (i < tempHalf){
                participants.add(new Monk("Guan-Іn[" + String.valueOf(i) + "]", energy[i]));
            } else {
                participants.add(new Monk("Guan-Yan[" + String.valueOf(i-tempHalf) + "]", energy[i]));
            }
        }
    }

    public int getSize() { return this.participants.size(); }

    public String getMonksInfo(int position) { return this.participants.get(position).getMonksInfo(); }

    public boolean isWinner() {
        if (this.participants.size() > 0) {
            return this.participants.get(0).getWins() == 5;
        } else {
            return false;
        }
    }

    public ArrayList<Monk> getRivals(Thread thread) {
        l.lock();
        System.out.println(red(Thread.currentThread().getName()) + " asked to get rivals.");
        ArrayList<Monk> newRivals = new ArrayList<>();
        if (this.participants.size() >= 2){
            newRivals.add(this.participants.get(0));
            this.participants.remove(0);

            int tempWins = newRivals.get(0).getWins();
            boolean found = false;

            while (!found){
                for (int i = 0; i < this.participants.size(); i++){
                    if (this.participants.get(i).getWins() == tempWins){
                        newRivals.add(this.participants.get(i));
                        this.participants.remove(i);
                        found = true;
                        break;
                    }
                }
            }
            System.out.println(red(Thread.currentThread().getName()) + " got rivals.");
        }
        else {
            System.out.println(red(Thread.currentThread().getName()) + " didn't get rivals.");
        }

        l.unlock();
        return newRivals;
    }

    public void addWinner(Thread thread, Monk winner){
        l.lock();
        System.out.println(red(Thread.currentThread().getName()) + " is adding a winner " + winner.getName() + ".");
        winner.addWin();
        this.participants.add(winner);
        System.out.println(red(Thread.currentThread().getName()) + ": winner " + winner.getName() + " added.");
        l.unlock();
    }

    public void announceWinner(){
        l.lock();
        if (!this.winnerAnnounced){
            this.winnerAnnounced = true;
            System.out.println("The Winner is " + this.participants.get(0).getMonksInfo() + "!");
        }
        l.unlock();
    }
}
