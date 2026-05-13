# Let's Communicate

## Overview
- Android application with package name `com.example.imagepro`.
- Modules: `app`, `openCVLibrary3413`.
- Launcher activity: `MainActivity`.

## Features
- Camera-based sign recognition using OpenCV and TensorFlow Lite models loaded from assets: `hand_model.tflite`, `Sign_language_model.tflite`, `custom_label.txt`.
- Text-to-speech output in the camera flow.
- Text-to-sign view that maps typed letters to Firebase Storage image URLs.
- Embedded YouTube player for sign-language learning video (video ID `S0Q4gqBUs7c`).
- Login, registration, and OTP email verification backed by local SQLite storage.
- Navigation drawer with `HomeFragment` and `ProfileFragment`.

## Project structure
- Application module: `app`
- OpenCV module: `openCVLibrary3413`
- Assets:
  - `app/src/main/assets/Sign_language_model.tflite`
  - `app/src/main/assets/hand_model.tflite`
  - `app/src/main/assets/model2.tflite`
  - `app/src/main/assets/custom_label.txt`

## Build and run
- Android Gradle project with the Gradle wrapper scripts: `gradlew`, `gradlew.bat`.
- Android Gradle Plugin version: 7.4.2.
- compileSdkVersion: 34, minSdkVersion: 21, targetSdkVersion: 29.

## Dependencies
- TensorFlow Lite: `tensorflow-lite-metadata`, `tensorflow-lite-gpu`, `tensorflow-lite-support`, `tensorflow-lite-task-vision`, `tensorflow-lite-task-text`
- Firebase: `firebase-database`, `firebase-auth`
- OpenCV module: `openCVLibrary3413`
- Mail: `com.sun.mail:android-mail`
- Glide: `com.github.bumptech.glide:glide`
- YouTube Player: `com.pierfrancescosoffritti.androidyoutubeplayer:core`

## Permissions
- `android.permission.CAMERA`
- `android.permission.INTERNET`
- `android.permission.GET_ACCOUNTS`

## Result video
- [resultvideo.mp4](resultvideo.mp4)


## Credits
- Dependencies listed above are used in this project.
