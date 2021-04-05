package com.example.carexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.carexample.adapters.CarAdapter;
import com.example.carexample.data.model.CarData;
import com.example.carexample.data.model.CarModel;
import com.example.carexample.data.network.CarApiInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.carexample.HelperClass.API_URL;
import static com.example.carexample.HelperClass.ERROR_HAPPENED;
import static com.example.carexample.HelperClass.OPEN_INTERNET;

public class MainActivity extends AppCompatActivity {

    NestedScrollView nestedScrollView;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    SwipeRefreshLayout refreshLayout;
    CarAdapter carAdapter;

    List<CarData> carDataList = new ArrayList<>();
    int page = 1;

    private CarApiInterface gitHubAPIInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nestedScrollView = findViewById(R.id.scroll_view);
        recyclerView = findViewById(R.id.rv);
        progressBar = findViewById(R.id.progress_bar);
        refreshLayout = findViewById(R.id.swipeContainer);
        carAdapter = new CarAdapter(carDataList, this);


        if (HelperClass.checkInternetState(this)) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(carAdapter);
            getData(page);
        } else {
            Toast.makeText(this, OPEN_INTERNET, Toast.LENGTH_SHORT)
                    .show();
        }

        nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener)
                (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                    if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                        Log.i(HelperClass.TAG, "onScrollChange: ");
                        if (HelperClass.checkInternetState(this)) {
                            page++;
                            progressBar.setVisibility(View.VISIBLE);
                            getData(page);
                        } else {
                            Toast.makeText(this, OPEN_INTERNET, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // on swipe refresh see if there is internet connection  get a new data
        refreshLayout.setOnRefreshListener(() -> {
            if (HelperClass.checkInternetState(this)) {
                callNewData();
            } else {
                Toast.makeText(this, OPEN_INTERNET, Toast.LENGTH_SHORT).show();
            }
            refreshLayout.setRefreshing(false);
        });

    }

    private void getData(int page) {
        InitializeRetrofitVars();
        Call<CarModel> repoModelCall = gitHubAPIInterface.getCarData(page);
        repoModelCall.enqueue(new Callback<CarModel>() {

            @Override
            public void onResponse(Call<CarModel> call, Response<CarModel> response) {
                if (response.body() != null) {
                    Log.i(HelperClass.TAG, "onResponse: response.isSuccessful() >> "
                            + response.isSuccessful()
                            + " -- size of array >> " + response.body() +
                            " - page >> " + page);
                }
                if (response.isSuccessful() && response.body() != null) {
                    Log.i(HelperClass.TAG, "onResponse: boyd >>  " + response.body().getClass().getSimpleName() );
                    progressBar.setVisibility(View.GONE);
                    if (response.body() != null) {
                        carAdapter.setCarsList(response.body().getCarDetailsList());

                    }
                }
            }
            @Override
            public void onFailure(Call<CarModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, ERROR_HAPPENED, Toast.LENGTH_SHORT).show();
                Log.i(HelperClass.TAG, "onFailure: errorMsg" + t.getMessage());
                t.printStackTrace();
            }
        });

    }

    /**
     * Initialize variables needed in retrofit
     */
    private void InitializeRetrofitVars() {
        Log.i(HelperClass.TAG, "MainActivity -- InitializeRetrofitVars() ");
        // logs request and response information.
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        gitHubAPIInterface = retrofit.create(CarApiInterface.class);
    }

    /**
     * reset the page number to default ,clear the list that is on the adapter
     * then get a new data from api
     */
    private void callNewData() {
        page = 1;
        carDataList.clear();
        carAdapter.notifyDataSetChanged();
        getData(page);
    }
}