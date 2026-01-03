package assign3_3;

public class SlicerMachine {
	
	int numOfCucumbers = 0;
	int numOfTomatoes = 0;
	int numOfOnions = 0;
	int numOfPreparedSalads = 0;
	
	final int cucumbersNeededForOneSalad = 5;
	final int tomatoesNeededForOneSalad = 3;
	final int onionsNeededForOneSalad = 1; //changed from 3 to 1

	private final int targetSalads;
	private boolean done = false;

	public SlicerMachine(int targetSalads) {
		this.targetSalads = targetSalads;
	}
	
	// add one cucumber into the slicer chamber
	public synchronized void addOneCucumber() throws InterruptedException {
		while (!done && numOfCucumbers >= cucumbersNeededForOneSalad) {
			wait();
		}
		if (done) throw new InterruptedException();

		System.out.println("adding one cucumber to the machine");
		numOfCucumbers++;
		notifyAll();
	}

	// add one onion into the slicer chamber
	public synchronized void addOneOnion() throws InterruptedException {
		while (!done && numOfOnions >= onionsNeededForOneSalad) {
			wait();
		}
		if (done) throw new InterruptedException();

		System.out.println("adding one onion to the machine");
		numOfOnions++;
		notifyAll();
	}


	// add one tomato into the slicer chamber
	public synchronized void addOneTomato() throws InterruptedException {
		while (!done && numOfTomatoes >= tomatoesNeededForOneSalad) {
			wait();
		}
		if (done) throw new InterruptedException();

		System.out.println("adding one tomato to the machine");
		numOfTomatoes++;
		notifyAll();
	}
	
	// if there are enough vegetables in the slicer
	// chamber, make another salad
	public synchronized void sliceVegetables() throws InterruptedException {
		while (!done && !canMakeSalad()) {
			wait();
		}
		if (done) throw new InterruptedException();

		makeNewSalad();

		if (numOfPreparedSalads >= targetSalads) {
			done = true;
			notifyAll(); // wake anyone waiting so they can exit
			return; // normal completion
		}

		notifyAll();
	}

	private boolean canMakeSalad() {
		return numOfOnions >= onionsNeededForOneSalad
				&& numOfCucumbers >= cucumbersNeededForOneSalad
				&& numOfTomatoes >= tomatoesNeededForOneSalad;
	}

	private void makeNewSalad() {
		System.out.println("== preparing one more salad ==");
		numOfPreparedSalads++; 
		// update stock
		numOfTomatoes = numOfTomatoes - tomatoesNeededForOneSalad;
		numOfCucumbers = numOfCucumbers - cucumbersNeededForOneSalad;
		numOfOnions = numOfOnions - onionsNeededForOneSalad;
	}	
	
	public synchronized int getNumOfPreparedSalads() {
		return numOfPreparedSalads;
	}
}
