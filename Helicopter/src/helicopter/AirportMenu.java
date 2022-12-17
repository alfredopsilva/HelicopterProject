package helicopter;

import java.util.ArrayList;
import java.util.Random;

import utility.console.ConsoleMenu;

public class AirportMenu {
    private final ArrayList<Helicopter> helicopters;
    private final ConsoleMenu AirportMenu;
    public AirportMenu()
    {
        // Adding 5 Helicopters to the ArrayList and *technical name for the 16 line*
        helicopters = new ArrayList<>();
        helicopters.add(createRandomHelicopter());
        helicopters.add(createRandomHelicopter());
        helicopters.add(createRandomHelicopter());
        helicopters.add(createRandomHelicopter());
        helicopters.add(createRandomHelicopter());


        // Adding Options for Menu
        ArrayList<String> options = new ArrayList<String>();
        options.add("> Add Helicopter");
        options.add("> Add AttackHelicopter");
        options.add("> Remove Helicopter");
        options.add("> Control Helicopter");
        options.add("> Land All Helicopters");
        options.add("> Display All Helicopter");
        options.add("> Exit");

        //Creating Menu List
        AirportMenu = new ConsoleMenu("AIRPORT MENU", "Select an Option above : ", options);
    }

   public void start() {

        displayAllHelicopters();
        while(true)
        {
            int selection = AirportMenu.displayAndGetSelection();
            switch(selection) {
                case 1: addHelicopter();              break;
                case 2: addAttackHelicopter();        break;
                case 3: removeHelicopter();           break;
                case 4: controlHelicopter();          break;
                case 5: landAllHelicopter();          break;
                case 6: displayAllHelicopters();      break;
                case 7: return;
            }
            displayAllHelicopters();
        }
    }

     private static Helicopter createRandomHelicopter(){
        Random random = new Random();
        double low = 0;
        double high = 1;
        double generatingRandomNumber =  random.nextDouble(high-low) + low;

        if(generatingRandomNumber <=.5){
            return new Helicopter();
        }
        else{
            return new AttackHelicopter();
        }
    }

    public void addHelicopter()
    {
        helicopters.add(new Helicopter());
    }

    public void addAttackHelicopter()
    {
        helicopters.add(new AttackHelicopter());
    }
    public void removeHelicopter()
    {
        ArrayList<String> options = new ArrayList<String>();
        for(Helicopter helicopter : helicopters) {
            options.add(helicopter.toString());
        }
        options.add("Exit");

        ConsoleMenu HelicopterMenu = new ConsoleMenu("Remove an Helicopter", "Select One Helicopter:", options);
        int index = HelicopterMenu.displayAndGetSelection() - 1;
        if(index < helicopters.size()){
            helicopters.remove(index);
        }
        else if(index == helicopters.size() + 1)
        {
            return;
        }
    }

    public void controlHelicopter()
    {
        ArrayList<String> options = new ArrayList<String>();
        for(Helicopter helicopter : helicopters) {
            options.add(helicopter.toString());
        }
        options.add("Exit");

        ConsoleMenu HelicopterMenu = new ConsoleMenu("Control an Helicopter", "Select One Helicopter:", options);
        int index = HelicopterMenu.displayAndGetSelection() - 1;
        if(index < helicopters.size()){
            HelicopterMenu menu = new HelicopterMenu(helicopters.get(index));
            menu.start();
        }
        else if(index == helicopters.size() + 1)
        {
            return;
        }


    }

    public void landAllHelicopter()
    {
        for(Helicopter helicopter : helicopters)
        {
            helicopter.landing();
        }
    }

    public void displayAllHelicopters()
    {
        for(Helicopter helicopter : helicopters)
        {
            System.out.println(helicopter.displayHelicopter());
        }
    }

}
