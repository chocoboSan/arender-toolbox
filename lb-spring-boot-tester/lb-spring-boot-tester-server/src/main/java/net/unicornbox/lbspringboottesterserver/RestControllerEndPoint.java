package net.unicornbox.lbspringboottesterserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class RestControllerEndPoint {
    @Value("${POD_NAME:could not get podName}")
    private String localPodName;

    @GetMapping
    public String getPodName() {
        return localPodName;
    }
}
