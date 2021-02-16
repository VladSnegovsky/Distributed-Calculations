public class BeeManager {
    private final Forest forest;
    private boolean bear_is_found;
    private int line;

    static String red(String str){
        return (char) 27 + "[31m" + str + (char) 27 + "[0m";
    }

    BeeManager(Forest _forest){
        this.forest = _forest;
        this.bear_is_found = false;
        this.line = -1;
    }

    void start_searching(){
        BeeGroup beeGroup1 = new BeeGroup(this.forest, this, "First Group");
        BeeGroup beeGroup2 = new BeeGroup(this.forest, this, "Second Group");
        BeeGroup beeGroup3 = new BeeGroup(this.forest, this, "Third Group");
    }

    synchronized int get_new_line(BeeGroup beeGroup){
        if (bear_is_found){
            System.out.println(red(beeGroup.thread.getName() + " stopped searching") + " because the bear was already found.");
            beeGroup.stop_searching();
            return 0;
        }
        else {
            if (this.line >= 9){
                System.out.println(red(beeGroup.thread.getName() + " stopped searching") + " because there are no new sectors.");
                beeGroup.stop_searching();
                return 0;
            }
            else {
                this.line += 1;
                return this.line;
            }
        }
    }

    void found(){
        System.out.println(red("Bee Manager found out that the bear was found"));
        this.bear_is_found = true;
    }
}
