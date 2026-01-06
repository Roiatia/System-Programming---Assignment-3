package assign3_3;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please Type How Many Salads To Prepare:");
		int numOfSaladsToPrepare = 0;


		while(true) {
			String line = scan.nextLine();
			if (line.isEmpty()) {
				System.out.println("Input empty, try again!");
				continue;
			}
			try {
				numOfSaladsToPrepare = Integer.parseInt(line);
				if (numOfSaladsToPrepare <= 0) {
					System.out.println("Number must be positive, try again!");
					continue;
				}
				break;
			} catch(NumberFormatException e) {
				System.out.println("Invalid number, try again!");
			}
		}
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
