# Buslines application

<img alt="example" src="/src/main/resources/img/example.png"/>

## Description
A webapplication that displays top 10 buslines with the most stops along their jorney using the SL API.

There is also an example image of how the webpage is
displayed in the img folder.
## Configuration
The application is configured to run on port 8082 but can be configured by the user.

## How to use

- Build and run as explained below
- Open a browser and go to http://localhost:8082/bus-lines
- The API will load and sort the data
- The top 10 buslines with the most stops will be displayed
- You can also toggle the buslines to display or hide their stops.

## How to build and run

Build:

    ```./gradlew build```

Run:

        ```./gradlew run```
