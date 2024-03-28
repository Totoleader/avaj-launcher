package Aircrafts;

import simulation.WeatherTower;

public class Helicopter extends Aircraft implements Flyable{
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates)
    {
        super(name, coordinates);
    }

    public void updateConditions()
    {
        String currentWeather = weatherTower.getWeather(coordinates);

        if (this.coordinates.getHeight() <= 0)
        {
            System.out.println("Helicopter#" + name + "(" + id + "): Unregistered to the weather tower.");
            weatherTower.unregister(this);
            return ;
        }
        
        switch (currentWeather) {
            case "SUN":
                coordinates.addLongitude(10);
                coordinates.addHeight(2);
                System.out.println("Helicopter#" + name + "(" + id + "): Suncopter.  " + coordinates.getLongitude() + " : " + coordinates.getLatitude() + " : " + coordinates.getHeight());
                break;
            case "RAIN":
                coordinates.addLongitude(5);
                System.out.println("Helicopter#" + name + "(" + id + "): Raincopter.  " + coordinates.getLongitude() + " : " + coordinates.getLatitude() + " : " + coordinates.getHeight());
                break;
            case "FOG":
                coordinates.addLongitude(1);
                System.out.println("Helicopter#" + name + "(" + id + "): Fogcopter.  " + coordinates.getLongitude() + " : " + coordinates.getLatitude() + " : " + coordinates.getHeight());
                break;
            case "SNOW":
                coordinates.addHeight(-12);
                System.out.println("Helicopter#" + name + "(" + id + "): Snowcopter.  " + coordinates.getLongitude() + " : " + coordinates.getLatitude() + " : " + coordinates.getHeight());
                break;
            default:
                break;
        }
    }

    public void registerTower(WeatherTower weatherTower)
    {
        System.out.println("Helicopter#" + name + "(" + id + "): Registered to the weather tower.");
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }
}
