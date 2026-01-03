package assig3_2;

public class Main
{
	public static void main(String[] args) throws InterruptedException
	{
		GamePlay game = new GamePlay();

		Gamer player1 = new Gamer(game);
		Gamer player2 = new Gamer(game);

		Judge judge = new Judge(game);

		judge.start();

		player1.start();
		player2.start();

		player1.join();
		player2.join();

		judge.interrupt();
		judge.join();

		// showdown
		if (player1.getScore() > player2.getScore()) // p1 > p2
			System.out.println("player 1 wins");

		else if (player2.getScore() > player1.getScore()) // p2 > p1
			System.out.println("player 2 wins");

		else System.out.println("tie"); // p1 == p2
	}
}