package assig3_2;

public class Gamer extends Thread
{
	private int goodFlipsCounter = 0;
	private final GamePlay gamePlay;

	public Gamer(GamePlay gamePlay)
	{
		this.gamePlay = gamePlay;
	}

	public void play()
	{
		while(!this.isInterrupted() && gamePlay.getNumOfRounds() <= 10)
		{
			try
			{
				if(gamePlay.flipCoin())
					this.goodFlipsCounter++;

				this.sleep(1000);
			}
			catch (InterruptedException e)
			{
				interrupt();
			}
		}
	}

	public int getScore()
	{
		return goodFlipsCounter;
	}

	@Override
	public void run()
	{
		play();
	}
}
