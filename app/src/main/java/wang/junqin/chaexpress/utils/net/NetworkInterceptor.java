package wang.junqin.chaexpress.utils.net;

import android.os.SystemClock;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by KN on 2017/6/6.
 */
public class NetworkInterceptor implements Interceptor {
    private final static String TAG = NetworkInterceptor.class.getSimpleName();


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request requestOrigin = chain.request();
        Headers headersOrigin = requestOrigin.headers();
        Headers headers = headersOrigin.newBuilder().set("User-Agent", "UA:" + System.currentTimeMillis()).build();
        Request request = requestOrigin.newBuilder().headers(headers).build();
        Response response = chain.proceed(request);
        return response;
    }


}
