package improved;

public class TemperatureControllerMain {
	
	public static void main(String[] args) throws InterruptedException {
		Thermostat thStat = new Thermostat();
		TemperatureController tc = new TemperatureController(thStat);
		
		System.out.println("Sliding to Heater enabled mode");
		testHeaterMode(thStat, tc);
		System.out.println("");
		System.out.println("");
		System.out.println("Sliding to Fan enabled mode");
		testFanOn(tc);
		Thread.sleep(30000);
		System.out.println("");
		System.out.println("");
		System.out.println("Sliding to Heater enabled mode");
		testHeaterMode(thStat, tc);
		Thread.sleep(3000);
		System.out.println("");
		System.out.println("");
		System.out.println("Sliding to AC enabled mode");
		testAcMode(thStat, tc);
		System.out.println("");
		System.out.println("");
		Thread.sleep(3000);
		System.out.println("Sliding to Heater enabled mode");
		testHeaterMode(thStat, tc);
		
		
		
	}

	private static void testAcMode(Thermostat thStat, TemperatureController tc) throws  InterruptedException {
		tc.setAcModeEnabled();
		thStat.setRoomTemperature(20);
		thStat.setCurrentTemperature(22);
		Thread.sleep(2000);
		thStat.setCurrentTemperature(35);
		Thread.sleep(2000);
		thStat.setCurrentTemperature(32);
		Thread.sleep(2000);
		thStat.setCurrentTemperature(30);
		Thread.sleep(2000);
		thStat.setCurrentTemperature(24);
		Thread.sleep(2000);
		thStat.setCurrentTemperature(22);
		Thread.sleep(2000);
		thStat.setCurrentTemperature(24);
		Thread.sleep(2000);
		thStat.setCurrentTemperature(26);
		Thread.sleep(2000);
		thStat.setCurrentTemperature(28);
		Thread.sleep(2000);
	}

	private static void testFanOn(TemperatureController tc) {
		tc.switchFanOn();
	}

	private static void testHeaterMode(Thermostat thStat,
			TemperatureController tc) throws InterruptedException {
		tc.setHeaterModeOn();
		thStat.setRoomTemperature(20);
		thStat.setCurrentTemperature(22);
		Thread.sleep(2000);
		thStat.setCurrentTemperature(20);
		Thread.sleep(2000);
		thStat.setCurrentTemperature(22);
		Thread.sleep(2000);
		thStat.setCurrentTemperature(18);
		Thread.sleep(2000);
		thStat.setCurrentTemperature(15);
		Thread.sleep(2000);
		thStat.setCurrentTemperature(10);
		Thread.sleep(2000);
		thStat.setCurrentTemperature(22);
		Thread.sleep(2000);
		thStat.setCurrentTemperature(20);
		Thread.sleep(2000);
		thStat.setCurrentTemperature(22);
		Thread.sleep(2000);
	}

}
