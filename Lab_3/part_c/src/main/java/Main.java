public class Main {
    public static void main(String[] args) {
        Table table = new Table();

        Mediator mediator = new Mediator(table);
        SmokerPaper smokerPaper = new SmokerPaper(table);
        SmokerMatches smokerMatches = new SmokerMatches(table);
        SmokerTobacco smokerTobacco = new SmokerTobacco(table);
    }
}
