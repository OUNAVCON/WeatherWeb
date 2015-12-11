package org.weather.rest;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;
import org.weather.WeatherService;
import org.weather.samples.WeatherSamples;

public class Activator extends DependencyActivatorBase{

	@Override
	public void destroy(BundleContext arg0, DependencyManager arg1)
			throws Exception {
	}

	@Override
	public void init(BundleContext context, DependencyManager manager)
			throws Exception {
		manager.add(createComponent().setInterface(Object.class.getName(),null)
				                     .setImplementation(WeatherRest.class)
				                     .add(createServiceDependency()
				                    		 .setService(WeatherService.class)
				                    		 .setRequired(true))
				                    .add(createServiceDependency()
						              		 .setService(WeatherSamples.class)
						                	 .setRequired(true)));
	}

}
