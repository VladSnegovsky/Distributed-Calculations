import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

public class ListOfMonks extends RecursiveTask<Monk> {
    private final ArrayList<Monk> participants;
//    private final boolean winnerAnnounced = false;
    private final int first;
    private final int last;

    lampord.LampordLock l;

//    static private String red(String str){
//        return (char) 27 + "[31m" + str + (char) 27 + "[0m";
//    }

    ListOfMonks(ArrayList<Monk> _participants, lampord.LampordLock _l, int _first, int _last) {
        this.first = _first;
        this.last = _last;
        this.l = _l;
        this.participants = _participants;
    }

    Monk determineWinner(Monk firstMonk, Monk secondMonk) {
        if (firstMonk.getEnergy() > secondMonk.getEnergy()) {
            firstMonk.addWin();
            System.out.println("{" + firstMonk.getMonksInfo() + "} wins {" + secondMonk.getMonksInfo() + "}");
            return firstMonk;
        }
        else if (firstMonk.getEnergy() < secondMonk.getEnergy()) {
            secondMonk.addWin();
            System.out.println("{" + secondMonk.getMonksInfo() + "} wins {" + firstMonk.getMonksInfo() + "}");
            return secondMonk;
        }
        else {
            int num = (int) (Math.random() * 1);
            if (num == 1) {
                firstMonk.addWin();
                System.out.println("{" + firstMonk.getMonksInfo() + "} wins {" + secondMonk.getMonksInfo() + "}");
                return firstMonk;
            }
            else {
                secondMonk.addWin();
                System.out.println("{" + secondMonk.getMonksInfo() + "} wins {" + firstMonk.getMonksInfo() + "}");
                return secondMonk;
            }
        }
    }

    @Override
    protected Monk compute() {
        if (last - first == 0){
            return determineWinner(participants.get(first), participants.get(last));
        }

        ListOfMonks leftWinner = new ListOfMonks(participants, l, first, (first + last) / 2);
        leftWinner.fork();

        ListOfMonks rightWinner = new ListOfMonks(participants, l, (last + first) / 2 + 1, last);
        rightWinner.fork();

        Monk left = leftWinner.join();
        Monk right = rightWinner.join();

        return determineWinner(left, right);
    }

//    public int getSize() { return this.participants.size(); }
//
//    public String getMonksInfo(int position) { return this.participants.get(position).getMonksInfo(); }
//
//    public boolean isWinner() {
//        if (this.participants.size() > 0) {
//            return this.participants.get(0).getWins() == 5;
//        } else {
//            return false;
//        }
//    }
//
//    public ArrayList<Monk> getRivals(Thread thread) {
//        l.lock();
//        System.out.println(red(Thread.currentThread().getName()) + " asked to get rivals.");
//        ArrayList<Monk> newRivals = new ArrayList<>();
//        if (this.participants.size() >= 2){
//            newRivals.add(this.participants.get(0));
//            this.participants.remove(0);
//
//            int tempWins = newRivals.get(0).getWins();
//            boolean found = false;
//
//            while (!found){
//                for (int i = 0; i < this.participants.size(); i++){
//                    if (this.participants.get(i).getWins() == tempWins){
//                        newRivals.add(this.participants.get(i));
//                        this.participants.remove(i);
//                        found = true;
//                        break;
//                    }
//                }
//            }
//            System.out.println(red(Thread.currentThread().getName()) + " got rivals.");
//        }
//        else {
//            System.out.println(red(Thread.currentThread().getName()) + " didn't get rivals.");
//        }
//
//        l.unlock();
//        return newRivals;
//    }
//
//    public void addWinner(Thread thread, Monk winner){
//        l.lock();
//        System.out.println(red(Thread.currentThread().getName()) + " is adding a winner " + winner.getName() + ".");
//        winner.addWin();
//        this.participants.add(winner);
//        System.out.println(red(Thread.currentThread().getName()) + ": winner " + winner.getName() + " added.");
//        l.unlock();
//    }
//
//    public void announceWinner(){
//        l.lock();
//        if (!this.winnerAnnounced){
//            this.winnerAnnounced = true;
//            System.out.println("The Winner is " + this.participants.get(0).getMonksInfo() + "!");
//        }
//        l.unlock();
//    }
}
