package kg.geektech.wapp.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.load.engine.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.wapp.data.models.Sys;
import kg.geektech.wapp.data.repositories.MainRepositories;

@HiltViewModel
public class CountryViewModel extends ViewModel {

    private MainRepositories repositories;
    public LiveData<Resource<Sys>> liveData;
    @Inject
    public CountryViewModel(MainRepositories repositories) {
        this.repositories = repositories;
    }

    public void getWeatherById(String id){

       // liveData = repositories.getWeatherByCity();
    }
}
