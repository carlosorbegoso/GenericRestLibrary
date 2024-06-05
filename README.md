The generic  Rest library*  class provider a generic class interface HTTP request either *Feign* or *WebClient* as the underlying HTTP client.
It simplifies the process of invoking Http methods and handles the underlying client configuration

##  Prerequisites
Before using the `GenericWebClient`, you need to have the following:

Java:
- A Java development environment.

Library:
- The `AsyncGenericRestLibrary` library added to your project's dependencies
- An interface annotated with `@RestService` that defines the HTTP request you want to make
- Create a `GenericWebClient` instance, yo need to provide the class of the service interface and optional    `HttClientConfig`  object.



```java
@RestService(baseUrl = "http:/api.example.com/users")
public class Users {
//
}
```

Inside the `Users` class, create fiel of type `GenericWebCliet` and initialize it in the  constructor

```java
private final GenericWebClient webClient;

public class Users (GenericWebClient webClient){
	this.webClient = webClient;
}
```

Usage  Retrieving `Users` To retrieve a list of users, create a new method  annotated with `@RestRequest(method.Request.GET, path="/")`

```java 
@RestRequest(method = RequestMethod.GET, path = "/") // Method GET/POST/PUT/DELETE
public ResponseEntity<?> getUsers() {
    return webClient.get();
}
```

## Configuration
You can configure `GenericWebClient` by passing a instance of `HttpClientConfig` to the `GenericWebClientBuilder`. For example

```java
HttpClientConfig config = new HttpClientConfig();
config.setTimeout(5000); // Set the timeout to 5 seconds
config.setFollowRedirects(true); // Enable following redirects

GenericWebClient webClient = new GenericWebClientBuilder()
    .forService(Users.class)
    .withConfig(config)
    .build();
```
## Using response
```java
Users user = new Users(webClient)

```





