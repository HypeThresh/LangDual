package com.example.langdual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    Button btnLogin;
    TextView txtSignUp, txtForgotPassword;
    TextInputEditText emailTextField, passwordTextField;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        txtForgotPassword = findViewById(R.id.linkForgotPassword);
        txtSignUp = findViewById(R.id.linkSignUp);

        btnLogin = findViewById(R.id.buttonLogIn);

        emailTextField = findViewById(R.id.editTextEmail);
        passwordTextField = findViewById(R.id.editTextPassword);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser != null)
        {
            finish();
            startActivity(new Intent(LogIn.this,MainActivity.class));
        }

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, ForgotPassword.class));
            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, SignUp.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = emailTextField.getText().toString();
                String password = passwordTextField.getText().toString();

                if(mail.isEmpty()||password.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Rellene todas las casillas", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                checkmailverificaction();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"La cuenta no existe o datos incorrectos",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void checkmailverificaction()
    {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser.isEmailVerified()==true)
        {
            Toast.makeText(getApplicationContext(), "Se ha iniciado sesión correctamente", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(LogIn.this,MainActivity.class));
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Verifica tu E-mail", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}