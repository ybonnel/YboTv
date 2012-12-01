mvn clean install
adb install -r target/ybo-tv-android-1.1-SNAPSHOT.apk
adb shell am start -n fr.ybo.ybotv.android/.activity.LoadingActivity
