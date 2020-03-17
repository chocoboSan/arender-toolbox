package net.unicornbox.lbspringboottesterclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LbSpringBootTesterClientApplication {

    static {
        java.security.Security.setProperty("networkaddress.cache.ttl", "0");
    }

    public static void main(String[] args) {
        SpringApplication.run(LbSpringBootTesterClientApplication.class, args);
    }

}
