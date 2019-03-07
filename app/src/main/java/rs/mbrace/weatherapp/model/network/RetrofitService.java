package rs.mbrace.weatherapp.model.network;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitService {

    private static RetrofitService instance;
    private Retrofit mRetrofit;

    public static RetrofitService getInstance(){
        if(instance == null){
            instance = new RetrofitService();
        }
        return instance;
    }

    public Retrofit getRetrofit(){
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(RetrofitApi.BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
