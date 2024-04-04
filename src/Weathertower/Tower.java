package Weathertower;

import java.util.ArrayList;

import Aircrafts.Flyable;

public class Tower {
    private ArrayList<Flyable> observers;
    private ArrayList<Flyable> toUnsub;
    protected int iterations;

    public Tower(int iterations)
    {
        observers = new ArrayList<>();
        toUnsub = new ArrayList<>();
        this.iterations = iterations;
    }

    public void register(Flyable flyable)
    {
        observers.add(flyable);
    }
    public void unregister(Flyable flyable)
    {
        toUnsub.add(flyable);
    }


    protected void conditionsChanged()
    {

        while (iterations > 0) 
        {
            for (Flyable flyable : observers)
                flyable.updateConditions();
            
            for (Flyable flyable : toUnsub)
                observers.remove(flyable);
            
            toUnsub.clear();
            if (observers.isEmpty())
            {
                System.out.println("\n*** All Aircrafts have landed with " + iterations + " iterations left ***\n");
                break;
            }
            iterations--;
        }
        if (!observers.isEmpty())
            System.out.println("\n*** Simulation over, no iterations left ***\n");
    }
}
