# URL Shortener

A Spring Boot URL Shortener built as a learning project.

The purpose of this project is to learn Java and Spring Boot by
designing and implementing a real application incrementally.

## Initial Features

- Create a shortened URL
- Generate a unique short code
- Store the URL mapping
- Redirect using the short code
- Validate incoming URLs

## Initial API

### Create Short URL

POST /api/urls

Request:

{
"url": "https://example.com"
}

Response:

{
"shortCode": "Ui901nvwL",
"shortUrl": "http://localhost:8080/Ui901nvwL",
"url": "https://example.com"
}

### Redirect

GET /{shortCode}

The endpoint resolves the short code and redirects the client to the
original URL.

## Learning Goal

Build the application incrementally while understanding the Java,
Spring Boot, HTTP, persistence, testing, and software engineering
concepts behind each implementation.