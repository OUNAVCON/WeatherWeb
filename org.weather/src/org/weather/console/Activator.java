package org.weather.console;

import java.util.Properties;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.apache.felix.service.command.CommandProcessor;
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
		Properties props = new Properties();
		props.put(CommandProcessor.COMMAND_SCOPE, "Weather");
		props.put(CommandProcessor.COMMAND_FUNCTION, new String[] {"printTemperature"});
		
		manager.add(createComponent().setInterface(Object.class.getName(), props).setImplementation(Commands.class).add(createServiceDependency().setService(TemperatureService.class).setRequired(true)));
	}

}
