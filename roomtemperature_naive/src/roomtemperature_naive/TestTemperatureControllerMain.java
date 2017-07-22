package roomtemperature_naive;

public class TestTemperatureControllerMain {

	public static void main(String[] args) throws InterruptedException {

		TemperatureController tc = new TemperatureController();
		System.out.println("Sliding to fan mode");
		tc.setMode(5);// FAN ON
		Thread.sleep(40*1000);
		System.out.println();
		System.out.println();
		System.out.println("Sliding to do nothing mode");
		tc.setMode(0);//OFF mode
		System.out.println();
		System.out.println();
		Thread.sleep(5*1000);
		System.out.println("Sliding to heater enabled mode");
		tc.setMode(7);//Heater Enabled mode
		tc.setRoomTemperature(20);
		tc.setCurrenttemp(22);
		Thread.sleep(2000);
		tc.setCurrenttemp(20);
		Thread.sleep(2000);
		tc.setCurrenttemp(22);
		Thread.sleep(2000);
		tc.setCurrenttemp(18);
		Thread.sleep(2000);
		tc.setCurrenttemp(15);
		Thread.sleep(2000);
		tc.setCurrenttemp(10);
		Thread.sleep(2000);
		tc.setCurrenttemp(22);
		Thread.sleep(2000);
		tc.setCurrenttemp(20);
		Thread.sleep(2000);
		tc.setCurrenttemp(22);
		Thread.sleep(2000);
		tc.setMode(0);//Off mode
		System.out.println("");
		System.out.println("");
		Thread.sleep(3000);
		System.out.println("Sliding to AC enabled mode");
		tc.setMode(8); //AC mode on
		tc.setRoomTemperature(20);
		tc.setCurrenttemp(22);
		Thread.sleep(2000);
		tc.setCurrenttemp(35);
		Thread.sleep(2000);
		tc.setCurrenttemp(32);
		Thread.sleep(2000);
		tc.setCurrenttemp(30);
		Thread.sleep(2000);
		tc.setCurrenttemp(24);
		Thread.sleep(2000);
		tc.setCurrenttemp(22);
		Thread.sleep(2000);
		tc.setCurrenttemp(24);
		Thread.sleep(2000);
		tc.setCurrenttemp(26);
		Thread.sleep(2000);
		tc.setCurrenttemp(28);
		Thread.sleep(2000);
		tc.setMode(0);//Off mode
		
		
	}
}
