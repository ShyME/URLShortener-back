package me.imshy.urlshortener.controller;

import com.google.gson.Gson;
import me.imshy.urlshortener.exception.RecordNotFoundException;
import me.imshy.urlshortener.service.UrlItemService;
import me.imshy.urlshortener.util.UrlResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin("http://localhost:4200")
public class ApiController {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);
    private static final Gson gson = new Gson();

    private final UrlItemService urlItemService;

    @Autowired
    public ApiController(UrlItemService urlItemService) {
        this.urlItemService = urlItemService;
    }

    @GetMapping(value = "/{shortUrl}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getLongUrl(@PathVariable String shortUrl, HttpServletResponse httpServletResponse) throws RecordNotFoundException {
        log.info("Received GET redirect url request with url suffix: {}", shortUrl);
        String redirectUrl = urlItemService.getRedirectLongUrl(shortUrl);
        UrlResponse urlResponse = new UrlResponse(redirectUrl, shortUrl);
        //httpServletResponse.setHeader("Location", redirectUrl);
        //httpServletResponse.setStatus(302);
        return new ResponseEntity<>(gson.toJson(urlResponse), HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addShortUrl(@RequestBody String longUrl) {
        log.info("Received POST short url request with url: {}", longUrl);
        urlItemService.saveUrlItem(longUrl);
        String shortUrl = urlItemService.getShortUrl(longUrl);
        UrlResponse urlResponse = new UrlResponse(longUrl, shortUrl);
        return new ResponseEntity<>(gson.toJson(urlResponse), HttpStatus.CREATED);
    }
}
