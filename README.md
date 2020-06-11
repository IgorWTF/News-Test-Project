## Tinkoff News - test task
MVVM + Kotlin + Room + Retrofit + RxJava + Dagger

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
