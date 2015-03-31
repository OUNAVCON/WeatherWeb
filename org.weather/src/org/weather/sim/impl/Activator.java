package org.weather.sim.impl;

import java.util.Properties;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.log.LogService;
import org.weather.WeatherService;

public class Activator extends DependencyActivatorBase{

	@Override
	public void destroy(BundleContext arg0, DependencyManager arg1)
			throws Exception {		
	}

	@Override
	public void init(BundleContext context, DependencyManager manager)
			throws Exception {
		
		
		Properties props = new Properties();
		props.put(Constants.SERVICE_PID, "org.weather.samplesize");
		
		
		manager.add(createComponent()
				.setInterface(WeatherService.class.getName(),null)
				.setImplementation(WeatherImpl.class));
		manager.add(createComponent()
				.setInterface(ManagedService.class.getName(), props)
				.setImplementation(WeatherImpl.class)
				.add(createServiceDependency()
						.setService(LogService.class)
						.setRequired(false)));
		
	}

}
