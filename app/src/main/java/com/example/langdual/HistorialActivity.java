package com.example.langdual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.langdual.adapters.HistorialAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;


public class HistorialActivity extends AppCompatActivity {

    ImageView back;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        back = findViewById(R.id.backHome);
        recyclerView = findViewById(R.id.RecyclerHistorial);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(HistorialActivity.this, MainActivity.class));
            }
        });

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("historial").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isComplete()){
                    ArrayList<String> list = new ArrayList<>();
                    String key;
                    for (DataSnapshot child : task.getResult().getChildren()) {
                        key = child.getKey();
                        list.add(key);
                    }

                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
                    HistorialAdapter mAdapter = new HistorialAdapter(list);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(mAdapter);
                }
            }
        });
    }
}