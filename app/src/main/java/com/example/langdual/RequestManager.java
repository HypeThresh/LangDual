package com.example.langdual;

import android.content.Context;
import android.widget.Toast;

import com.example.langdual.models.api_response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RequestManager {
    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getSignificadoPalabra(OnFetchDataListener listener, String word){
        CallDiccionario callDiccionario = retrofit.create(CallDiccionario.class);
        Call<List<api_response>> call = callDiccionario.callSignificado(word);

        try {
            call.enqueue(new Callback<List<api_response>>() {
                @Override
                public void onResponse(Call<List<api_response>> call, Response<List<api_response>> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.onFetchDataSuccess(response.body().get(0), response.message());
                }

                @Override
                public void onFailure(Call<List<api_response>> call, Throwable t) {
                    listener.onError("Pedido fallido!");
                }
            });
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, "Ha ocurrido un error!", Toast.LENGTH_SHORT).show();
        }
    }

    public interface CallDiccionario{
        @GET("entries/en/{word}")
        Call<List<api_response>> callSignificado(
                @Path("word") String word
        );
    }
}
