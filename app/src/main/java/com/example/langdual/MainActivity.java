package com.example.langdual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.langdual.adapters.MeaningAdapter;
import com.example.langdual.adapters.PhoneticsAdapter;
import com.example.langdual.models.api_response;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    TextView word;
    RecyclerView recyclerViewFonetica, recyclerViewSignificado;
    ProgressDialog progressDialog;//Barra de carga
    PhoneticsAdapter foneticaAdapter;
    MeaningAdapter significadoAdapter;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String name = user.getDisplayName();

        if(name.isEmpty()){
            finish();
            startActivity(new Intent(getApplicationContext(),UserProfile.class));
        }


        searchView = findViewById(R.id.search_view);
        word = findViewById(R.id.word);
        recyclerViewFonetica = findViewById(R.id.recyclerFonetica);
        recyclerViewSignificado = findViewById(R.id.recyclerSignificado);
        progressDialog = new ProgressDialog(this);

        progressDialog.setTitle("Loading...");
        progressDialog.show();

        RequestManager manager = new RequestManager(MainActivity.this);
        manager.getSignificadoPalabra(listener, "hello");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                progressDialog.setMessage("Loading..."+query);
                progressDialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getSignificadoPalabra(listener,  query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchDataSuccess(api_response apiResponse, String message) {
            if(apiResponse==null) {
                //codigo para historial
                Toast.makeText(MainActivity.this, "No se encontraron datos", Toast.LENGTH_SHORT).show();

                return;
            }
            showData(apiResponse);
            insertHistorial(word.getText().toString());
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void insertHistorial(String histori) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("historial");
        myRef.child(histori).setValue(histori);
    }


    private void showData(api_response apiResponse) {
        word.setText(apiResponse.getWord());
        recyclerViewFonetica.setHasFixedSize(true);
        recyclerViewFonetica.setLayoutManager(new GridLayoutManager(this,1));
        foneticaAdapter = new PhoneticsAdapter(this, apiResponse.getPhonetics());
        recyclerViewFonetica.setAdapter(foneticaAdapter);

        recyclerViewSignificado.setHasFixedSize(true);
        recyclerViewSignificado.setLayoutManager(new GridLayoutManager(this,1));
        significadoAdapter = new MeaningAdapter(this, apiResponse.getMeanings());
        recyclerViewSignificado.setAdapter(significadoAdapter);
    }
}