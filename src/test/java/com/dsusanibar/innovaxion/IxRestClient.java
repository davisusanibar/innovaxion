package com.dsusanibar.innovaxion;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IxRestClient {

    @Test
    public void httpTest() throws IOException {
        HttpClient httpClient = HttpClients.custom().build();
        String urlOverHttp = "https://localhost:8443/ix/time";
        HttpGet requestMapping = new HttpGet(urlOverHttp);
        HttpResponse httpResponse = httpClient.execute(requestMapping);
        Assert.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void givenAcceptingAllCertificates_whenHttpsUrlIsConsumed_thenException()
            throws IOException, GeneralSecurityException {
        TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
        SSLSocketFactory sf = new SSLSocketFactory(
                acceptingTrustStrategy, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("https", 8443, sf));
        ClientConnectionManager ccm = new PoolingClientConnectionManager(registry);

        DefaultHttpClient httpClient = new DefaultHttpClient(ccm);

        String urlOverHttps
                = "https://localhost:8443/ix/time";
        HttpGet getMethod = new HttpGet(urlOverHttps);

        HttpResponse response = httpClient.execute(getMethod);
        System.out.print("Response: "+response.toString());
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }
}
