package Aircrafts;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude, int height)
    {
        this.longitude = (longitude < 1) ? 0 : latitude;
        this.latitude = (latitude < 1) ? 0 : latitude;
        this.height = (height < 1) ? 0 : height;
        this.height = (height > 100) ? 100 : height;

    }

    public int getLongitude()
    {
        return longitude;
    }

    public int getLatitude()
    {
        return latitude;
    }

    public int getHeight()
    {
        return height;
    }

    public void addLatitude(int add)
    {
        if (this.latitude + add > 0)
            this.latitude += add;
        else
            this.latitude = 0;
    }
    public void addLongitude(int add)
    {
        if (this.longitude + add > 0)
            this.longitude += add;
        else
            this.longitude = 0;
    }
    public void addHeight(int add)
    {
        if (this.height + add > 100)
            this.height = 100;
        else if (this.height + add < 0)
            this.height = 0;
        else 
            this.height += add;
    }
}
