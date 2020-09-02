CREATE USER 'urlshortener'@'localhost' IDENTIFIED BY 'urlshortener';

GRANT ALL PRIVILEGES ON * . * TO 'urlshortener'@'localhost';

ALTER USER 'urlshortener'@'localhost' IDENTIFIED WITH mysql_native_password BY 'urlshortener';
