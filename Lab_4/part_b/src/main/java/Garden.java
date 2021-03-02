public class Garden {
    private boolean lockR;
    private boolean lockW;
    private final String[][] plant;
    private int numOfReaders;

    private final String stateBad = "B";
    private final String stateGood = "G";

    Garden() {
        numOfReaders = 0;
        plant = new String[10][10];
        lockW = false;
        lockR = false;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                plant[i][j] = stateBad;
            }
        }
    }

    boolean getLockR() { return lockR; }
    boolean getLockW() { return lockW; }

    synchronized void unlockReader(String name) {
        numOfReaders -= 1;
        if (numOfReaders == 0) {
            lockR = false;
        }
        System.out.println("Thread " + name + " removes from readers.");
    }
    synchronized void unlockWriter(String name) {
        System.out.println("Thread " + name + " unlocks writer.");
        lockW = false;
    }
    synchronized boolean lockReaderWriter(boolean reader, boolean writer, String name){
        if (reader){
            numOfReaders += 1;
            lockR = true;
            System.out.println("Thread " + name + " adds to readers.");
            return true;
        } else if (writer) {
            System.out.println("Thread " + name + " tries to lock writer.");
            if (lockW) {
                System.out.println("Thread " + name + " didn't lock writer.");
                return false;
            } else {
                System.out.println("Thread " + name + " locked writer.");
                lockW = true;
                return true;
            }
        }
        return false;
    }

    synchronized void toWaterFlowers(int i, String name) {
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("Thread " + name + " is watering flowers.");
        for (int j = 0; j < 10; j++) { plant[i][j] = "G"; }
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("Thread " + name + " watered flowers.");
    }

    String getGardenInfo(String name) {
        System.out.println("Thread " + name + " asked to get Garden Info.");
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                answer.append(plant[i][j]).append(" ");
            }
            answer.append("\n");
        }
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("Thread " + name + " gets Garden Info.");
        return answer.toString();
    }

    synchronized void changeState(int i, int j, String name) {
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        if (plant[i][j].equals(stateGood)) {
            plant[i][j] = stateBad;
            System.out.println("Thread " + name + " is changing state of plant[" + String.valueOf(i) + "][" + String.valueOf(j) + "] to state B.");
        } else if (plant[i][j].equals(stateBad)) {
            plant[i][j] = stateGood;
            System.out.println("Thread " + name + " is changing state of plant[" + String.valueOf(i) + "][" + String.valueOf(j) + "] to state G.");
        }
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
