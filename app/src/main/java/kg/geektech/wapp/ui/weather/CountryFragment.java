package kg.geektech.wapp.ui.weather;

import android.view.View;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.wapp.base.BaseFragment;
import kg.geektech.wapp.databinding.FragmentCountryBinding;


@AndroidEntryPoint
public class CountryFragment extends BaseFragment<FragmentCountryBinding> {


    @Override
    protected FragmentCountryBinding bind() {
        return FragmentCountryBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setUpViews() {

    }

    @Override
    protected void setUpListeners() {
      binding.btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              //Prefs prefs = new Prefs(requireContext());
              //prefs.saveCity(binding.etCity.getText().toString)

              String city = binding.etCity.getText().toString();
              controller.navigate(CountryFragmentDirections.actionCountryFragmentToWeatherFragment().setCity(city));
          }
      });

    }

    @Override
    protected void setUpObservers() {

    }

    @Override
    protected void callRequests() {


    }


}

