package assign3_3;

import java.util.Random;

public class TomatoesThread extends Thread {
private final Random rand = new Random();
private final SlicerMachine machine;

public TomatoesThread(SlicerMachine machine) {
    this.machine = machine;
    setName("Tomatoes");
}

@Override
public void run() {
    while (true) {
        try {
            machine.addOneTomato();
            Thread.sleep(30 + rand.nextInt(120));
        } catch (InterruptedException e) {
            return;
        }
    }
}
}
