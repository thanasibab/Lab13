# Lab13 - SOEN 487

This is the template provided to start Lab 13

We encourage you to use either IntelliJ IDE **or** maven from command line in order to get the best support from our TAs.

## Included Projects ##

1. The server/REST API
    * Located in /server
    * Is used to hold the REST API built in tutorial 3

## IntelliJ Instructions ##

* For each included project, navigate to the directory and find the pom.xml file, right

click and click on "Add as Maven Project" to load up the project and start working on it.

* Browse to desired class to run the main method
* Right click associated class and choose Run main()

## Maven Instructions ##

    Run the following commands in the associated project directory:

    Compile:
        mvn install

    Run (Server)
        mvn exec:java -Pserver

    Clean:
        mvn clean
