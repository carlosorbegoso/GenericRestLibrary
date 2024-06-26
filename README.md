
<h1 style="text-align: center; font-size: xx-large"><span >&#9889;</span> Generic Rest Library <span>&#9889;</span></h1>  

<p><code>GenericWebClient</code> is a generic library that simplifies the process of invoking <strong>HTTP</strong> requests and handles the configuration of the underlying <strong>HTTP</strong> client such as <strong>Feign</strong> or <strong>WebClient</strong>. It allows you to make <span>&#9989;</span> GET, <span>&#10004;</span> POST, <span>&#9193;</span> PUT, and <span>&#10060;</span> DELETE requests easily.</p>  

## Prerequisites:

### 💻 Java:
- A Java development environment.

  ### 📦 Library:
    - The `AsyncGenericRestLibrary` library added to your project's dependencies
    - An interface annotated with `@RestService` that defines the HTTP request you want to make
    - Create a `GenericWebClient` instance, yo need to provide the class of the service interface and optional    `HttClientConfig`  object.


## ⚙️ Configuration
```java  
// Define the service interface  
@RestService(baseUrl = "http://api.example.com/users")  
public interface Users {  
    // HTTP request methods  
}  
  
// Create a GenericWebClient instance  
HttpClientConfig config = new HttpClientConfig();  
config.setTimeout(5000); // Set the timeout to 5 seconds  
config.setFollowRedirects(true); // Enable following redirects  
  
GenericWebClient webClient = new GenericWebClientBuilder()  
    .forService(Users.class)  
    .withConfig(config)  
    .build();  
```  

The `@RestService` annotation defines the base URL for the HTTP requests of the service interface.

The `HttpClientConfig` object allows you to configure options such as the timeout and follow redirects.

## ✍ Usage

Within the service interface annotated with `@RestService`, you can define methods annotated with `@RestRequest` to specify the HTTP request type and the relative path.
```java  
@RestService(baseUrl = "http://api.example.com/users")  
public interface Users {  
  
    @RestRequest(method = RequestMethod.GET, path = "/")  
    ResponseEntity<?> getUsers();  
      
    @RestRequest(method = RequestMethod.POST, path = "/")  
    ResponseEntity<?> createUser( User user);  
      
    @RestRequest(method = RequestMethod.PUT, path = "/id")  
    ResponseEntity<?> updateUser( User user);  
      
    @RestRequest(method = RequestMethod.DELETE, path = "/id")  
    ResponseEntity<?> deleteUser();  
}  
```  
*add  queryParams or  Headers :*
```java  
Map<String, String> queryParams = Map.ofEntries(    
        Map.entry("key", "value") 
        // add more params  
);  
  
HttpHeaders headers = new HttpHeaders();  
headers.setBearerAuth(beader);  
  
```  

The `@RestRequest` annotation defines the HTTP method (`GET`, `POST`, `PUT`, `DELETE`) and the relative path for the request. You can also use annotations like `@RequestBody` and `@PathVariable` to send data in the request body or in the path, respectively.

Once the service interface is defined, you can inject the `GenericWebClient` instance and call its methods:

```java  
Users users = new Users(webClient);  
ResponseEntity<?> response = users.getUsers();  
```  

The `ResponseEntity` object returned by the methods of the service interface contains the complete HTTP response, including the body, headers, and status code.

You can access the response body using the `getBody()` method:

```java  
List<User> userList = (List<User>) response.getBody();  
```  
You can also check the status code of the response:

```java  
if (response.getStatusCode() == HttpStatus.OK) {  
    // Handle successful response  
} else {  
    // Handle error  
}  
```  

##  Additional Examples

- **Adding  configurations**:

```java  
  
HttpClientConfig config = new HttpClientConfig();    
config.setTimeout(5000);    
config.setFollowRedirects(true);  
  
GenericWebClient webClient = new GenericWebClientBuilder()  
        .forService(Users.class)  
        .withConfig(config)  
        .build();  
```

## Adding as a Dependency

To add `AsyncGenericRestLibrary` as a dependency in your project, you should add the following line to your `pom.xml` file if you are using Maven:

```xml
<dependency>
  <groupId>com.intercorpretail</groupId>
  <artifactId>AsyncGenericRestLibrary</artifactId>
  <version>X.Y.Z</version>
</dependency>
```

If you are using Gradle, you should add the following line to your build.gradle file:

```gradle
dependencies {
    implementation 'com.intercorpretail:AsyncGenericRestLibrary:X.Y.Z'
}
```

## Versions
The `AsyncGenericRestLibrary` library has the following versions:

| Version | Description |
|---------|-------------|