package assign3_3;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Please Type How Many Salads To Prepare:");
		Scanner scan = new Scanner(System.in);
		final int numOfSaladsToPrepare = scan.nextInt();
		System.out.println("Preparing " + numOfSaladsToPrepare + " Salads...");

		// YOUR CODE HERE: use threads to prepare N salads (as the user requested)
		SlicerMachine machine = new SlicerMachine(numOfSaladsToPrepare);
		Thread cucumbersThread = new CucumbersThread(machine);
		Thread tomatoesThread = new TomatoesThread(machine);
		Thread onionsThread = new OnionThread(machine);
		Thread slicerThread = new SlicerThread(machine);

		cucumbersThread.start();
		tomatoesThread.start();
		onionsThread.start();
		slicerThread.start();

		try{
			cucumbersThread.join();
			tomatoesThread.join();
			onionsThread.join();
			slicerThread.join();
		} catch (InterruptedException e) {

		}



		System.out.println("Done");
		scan.close();
	}

}
