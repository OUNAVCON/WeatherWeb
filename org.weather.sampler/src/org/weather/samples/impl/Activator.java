package org.weather.samples.impl;

import java.util.Properties;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.log.LogService;
import org.quartz.Job;
import org.weather.TemperatureService;
import org.weather.samples.WeatherSamples;

public class Activator extends DependencyActivatorBase{

	@Override
	public void destroy(BundleContext arg0, DependencyManager arg1)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void init(BundleContext arg0, DependencyManager dm)
			throws Exception {
		
		Properties props = new Properties();
		props.put(Constants.SERVICE_PID, "org.weathersampler");
		
		dm.add(createComponent()
				.setInterface(WeatherSamples.class.getName(),null)
				.setImplementation(WeatherSamplesImpl.class)
				.add(createServiceDependency()
						.setService(TemperatureService.class)
						.setRequired(true))
				.add(createServiceDependency()
						.setService(LogService.class)
						.setRequired(false)));		
		
		dm.add(createComponent()
				.setInterface(Job.class.getName(), null)
				.setImplementation(WeatherSamplesImpl.class)
				.add(createServiceDependency()
						.setService(TemperatureService.class)
						.setRequired(true))
				.add(createServiceDependency()
						.setService(LogService.class)
						.setRequired(false)));
		
		dm.add(createComponent()
				.setInterface(ManagedService.class.getName(), props)
				.setImplementation(WeatherSamplesImpl.class)
				.add(createServiceDependency()
						.setService(LogService.class)
						.setRequired(false)));

	}

}
