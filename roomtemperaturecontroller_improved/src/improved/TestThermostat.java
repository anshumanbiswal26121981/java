package improved;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestThermostat {

	Thermostat thStat;
	TemperatureController tc ;
	@Before
	public void setUp() throws Exception {
		thStat = new Thermostat();
		tc = new TemperatureController(thStat);
	
	}

	@Test
	public void testThermostat() {
		
		assertNotNull(thStat);
		assertNotNull(tc);
	}

	@Test
	public void testNotifyObservers() {
		
		
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		outContent.reset();
		thStat.notifyObservers(Signal.SIGNAL_TOO_HOT);
		String str = outContent.toString();
		String message = "Signal received: SIGNAL_TOO_HOT\r\n";
		int compRes = message.compareTo(str.toString());
		Assert.assertTrue(compRes == 0);
		
		outContent.reset();
		thStat.notifyObservers(Signal.SIGNAL_TOO_COLD);
		 str = outContent.toString();
		message = "Signal received: SIGNAL_TOO_COLD\r\n";
		compRes = message.compareTo(str.toString());
		Assert.assertTrue(compRes == 0);
		
		outContent.reset();
		thStat.notifyObservers(Signal.SIGNAL_IDEAL_TEMPERATURE);
		 str = outContent.toString();
		message = "Signal received: SIGNAL_IDEAL_TEMPERATURE\r\n";
		compRes = message.compareTo(str.toString());
		Assert.assertTrue(compRes == 0);
	}

	@Test
	public void testRegisterObserver() {
		Assert.assertTrue(thStat.getObserversSize()==1);
	}

	@Test
	public void testRemoveObserver() {
	}


	@Test
	public void testSetCurrentTemperature() {
		thStat.setRoomTemperature(20);
		thStat.setCurrentTemperature(35);
		Assert.assertTrue(thStat.getSignal() == Signal.SIGNAL_TOO_HOT);
		
	}


}
