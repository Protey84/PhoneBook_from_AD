package org.example;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class Runner {

    public static void main(String[] args) {
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new NTCredentials("username", "password", "", "ROSTAT.LOCAL"));
        try (org.apache.http.impl.client.CloseableHttpClient client = HttpClients.custom()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();) {
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(client);
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("url", new HttpEntity<>("yourDtoObject"), String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
