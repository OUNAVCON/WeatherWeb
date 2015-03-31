package org.weather.samples.impl;

import org.amdatu.scheduling.annotations.RepeatForever;
import org.amdatu.scheduling.annotations.RepeatInterval;
import org.osgi.service.log.LogService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.weather.Weather;
import org.weather.WeatherService;
import org.weather.samples.WeatherSamples;

@RepeatForever
@RepeatInterval(period = RepeatInterval.SECOND, value = 10)
public class WeatherSamplesImpl implements WeatherSamples, Job {

	private volatile WeatherService weatherService;
	private volatile LogService logService;

	@Override
	public synchronized Weather getLatestWeather() {
		Weather currentWeatherSample = weatherService.getWeather();
		weatherService.addWeather(currentWeatherSample);
		return currentWeatherSample;
	}

	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		Weather temp = this.getLatestWeather();
		if (logService != null) {
			logService.log(LogService.LOG_INFO, "CurrentTemp is: " + temp);
		}
	}

}
