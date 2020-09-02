package me.imshy.urlshortener.service;

import io.seruco.encoding.base62.Base62;
import me.imshy.urlshortener.entity.UrlItem;
import me.imshy.urlshortener.exception.RecordNotFoundException;
import me.imshy.urlshortener.repository.UrlItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.ByteBuffer;

@Service
public class UrlItemService {

    private static final Logger log = LoggerFactory.getLogger(UrlItemService.class);

    private final Base62 base62 = Base62.createInstance();

    private final UrlItemRepository urlItemRepository;

    private long lastId;

    @Autowired
    public UrlItemService(UrlItemRepository urlItemRepository) {
        this.urlItemRepository = urlItemRepository;
    }

    @PostConstruct
    public void getLastId() {
        lastId = urlItemRepository.getMaxId();
        log.info("Got lastId: {}", lastId);
    }

    public String getRedirectLongUrl(String shortUrlSuffix) throws RecordNotFoundException {
        log.info("Received a call to getRedirectLongUrl with shortUrlSuffix: {}", shortUrlSuffix);
        if(urlItemRepository.existsByShortUrlSuffix(shortUrlSuffix)) {
            String longUrl = urlItemRepository.findByShortUrlSuffix(shortUrlSuffix).getLongUrl();
            log.info("Found a record with such shortUrlSuffix: {}, returning longUrl: {}", shortUrlSuffix, longUrl);
            return longUrl;
        } else {
            log.info("Could not find a record with such hortUrlSuffix: {}, returning 404 Not Found", shortUrlSuffix);
            throw new RecordNotFoundException("Such shorted url does not exist in the database!");
        }
    }

    public void saveUrlItem(String longUrl) {
        log.info("Received a call to saveUrlItem with longUrl: {}", longUrl);
        if(!urlItemRepository.existsByLongUrl(longUrl)) {

            UrlItem urlItem = new UrlItem(longUrl, getShortUrlSuffix(++lastId));
            log.info("Creating a new urlItem: {} and saving", urlItem.toString());
            this.urlItemRepository.save(urlItem);
        } else {
            log.info("Such longUrl: {} already exists within the database!", longUrl);
        }
    }

    public String getShortUrl(String longUrl) {
        return urlItemRepository.findByLongUrl(longUrl).getShortUrlSuffix();
    }

    private String getShortUrlSuffix(long id) {
        return new String(base62.encode(longToBytes(id))).replaceFirst("^0+(?!$)", "");
    }

    private byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(0, x);
        return buffer.array();
    }
}
