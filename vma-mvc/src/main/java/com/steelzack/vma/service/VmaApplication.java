package com.steelzack.vma.service;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

import com.steelzack.vma.util.OriginalInfoResponseFilter;

public class VmaApplication extends ResourceConfig {
	public VmaApplication() {
		register(RequestContextFilter.class);
		register(OriginalInfoRestService.class);
		register(JacksonFeature.class);
		register(OriginalInfoResponseFilter.class);
	}
}
