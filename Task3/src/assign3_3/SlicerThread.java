package assign3_3;

public class SlicerThread extends Thread {
	private final SlicerMachine machine;

    public SlicerThread(SlicerMachine machine) {
        this.machine = machine;
        setName("Slicer");
    }
    @Override
    public void run() {
        while (true) {
            try {
                machine.sliceVegetables();
              //  Thread.sleep(50);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
	

}
