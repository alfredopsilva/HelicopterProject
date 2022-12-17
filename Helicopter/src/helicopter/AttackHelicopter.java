package helicopter;

public class AttackHelicopter extends Helicopter{

    public AttackHelicopter(){
        super();
    }

    @Override
    public String toString()
    {
        return "Attack" + super.toString();
    }

    @Override
    public double getFuelRate()
    {
        return .3;
    }

    @Override
    public double getMaxFuelLevel()
    {
        return 500;
    }

    @Override
    public double getMaxAltitude()
    {
        return 10000;
    }

    @Override
    protected void crash()
    {
        if(getAltitude() > 1000)
            crashAndExplode();
        else
            crashWithoutExploding();
    }
}
