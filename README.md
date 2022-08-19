# Eastream

Eastream is a Korean Drama streaming guide the helps you find where to legally watch dramas in your country.

## Features

* Get all the Korean Dramas currently available for the top streamers
* Search for show by name and find streamer information
* Create an account and save your favorite shows, organized by streamer

## Dependencies

To use this app, fork the repository and ensure that all dependencies are synced.
This project uses:

* Android Studio
* Kotlin / Jetpack Compose
* Firebase Firestore 
* Firebase Authentication
* Python for populated titles

## Setting Up The App
Here are the dependencies needed for this app.

<details>
<summary>Dependenices: Project Level</summary>

Add these dependencies to the project level gradle

```
repositories {
  google()  // Google's Maven repository
}
```

Dependencies

```
 classpath 'com.google.gms:google-services:4.3.13'
 classpath 'org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31'
```
</details>

<details>
<summary>Dependencies: App Level</summary>

Add these to your add level gradle

Add to Plugins

```
id 'org.jetbrains.kotlin.android'
id 'com.google.gms.google-services'
id  'kotlin-kapt'
id "dagger.hilt.android.plugin"
id 'kotlin-parcelize'
```

Add these to your dependencies

Material Design

```
    implementation 'com.google.android.material:material:1.6.1'
    implementation "androidx.compose.material:material-icons-extended:$compose_version"
```

Firebase

```
    implementation platform('com.google.firebase:firebase-bom:30.3.1')
    implementation 'com.google.firebase:firebase-analytics-ktx'
    implementation 'com.google.firebase:firebase-auth'
    implementation 'com.google.firebase:firebase-firestore'
    implementation 'com.google.android.gms:play-services-auth:20.2.0'
    implementation 'com.google.firebase:firebase-auth-ktx'
```

Coil

```
    implementation "io.coil-kt:coil-compose:1.4.0"
    implementation "io.coil-kt:coil-svg:1.3.2"
```

ViewModel

```    
    implementation 'androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1'
```

Coroutine Lifecycle Scopes

```
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1'
```

Lifecycle

```
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.1'
```

Navigation

```
    implementation "androidx.navigation:navigation-compose:2.5.1"
```

DataStore

```
    implementation "androidx.datastore:datastore-preferences:1.0.0"
```
</details>

Users will also need to set up their Firebase Firestore to have a `Titles` collection prepopulated from the [TMDB Database API](https://www.themoviedb.org/documentation/api?language=en-US)
The second collection is for users and will be generated dynamically by the code

<details>
<summary>Collection Structure</summary>

```
TMDBId : number
backdrop : string
networkImg : map 
networks : array of string
popularity : number
poster : string
showLink : string
summary : string
title : string
voteAvg : number
year : number
```
</details>

## Screenshots
Here are some looks at the main functionality of the app in both light and dark mode (default)

<img src="/Images/SplashScreen.png" alt="Splash Screen" width="200" height="400"> <img src="/Images/Titles.png" alt="Main Screen" width="200" height="400"> 
<img src="/Images/TitlesLt.png" alt="Main Screen in Light Mode" width="200" height="400"> <img src="/Images/TitleInfo.png" alt="Show Details Screen" width="200" height="400"> <img src="/Images/Profile.png" alt="Profile Screen" width="200" height="400"> <img src="/Images/Menu.png" alt="Menu Image" width="200" height="400">

## Author

Eastream is developed by Chinazo Onwukaike.
