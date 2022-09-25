# Project Title

Asteroid Radar

## Getting Started

Asteroid Radar is an app to view the asteroids detected by NASA that pass near Earth, you can view all the detected asteroids in a period of time, their data (Size, velocity, distance to Earth) and if they are potentially hazardous.

The app is consists of two screens: A Main screen with a list of all the detected asteroids and a Details screen  to display the data of that asteroid once it´s selected in the Main screen list. The main screen will also show the NASA image of the day to make the app more striking.



### Screenshots

![Screenshot 1](starter/screenshots/screen_1.png)
![Screenshot 2](starter/screenshots/screen_2.png)
![Screenshot 3](starter/screenshots/screen_3.png)
![Screenshot 4](starter/screenshots/screen_4.png)

### Dependencies

```
implementation fileTree(dir: 'libs', include: ['*.jar'])
implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
implementation 'androidx.appcompat:appcompat:1.1.0'
implementation 'androidx.core:core-ktx:1.2.0'
implementation 'androidx.constraintlayout:constraintlayout:1.1.3'

implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"

implementation "android.arch.navigation:navigation-fragment-ktx:1.0.0"
implementation "android.arch.navigation:navigation-ui-ktx:1.0.0"

// Download and parse data
implementation "com.squareup.moshi:moshi:1.8.0"
implementation "com.squareup.moshi:moshi-kotlin:1.8.0"
implementation "com.squareup.retrofit2:retrofit:2.6.2"
implementation "com.squareup.retrofit2:converter-moshi:2.5.0"
implementation 'com.squareup.retrofit2:converter-scalars:2.5.0'

// Kotlin coroutines
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.0"
implementation "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"

implementation "androidx.recyclerview:recyclerview:1.1.0"

// Image downloader
implementation 'com.squareup.picasso:picasso:2.5.2'

implementation "androidx.room:room-runtime:2.2.3"
kapt "androidx.room:room-compiler:2.2.3"

implementation "android.arch.work:work-runtime-ktx:1.0.1"

testImplementation 'junit:junit:4.12'
androidTestImplementation 'androidx.test.ext:junit:1.1.1'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
```


## Dependencies


The most important dependencies I used to develop this application are:
- Retrofit to download the data from the Internet.
- Moshi to convert the JSON data we are downloading to usable data in form of custom classes.
- Glide to download and cache images.
- RecyclerView to display the asteroids in a list.
