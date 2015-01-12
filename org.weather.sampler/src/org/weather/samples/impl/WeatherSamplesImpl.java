package org.weather.samples.impl;

import java.util.Dictionary;

import org.amdatu.scheduling.annotations.RepeatForever;
import org.amdatu.scheduling.annotations.RepeatInterval;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.log.LogService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.weather.TemperatureService;
import org.weather.samples.WeatherSamples;

@RepeatForever
@RepeatInterval(period=RepeatInterval.SECOND, value = 10)
public class WeatherSamplesImpl implements WeatherSamples, Job, ManagedService {

	private volatile TemperatureService temperatureService;
	private volatile LogService logService;
	
	public volatile static int MAX_NUMBER_OF_SAMPLES = 255;
	private CircularFifoQueue<Double> temperatureList = new CircularFifoQueue<Double>(MAX_NUMBER_OF_SAMPLES);
	
	
	@Override
	public double getLatestTemperater() {
		double currentTemperatureSample = temperatureService.getTemperature();
	    temperatureList.add(currentTemperatureSample);
	return currentTemperatureSample;
	}
	
	 @Override
	 public void execute(JobExecutionContext ctx) throws JobExecutionException {
		 
		 double temp = this.getLatestTemperater();
		 if(logService != null){
			 logService.log(LogService.LOG_INFO,"CurrentTemp is: " + temp);
		 }
	//	 System.out.println("CurrentTemp is: " + temp +" , with " + WeatherSamplesImpl.MAX_NUMBER_OF_SAMPLES + " samples");
	//	 System.out.println("Average Temp is: " + temperatureList.stream().mapToDouble(w -> w).average().getAsDouble());
	 }

	@Override
	public void updated(Dictionary<String, ?> parameters)
			throws ConfigurationException {
		if(parameters != null)
		{
			int previousValues = MAX_NUMBER_OF_SAMPLES;
			try{
				WeatherSamplesImpl.MAX_NUMBER_OF_SAMPLES = Integer.parseInt(String.valueOf(parameters.get(WeatherSamplerConstants.MAX_NUMBER_OF_STORED_SAMPLES)));

				 if(logService != null){
					 logService.log(LogService.LOG_INFO, "Number of samples changed from  " + previousValues + " to " + WeatherSamplesImpl.MAX_NUMBER_OF_SAMPLES);
				 }
			}
			catch(Exception e){
				
			}
		}
		
	}

}
