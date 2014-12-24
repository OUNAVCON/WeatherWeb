package org.weather.rest;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;
import org.weather.TemperatureService;

public class Activator extends DependencyActivatorBase{

	@Override
	public void destroy(BundleContext arg0, DependencyManager arg1)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void init(BundleContext context, DependencyManager manager)
			throws Exception {
		manager.add(createComponent().setInterface(Object.class.getName(),null)
				                     .setImplementation(TemperatureRest.class)
				                     .add(createServiceDependency()
				                    		 .setService(TemperatureService.class)
				                    		 .setRequired(true)));
	}

}
