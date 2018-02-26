# Spring Mvc Jpa Sample

This is a basic spring traditional mvc app with the following characteristics

1. Straight spring-boot mvc
2. Uses `Undertow` vs `TonmmyCat` for Servlet support
3. Uses `HikariCP` as the pooled connection pool manager and not the default tommycat one
4. Uses H2 to simplify quickly cloaning and running the app.
5. Uses Jpa/Hibernate for persistence.
6. Uses `swagger` for simple interaction with the REST service

### Why switch from Tomcat

For most REST-based services a simple servlet container is fine.  To make your app's memory footprint smaller,
and to help your app run faster, use `Undertow`.  It is a very lightweight, fast container.  The only modifications
you'll have to make is to change your POM or build.gradle file, exclude tomcat, and include udertow.

To switch from Tomcat to another Servlet, such as Undertow, just modify your POM or gradle file as defined [here](https://docs.spring.io/spring-boot/docs/1.5.10.RELEASE/reference/htmlsingle/#howto-use-undertow-instead-of-tomcat)

In addition, the default connection pool that comes with Tomcat is also fairly slow.  There's an all java based connection
pool called `HikariCP`.  This is an all-java based connection pool known for it's lightweight and speed.  We'll switch
the project over to use that also.

You should only have to exclude `tomcat-jdbc` and then include the `HikariCP'.  For a sample click [here](https://www.mkyong.com/spring-boot/spring-boot-jdbc-mysql-hikaricp-example/)

As with the tomcat starters in the build.gradle file, you'll have to exclude tomcat-jdbc, and then include the HikariCP.  Just have a look-see in this project's 
build.gradle file 

### Configuring JPA & HikariCP

The next step is to get a basic database connection going with JPA using HikariCP.

Have a look at what application.properties file entries were added.

#### First
 Just set the required spring.datasource fields for db driver, URL, user-id and password.  `Remember` you should externalize your user-id and password and even the URL.
 
 #### Next
 Create an `application-local.propereties` file.  Just copy the main props file.  You can then customize this file and use it just when you are running locally
    
`IMPORTANT`
    
You *must* set your spring profile to `local`
To do this you can set an environment variable at the console or in your IDE and set `SPRING_PROFILES_ACTIVE=local`
    
At this point, you can alter your local confiruation so it can be different than your run configuration when you push.  We still haven't 
created any jpa EntityManagerFactory nor entities.  We'll do that on the next step.  Just be sure your application will start up.

### Create some JPA entities

#### But first
We're going to start using `Lombock` since we are not creating POJOs  Among other things, `Lombock` will automatically create default constructors, getters and
setters, and many many other things.  For more information click [here](https://projectlombok.org/)

### Back to JPA.

We'll start with a COMPANY entity which will hold basic information about a company.  No revelations here just check out the [Company](src/com/astar/andy.dao.entities.Company.java) entiry

