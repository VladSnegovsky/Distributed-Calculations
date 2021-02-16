public class BeeGroup implements Runnable {
    Thread thread;
    Forest forest;
    BeeManager beeManager;
    int line;
    volatile boolean work;

    String red(String str){
        if (this.thread.getName() == "First Group"){
            return (char) 27 + "[32m" + str + (char) 27 + "[0m";
        }
        if (this.thread.getName() == "Second Group"){
            return (char) 27 + "[33m" + str + (char) 27 + "[0m";
        }
        if (this.thread.getName() == "Third Group"){
            return (char) 27 + "[34m" + str + (char) 27 + "[0m";
        }
        return (char) 27 + "[31m" + str + (char) 27 + "[0m";
    }

    BeeGroup(Forest _forest, BeeManager _beeManager, String name){
        this.forest = _forest;
        this.work = true;
        this.beeManager = _beeManager;
        this.thread = new Thread(this);
        this.thread.setName(name);
        this.thread.start();
    }

    @Override
    public void run() {
        System.out.println(red(this.thread.getName()) + " of bees started searching!");
        while (this.work){
            this.line = this.beeManager.get_new_line(this);
            if (!this.work)
                break;
            System.out.println(red(this.thread.getName()) + " got " + red(String.valueOf(this.line)) + " line of forest.");
            for (int i = 0; i < 10; i++){
                System.out.println(red(this.thread.getName()) + " is searching in [" + String.valueOf(this.line) + "," + String.valueOf(i) + "] sector.");
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.forest.location[line][i] == 1){
                    System.out.println(red(this.thread.getName()) + " " + red("found a bear") + " in [" + String.valueOf(this.line) + "," + String.valueOf(i) + "] sector!");
                    this.beeManager.found();
                    break;
                }
            }
        }
    }

    void stop_searching(){
        this.work = false;
    }
}
