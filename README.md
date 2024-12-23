# Weather App

This is a Weather App built using Jetpack Compose, Kotlin, and Android Studio. The app provides current weather information for a specified city.

## Features

- Display current temperature with a degree icon
- Show error messages using Snackbar for network issues and invalid city names

## Project Setup

### Prerequisites

- Android Studio Ladybug | 2024.2.1 Patch 3
- Kotlin 1.8.0 or higher
- Gradle 7.0 or higher

### Clone the Repository

```sh
git clone https://github.com/rishirajput/weather-app.git
cd weather-app
```

### Provide Weather API Key

To provide the Weather API key via Gradle command line options, follow these steps:

1. Open `local.properties` file in the root of your project and add the following line:

    ```properties
    weatherApiKey=YOUR_API_KEY
    ```

2. Alternatively, you can pass the API key as a command line option when building or running the project:

    ```sh
    ./gradlew assembleDebug -PweatherApiKey=YOUR_API_KEY
    ```

### Configure Gradle

Ensure that your `build.gradle` file is configured to read the API key from the command line options or `local.properties` file:

```groovy
android {
    // Other configurations...

    defaultConfig {
        buildConfigField "String", "WEATHER_API_KEY", "\"${project.hasProperty('weatherApiKey') ? project.weatherApiKey : 'YOUR_DEFAULT_API_KEY'}\""
    }
}
```

### Build and Run the Project

1. Open the project in Android Studio.
2. Sync the project with Gradle files.
3. Build and run the project on an emulator or physical device.

## Testing

### Unit Tests

To run unit tests, use the following command:

```sh
./gradlew testDebugUnitTest
```

### UI Tests

To run UI tests, use the following command:

```sh
./gradlew connectedAndroidTest
```

### Test Coverage

To generate a test coverage report using JaCoCo, follow these steps:

1. Add the JaCoCo plugin to your `build.gradle` file:

    ```groovy
    plugins {
        id 'jacoco'
    }
    ```

2. Configure JaCoCo in your `build.gradle` file:

    ```groovy
    jacoco {
        toolVersion = "0.8.7"
    }

    android {
        // Other configurations...

        testOptions {
            unitTests.all {
                jacoco {
                    includeNoLocationClasses = true
                }
            }
        }
    }

    tasks.withType(Test) {
        jacoco.includeNoLocationClasses = true
        jacoco.excludes = ['jdk.internal.*']
    }
    ```

3. Create a task to generate the JaCoCo report:

    ```groovy
    task jacocoTestReport(type: JacocoReport) {
        dependsOn 'testDebugUnitTest'

        reports {
            xml.enabled = true
            html.enabled = true
        }

        def fileFilter = ['**/R.class', '**/R$*.class', '**/BuildConfig.*', '**/Manifest*.*', '**/*Test*.*']
        def debugTree = fileTree(dir: "$buildDir/tmp/kotlin-classes/debug", excludes: fileFilter)
        def mainSrc = "$projectDir/src/main/java"

        sourceDirectories.setFrom(files([mainSrc]))
        classDirectories.setFrom(files([debugTree]))
        executionData.setFrom(fileTree(dir: "$buildDir", includes: [
                'jacoco/testDebugUnitTest.exec',
                'outputs/code-coverage/connected/*coverage.ec'
        ]))
    }
    ```

4. Run the tests and generate the coverage report:

    ```sh
    ./gradlew testDebugUnitTest jacocoTestReport
    ```

The coverage report will be generated in the `build/reports/jacoco/jacocoTestReport/html` directory. Open the `index.html` file in a browser to view the coverage report.

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.
