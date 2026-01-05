package assig3_2;

public class Judge extends Thread
{
	private final GamePlay gamePlay;

	public Judge(GamePlay gamePlay)
	{
		this.gamePlay = gamePlay;
	}

	@Override
	public void run()
	{
		while(!this.isInterrupted())
		{
			try
			{
				gamePlay.makeCoinAvail(false);
				this.sleep(1000);

				gamePlay.makeCoinAvail(true);
				this.sleep(500);
			}
			catch (InterruptedException e)
			{
				interrupt();
			}
		}
	}
}
