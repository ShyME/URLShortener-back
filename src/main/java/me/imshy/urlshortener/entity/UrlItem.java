package me.imshy.urlshortener.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "url_items")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UrlItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LONG_URL")
    private String longUrl;

    @Column(name = "SHORT_URL_SUFFIX")
    private String shortUrlSuffix;

    public UrlItem(String longUrl, String shortUrlSuffix) {
        this.longUrl = longUrl;
        this.shortUrlSuffix = shortUrlSuffix;
    }

}
