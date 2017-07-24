package improved;

public interface IStateChangeSubjectForGui {
	public void registerObserver(IStateChangeObserverForGui o);
	public void removeObserver(IStateChangeObserverForGui o);
	public void notifyObservers(String text);

}
