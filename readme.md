# Spring Mvc Jpa Sample

This is a basic spring traditional mvc app with the following characteristics

1. Straight spring-boot mvc
2. Uses `Undertow` vs `TonmmyCat` for Servlet support
3. Uses `Hiroku` as the pooled connection pool manager and not the default tommycat one.d
4. Uses H2 to simplify quickly cloaning and running the app.
5. Uses Jpa/Hibernate for persistence.
6. Uses `swagger` for simple interaction with the REST service

### Why switch from Tomcat

For most REST-based services a simple servlet container is fine.  To make your app's memory footprint smaller,
and to help your app run faster, use `Undertow`.  It is a very lightweight, fast container.  The only modifications
you'll have to make is to change your POM or build.gradle file, exclude tomcat, and include udertow.

To switch from Tomcat to another Servlet, such as Undertow, just modify your POM or gradle file as defined [here](https://docs.spring.io/spring-boot/docs/1.5.10.RELEASE/reference/htmlsingle/#howto-use-undertow-instead-of-tomcat)


