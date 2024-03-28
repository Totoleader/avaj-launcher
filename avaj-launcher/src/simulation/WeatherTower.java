package simulation;

import Aircrafts.Coordinates;
import Weatherprovider.WeatherProvider;
import Weathertower.Tower;

public class WeatherTower extends Tower{

    public WeatherTower(int iterations)
    {
        super(iterations);
    }

    public String getWeather(Coordinates coordinates)
    {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    void changeWeather()
    {
        conditionsChanged();
    }
}
