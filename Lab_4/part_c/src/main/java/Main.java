public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        City cityKyiv = new City("Kyiv", 80);
        City cityLviv = new City("Lviv", 45);
        City cityKharkiv = new City("Kharkiv", 60);
        City cityLondon = new City("London", 170);
        City cityLosAngeles = new City("Los Angeles", 140);
        graph.addCityAuto(cityKyiv);
        graph.addCityAuto(cityLviv);
        graph.addCityAuto(cityKharkiv);
        graph.addCityAuto(cityLondon);
        graph.addCityAuto(cityLosAngeles);
        graph.connectCitiesAuto(cityKyiv, cityLviv);
        graph.connectCitiesAuto(cityKyiv, cityLosAngeles);
        graph.connectCitiesAuto(cityKharkiv, cityLondon);

        ThreadAddDeleteCity threadAddDeleteCity = new ThreadAddDeleteCity(graph);
        ThreadAddDeleteRoad threadAddDeleteRoad = new ThreadAddDeleteRoad(graph);
        ThreadChangeCost threadChangeCost = new ThreadChangeCost(graph);
        ThreadGetCost threadGetCost = new ThreadGetCost(graph, "Los Angeles", "Kyiv");
        ThreadGetCost threadGetCost1 = new ThreadGetCost(graph, "Los Angeles", "London");
    }
}
