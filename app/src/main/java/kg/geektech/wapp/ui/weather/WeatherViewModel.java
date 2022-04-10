package kg.geektech.wapp.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.wapp.common.Resource;
import kg.geektech.wapp.data.models.MainResponse;
import kg.geektech.wapp.data.repositories.MainRepositories;


@HiltViewModel

public class WeatherViewModel extends ViewModel {

    public LiveData<Resource<MainResponse>> liveData;
    private MainRepositories repositories;

    @Inject
    public WeatherViewModel(MainRepositories repositories) {
        this.repositories = repositories;
    }

    public void  getWeathers(String city){
        liveData = repositories.getWeathers(city);
    }

}
