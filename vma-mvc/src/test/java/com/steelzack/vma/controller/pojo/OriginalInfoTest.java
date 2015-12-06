package com.steelzack.vma.controller.pojo;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class OriginalInfoTest {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8089); // No-args
																// constructor
																// defaults to
																// port 8080

	@Test
	public void exampleTest() {
		stubFor(get(urlEqualTo("/my/resource")).willReturn(aResponse().withStatus(200)
				.withHeader("Content-Type", "text/xml").withBody("<response>Some content</response>")));

		System.out.println("test");

		// Result result = myHttpServiceCallingObject.doSomething();
		//
		// assertTrue(result.wasSuccessFul());
		//
		// verify(postRequestedFor(urlMatching("/my/resource/[a-z0-9]+"))
		// .withRequestBody(matching(".*<message>1234</message>.*"))
		// .withHeader("Content-Type", notMatching("application/json")));
	}
}
