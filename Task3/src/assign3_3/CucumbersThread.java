package assign3_3;

import java.beans.IntrospectionException;
import java.util.Random;

public class CucumbersThread extends Thread {
    private final SlicerMachine machine;
    private final Random rand = new Random();

    public CucumbersThread(SlicerMachine machine) {
        this.machine = machine;
        setName("Cucumbers");
    }

    @Override
    public void run() {
        while(true){
            try {
                machine.addOneCucumber();
                Thread.sleep(30 + rand.nextInt(120));
            } catch (IntrospectionException e) {
                return;
            }
        }
    }

}
