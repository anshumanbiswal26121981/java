package improved;

import java.util.HashSet;
import java.util.Set;

public class StateMachine implements Cloneable{

	/** A description of the state machine */
	private String description;

	/** The set of states making up the state machine */
	private HashSet states;

	/** The starting state */
	private BaseState startState;

	/** The current state of the state machine */
	private BaseState currentState;
	
	public StateMachine(){}

	/**
	 * Create a state machine given its states and start state.
	 * 
	 * @param states
	 *            - Set<State> for the state machine
	 * @param startState
	 *            - the starting state
	 */
	public StateMachine(Set states, BaseState startState) {
		this(states, startState, null);
	}

	public void setStatesOfTheMachine(HashSet statesSet){
		states = statesSet;
	}
	
	public void setStartState(BaseState stState){
		startState = stState;
		currentState = startState;
	}
	/**
	 * Create a state machine given its states and start state.
	 * 
	 * @param states
	 *            - Set<State> for the state machine
	 * @param startState
	 *            - the starting state
	 * @param description
	 *            - an optional description of the state machine
	 */
	public StateMachine(Set states, BaseState startState, String description) {
		this.states = new HashSet(states);
		this.startState = startState;
		this.currentState = startState;
		this.description = description;
	}

	/**
	 * Make a copy of the StateMachine maintaining the current state.
	 * 
	 * @return a copy of the StateMachine.
	 */
	public Object clone() {
		StateMachine clone = new StateMachine(states, startState, description);
		clone.currentState = currentState;
		return clone;
	}

	/**
	 * Get the state machine description.
	 * 
	 * @return an possibly null description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get the current state of the state machine.
	 * 
	 * @return the current state.
	 */
	public BaseState getCurrentState() {
		return currentState;
	}

	/**
	 * Get the start state of the state machine.
	 * 
	 * @return the start state.
	 */
	public BaseState getStartState() {
		return startState;
	}

	/**
	 * Get the states of the state machine.
	 * 
	 * @return the machine states.
	 */
	public Set getStates() {
		return states;
	}

	/**
	 * Transition to the next state given the name of a valid transition.
	 * 
	 * @param actionName
	 *            - the name of transition that is valid for the current state.
	 * @return the next state
	 * @throws IllegalTransitionException
	 */
	public BaseState nextState(EventActions actionName) throws IllegalTransitionException {
		Transition t = currentState.getTransition(actionName);
		if (t == null) {
			String msg = "No transition for action: '" + actionName
					+ "' from state: '" + currentState.getState() + "'";
			throw new IllegalTransitionException(msg);
		}
		BaseState nextState = t.getTarget();
		//System.out.println("nextState(" + actionName + ") = " + nextState);
		currentState = nextState;
		return currentState;
	}

	/**
	 * Reset the state machine back to the start state
	 * 
	 * @return the start state
	 */
	public BaseState reset() {
		this.currentState = startState;
		return currentState;
	}



	public void processRequest() {
		getCurrentState().process();
	}

	public void registerAllStateWithGUi(TemperatureControllerGui window) {
		// TODO Auto-generated method stub
		
	}

	
}