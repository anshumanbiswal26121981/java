package improved;

import java.util.HashSet;
import java.util.Set;

public class SimpleFSM implements Cloneable{

	private String description;

	private HashSet fsmStates;

	private BaseState baseState;

	private BaseState presentState;
	
	public SimpleFSM(){}

	
	public SimpleFSM(Set states, BaseState startState) {
		this(states, startState, null);
	}

	public void setStatesOfTheMachine(HashSet statesSet){
		fsmStates = statesSet;
	}
	
	public void setStartState(BaseState stState){
		baseState = stState;
		presentState = baseState;
	}
	
	public SimpleFSM(Set states, BaseState startState, String description) {
		this.fsmStates = new HashSet(states);
		this.baseState = startState;
		this.presentState = startState;
		this.description = description;
	}

	
	public Object clone() {
		SimpleFSM clone = new SimpleFSM(fsmStates, baseState, description);
		clone.presentState = presentState;
		return clone;
	}

	
	public String getDescription() {
		return description;
	}

	
	public BaseState getCurrentState() {
		return presentState;
	}


	public BaseState getStartState() {
		return baseState;
	}

	
	public Set getStates() {
		return fsmStates;
	}

	
	public BaseState nextState(EventActions actionName) throws IllegalTransitionException {
		Transition t = presentState.getTransition(actionName);
		if (t == null) {
			String msg = "No transition for action: '" + actionName
					+ "' from state: '" + presentState.getState() + "'";
			throw new IllegalTransitionException(msg);
		}
		BaseState nextState = t.getTarget();
		//System.out.println("nextState(" + actionName + ") = " + nextState);
		presentState = nextState;
		return presentState;
	}

	
	public BaseState reset() {
		this.presentState = baseState;
		return presentState;
	}



	public void processRequest() {
		getCurrentState().process();
	}

	public void registerAllStateWithGUi(TemperatureControllerGui window) {
		// TODO Auto-generated method stub
		
	}

	
}