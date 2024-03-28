package simulation;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Set;

import Aircrafts.AircraftFactory;
import Aircrafts.Flyable;

public class Simulation {
    static Scanner myReader;
    static int iterations = 0;
    static private ArrayList<Flyable> aircrafts = new ArrayList<>();
    static Set<String> set = new HashSet<String>();
    static AircraftFactory factory = new AircraftFactory();
    
    private Simulation()
    {
    }

    private static void error()
    {
        System.out.println("Bad Input.");
        System.exit(0);
    }

    private static boolean lineValid(String line)
    {
        String splitted_line[] = line.split(" ");
    
        if (splitted_line.length != 5 || !set.contains(splitted_line[0]))
        {
            return false;
        }
        try
        {
            Integer.parseInt(splitted_line[2]);
            Integer.parseInt(splitted_line[3]);
            Integer.parseInt(splitted_line[4]);
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static void initWeatherSet()
    {
        set.add("JetPlane");
        set.add("Helicopter");
        set.add("Baloon");
    }
    
    private static void readFile()
    {
        String data;
        
        data = myReader.nextLine();
        try
        {
            iterations = Integer.parseInt(data);
        }
        catch(NumberFormatException e)
        {
            error();
        }

        while (myReader.hasNextLine()) 
        {
            data = myReader.nextLine();
            if (!lineValid(data))
            {
                error();
            }
            else
            {
                String info[] = data.split(" ");
                Flyable newFlyable = factory.newAircraft(info[0], info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[4]));
                aircrafts.add(newFlyable);
            }
        }
    }


    private static void run()
    {
        WeatherTower tow = new WeatherTower(iterations);

        for (Flyable flyable : aircrafts)
            flyable.registerTower(tow);

        tow.changeWeather();
    }
    
    
    public static void main(String[] args)
    {
        initWeatherSet();

        if (args.length != 1)
            System.out.println("Invalid Input.");
        
        try
        {
            File myObj = new File(args[0]);
            myReader = new Scanner(myObj);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Input file not found");
            System.exit(0);
        }
        readFile();
        run();
    }
}

