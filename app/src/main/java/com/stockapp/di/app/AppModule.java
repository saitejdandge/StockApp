package com.stockapp.di.app;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
abstract class AppModule {

    @Provides
    @Singleton
    static Retrofit provideRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        return new Retrofit
                .Builder()
                .baseUrl("http://theshapedev.ap-south-1.elasticbeanstalk.com/")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }


//    @Binds
//    @IntoMap
//    @ViewModelKey(CancelOrderBottomsheetViewModel.class)
//    public abstract ViewModel bindCancelOrderBottomsheetViewModel(CancelOrderBottomsheetViewModel viewModel);
//
}
