package com.example.msi.myapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msi.myapplication.ApiService;
import com.example.msi.myapplication.R;
import com.example.msi.myapplication.datamodel.WeatherInfo;

public class WeatherSampleActivity extends AppCompatActivity implements ApiService.OnWeatherInfoReceived{

    private static final String TAG = "WeatherSampleActivity";
    private ApiService apiService;
    private ProgressBar progressBar;
    private TextView txtWeatherName;
    private TextView txtWeatherDescription;
    private TextView txtTemp;
    private TextView txtHumidity;
    private TextView txtPressure;
    private TextView txtMinTemp;
    private TextView txtMaxTemp;
    private TextView txtWindSpeed;
    private TextView txtWindDegree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_sample);
        apiService = new ApiService(this);

        initViews();

        Button btnSendRequest = (Button)findViewById(R.id.send_request);
        btnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService.getCurrentWeather(WeatherSampleActivity.this,"Tehran");
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }


    private void initViews(){
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        txtWeatherName = (TextView)findViewById(R.id.txt_weather_name);
        txtWeatherDescription = (TextView)findViewById(R.id.txt_weather_description);
        txtTemp = (TextView)findViewById(R.id.txt_temperature);
        txtHumidity = (TextView)findViewById(R.id.txt_humidity);
        txtPressure = (TextView)findViewById(R.id.txt_pressure);
        txtMinTemp = (TextView)findViewById(R.id.txt_min_temp);
        txtMaxTemp = (TextView)findViewById(R.id.txt_max_temp);
        txtWindSpeed = (TextView)findViewById(R.id.txt_wind_speed);
        txtWindDegree = (TextView)findViewById(R.id.txt_wind_deg);
    }

    @Override
    public void onReceived(WeatherInfo weatherInfo) {
        if(weatherInfo != null){
            txtWeatherName.setText("وضعیت آب و هوا: " + weatherInfo.getWeatherName());
            txtWeatherDescription.setText("توصیحات آب و هوا: " + weatherInfo.getWeatherDescription());
            txtTemp.setText("دمای هوا: "+String.valueOf(weatherInfo.getWeatherTemperature()));
            txtHumidity.setText("رطوبت هوا: "+String.valueOf( weatherInfo.getHumidity()));
            txtPressure.setText("فشار هوا: "+String.valueOf( weatherInfo.getPressure()));
            txtMinTemp.setText("حداکثر دما: "+String.valueOf( weatherInfo.getMinTemperature()));
            txtMaxTemp.setText("حداقل دما: "+String.valueOf( weatherInfo.getMinTemperature()));
            txtWindSpeed.setText("سرعت باد: "+String.valueOf( weatherInfo.getWindSpeed()));
            txtWindDegree.setText("درجه ی باد: "+String.valueOf( weatherInfo.getWindDegree()));


        }else {
            Toast.makeText(this, "خطا در دریافت اطلاعات!",Toast.LENGTH_LONG).show();
        }
        progressBar.setVisibility(View.INVISIBLE);
    }
}
