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

public class SignUp extends AppCompatActivity {

    Button btnSignUp;
    TextView txtLogIn;
    TextInputEditText emailTextField, passwordTextField;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignUp = findViewById(R.id.buttonSignUp);
        txtLogIn = findViewById(R.id.textLogInView);

        emailTextField = findViewById(R.id.signUpEmail);
        passwordTextField = findViewById(R.id.textInputEditTextPassword);

        firebaseAuth = FirebaseAuth.getInstance();

        txtLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, LogIn.class));
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = emailTextField.getText().toString();
                String password = passwordTextField.getText().toString();

                if(mail.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();

                }
                else if(password.length()<7)
                {
                    Toast.makeText(getApplicationContext(), "La contraseña debe contener 7 caracteres como minimo", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Registro del usuario en firebase

                    firebaseAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                                sendEmailVerification();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Error al registrarse", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }



            }
        });

    }

    private void sendEmailVerification()
    {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "Correo de verificacion enviado, Verifique e Ingrese de nuevo", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(SignUp.this, LogIn.class));
                }
            });
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Error al enviar correo de verificacion", Toast.LENGTH_SHORT).show();
        }
    }
}