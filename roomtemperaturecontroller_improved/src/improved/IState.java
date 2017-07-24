package improved;

public interface  IState{
	
	  public void process();

	  public void notifySignalReceived(Signal sig);
	  
	}