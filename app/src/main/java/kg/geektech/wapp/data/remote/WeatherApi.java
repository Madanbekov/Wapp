package kg.geektech.wapp.data.remote;

import kg.geektech.wapp.data.models.MainResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather")
    Call<MainResponse> getWeathers(@Query("q") String city,
                                   @Query("appid") String apiKey,
                                   @Query("units") String units);
}
