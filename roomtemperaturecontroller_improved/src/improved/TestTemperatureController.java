package improved;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTemperatureController {
	Thermostat thStat;
	TemperatureController tc;

	@Before
	public void setUp() throws Exception {
		thStat = new Thermostat();
		tc = new TemperatureController(thStat);
	}

	@Test
	public void testTemperatureController() {
		assertNotNull(tc);
	}

	@Test
	public void testTempeChangeUpdate() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		outContent.reset();
		tc.tempeChangeUpdate(Signal.SIGNAL_TOO_HOT);
		String str = outContent.toString();
		String[] splitStr = str.split("\r\n");
		String message = "Signal received: SIGNAL_TOO_HOT";
		int compRes = message.compareTo(splitStr[0]);
		Assert.assertTrue(compRes == 0);
		
		outContent.reset();
		tc.tempeChangeUpdate(Signal.SIGNAL_TOO_COLD);
		str = outContent.toString();
		String[] splitStr1 = str.split("\r\n");
		message = "Signal received: SIGNAL_TOO_COLD";
		compRes = message.compareTo(splitStr1[0]);
		Assert.assertTrue(compRes == 0);
		
		outContent.reset();
		tc.tempeChangeUpdate(Signal.SIGNAL_IDEAL_TEMPERATURE);
		str = outContent.toString();
		String[] splitStr2 = str.split("\r\n");
		message = "Signal received: SIGNAL_IDEAL_TEMPERATURE";
		compRes = message.compareTo(splitStr2[0]);
		Assert.assertTrue(compRes == 0);
	}

	@Test
	public void testSetHeaterModeOn() {
		
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		outContent.reset();
		tc.setHeaterModeOn();
		String str = outContent.toString();
		String message = "System is in Heater Enabled state\r\n";
		int compRes = message.compareTo(str.toString());
		Assert.assertTrue(compRes == 0);
	}
	
	

	@Test
	public void testSwitchFanOn() {
	
	final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	System.setOut(new PrintStream(outContent));
	outContent.reset();
	tc.switchFanOn();
	validateSwitchFanOn(outContent);
	}

	private void validateSwitchFanOn(final ByteArrayOutputStream outContent) {
		String str = outContent.toString();
		String message = "Switching the Fan On . It will stop after 10 minutes\r\n";
		int compRes = message.compareTo(str.toString());
		Assert.assertTrue(compRes == 0);
	}

	@Test
	public void testSwitchFanOff() {
		
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		outContent.reset();
		try {
			tc.switchFanOn();
			Thread.sleep(10*1000);
			tc.switchFanOff();
		} catch (IllegalTransitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = outContent.toString();
		List<String > list = new ArrayList<String>();
		String [] str1 = str.split("\r\n");
		String message = "Switching the Fan Off . It will start after 10 minutes";
		for(String s : str1){
			list.add(s);
		}
		
		
		boolean compRes =list.contains(message);
		
		Assert.assertTrue(compRes);
	}

	@Test
	public void testSetAcModeEnabled() {
		
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		outContent.reset();
			tc.setAcModeEnabled();
		String str = outContent.toString();
		String message = "System is in AC Enabled state\r\n";
		int compRes = message.compareTo(str.toString());
		Assert.assertTrue(compRes == 0);
	}

}
