package Weatherprovider;

import Aircrafts.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private String[] weather = {"SNOW", "FOG", "SUN", "RAIN"};
    
    private WeatherProvider()
    {
        
    }

    public static WeatherProvider getProvider() {
        if (weatherProvider == null)
            return new WeatherProvider();
        else
            return weatherProvider;
            
    }

    public String getCurrentWeather(Coordinates coordinates)
    {
        int addup = (coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude() + 7) / 3;
        return weather[addup % 4];
    }
}
