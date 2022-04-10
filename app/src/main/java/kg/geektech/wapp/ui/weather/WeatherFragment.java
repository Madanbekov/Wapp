package kg.geektech.wapp.ui.weather;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.wapp.R;
import kg.geektech.wapp.base.BaseFragment;
import kg.geektech.wapp.common.Resource;
import kg.geektech.wapp.data.models.MainResponse;
import kg.geektech.wapp.databinding.FragmentWeatherBinding;

@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {

    private WeatherViewModel viewModel;
    private WeatherFragmentArgs args;

    public WeatherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            args = WeatherFragmentArgs.fromBundle(getArguments());
        }
    }

    @Override
    protected FragmentWeatherBinding bind() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }


    @Override
    protected void setUpViews() {
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
    }

    @Override
    protected void setUpListeners() {
        binding.textCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.countryFragment);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Calendar uh = Calendar.getInstance();
        int timeOfDay = uh.get(Calendar.HOUR_OF_DAY);
        if (timeOfDay >= 0 && timeOfDay < 24){
            binding.imageCity.setImageResource(R.drawable.city_night);
        }
        else {
            binding.imageCity.setImageResource(R.drawable.city_day);
        }
    }

    @Override
    protected void setUpObservers() {

        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<Resource<MainResponse>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Resource<MainResponse> mainResponseResource) {


                switch (mainResponseResource.status) {


                    case SUCCESS: {
                        loadData(mainResponseResource.data);


                    }
                    case LOADING: {
                        break;
                    }
                    case ERROR: {
                        Snackbar.make(binding.getRoot(),
                                mainResponseResource.msg,
                                BaseTransientBottomBar.LENGTH_LONG)
                                .show();
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected void callRequests() {
        viewModel.getWeathers(args.getCity());
    }

    private void loadData(MainResponse response) {

       binding.textTemp.setText(String.valueOf((int) Math.round(response.getMain().getTemp())));
        binding.textHumidityCifry.setText(response.getMain().getHumidity() + " %");
        binding.textPressureCifry.setText(response.getMain().getPressure() + " mBar");
        binding.textWindCifry.setText(response.getWind().getSpeed() + " km/h");
        binding.maxTemp.setText(response.getMain().getTempMin() + " \u2103");
        binding.minTemp.setText(response.getMain().getTempMax() + " \u2103");
        binding.textCity.setText(String.valueOf(response.getName()));
        binding.textSunriseCifry.setText(getDate(response.getSys().getSunrise(), "hh:mm a"));
        binding.textSunsetCifry.setText(getDate(response.getSys().getSunset(), "hh:mm a"));
        binding.textDaytimeCifry.setText(getDate(response.getDt(), "hh:mm"));
        binding.textWeather.setText(String.valueOf(response.getWeather().get(0).getDescription()));

        String urlImg = "http://openweathermap.org/img/wn/"
                + response.getWeather().get(0).getIcon() + ".png";
        Glide.with(requireActivity()).load(urlImg).into(binding.imageWeather);
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy  HH:mm");
        String currentDate = format.format(new Date());
        binding.date.setText(currentDate);

    }

    public static String getDate(Integer miliS, String data) {

        SimpleDateFormat format = new SimpleDateFormat(data);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(miliS);
        return format.format(calendar.getTime());

    }


}