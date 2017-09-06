package com.dsusanibar.innovaxion;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InnovaxionApplicationTests {

	@Autowired
	SSLContext sslContext;

	@Autowired
	@LocalServerPort
	private int port;

	@Value("${local.server.protocol}")
	private String protocol; // injected by EasySSL

	private RestTemplate restTemplate;

	@Test
	public void contextLoads() {
	}

	public void restClientTest(){

	}

	private RestTemplate getRestTemplate(SSLContext sslContext) throws Exception {
		HttpClient httpClient = HttpClientBuilder.create().setSSLContext(sslContext).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		return new RestTemplateBuilder().rootUri(protocol + "://localhost:" + port).requestFactory(requestFactory).build();
	}
}
