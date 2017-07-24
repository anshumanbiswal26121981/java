package improved;

import java.util.ArrayList;

public class Thermostat implements ITemperatureChangeSubject{

	private float mCurrentTemp,mRoomTemp;
	private ArrayList observers;
	private Signal mSignal;
	
	public Thermostat(){
		observers  = new ArrayList();
	}

	@Override
	public void notifyObservers(Signal sig) {
		for(int i = 0;i< observers.size();i++){
			ITemperatureChangeObserver observer = (ITemperatureChangeObserver)observers.get(i);
			observer.tempeChangeUpdate(sig);
		}
	}

	@Override
	public void registerObserver(ITemperatureChangeObserver o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(ITemperatureChangeObserver o) {
		int i = observers.indexOf(o);
		if(i>=0){
			observers.remove(i);
		}
		
	}
	
	private void measurementChanged(){
		System.out.println("Current temperature is "+ mCurrentTemp);
		if(mCurrentTemp < mRoomTemp){
			mSignal = Signal.SIGNAL_TOO_COLD;
		}else if(mCurrentTemp > mRoomTemp){
			mSignal = Signal.SIGNAL_TOO_HOT;
		}else if(mCurrentTemp == mRoomTemp){
			mSignal = Signal.SIGNAL_IDEAL_TEMPERATURE;
		}
		notifyObservers(mSignal);
	}

	public void setCurrentTemperature(float temperature){
		mCurrentTemp = temperature;
		measurementChanged();
	}
	
	public Signal getSignal(){
		return mSignal;
	}
	public void setRoomTemperature(float temp){
		mRoomTemp = temp;
	}
	
	public float getRoomTemperature(){
		return mRoomTemp;
	}
	
		
	public int getObserversSize(){
		return observers.size();
	}
}
