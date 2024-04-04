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
    static Set<String> name_set = new HashSet<String>();
    static AircraftFactory factory = new AircraftFactory();
    
    private Simulation()
    {
    }
    
    private static void error()
    {
        System.exit(0);
    }

    private static void initWeatherSet()
    {
        set.add("JetPlane");
        set.add("Helicopter");
        set.add("Baloon");
    }
    
    private static boolean lineValid(String line)
    {
        String splitted_line[] = line.split(" ");
        
        if (splitted_line.length != 5)
        {
            System.out.println("Error: Wrong number of args in line.");
            return false;
        }
        else if (!set.contains(splitted_line[0]))
        {
            System.out.println("Error: Aircraft type does not exist.");
            return false;
        }
        else if (name_set.contains(splitted_line[1]))
        {
            System.out.println("Error: Aircraft name duplicate.");
            return false;
        }
        name_set.add(splitted_line[1]);
        try
        {
            if(Integer.parseInt(splitted_line[2]) < 0
                || Integer.parseInt(splitted_line[3]) < 0
                || Integer.parseInt(splitted_line[4]) < 0)
            {
                System.out.print("Coordinate value out of bounds. ");
                throw new NumberFormatException("");
            }
        }
        catch(NumberFormatException e)
        {
            System.out.println("Error: Bad coordinates input.");
            return false;
        }
        return true;
    }

    private static void addAricraft(String data)
    {
        String info[] = data.split(" ");
        Flyable newFlyable = factory.newAircraft(info[0], info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[4]));
        aircrafts.add(newFlyable);
    }

    private static void readFile()
    {
        String data;
        
        try
        {
            data = myReader.nextLine();
            iterations = Integer.parseInt(data);
            if (iterations < 0)
                throw new NumberFormatException("");
        }
        catch(NumberFormatException e)
        {
            System.out.println("Error: First line must be number of iterations in the simulation (positive int).");
            error();
        }
        catch(Exception e)
        {
            System.out.println("Error: empty file.");
            error();
        }

        while (myReader.hasNextLine()) 
        {
            data = myReader.nextLine();
            if (!lineValid(data))
                error();
            else
                addAricraft(data);
        }
    }


    private static void run()
    {
        WeatherTower tow = new WeatherTower(iterations);

        for (Flyable flyable : aircrafts)
            flyable.registerTower(tow);

        tow.changeWeather();
    }

    private static void openFile(String[] args)
    {
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
    }    
    
    public static void main(String[] args)
    {
        initWeatherSet();

        if (args.length != 1)
            System.out.println("Invalid Input.");
        
        openFile(args);
        readFile();
        run();
    }
}

