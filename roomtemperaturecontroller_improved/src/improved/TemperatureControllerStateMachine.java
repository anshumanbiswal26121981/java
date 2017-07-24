package improved;

import java.util.HashSet;
import java.util.Set;

public class TemperatureControllerStateMachine extends SimpleFSM {
	private static TemperatureControllerStateMachine instance;
	
	private HeaterEnabledState mHeaterEnabledState;
	private FanOnState mFanOnState;
	private AcEnabledState mACEnabledState;
	private OffState mOffState;
	private HeaterOnState mHeaterOnState;
	private HeaterOffState mHeaterOffState;
	private AcOnState mAcOnState;
	private AcOffState mAcOffState;
	private FanOffState mFanOffState;

	private HashSet<IState> states;
	
	private TemperatureControllerStateMachine(Set<IState> states, BaseState startState) {
		super(states, startState);
	}
	private TemperatureControllerStateMachine(){
		createStates();
		validTransitions();
		
		states = new HashSet<IState>();
		states.add(mOffState);
		states.add(mHeaterEnabledState);
		states.add(mACEnabledState);
		states.add(mHeaterOnState);
		states.add(mHeaterOffState);
		states.add(mAcOnState);
		states.add(mAcOffState);
		states.add(mFanOnState);
		states.add(mFanOffState);
		setStatesOfTheMachine(states);
		setStartState(mOffState);
		
	}
	private void validTransitions() {
		mOffState.addTransition(new Transition(EventActions.SET_HEATER_MODE_ACTIVE_TRANSITION, mHeaterEnabledState));
		mOffState.addTransition(new Transition(EventActions.SWITCH_FAN_ON_TRANSITION, mFanOnState));
		mOffState.addTransition(new Transition(EventActions.SET_AC_MODE_ACTIVE_TRANSITION, mACEnabledState));
		
		
		mHeaterEnabledState.addTransition(new Transition(EventActions.SWITCH_FAN_ON_TRANSITION, mFanOnState));
		mHeaterEnabledState.addTransition(new Transition(EventActions.DO_NOTHING_TRANSITION, mOffState));
		mHeaterEnabledState.addTransition(new Transition(EventActions.SET_AC_MODE_ACTIVE_TRANSITION, mACEnabledState));
		mHeaterEnabledState.addTransition(new Transition(EventActions.SWITCH_HEATER_ON_WHEN_HEATER_ENABLED_TRANSITION, mHeaterOnState));
		mHeaterEnabledState.addTransition(new Transition(EventActions.SWITCH_HEATER_OFF_WHEN_HEATER_ENABLED_TRANSITION, mHeaterOffState));
		
		
		mACEnabledState.addTransition(new Transition(EventActions.DO_NOTHING_TRANSITION, mOffState));
		mACEnabledState.addTransition(new Transition(EventActions.SET_HEATER_MODE_ACTIVE_TRANSITION, mHeaterEnabledState));
		mACEnabledState.addTransition(new Transition(EventActions.SWITCH_FAN_ON_TRANSITION, mFanOnState));
		mACEnabledState.addTransition(new Transition(EventActions.SWITCH_AC_ON_WHEN_AC_ENABLED_TRANSITION, mAcOnState));
		mACEnabledState.addTransition(new Transition(EventActions.SWITCH_AC_OFF_WHEN_AC_ENABLED_TRANSITION, mAcOffState));
		
		mHeaterOnState.addTransition(new Transition(EventActions.SWITCH_HEATER_OFF_WHEN_HEATER_ENABLED_TRANSITION, mHeaterOffState));
		mHeaterOffState.addTransition(new Transition(EventActions.SWITCH_HEATER_ON_WHEN_HEATER_ENABLED_TRANSITION, mHeaterOnState));
		mHeaterOffState.addTransition(new Transition(EventActions.SWITCH_FAN_ON_TRANSITION, mFanOnState));
		mHeaterOnState.addTransition(new Transition(EventActions.SWITCH_FAN_ON_TRANSITION, mFanOnState));
		mHeaterOffState.addTransition(new Transition(EventActions.SET_AC_MODE_ACTIVE_TRANSITION, mACEnabledState));
		mHeaterOnState.addTransition(new Transition(EventActions.SET_AC_MODE_ACTIVE_TRANSITION, mACEnabledState));
		
		mAcOnState.addTransition(new Transition(EventActions.SWITCH_AC_OFF_WHEN_AC_ENABLED_TRANSITION, mAcOffState));
		mAcOffState.addTransition(new Transition(EventActions.SWITCH_AC_ON_WHEN_AC_ENABLED_TRANSITION, mAcOnState));
		mAcOnState.addTransition(new Transition(EventActions.SWITCH_FAN_ON_TRANSITION, mFanOnState));
		mAcOffState.addTransition(new Transition(EventActions.SWITCH_FAN_ON_TRANSITION, mFanOnState));
		mAcOnState.addTransition(new Transition(EventActions.SET_HEATER_MODE_ACTIVE_TRANSITION, mHeaterEnabledState));
		mAcOffState.addTransition(new Transition(EventActions.SET_HEATER_MODE_ACTIVE_TRANSITION, mHeaterEnabledState));
		
		mFanOnState.addTransition(new Transition(EventActions.SWITCH_FAN_OFF_TRANSITION, mFanOffState));
		mFanOnState.addTransition(new Transition(EventActions.SWITCH_FAN_ON_TRANSITION, mFanOnState));
		
		mFanOffState.addTransition(new Transition(EventActions.SWITCH_FAN_ON_TRANSITION, mFanOnState));
		mFanOffState.addTransition(new Transition(EventActions.DO_NOTHING_TRANSITION, mOffState));
		mFanOffState.addTransition(new Transition(EventActions.SWITCH_FAN_OFF_TRANSITION, mFanOffState));
		mFanOffState.addTransition(new Transition(EventActions.SET_AC_MODE_ACTIVE_TRANSITION, mACEnabledState));
		mFanOffState.addTransition(new Transition(EventActions.SET_HEATER_MODE_ACTIVE_TRANSITION, mHeaterEnabledState));
	}
	private void createStates() {
		mOffState = new OffState();
		mHeaterEnabledState = new HeaterEnabledState();
		mACEnabledState = new AcEnabledState();
		mFanOnState = new FanOnState();
		mHeaterOnState = new HeaterOnState();
		mHeaterOffState = new HeaterOffState();
		mAcOnState = new AcOnState();
		mAcOffState = new AcOffState();
		mFanOffState = new FanOffState();
	}
	
	
	
	public static synchronized TemperatureControllerStateMachine getInstance() {
		if (instance == null) {
			
			instance = new TemperatureControllerStateMachine();
		}
		return instance;
	}
	public void registerAllStateWithGUi(TemperatureControllerGui window) {
		mOffState.registerObserver(window); 
		mHeaterEnabledState.registerObserver(window); 
		mACEnabledState.registerObserver(window); 
		mFanOnState.registerObserver(window); 
		mHeaterOnState.registerObserver(window); 
		mHeaterOffState.registerObserver(window); 
		mAcOnState.registerObserver(window); 
		mAcOffState.registerObserver(window); 
		mFanOffState.registerObserver(window); 
		
	}

}
