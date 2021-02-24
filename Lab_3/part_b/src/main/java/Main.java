public class Main {
    public static void main(String[] args) {
        Salon salon = new Salon();
        Thread thread = new Thread(salon);
        thread.start();
    }
}
