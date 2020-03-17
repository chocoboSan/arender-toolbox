package net.unicornbox.lbspringboottesterclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.UUID;

@Component
@Order(1)
class RestRequestExecutor implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestRequestExecutor.class);

    @Value("${use.lb.headers}")
    private boolean useLbHeaders = false;

    @Value("${host.name}")
    private String hostName = "http://localhost:20000";

    @Value("${number.requests}")
    private int numberRequests = 100;

    @Value("${recycle.rest.template}")
    private boolean recycleRestTemplate = false;

    @Value("${use.exchange}")
    private boolean useExchange = true;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        RestTemplate template = new RestTemplate();
        URI url = UriComponentsBuilder.fromHttpUrl(hostName).build().toUri();
        LOGGER.info("Going to execute requests on " + url + "...");
        if (useLbHeaders) {
            LOGGER.info("Requests will have a specific LB cookie placed");
        }
        ResponseEntity<String> podName;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        if (useLbHeaders) {
            headers.add("Cookie", "LB_ID" + "=" + UUID.randomUUID());
        }
        for (int i = 0; i < numberRequests; i++) {
            HttpEntity<?> newEntity = new HttpEntity<Object>(null, headers);
            RestTemplate currentTemplate;
            if (recycleRestTemplate) {
                currentTemplate = template;
            } else {
                currentTemplate = new RestTemplate();
            }
            if (useExchange) {
                podName = currentTemplate.exchange(url, HttpMethod.GET, newEntity, String.class);
            } else {
                podName = currentTemplate.getForEntity(url, String.class);
            }
            if (podName.getStatusCode() == HttpStatus.OK) {
                LOGGER.info("Pod Name : " + podName.getBody());
            }
        }
        applicationContext.close();
    }
}