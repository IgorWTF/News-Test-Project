## Tinkoff News - test task
MVVM + Kotlin + Room + Retrofit + RxJava + Dagger

![TitleImage](https://user-images.githubusercontent.com/26382897/84543334-9fca2e00-ad14-11ea-8dc0-55ea49bf4648.png)

## MainActivity
Contains RecyclerView and SwipeRefreshLayout. Sends requests to the NewsViewModel and responds to updates in the NewsViewModel. When you click on a news item, it opens SecondActivity and gives it the news ID.

## SecondActivity
Contains ScrollView and SwipeRefreshLayout. Sends requests to the NewsViewModel and responds to updates in the NewsViewModel.

## MainViewModel : NewsViewModel
Passes requests to the main repository using RxJava. Contains MuttableLiveData

## MainRepository : NewsRepository
Sends data requests to LocalDataSource. If necessary, uses RemoteDataSource while updating LocalDataSource. Processes data using DataMapperService. Uses RxJava.

## MainLocalDataSource : NewsLocalDataSource
Works with a database using Room. Uses RxJava

## MainRemoteDataSource : NewsRemoteDataSource
Downloaded data from the network using Retrofit. Uses RxJava
