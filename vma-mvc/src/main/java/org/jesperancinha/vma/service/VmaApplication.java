package org.jesperancinha.vma.service;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import org.jesperancinha.vma.util.OriginalInfoResponseFilter;

public class VmaApplication extends ResourceConfig {
	public VmaApplication() {
		register(RequestContextFilter.class);
		register(OriginalInfoRestService.class);
		register(JacksonFeature.class);
		register(OriginalInfoResponseFilter.class);
	}
}
