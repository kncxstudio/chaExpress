package wang.junqin.chaexpress.utils.net.impl;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import wang.junqin.chaexpress.utils.net.IRequest;
import wang.junqin.chaexpress.utils.net.NetworkInterceptor;

/**
 * Created by KN on 2017/5/29.
 */

public class RetrofitEntity {

    public static String API_URL = "http://www.kuaidi100.com";
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new NetworkInterceptor())
            .build();


    public static IRequest create(){


        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();



        return retrofit.create(IRequest.class);
    }
}
