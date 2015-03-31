package org.weather.sim.impl;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.log.LogService;
import org.weather.WeatherService;
import org.weather.Weather;

public class WeatherImpl implements WeatherService, ManagedService  {

	private volatile LogService logService;
	
	public volatile static int MAX_NUMBER_OF_SAMPLES = 255;
	private volatile CircularFifoQueue<Weather> temperatureList = new CircularFifoQueue<Weather>(255);
	
	private float result = 90;
	private Random rand = new Random();
	
	
	@Override
	public Weather getWeather() {          
		result += (rand.nextDouble() - 0.5F);
		return new Weather(result,0.0f,0.0f);
	}


	@Override
	public Weather addWeather(Weather weather) {
		temperatureList.add(weather);
		return null;
	}


	@Override
	public List<Weather> getAllWeatherSamples() {
		 List<Weather> result = new ArrayList<Weather>();
		  Iterator<Weather> myIt = this.temperatureList.iterator();
		  while(myIt.hasNext()){
			result.add(myIt.next());
		  }
		return result;
	}

	@Override
	public void updated(Dictionary<String, ?> parameters)
			throws ConfigurationException {
		if(parameters != null)
		{
			int previousValues = MAX_NUMBER_OF_SAMPLES;
			int samples = 0;
			try{
				samples= Integer.parseInt(String.valueOf(parameters.get(WeatherSamplerConstants.MAX_NUMBER_OF_STORED_SAMPLES)));

				 if(logService != null){
					 logService.log(LogService.LOG_INFO, "Number of samples changed from  " + previousValues + " to " + samples);
				 }
			}
			catch(Exception e){
				
			}
		}
		
	}
	
}
