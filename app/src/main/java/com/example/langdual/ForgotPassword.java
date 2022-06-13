package com.example.langdual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    TextInputEditText emailTextField;
    Button btnSend;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailTextField = findViewById(R.id.editTextEmail);
        btnSend = findViewById(R.id.buttonSendChangePassword);


        firebaseAuth = FirebaseAuth.getInstance();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTextField.getText().toString().trim();
                if (email.isEmpty()) {
                    emailTextField.setError("Email is required");
                    emailTextField.requestFocus();
                    return;
                }else {
                    firebaseAuth.sendPasswordResetEmail(emailTextField.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ForgotPassword.this, "Password Reset Link Sent to Your Email", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ForgotPassword.this, LogIn.class));
                            }
                            else
                            {
                                Toast.makeText(ForgotPassword.this, "Error Occured", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }
}