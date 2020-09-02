package me.imshy.urlshortener.repository;

import me.imshy.urlshortener.entity.UrlItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlItemRepository extends CrudRepository<UrlItem, Long> {

    boolean existsByLongUrl(String longUrl);
    boolean existsByShortUrlSuffix(String shortUrlSuffix);

    UrlItem findByShortUrlSuffix(String shortUrlSuffix);
    UrlItem findByLongUrl(String longUrl);

    @Query("SELECT coalesce(max(id), 0) FROM UrlItem")
    Long getMaxId();

}
