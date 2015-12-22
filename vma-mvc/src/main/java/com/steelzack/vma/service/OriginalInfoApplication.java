package com.steelzack.vma.service;

import org.glassfish.jersey.server.ResourceConfig;

public class OriginalInfoApplication extends ResourceConfig {
	public OriginalInfoApplication() {
		register(OrigianlInfoRestService.class);
		register(OriginalInfoResponseFilter.class);
	}
}
