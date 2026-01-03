package assig3_2;

public class GamePlay
{
	private boolean _coin_available = false;
	private int _rounds_counter = 0;

	public GamePlay() {}

	public synchronized void makeCoinAvail(boolean val)
	{
		this._coin_available = val;
		if(val)
			notifyAll();
	}

	public synchronized boolean flipCoin() throws InterruptedException
	{
		String tName = Thread.currentThread().getName();
		while(!this._coin_available)
		{
			System.out.printf("%s is waiting for coin\n", tName);
			wait();
		}

		System.out.printf("%s is flipping coin\n", tName);
		this._coin_available = false;
		this._rounds_counter++;

		int rand = (int)Math.round(Math.random());
		this._coin_available = true;
		notifyAll();

		if(rand == 1)
			return true;
		return false;
	}

	public synchronized int getNumOfRounds()
	{
		return this._rounds_counter;
	}
}
