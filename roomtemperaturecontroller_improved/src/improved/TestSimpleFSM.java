package improved;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class TestSimpleFSM {

	private SimpleFSM m_fsm;
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
    @Before
    public void setUp(){
    	mOffState = new OffState();
		mHeaterEnabledState = new HeaterEnabledState();
		mACEnabledState = new AcEnabledState();
		mFanOnState = new FanOnState();
		mHeaterOnState = new HeaterOnState();
		mHeaterOffState = new HeaterOffState();
		mAcOnState = new AcOnState();
		mAcOffState = new AcOffState();
		mFanOffState = new FanOffState();
		
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
		
		mAcOnState.addTransition(new Transition(EventActions.SWITCH_AC_OFF_WHEN_AC_ENABLED_TRANSITION, mAcOffState));
		mAcOffState.addTransition(new Transition(EventActions.SWITCH_AC_ON_WHEN_AC_ENABLED_TRANSITION, mAcOnState));
		mAcOnState.addTransition(new Transition(EventActions.SWITCH_FAN_ON_TRANSITION, mFanOnState));
		mAcOffState.addTransition(new Transition(EventActions.SWITCH_FAN_ON_TRANSITION, mFanOnState));
		
		mFanOnState.addTransition(new Transition(EventActions.DO_NOTHING_TRANSITION, mOffState));
		mFanOnState.addTransition(new Transition(EventActions.SWITCH_FAN_OFF_TRANSITION, mFanOffState));
		
		mFanOffState.addTransition(new Transition(EventActions.SWITCH_FAN_ON_TRANSITION, mFanOnState));
		mFanOffState.addTransition(new Transition(EventActions.DO_NOTHING_TRANSITION, mOffState));
		mFanOffState.addTransition(new Transition(EventActions.SET_HEATER_MODE_ACTIVE_TRANSITION, mHeaterEnabledState));
		mFanOffState.addTransition(new Transition(EventActions.SET_AC_MODE_ACTIVE_TRANSITION, mACEnabledState));
		
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
		
		m_fsm = new SimpleFSM(states, mOffState);
    }
    

	@Test
	public void testGetDescription() {
		assertNull(m_fsm.getDescription());
	}

	@Test
	public void testGetCurrentState() {
		assertNotNull(m_fsm.getCurrentState().getState());
		assertTrue(m_fsm.getCurrentState().getState() == StateList.OFF);
	}

	@Test
	public void testGetStartState() {
		assertTrue(m_fsm.getStartState().getState() == StateList.OFF);
	}

	@Test
	public void testGetStates() {
		assertNotNull(m_fsm.getStates());
		assertTrue(m_fsm.getStates().size() == states.size());
	}

	@Test
	public void testNextState() throws IllegalTransitionException {
		BaseState state = m_fsm.nextState(EventActions.SET_HEATER_MODE_ACTIVE_TRANSITION);
		assertNotNull(state);
		assertTrue(state.getState() == StateList.HEATER_ENABLED);
		assertTrue(m_fsm.getCurrentState().getState() == StateList.HEATER_ENABLED);
		
	}

	@Test
	public void testReset() throws IllegalTransitionException {
		BaseState state = m_fsm.nextState(EventActions.SET_HEATER_MODE_ACTIVE_TRANSITION);
		assertNotNull(state);
		assertTrue(state.getState() == StateList.HEATER_ENABLED);
		assertTrue(m_fsm.getCurrentState().getState() == StateList.HEATER_ENABLED);
		m_fsm.reset();
		testGetStartState();
		testGetCurrentState();
	}

	@Test
	public void testProcessRequest() throws IllegalTransitionException {
		final String message = "System is in Heater Enabled state\r\n";
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		BaseState state = m_fsm.nextState(EventActions.SET_HEATER_MODE_ACTIVE_TRANSITION);
		assertNotNull(state);
		assertTrue(state.getState() == StateList.HEATER_ENABLED);
		assertTrue(m_fsm.getCurrentState().getState() == StateList.HEATER_ENABLED);
		outContent.reset();
		state.process();
		String str = outContent.toString();
		int compRes = message.compareTo(str.toString());
		assertTrue(compRes ==0);
		System.setOut(null);
	}

}
