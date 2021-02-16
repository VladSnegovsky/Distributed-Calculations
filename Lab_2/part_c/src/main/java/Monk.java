public class Monk {
    private String Name;
    private int Energy;
    private int Wins;

    Monk(String _Name, int _Energy){
        this.Name = _Name;
        this.Energy = _Energy;
    }

    public String getName(){ return this.Name; }

    public int getWins() { return this.Wins; }

    public int getEnergy() { return this.Energy; }

    public void addWin() { this.Wins++; }

    public String getMonksInfo() {
        return this.getName() + " [" + String.valueOf(this.getEnergy()) + "], "
                + String.valueOf(this.getWins()) + " wins.";
    }
}
