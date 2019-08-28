package com.example.suyeon.mvvmarchitecture.di.module;

import android.app.Application;
import android.content.Context;

import com.example.suyeon.mvvmarchitecture.BuildConfig;
import com.example.suyeon.mvvmarchitecture.data.PreferenceHelper;
import com.example.suyeon.mvvmarchitecture.data.rest.HeaderInterceptor;
import com.example.suyeon.mvvmarchitecture.data.rest.NetworkCheckInterceptor;
import com.example.suyeon.mvvmarchitecture.data.rest.NetworkService;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


// 연결할 Module을 정의
@Module(includes = ViewModelModule.class)
public abstract class ApplicationModule {

    @Provides
    static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    static OkHttpClient provideOkHttpClient(Context context, PreferenceHelper preferenceHelper) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);


        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(new HeaderInterceptor(preferenceHelper))
                .addInterceptor(new NetworkCheckInterceptor(context))
                .build();
    }

    @Provides
    static NetworkService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }

    @Provides
    static PreferenceHelper providePreferenceHelper(Context context) {
        return new PreferenceHelper(context);
    }

    @Binds
    abstract Context bindContext(Application application);
}
