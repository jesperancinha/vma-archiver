package com.steelzack.vma.service;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class VmaApplication extends ResourceConfig {
	public VmaApplication() {
		register(OriginalInfoRestService.class);
		register(OriginalInfoResponseFilter.class);
		register(JacksonFeature.class);
	}
}
