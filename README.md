# URLShortener-back
Spring Boot (Web, JPA) app connected with MySQL DB exposing a simple API for url shortening service.
Hosted by default on `http://localhost:8080`

## API methods
POST at home URL with a body of raw String describing an URL to shorten.
GET at home/:id returning a JSON of UrlResponse
Example: 
`{
  "longUrl": "https://github.com"
  "shortUrl": "1"
}`

shortUrl value represents a base62 encoded id of a request.
