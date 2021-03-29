# MealsFood
<img src="https://github.com/alvayonara/MealsFood/blob/main/image/mealsfood-thumb.png" width="1000"/>

[![alvayonara](https://circleci.com/gh/alvayonara/MealsFood.svg?style=shield)](https://circleci.com/gh/alvayonara/MealsFood) <br/>
A Recipe Android Apps using Koin, RxJava, Jetpack (Room, ViewModel, LiveData) based on MVVM architecture purely written in Kotlin.
There are 4 features on this apps:
1. Discover popular foods and get foods by category
2. See information foods selected (ingredients, etc.)
3. Save food to favorite
4. Search food by name
<br>

## ⚡️ Download
<a href='https://play.google.com/store/apps/details?id=com.alvayonara.mealsfood'><img alt='Get it on Google Play' src='https://github.com/alvayonara/MealsFood/blob/main/image/google-play-badge-small.png' height="100" width="250"/></a>
##### Google Play and the Google Play logo are trademarks of Google LLC.
<br>

## 💡 Stack and Libraries
* [Kotlin](https://https://kotlinlang.org/) - built with 100% Kotlin.
* [Jetpack Components](https://developer.android.com/jetpack/)
  - Navigation
  - Room
  - ViewModel
  - Material Components
  - ViewBinding
* [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) - separates code into layers.
* [Modularization](https://https://developer.android.com/guide/app-bundle/play-feature-delivery/) - separating logical components of project into discrete modules.
* [Dynamic Feature](https://medium.com/mindorks/dynamic-feature-modules-the-future-4bee124c0f1/) - separate certain features and resources from the base module and include them in app bundle.
* [NetworkBoundResource](https://developer.android.com/jetpack/guide/) - offline first strategy.
* [Koin](https://insert-koin.io/) - dependency injection.
* [Retrofit2](https://github.com/square/retrofit/) - REST APIs.
* [GSON](https://github.com/google/gson/) - JSON serialization.
* [RxJava](https://github.com/ReactiveX/RxJava/) - reactive programming.
  - Retrofit - Flowable
  - Room - Flowable
  - Search - Observable
* [Glide](https://github.com/bumptech/glide/) - load images.
* [Timber](https://github.com/JakeWharton/timber/) - logger.
* [Shimmer](https://github.com/facebook/shimmer-android/) - loading indicator.
* [Expandable TextView](https://github.com/Manabu-GT/ExpandableTextView/) - expand/collapse TextView.
* [Leak Canary](https://github.com/square/leakcanary/) - memory leak detection.
* [OWASP Certificate Pinning](https://owasp.org/www-community/controls/Certificate_and_Public_Key_Pinning/) - prevent fake certificate and causing [Man-in-the-middle attack](https://owasp.org/www-community/attacks/Man-in-the-middle_attack/).
* [SQLCipher](https://github.com/sqlcipher/android-database-sqlcipher/) - local database encryption.
* [ProGuard](https://github.com/Guardsquare/proguard/) - prevent reverse engineering.
* [Mockito](https://github.com/mockito/mockito/) - unit testing.
<br>

## 💎 Architecture
This apps uses Clean Architecture to separate code into layers (Data - Domain - Presentation).
<img src="https://github.com/alvayonara/MealsFood/blob/main/image/app-structure.png" width="600"/>
<br>

## 🚀 MAD Scoreboard
<img src="https://github.com/alvayonara/MealsFood/blob/main/image/summary.png"/>

<img src="https://github.com/alvayonara/MealsFood/blob/main/image/jetpack.png"/>
<br>

## 🛠 Configure project
The foods information provided in this apps belongs to **themealdb.com**, so you need an API Key <b>Production</b> from their platform in order to display it. You can get one from [this link](https://www.themealdb.com/api.php/).

Once you have it, create a _apikey.properties_ file at the root folder with the following information:

```
THE_MEAL_DB="YOUR API KEY HERE"
```

Replace YOUR API KEY HERE with your API Key from TheMealDB.
<br>

## 📱 App Preview
<img src="https://github.com/alvayonara/MealsFood/blob/main/image/app-preview.jpg" width="1000"/>
