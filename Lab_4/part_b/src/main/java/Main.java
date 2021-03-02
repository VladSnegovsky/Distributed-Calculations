public class Main {
    public static void main(String[] args) {
        Garden garden = new Garden();
        Gardener gardener = new Gardener(garden);
        Nature nature = new Nature(garden);
        Monitor1 monitor1 = new Monitor1(garden);
        Monitor2 monitor2 = new Monitor2(garden);
    }
}
