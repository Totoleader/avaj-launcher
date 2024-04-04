package Aircrafts;

import simulation.WeatherTower;

public class Baloon extends Aircraft implements Flyable{
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates)
    {
        super(name, coordinates);
    }

    public void updateConditions()
    {
        String currentWeather = weatherTower.getWeather(coordinates);
        if (this.coordinates.getHeight() <= 0)
        {
            System.out.println("Baloon#" + name + "(" + id + "): Unregistered to the weather tower.");
            weatherTower.unregister(this);
            return ;
        }

        switch (currentWeather) {
            case "SUN":
                coordinates.addLongitude(2);
                coordinates.addHeight(4);
                System.out.println("Baloon#" + name + "(" + id + "): SunBaloon.  " + coordinates.getLongitude() + " : " + coordinates.getLatitude() + " : " + coordinates.getHeight());
                break;
            case "RAIN":
                coordinates.addHeight(-5);
                System.out.println("Baloon#" + name + "(" + id + "): RainBaloon.  " + coordinates.getLongitude() + " : " + coordinates.getLatitude() + " : " + coordinates.getHeight());
                break;
            case "FOG":
                coordinates.addHeight(-3);
                System.out.println("Baloon#" + name + "(" + id + "): FogBaloon.  " + coordinates.getLongitude() + " : " + coordinates.getLatitude() + " : " + coordinates.getHeight());
                break;
            case "SNOW":
                coordinates.addHeight(-15);
                System.out.println("Baloon#" + name + "(" + id + "): SnowBaloon.  " + coordinates.getLongitude() + " : " + coordinates.getLatitude() + " : " + coordinates.getHeight());
                break;
            default:
                break;
        }
    }

    public void registerTower(WeatherTower weatherTower)
    {
        System.out.println("Baloon#" + name + "(" + id + "): Registered to the weather tower.");
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }
}
