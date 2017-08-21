package yurinevenchenov1970.github.com.itunesfinder.net;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Yuri Nevenchenov on 8/21/2017.
 */

public class ApiClient {
    
    private static final String BASE_URL = "https://itunes.apple.com/";
    private static Retrofit sRetrofit = null;

    private ApiClient() {
        throw new IllegalStateException("Can't create object");
    }

    public static Retrofit getClient() {
        if (sRetrofit == null) {
                sRetrofit = new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(JacksonConverterFactory.create())
                                .build();
            }
        return sRetrofit;
    }
}