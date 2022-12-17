package helicopter;

import java.text.NumberFormat;

public class Helicopter {

    // Final Data-Members

    private static final int MAX_ALTITUDE = 5000;
    private static final int MIN_ALTITUDE = 0;
    private static final int MAX_FUEL_RANGE = 100;
    private static final int MIN_FUEL_RANGE = 0;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    //Private Data-Members
     private static int nextId = 82;
     private String id = "HEP12";
     private double fuelLevel ;
     private double altitude ;
     private boolean engineRunning = false;
    private boolean exploded;

    // Constructor

    public Helicopter(){
        this.id = id + nextId++;
        this.fuelLevel = getMaxFuelLevel();
        this.altitude = 0;
        this.engineRunning = false;
        this.exploded = false;
    };

    // Getters
    public String getId()
    {
        return id;
    }
    public double getFuelLevel()
    {
        return fuelLevel;
    }
    public double getAltitude()
    {
        return altitude;
    }
    public boolean isEngineRunning()
    {
        return engineRunning;
    }
    public boolean isExploded()
    {
        return exploded;
    };
    public double getFuelRate()
    {
        return 1.00 / 100.00;
    }
    public boolean isFuelEmpty()
    {
        return getFuelLevel() <= getMinFuelLevel();
    }

    public boolean isFuelFull()
    {
        return getFuelLevel() == MAX_FUEL_RANGE;
    }

    public boolean isLanded()
    {
        return getAltitude() == MIN_ALTITUDE;
    }

    public boolean isFlying()
    {
        return getAltitude() > MIN_ALTITUDE;
    }

    public double getMinFuelLevel()
    {
        return 0;
    }

    public double getMaxFuelLevel()
    {
        return 100;
    }
    public double getMinAltitude()
    {
        return 0;
    }
    public double getMaxAltitude()
    {
        return 5000;
    }
    // Setters
    public void setFuelLevel(double amount)
    {
        if(fuelLevel < getMinFuelLevel())
            fuelLevel = getMinFuelLevel();
        else if(fuelLevel > getMaxFuelLevel())
            fuelLevel = getMaxFuelLevel();

        this.fuelLevel = amount;

      /*  if (isFuelEmpty() && isFlying())
        {
            crash();
        }*/
    }

    // Can-Do-Action Getters
    public boolean canStartEngine()
    {
        return !engineRunning
                && isLanded()
                && !isFuelEmpty()
                && !exploded;
    }

    public void startEngine(){
        if(canStartEngine())
        {
            engineRunning = true;
        }
    }

    public boolean canStopEngine()
    {
        return isEngineRunning()
                && isLanded()
                && !exploded;
    }
    public void stopEngine()
    {
        if(canStopEngine())
            engineRunning = false;
    }

    public boolean canFly(){

        return isEngineRunning()
                && !exploded
                && !isFuelEmpty();

    }
    public void flyToAltitude(double altitude)
    {
         if(canFly())
         {

             if(altitude < getMinAltitude())
                 altitude = getMinAltitude();
             else if(altitude > getMaxAltitude())
                 altitude = getMaxAltitude();
            double offset = altitude - this.altitude;
            double distance = Math.abs(offset);
            double fuelBurned = distance * getFuelRate();
            //this.fuelLevel -= fuelBurned;
             setFuelLevel(getFuelLevel() - fuelBurned);
             this.altitude = altitude;
             if (isFuelEmpty() && isFlying())
             {
                 crash();
             }
         }


    }

    public void landing()
    {
        this.altitude = 0;
    }

    public boolean canRefuel(double amount)
    {
        return !engineRunning
                && !exploded
                && isLanded()
                && !isFuelFull()
                && amount > 0;
    }
    public void refuel (double amount)
    {
        if(canRefuel(amount))
        {
            double maxAmount = getMaxFuelLevel() - fuelLevel;
            if(amount > maxAmount)
                amount = maxAmount;
            setFuelLevel(fuelLevel + amount);
        }
    }

    protected void crash()
    {
        crashAndExplode();
    }

    protected final void crashAndExplode()
    {
        System.out.println("YOU CRASHED!");
        engineRunning = false;
        altitude = getMinAltitude();
        exploded = true;
    }

    protected final void crashWithoutExploding()
    {
        engineRunning = false;
        altitude = getMinAltitude();
    }


    // To String method
    @Override
    public String toString()
    {
        return "Helicopter #" + id;
    }

    public String displayHelicopter() // Why this method is not Override?
    {
        NumberFormat display = NumberFormat.getInstance();
        return toString()
               + ": " + (isEngineRunning() ? "Engine" + ANSI_GREEN + " ON" + ANSI_RESET:
                                              "Engine" + ANSI_RED + " OFF" + ANSI_RESET)
               + ", " + "Altitude = " + altitude
               + ", " + "Fuel Level = " + display.format(fuelLevel)
                + ", " + (exploded ? "(Exploded)" : "(Functional)");
    }
}


