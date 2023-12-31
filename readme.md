Spring MVC
----------

MVC - design pattern
-------
1. MVC stands for Model-View-Controller, it is a design pattern which divides application into three main interconnected component types.
    ![img.png](img.png)
   ![img_1.png](img_1.png)
2. Spring MVC introduces ready to use components that you can use in your application for MVC pattern.
    ![img_2.png](img_2.png)
3. Usage of MVC design pattern has following advantages:
   1. Separation of concerns
   2. Increased code cohesion
   3. Increased code reusability
   4. Reduces coupling between data, logic and information representation
   5. Lowers maintenances costs
   6. Increases extendibility
4. [Source Code](MVCPattern)

DispatcherServlet
---------------
1. DispatcherServlet is an internal Spring MVC component that implements
   HttpServlet from Java Servlet API and Front Controller Design Pattern. It is used
   to handle all requests to the application, based on servlet mapping, delegate
   those requests to controllers and produce response based on identified view.
2. DispatcherServlet has following responsibilities:
   1. Delegates received requests to Controllers
   2. Uses View Resolvers to resolve views pointed out by Controllers
   3. Produces Response that is sent to use
   4. Handles shared concerns, like exception mapping, error handling, security
      etc

Front Controller Design Pattern
-----------------------------
1. Front Controller Design Pattern allows you to implement shared algorithm for
   entire application responsible for request processing and handling shared
   concerns.
    ![img_3.png](img_3.png)

Java Servlet API
----------------
1. Servlet is a Java Technology used to create Web Applications on Java Platform with usage
of Application Servers. It is a set of interfaces, classes and documentation allowing you to
extend capabilities of Application Servers. Servlet is protocol independent, however usually
it is used to process HTTP Requests with usage of custom implementation of HttpServlet
class. Servlet can be registered via web.xml, or programmatically via annotations since
Servlet 3. Servlet registration requires url-patterns which informs application server which
requests should be mapped to your servlet
   ![img_4.png](img_4.png)

Examples
----------
1. Implementing MVC , front controller and Dispatcher Servlet on own
   1. [Source Code](MVCInAction)
2. Using Spring MVC libraries
   1. [Source Code](SpringMVCServlet)


web application context - Scopes
------------------------------------
1. Web Application Context is a Spring Application Context for Web Applications that
   runs under Embedded or Standalone Application Server that supports Servlet API
   and acts as Servlet Container
2. Web Application Context is described by WebApplicationContext interface
   and it allows you to access ServletContext interface from Servlet API.
3. Web Application Context provides four additional scopes:
   1. Request Scope
      1. Defined by `@RequestScope` annotation
      2. Bean lifecycle is tightly coupled with HTTP Request lifecycle
      3. New Bean instance is created for each request
      ```java
      @RequestScope
      @Component
      public class RequestScopeBean {
      }
         ```
   2. Session Scope
      1. Defined by `@SessionScope` annotation
      2. Bean lifecycle is tightly coupled with HTTP Session lifecycle
      3. New Bean is created for each new session and Bean instance lives as long as HTTP
         Session is alive
      ```java
      @SessionScope
      @Component
      public class SessionScopeBean {
      }
        ```
   3. Application Scope
      1. Defined by `@ApplicationScope` annotation
      2. Bean lifecycle is tightly coupled with ServletContext
      3. One Bean instance available per entire Web Application – ServletContext
      4. Differences compared to Singleton Bean:
         1. Singleton per ServletContext, not per Spring Application Context (one Web Application
            may have several Spring Application Contexts)
         2.  Exposed via attribute of ServletContext
      ```java
      @ApplicationScope
      @Component
      public class ApplicationScopeBean {
      }
       ``` 
   4. Websocket Scope
      1. Defined by @Scope annotation with specified properties:
         1. `@Scope(scopeName = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)`
      2. Bean lifecycle is coupled with lifecycle of WebSocket Session, however bean usually lives
         longer then WebSocket Session.
      ```java
      @Scope(scopeName = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
      @Component
      public class WebSocketScopeBean {
      }
      ```
   5. [Source Code](WebApplicationScopes)

@Controller
-------
1. @Controller annotation is used to indicate that annotated class is a Controller from
   Model-View-Controller Design Pattern, and should be considered a candidate for request
   handling when DispatcherServlet searches for component to which work can be
   delegated.
2. @Controller annotation is a specialization of @Component annotation, this allows
   Spring to autodetect controllers during classpath scanning.
3. Controllers in Spring do not have to implement any interface or extend any base class,
   Spring uses annotation-based programming model with @Controller annotation being
   part of it. Controllers have flexible methods signatures with mapping expressed via
   annotations like @RequestMapping, @GetMapping, @PostMapping etc.
   ```java
   @Controller
   public class HelloController {
   }

    ```

How is an incoming request mapped to a controller and mapped to a method
----------------------
1. Incoming request is mapped to a controller and a method by DispatcherServlet,
   which uses `HandlerMapping` and `HandlerAdapter` components for this purpose.
2. HandlerMapping components are used during Spring initialization to scan classpath
   for @Controller or @RestController classes with one of request mapping
   annotations that are part of annotation based programming model:
   1. @RequestMapping
   2. @GetMapping
   3. @PostMapping
   4. @PutMapping
   5. @PatchMapping
   6. @DeleteMapping
3. HandlerAdapter components are responsible for execution of method identified as
   handler candidate for the request.
4. When request is performed against the server following steps are executed:
   1. Application Server (Standalone or Embedded) searches for Servlet that can
      handle request, DispatcherServlet is selected based on Servlet
      Registration and url-pattern.
   2. DispatcherServlet uses HandlerMapping classes to get request mapping
      information and HandlerAdapter
   3. DispatcherServlet uses HandlerAdapter to execute controller method
      that will handle request.
   4. DispatcherServlet interprets results of method execution and renders
      View with help of ViewResolver classes.
   5. @RequestMapping allows you to specify conditions that request has to match
      for a method to be used as request handler. @RequestMapping can be used at
      class or method level, when used at the class level, all method level mappings
      inherit this primary mapping, narrowing it to a specific handler method.
   6. For example, below controllers are supposed to map GET /say/hello
      requests, even though request mapping is defined differently, all are equal.
      ![img_5.png](img_5.png)
   7. @RequestMapping annotation allows you to specify following criteria for request:
      1. path - uri path/paths for request, for example /api/books
      2. method – supported HTTP method/methods: GET, POST, HEAD, OPTIONS, PUT,
         PATCH, DELETE, TRACE
      3. params – required parameters of request, for example key1=value1,
         key2!=value2, key1, !key1
      4. headers – header needs to match specified condition, for example header1=value1,
         header2!=value2, header1, !header1, content-type=text/*
      5. consumes – media types that can be consumed by request, for example
         application/json
      6. produces – media types that are produced by method handling the request, for
         example application/pdf

   8. Spring MVC also supports composed annotations for request mapping:
      1. @GetMapping
      2. @PostMapping
      3. @PutMapping
      4. @PatchMapping
      5. @DeleteMapping
   9. Each of those annotations allows you to specify same conditions as @RequestMapping
      except for HTTP method field, following fields in @*Mapping are aliases to
      @RequestMapping: path, params, headers, consumes, produces.
   10. In most of the cases it is possible to translate mappings between those annotations, one
       example when this is not possible is when creating HTTP HEAD request mapping
       ![img_6.png](img_6.png)
   11. [Source Code](RequestMappings)


@RequestMapping vs @GetMapping
-------------
1. The main difference between @RequestMapping and @GetMapping is that first one can be
   used to map any HTTP method requests and second one can be used to map only HTTP GET
   method requests. @GetMapping is less flexible, but easier to use.
2. @GetMapping annotation is a composed annotation that is equal to
   @RequestMapping(method = RequestMethod.GET).
   Both annotations allows you to specify multiple criteria for request mapping, like uri path,
   required headers, consumable media types, producible media types, however only
   @RequestMapping allows you to specify HTTP method or HTTP methods through method field.
   If none HTTP methods are specified, all HTTP methods will be mapped.
3. Spring also includes other specialized versions of @RequestMapping:
   1. @PostMapping
   2. @PutMapping
   3. @DeleteMapping
   4. @PatchMapping
4. Usage of those simpler, specialized versions is recommended for simple HTTP method mappings.


@RequestParam
----------
1. @RequestParam is used to bind web request parameters to controller method
   parameter.
   ![img_7.png](img_7.png)
2. Because Servlet API combines query parameters and form data into a single
   parameters map, it is possible to use @RequestParam annotation to map:
   1. query parameters
   2. form data
   3. parts in multipart requests
3. @RequestParam allows you to specify following parameters:
   1. name – the name of request parameter to bind
   2. required - whether the parameter is required or not
      1. by default parameter is required and in case of it being absent exception will be
         thrown
      2. If switched to false, in case of parameter being absent null value will be provided
         or value pointed out by defaultValue property
   3. defaultValue – allows you to specify default value to use in case of absence
      of optional parameter
   4. @RequestParam annotation also supports Java 8 Optional, so following will
      be equal:
      ![img_8.png](img_8.png)
   5. @RequestParam also supports additional use cases, like:
      1. Mapping all request parameters to Map
         ![img_9.png](img_9.png)
      2. Mapping all values to List
         ![img_10.png](img_10.png)
   6. Example
      ```java
      @Controller
      public class IndexController {

        // curl localhost:8080/actionA?name=John
        // curl localhost:8080/actionA
        @GetMapping("/actionA")
        @ResponseBody
        public String actionA(@RequestParam("name") String name) {
            return String.format("Retrieved name = [%s]\n", name);
        }

        // curl "localhost:8080/actionB?name=John&city=NYC"
        // curl localhost:8080/actionB?name=John
        @GetMapping("/actionB")
        @ResponseBody
        public String actionB(@RequestParam("name") String name, @RequestParam(value = "city", required =     false) String city) {
            return String.format("Retrieved name = [%s], city = [%s]\n", name, city);
        }

        // curl "localhost:8080/actionC?name=John&city=NYC"
        // curl localhost:8080/actionC?name=John
        @GetMapping("/actionC")
        @ResponseBody
        public String actionC(@RequestParam("name") String name, @RequestParam(value = "city", required =     false, defaultValue = "N/A") String city) {
            return String.format("Retrieved name = [%s], city = [%s]\n", name, city);
        }

        // curl "localhost:8080/actionD?name=John&city=NYC"
        // curl localhost:8080/actionD?name=John
        @GetMapping("/actionD")
        @ResponseBody
        public String actionD(@RequestParam("name") String name, @RequestParam(value = "city")     Optional<String> city) {
            return String.format("Retrieved name = [%s], city = [%s]\n", name, city.orElse("N/A"));
        }

        // curl "localhost:8080/actionE?name=John&city=NYC&country=US"
        @GetMapping("/actionE")
        @ResponseBody
        public String actionE(@RequestParam Map<String, String> parameters) {
            String parametersAsString = parameters.entrySet().stream()
                .map(entry -> String.format("[%s] -> [%s]", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", "));

            return String.format("Retrieved parameters map = [%s]\n", parametersAsString);
        }

        // curl "localhost:8080/actionF?cities=1,2,3"
        @GetMapping("/actionF")
        @ResponseBody
        public String actionF(@RequestParam("cities") List<String> cities) {
            return String.format("Retrieved cities identifiers = [%s]\n", String.join(", ", cities));
        }

        // curl "localhost:8080/actionG?name=John&city=NYC&country=US"
        @GetMapping("/actionG")
        @ResponseBody
        public String actionG(@RequestParam("name") String name, @RequestParam("city") String city,     @RequestParam("country") String country) {
            return String.format("Retrieved name = [%s], city = [%s], country = [%s]\n", name, city,     country);
        }
       }
      ```

@PathVariable
---------
1. The main difference between @RequestParam and @PathVariable is a purpose of each annotation.
2. @PathVariable is responsible for mapping parts of URI, marked with usage of URI templates variables
   to controller method parameters. URI templates are identifiers surrounded with curly brackets.
   ![img_11.png](img_11.png)
3. The other difference between @RequestParam and @PathVariable is following:
   1. @RequestParam allows you to specify defaultValue property, @PathVariable
      does not
4. Similarities are following, both allows you to:
      1. Specify name of variable to bind
      2. Mark variables as required or optional
      3. Use Java 8 Optional for optional values
      4. Map all parameters to Map
      5. Map list of values for parameter to collection
5. Example
   ```java
       @Controller
       public class IndexController {
    
        // curl localhost:8080/actionA/cities/ORD
        // curl localhost:8080/actionA/cities/LAX
        @GetMapping("/actionA/cities/{city}")
        @ResponseBody
        public String cityByCode(@PathVariable("city") String city) {
            return String.format("Retrieved city = [%s]\n", city);
        }
    
        // curl localhost:8080/actionB/countries/US/cities/DEN
        // curl localhost:8080/actionB/countries/PL/cities/KRK
        @GetMapping("/actionB/countries/{country}/cities/{city}")
        @ResponseBody
        public String countryAndCityByCode(@PathVariable("country") String country, @PathVariable(value = "city") String city) {
            return String.format("Retrieved country = [%s], city = [%s]\n", country, city);
        }
    
        // curl localhost:8080/actionC/countries/US/cities/DEN
        // curl localhost:8080/actionC/countries/US
        @GetMapping({"/actionC/countries/{country}/cities/{city}", "/actionC/countries/{country}"})
        @ResponseBody
        public String countryAndOptionalCityByCode(@PathVariable("country") String country, @PathVariable(value = "city", required = false) String city) {
            return String.format("Retrieved name = [%s], city = [%s]\n", country, city);
        }
    
        // curl localhost:8080/actionD/countries/US/cities/DEN
        // curl localhost:8080/actionD/countries/US
        @GetMapping({"/actionD/countries/{country}/cities/{city}", "/actionD/countries/{country}"})
        @ResponseBody
        public String countryAndOptionalCityByCodeJava8(@PathVariable("country") String country, @PathVariable(value = "city") Optional<String> city) {
            return String.format("Retrieved name = [%s], city = [%s]\n", country, city.orElse("N/A"));
        }
    
        // curl localhost:8080/actionE/countries/US/cities/DFW/zip/75038
        @GetMapping("/actionE/countries/{country}/cities/{city}/zip/{code}")
        @ResponseBody
        public String actionE(@PathVariable Map<String, String> parameters) {
            String parametersAsString = parameters.entrySet().stream()
                    .map(entry -> String.format("[%s] -> [%s]", entry.getKey(), entry.getValue()))
                    .collect(Collectors.joining(", "));
    
            return String.format("Retrieved parameters map = [%s]\n", parametersAsString);
        }
    
        // curl localhost:8080/actionF/countries/US,PL,UK
        @GetMapping("/actionF/countries/{countries}")
        @ResponseBody
        public String actionF(@PathVariable("countries") List<String> countries) {
            return String.format("Retrieved cities identifiers = [%s]\n", String.join(", ", countries));
        }
       }
    ```
   
parameter types for a controller method
-----------------------------
1. `WebRequest, NativeWebRequest` – Access to HTTP request details, parameters, also
   request and session attributes, without direct use of the Servlet API
2. `javax.servlet.ServletRequest` - object to provide client request information,
   allows access to parameters, attributes and other request details without direct use of
   Spring Interfaces
3.` javax.servlet.ServletResponse` – object created by servlet container, passed to
   service method of servlet, used by servlet to send a response to the client
3. `javax.servlet.http.HttpSession` – allows access to session information and
   attributes, also enforces HTTP session for request
4. `javax.servlet.http.PushBuilder` - Servlet 4.0 push builder API for
   programmatic HTTP/2 resource pushes, allows resources to be delivered in advance by
   the server, resulting in a faster load time
5. `java.security.Principal` - currently authenticated user
6. `HttpMethod` – HTTP method used for request, one of GET, HEAD, POST, PUT,
   PATCH, DELETE, OPTIONS, TRACE
7. `java.util.Locale `- request locale, determined by the most specific
   LocaleResolver available
8.` java.util.TimeZone + java.time.ZoneId` - time zone associated with the
   current request, as determined by a LocaleContextResolver.
8. `java.io.InputStream, java.io.Reader` – allows access to raw request body as
   exposed by the Servlet API
9. `java.io.OutputStream, java.io.Writer `– allows to create raw response as
   exposed by the Servlet API
10. `HttpEntity<B> `- container object that exposes request headers and body, body is
    converted with usage of HttpMessageConverter
11. j`ava.util.Map, org.springframework.ui.Model,
    org.springframework.ui.ModelMap` – used to expose data to templates as part of
    view rendering
12. `RedirectAttributes` – specify attributes to use in case of redirect, regular
    attributes will be added to query string and flash attributes will be kept temporarily
    until end of request, flash attributes are kept typically in the session and are removed
    immediately after request is completed
13. `Errors, BindingResult `– used to gain access to form validation and binding data
    results, can be used with @ModelAttribute, @RequestBody or @RequestPart
    argument, Errors and BindingResult argument must be declared immediately
    after the validated method argument
14. `SessionStatus + class-level @SessionAttributes` – useful for multi step form
    processing, @SessionAttributes allows to keep @ModelAttribute objects
    between requests and SessionStatus allows to clean session variables when form
    processing is done
15. `UriComponentsBuilder` – used to build URLs relative to current scheme, host, port,
    contextPath etc.
16. `Any other argument `– if a method argument is not matched against types defined
    before, and it is a simply type, it is treated as` @RequestParam`, if it is a complex
    type, it is treated as `@ModelAttribute`
17. [Source Code](UsingControllerParameters)

Annotations might you use on a controller method parameter
------------------
1. `@RequestParam` - access to the Servlet request parameters, including multipart files,
   parameters will be automatically converted to declared method argument types,
   parameters can be made optional with usage of required attribute or Optional from
   Java 8, for optional request parameters defaultValue can be set as well
2. `@PathVariable` – access to URI template variables, parameters can be made optional
   with usage of required attribute or Optional from Java 8
3. `@MatrixVariable` - access to name-value pairs in URI path segments as described in
   RFC 3986, allows mapping variables from requests like
   /employees/id=1;name=John
4. `@CookieValue` - bind the value of an HTTP cookie to a method argument in a
   controller, you can bind against simple types or Cookie class, cookie can be set with
   usage of HttpServletResponse, cookie can be set as required or optional via
   required attribute or with Optional from Java 8, when using required attribute,
   defaultValue can be used as well
5.` @RequestHeader `– access request header values or all header key and values when
   binding against a Map
5. `@RequestBody` – allows access to HTTP request body, content will be converted to
   method controller type by HttpMessageConverter, request body can be made
   optional with usage of required attribute or Java 8 Optional, can be used with
   @Valid for bean validation
6. `@RequestPart `– allows to bind multipart HTTP requests to method parameter,
   content will be converted to method controller type, request part can be made
   optional with usage of required attribute or Java 8 Optional, can be used with
   @Valid for bean validation
7. `@RequestAttribute` – allows access to HTTP request attributes populated on serverside during HTTP request by filter or interceptor, can be made optional with usage of
   required attribute or Java 8 Optional
8. `@ModelAttribute` - access to an existing attribute in the model (instantiated if not
   present) with data binding and validation applied
9. @SessionAttribute - access to pre-existing session attributes that are managed
   globally, can be made optional with usage of required attribute or Java 8 Optional
10. @SessionAttributes - used to store model attributes in the HTTP Servlet session
    between requests, useful for multi step from processing
11. [Source Code](ControllerParameterAnnotations/src/main/java/com/raghu/controller/para/annotations/controller)

Return types of a controller method
-------------
1. `@ResponseBody` – binds method return value to web response body, complex types
   will be converted with usage of HttpMessageConverter
2. `HttpEntity<B>, ResponseEntity<B> `- allows to specify full response with headers
   and body, ResponseEntity<B> additionally allows you to specify HTTP status code
3. `HttpHeaders` – allows to return response only with headers, without body
4. `String` – allows to return logical name of view to use when rendering response, view
   will be resolved by ViewResolver, usually used with implicit model through
   @ModelAttribute parameters or explicit model by declaring Model method
   parameter
5. `View` – allows to return instance of view, like JstlView, ThymeleafView,
   FreeMarkerView, usually used with implicit model through @ModelAttribute
   parameters or explicit model by declaring Model method parameter
6. `Map, Model` – allows you to specify attributes to be added to the implicit model, with
   the view name implicitly determined through a RequestToViewNameTranslator
7. `@ModelAttribute` – allows you to specify an attribute to be added to the model, with
   the view name implicitly determined through a RequestToViewNameTranslator
8. `ModelAndView `- view and model attributes to use and, optionally, a response status,
   view can be specified by logical name or instance of view can be passed, model can be
   specified as named object or Map
9. `void` – method that returns void can correctly handle request by using
   ServletResponse or OutputStream as parameter, or @ResponseStatus
   annotation, if none of previous are used RequestToViewNameTranslator will
   identify view based on request, void return type can also indicate “no response body”
   for REST controllers
10. `DeferredResult<V> `- allows to specify result for controller asynchronously from
    different Thread or as result of some event callback, part of integration with Servlet
    3.0 asynchronous request
11. `Callable<V>` - allows to produce return value asynchronously in a Spring MVCmanaged thread
12. `ListenableFuture<V>, CompletableFuture<V>, CompletionStage<V>` - allows
    to return set of chained, asynchronous operations, with callbacks and transformations
13. `ResponseBodyEmitter, SseEmitter` – allows to send objects in stream
    asynchronously, objects will be converted with usage of HttpMessageConverter, can
    be used with ResponseEntity, both classes have the same goal, however
    SseEmitter uses Server-Sent Events standardized with W3C SSE specification
14. `StreamingResponseBody `– allows to write to the response OutputStream
    asynchronously
15. `Reactive types` – allows to use Reactive types for streaming scenarios, handled by
    ReactiveAdapterRegistry
16. [Source Code](ControllerReturnTypes/src/main/java/com/raghu/controller/return/types/controller)






