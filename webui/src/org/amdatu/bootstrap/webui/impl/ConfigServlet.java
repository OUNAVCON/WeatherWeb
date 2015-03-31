package org.amdatu.bootstrap.webui.impl;

import java.io.IOException;
import java.net.URL;
import java.util.Dictionary;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.amdatu.bootstrap.webui.WebBackendConfig;
import org.amdatu.template.processor.TemplateContext;
import org.amdatu.template.processor.TemplateEngine;
import org.amdatu.template.processor.TemplateException;
import org.amdatu.template.processor.TemplateProcessor;
import org.apache.felix.dm.annotation.api.Component;
import org.apache.felix.dm.annotation.api.Inject;
import org.apache.felix.dm.annotation.api.Property;
import org.apache.felix.dm.annotation.api.ServiceDependency;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;

@Component(provides = {Servlet.class, ManagedService.class, WebBackendConfig.class}, 
		   properties = {@Property(name = "alias", values = "/configuration"),
						 @Property(name = Constants.SERVICE_PID, values="webui")})
public class ConfigServlet extends HttpServlet implements ManagedService, WebBackendConfig {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private volatile BundleContext m_bundleContext;
	
	@ServiceDependency
	private volatile TemplateEngine m_templateEngine;

	private volatile int m_port = 8080;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		URL resource = m_bundleContext.getBundle().getResource("/resources/settings.js.vm");
		TemplateContext context = m_templateEngine.createContext();
		context.put("host", "http://localhost:" + m_port);

		TemplateProcessor processor;
		try {
			processor = m_templateEngine.createProcessor(resource);
			processor.generateStream(context, resp.getWriter());
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updated(@SuppressWarnings("rawtypes") Dictionary properties) throws ConfigurationException {
		String port = (String)properties.get("port");
		if(port != null) {
			m_port = Integer.parseInt(port);
		}
	}

	@Override
	public int getPort() {
		return m_port;
	}
}
