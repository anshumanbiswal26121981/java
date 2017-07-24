package improved;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BaseState implements IState,IStateChangeSubjectForGui{
	private HashMap allowedTransitions = new HashMap();
	private ArrayList observers;
	
	public BaseState() {
		observers  = new ArrayList();
	}
	
	public ArrayList getObservers(){
		return observers;
	}
	public boolean isAcceptState() {
		  return allowedTransitions.size() == 0;
	}

	public BaseState addTransition(Transition transition) {
		allowedTransitions.put(transition.getName(), transition);
	    return this;
	}

	public Transition getTransition(EventActions name) {
		Transition t = (Transition) allowedTransitions.get(name);
	    return t;
	}

	public Map getTransitions() {
		return allowedTransitions;
	}
	
	public StateList getState() {
		return null;
	}

	@Override
	public void process() {
	}

	@Override
	public void notifySignalReceived(Signal sig) {
	}

	@Override
	public void registerObserver(IStateChangeObserverForGui o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(IStateChangeObserverForGui o) {
		int i = observers.indexOf(o);
		if(i>=0){
			observers.remove(i);
		}
	}

	@Override
	public void notifyObservers(String text) {
		for(int i = 0;i< observers.size();i++){
			IStateChangeObserverForGui observer = (IStateChangeObserverForGui)observers.get(i);
			observer.stateProcessedUpdateToGui(text);
		}
	}

}
