package org.weather.samples.impl;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;
import org.quartz.Job;
import org.weather.WeatherService;
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
	
		dm.add(createComponent()
				.setInterface(WeatherSamples.class.getName(),null)
				.setImplementation(WeatherSamplesImpl.class)
				.add(createServiceDependency()
						.setService(WeatherService.class)
						.setRequired(true))
				.add(createServiceDependency()
						.setService(LogService.class)
						.setRequired(false)));		
		
		dm.add(createComponent()
				.setInterface(Job.class.getName(), null)
				.setImplementation(WeatherSamplesImpl.class)
				.add(createServiceDependency()
						.setService(WeatherService.class)
						.setRequired(true))
				.add(createServiceDependency()
						.setService(LogService.class)
						.setRequired(false)));

	}

}
