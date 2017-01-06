# Sample_Android

###Coding Challenge:

You will build an Android app that displays information about different locations.
This app will fetch the information for the locations from the following URL: [link](http://localsearch.azurewebsites.net/api/Locations "Google's Homepage")

> The app must be written in Java, should run the latest stable version of Android and must run on the latest stable release of Android Studio. You are allowed to use 3rd party libraries in cases where you think it makes sense (please explain why you use them).

###Test Requirements
1. The first screen should show the locations fetched from the URL provided in a list format
   - Locations must be sorted, initially, by name
2. When the user taps on a location, he should be taken to the details screen:
   - The title of the screen must be the name of the location
   - This screen should contain a map that occupies half of the screen; the map should be centered on the coordinates of the location, and displaying a pin on those coordinates
   - In portrait, below the map in, the user should be able to see the location name, address, arrival time, latitude and longitude
     -- In landscape, this formation should be location at the right side of the map
   - The user has the ability to go back to the list of locations from this screen

###Android Studio Settings

```
Android Studio 2.2
Build #AI-145.3276617, built on September 15, 2016
JRE: 1.8.0_76-release-b03 x86_64
JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
```
Works on Android Sdk Version - 15 to 24

###Libraries used

1. Recyclerview and CardView
2. Volley
3. Butterknife
4. Joda Time


## Screen Shots
<img src="https://raw.github.com/pravinkandala/sampleandroid/master/Screens/Screen1.jpg" width="180" /> <img src="https://raw.github.com/pravinkandala/sampleandroid/master/Screens/Screen2.jpg" width="180" /> <img src="https://raw.github.com/pravinkandala/sampleandroid/master/Screens/Screen3.jpg" height="180" />




