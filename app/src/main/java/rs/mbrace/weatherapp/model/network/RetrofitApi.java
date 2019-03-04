package rs.mbrace.weatherapp.model.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rs.mbrace.weatherapp.model.json.CurrentForecast;

public interface RetrofitApi {

    String BASE_URL = "api.openweathermap.org/data/2.5/";
    String JSON_MODE_PATH = "&mode=json";
    String API_PATH = "&APPID=d26a8c8abe42737bfc76074a2433e46d";

    @GET("weather?q={city}" + JSON_MODE_PATH + API_PATH)
    Call<CurrentForecast> getCurrentForecast(@Path("city") String cityName);

    @GET("weather?q={city},{country}" + JSON_MODE_PATH + API_PATH)
    Call<CurrentForecast> getCurrentForecast(@Path("city") String cityName, @Path("country") String countryCode);

    @GET("weather?id={id}" + JSON_MODE_PATH + API_PATH)
    Call<CurrentForecast> getCurrentForecast(@Path("id") int cityID);

    @GET("forecast?q={city}" + JSON_MODE_PATH + API_PATH)
    Call<CurrentForecast> getFiveDayForecast(@Path("city") String cityName);

    @GET("forecast?q={city},{country}" + JSON_MODE_PATH + API_PATH)
    Call<CurrentForecast> getFiveDayForecast(@Path("city") String cityName, @Path("country") String countryCode);

    @GET("forecast?id={id}" + JSON_MODE_PATH + API_PATH)
    Call<CurrentForecast> getFiveDayForecast(@Path("id") int cityID);
}
