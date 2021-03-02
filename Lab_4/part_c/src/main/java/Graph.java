import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class City {
    private String Name;
    private int cost;
    private final List<City> roads;

    City(String Name, int cost) {
        this.Name = Name;
        this.cost = cost;
        this.roads = new ArrayList<City>();
    }

    void addRoad(City toCity) { this.roads.add(toCity); }

    void removeRoad(City toCity) { this.roads.remove(toCity); }

    int getCost() { return cost; }

    String getName() { return Name; }

    boolean checkRoad(City toCity) { return roads.contains(toCity); }

    void changeCost(int newCost) { cost = newCost; }

    void removeRoadsOfCity() {
        for (City road : this.roads) {
            road.removeRoad(this);
        }
    }
}

public class Graph {
    private final List<City> Cities;
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    Graph() {
        Cities = new ArrayList<City>();
    }

    void addCity(City newCity, String ThreadName) {
        System.out.println("Thread " + ThreadName + " locks Write.");
        try { Thread.sleep(1000); }
        catch (InterruptedException e) { e.printStackTrace(); }
        Cities.add(newCity);
        System.out.println("Thread " + ThreadName + " unlocks Write.");
    }

    void removeCity(City removeCity, String ThreadName) {
        System.out.println("Thread " + ThreadName + " locks Write.");
        try { Thread.sleep(1000); }
        catch (InterruptedException e) { e.printStackTrace(); }
        Cities.remove(removeCity);
        removeCity.removeRoadsOfCity();
        removeCity = null;
        System.out.println("Thread " + ThreadName + " unlocks Write.");
    }

    int findCostOfWay(City firstCity, City secondCity, String ThreadName) {
        System.out.println("Thread " + ThreadName + " locks Read.");
        try { Thread.sleep(1000); }
        catch (InterruptedException e) { e.printStackTrace(); }
        if (firstCity.checkRoad(secondCity)) {
            System.out.print("");
            System.out.println("Thread " + ThreadName + " unlocks Read.");
            return firstCity.getCost() + secondCity.getCost();
        } else {
            System.out.print("");
            System.out.println("Thread " + ThreadName + " unlocks Read.");
            return 0;
        }
    }

    void connectCities(City firstCity, City secondCity, String ThreadName) {
        System.out.println("Thread " + ThreadName + " locks Write.");
        try { Thread.sleep(1000); }
        catch (InterruptedException e) { e.printStackTrace(); }
        firstCity.addRoad(secondCity);
        secondCity.addRoad(firstCity);
        System.out.println("Thread " + ThreadName + " unlocks Write.");
    }

    void disconnectCities(City firstCity, City secondCity, String ThreadName) {
        System.out.println("Thread " + ThreadName + " locks Write.");
        try { Thread.sleep(1000); }
        catch (InterruptedException e) { e.printStackTrace(); }
        firstCity.removeRoad(secondCity);
        secondCity.removeRoad(firstCity);
        System.out.println("Thread " + ThreadName + " unlocks Write.");
    }

    City getCity(String Name, String ThreadName) {
        System.out.println("Thread " + ThreadName + " locks Read.");
        try { Thread.sleep(1000); }
        catch (InterruptedException e) { e.printStackTrace(); }
        for (City city : Cities) {
            if (city.getName().equals(Name))
                System.out.print("");
                System.out.println("Thread " + ThreadName + " unlocks Read.");
                return city;
        }
        System.out.print("");
        System.out.println("Thread " + ThreadName + " unlocks Read.");
        return new City("Error", 0);
    }

    void changeCost(City city, int cost, String ThreadName) {
        System.out.println("Thread " + ThreadName + " locks Write.");
        try { Thread.sleep(1000); }
        catch (InterruptedException e) { e.printStackTrace(); }
        city.changeCost(cost);
        System.out.println("Thread " + ThreadName + " unlocks Write.");
    }





    void addCityAuto(City newCity) {
        Cities.add(newCity);
    }
    void connectCitiesAuto(City firstCity, City secondCity) {
        firstCity.addRoad(secondCity);
        secondCity.addRoad(firstCity);
    }
}
