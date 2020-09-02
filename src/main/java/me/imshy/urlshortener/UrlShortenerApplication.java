package me.imshy.urlshortener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlShortenerApplication {

    private static final Logger log = LoggerFactory.getLogger(UrlShortenerApplication.class);

    public static void main(String[] args) {
        log.info("UrlShortenerApplication startup");
        SpringApplication.run(UrlShortenerApplication.class, args);
    }


}
