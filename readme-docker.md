# Dockerize this Spring-Boot App

There are many good on-line examples of how to do this, so I won't regurgitate that here.

Click [here](https://thepracticaldeveloper.com/2017/12/11/dockerize-spring-boot/).  This is a very good, concise how-to
for setting-up your spring-boot app. 

For a little for details from our beloved [Baeldung](www.baldung.com) you can also play with 
[his](http://www.baeldung.com/dockerizing-spring-boot-application) example.

The [spring.io docs](https://spring.io/guides/gs/spring-boot-docker/) is also good.

The primary docker documentation can be found [here](https://docs.docker.com/get-started/)

I elected to build-out my Docker integrations by hand; however, there's a plug-in available for 
Docker for `IntellJ`,  If you just create an empty `Dockerfile` in the root of your project folder, then the IDE should
then tell you there's a plug-in for `Dockerfile`, and just select the option to install it.

Also, there are several pretty good `Maven` and `Gradle` build plugins that you can add to your pom.xml or
build.gradle files to integrate Docker builds right into your application.

For a comparison of [Maven Plugins](https://github.com/fabric8io/shootout-docker-maven) check this and go with the one you like.

For `Gradle` [this one](https://github.com/bmuschko/gradle-docker-plugin) seems to be the most popular.

### The Dockerfile file

The app uses an internal db so it is simple to clone and run the application.  This also holds true with the integration with 
[Docker](https://docs.docker.com/get-started/).  To work with [Docker](https://docs.docker.com/get-started/) you simply need to:
1.  Install Docker on your box.
2.  In the root of your project folder create a [Dockerfile](Dockerfile) *like* the one in this project.
3.  For the most part you only have to change the location of where your jar is located.  If you're using `Gradle` then the file herein is correct  If you're using `Maven` then your output directory is, most likely, `./target/...`

To get everything going
1.  go to a terminal window and move to the root folder of this project.
2.  To be sure everything is working execute a gradle test by typing `./runApp.sh test`.  This command will set the required environment variables and then execute a test of the application.
3.  You should see successful test.
4.  Then go ahead and use gradle to clean and rebuild your jar.  To do this execute the command `./gradlew clean bootRepackage`
4.  You can then execute `./BuildDockerImage.sh` and be sure there are no errors.  This creates a Docker _image_ of the application.  Once an image is created is can be executed.  This would be the same thing as starting the app from within `IntellJ` or `Eclipse`
5.  Just to be sure we can check to be sure our image got created.  To do that execute the following command: `docker images` or `docker image ls`  You should see something like
```text
REPOSITORY                     TAG                 IMAGE ID            CREATED             SIZE
spring-mvc-test-manual-build   latest              39da4fb7ff77        8 minutes ago       119MB
openjdk                        8-jre-alpine        b1bd879ca9b3        8 weeks ago         82MB
```

Don't worry if your display is slightly different.  You're looking for `openjdk` and `spring-mvc-test-manual-build`

Now that you have an image you can start a `Docker Container` that will have THIS application runing inside, and is available
via port 8080.  To do this now you can run the following `docker run` command:

docker run -p 8080:8080 spring-mvc-test-manual-build

If everything worked, your console output should look almost exactly the same as if you executed the program from IntellJ or Eclipse or with ./gradlew bootRun

Now you can hit your service at localhost:8080/... like any other Spring Boot MVC app.

Remember `Docker build...` creates a Docker _image_.  The `run` command creates a `Docker Container` and it is this container your application
will be running in.

Common Commands

Most of the commands are centered around the creation and management of _images_ and _containers_.  You can always get
help by typing `Docker --help` or `Docker command --help`

Here are the commands that were used in this project:

To List all Images
    docker images
    
To build an image from a *Dockerfile* (*Note: the . at the end of the build command is REQUIRED*)
    docker build -t spring-mvc-test-manual-build .
List all Containers
    docker container ls
    
Remove any unused images
    docker image prune    
Force delete an unused image
    docker image rm -f f7767ea37898

To create a docker container from a previously created image:
    docker run -p 8080:8080 spring-mvc-test-manual-build

 Again, for additional information click [here](https://docs.docker.com/get-started/) and review the full docker documentation
 