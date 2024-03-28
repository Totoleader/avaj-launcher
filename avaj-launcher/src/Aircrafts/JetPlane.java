package Aircrafts;

import simulation.WeatherTower;

public class JetPlane extends Aircraft implements Flyable{
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates)
    {
        super(name, coordinates);
    }

    public void updateConditions()
    {
        String currentWeather = weatherTower.getWeather(coordinates);

        if (this.coordinates.getHeight() <= 0)
        {
            System.out.println("JetPlane#" + name + "(" + id + "): Unregistered to the weather tower.");
            weatherTower.unregister(this);
            return ;
        }

        switch (currentWeather) {
            case "SUN":
                coordinates.addLatitude(10);
                coordinates.addHeight(2);
                System.out.println("JetPlane#" + name + "(" + id + "): SunJet.  " + coordinates.getLongitude() + " : " + coordinates.getLatitude() + " : " + coordinates.getHeight());
                break;
            case "RAIN":
                coordinates.addLatitude(5);
                System.out.println("JetPlane#" + name + "(" + id + "): RainJet.  " + coordinates.getLongitude() + " : " + coordinates.getLatitude() + " : " + coordinates.getHeight());
                break;
            case "FOG":
                coordinates.addLatitude(1);
                System.out.println("JetPlane#" + name + "(" + id + "): FogJet.  " + coordinates.getLongitude() + " : " + coordinates.getLatitude() + " : " + coordinates.getHeight());
                break;
            case "SNOW":
                coordinates.addHeight(-7);
                System.out.println("JetPlane#" + name + "(" + id + "): SnowJet.  " + coordinates.getLongitude() + " : " + coordinates.getLatitude() + " : " + coordinates.getHeight());
                break;
            default:
                break;
        }
    }

    public void registerTower(WeatherTower weatherTower)
    {
        System.out.println("JetPlane#" + name + "(" + id + "): Registered to the weather tower.");
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }
}
