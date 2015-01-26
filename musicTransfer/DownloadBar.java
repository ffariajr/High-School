package musicTransfer;

public class DownloadBar implements Runnable
{
	private long size;
	private long progress;
	private boolean end;
	private long last;
	
	public DownloadBar(long size)
	{
		this.size=size;
		progress = 0;
	}
	
	public void add()
	{
		progress++;
	}
	
	public void end()
	{
		end = true;
	}
	
	public void run()
	{
		long barsize = size/80;
		
		while(!end)
		{
			if(progress-last == barsize)
			{
				System.out.print("|");
				last = progress;
			}
		}
	}
}
