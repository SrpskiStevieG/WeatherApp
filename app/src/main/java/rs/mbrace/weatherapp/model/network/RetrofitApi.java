package rs.mbrace.weatherapp.model.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rs.mbrace.weatherapp.model.json.TodayWeather;
import rs.mbrace.weatherapp.model.json.Forecast;

public interface RetrofitApi {

    String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    String JSON_MODE_PATH = "json";
    String API_PATH = "d26a8c8abe42737bfc76074a2433e46d";

    @GET("weather")
    Call<TodayWeather> getTodayWeather(@Query("q") String cityName, @Query("mode") String mode, @Query("APPID") String appID);

    @GET("weather")
    Call<TodayWeather> getTodayWeather(@Query("q") String cityName, @Query("country") String countryCode, @Query("mode") String mode, @Query("APPID") String appID);

    @GET("weather")
    Call<TodayWeather> getTodayWeather(@Query("id") long cityID, @Query("mode") String mode, @Query("APPID") String appID);

    @GET("forecast")
    Call<Forecast> getForecast(@Query("q") String cityName, @Query("mode") String mode, @Query("APPID") String appID);

    @GET("forecast")
    Call<Forecast> getForecast(@Query("q") String cityName, @Query("country") String countryCode, @Query("mode") String mode, @Query("APPID") String appID);

    @GET("forecast")
    Call<Forecast> getForecast(@Query("id") long cityID, @Query("mode") String mode, @Query("APPID") String appID);
}
