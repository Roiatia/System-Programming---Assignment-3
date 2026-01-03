package assign3_3;

import java.util.Random;

public class OnionThread extends Thread {
    private final Random rand = new Random();
    private final SlicerMachine machine;

    public OnionThread(SlicerMachine machine) {
        this.machine = machine;
        setName("Onions");
    }

    @Override
    public void run() {
        while (true) {
            try {
                machine.addOneOnion();
                Thread.sleep(40 + rand.nextInt(160));
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
