package org.weather.samples.impl;

import org.amdatu.scheduling.annotations.RepeatForever;
import org.amdatu.scheduling.annotations.RepeatInterval;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.weather.TemperatureService;
import org.weather.samples.WeatherSamples ;

@RepeatForever
@RepeatInterval(period=RepeatInterval.SECOND, value = 10)
public class WeatherSamplesImpl implements WeatherSamples, Job{

	private volatile TemperatureService temperatureService;
	private static int MAX_NUMBER_OF_SAMPLES = 255;
	//private CircularFifoQueue<Double> temperatureList = new CircularFifoQueue<Double>(MAX_NUMBER_OF_SAMPLES);
	
	
	@Override
	public double getLatestTemperater() {
		double currentTemperatureSample = temperatureService.getTemperature();
	    //temperatureList.add(currentTemperatureSample);
	return currentTemperatureSample;
	}
	
	 @Override
	 public void execute(JobExecutionContext ctx) throws JobExecutionException {
		 
		 System.out.println("Executing job!");
		 double temp = this.getLatestTemperater();
		 System.out.println("CurrentTemp is: " + temp);
	 }

}
