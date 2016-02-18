/*
 * Copyright (c) 2014 The Amdatu Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
		   properties = {@Property(name = "alias", values = "/configuration.js"),
						 @Property(name = Constants.SERVICE_PID, values="org.amdatu.bootstrap.webui")})
public class ConfigServlet extends HttpServlet implements ManagedService, WebBackendConfig {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private volatile BundleContext m_bundleContext;
	
	@ServiceDependency
	private volatile TemplateEngine m_templateEngine;

	private volatile int m_port = 8080;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		URL resource = m_bundleContext.getBundle().getResource("/resources/js/settings.js");
		TemplateContext context = m_templateEngine.createContext();
		context.put("host", "http://localhost:" + m_port);

		TemplateProcessor processor;
		try {
			processor = m_templateEngine.createProcessor(resource);
			resp.setContentType("application/json");
			processor.generateStream(context, resp.getWriter());
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updated(@SuppressWarnings("rawtypes") Dictionary properties) throws ConfigurationException {
		if(properties != null) {
			String port = (String)properties.get("port");
			if(port != null) {
				m_port = Integer.parseInt(port);
			}
		}
	}

	@Override
	public int getPort() {
		return m_port;
	}
}
