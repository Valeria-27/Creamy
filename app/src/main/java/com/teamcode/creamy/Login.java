package com.teamcode.creamy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.teamcode.creamy.Models.User;

public class Login extends AppCompatActivity {

    TextView etRegisterGo;
    EditText etEmailLogin, etPasswordLogin;
    Button btnLogin;

    FirebaseAuth myAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmailLogin = findViewById(R.id.etEmailLogin);
        etPasswordLogin = findViewById(R.id.etPasswordLogin);
        btnLogin = findViewById(R.id.btnRegister);
        etRegisterGo = findViewById(R.id.etRegisterGo);

        myAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }

        etRegisterGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailL = etEmailLogin.getText().toString().trim();
                String passwordL = etPasswordLogin.getText().toString().trim();

                if (emailL.equals("")) {
                    etEmailLogin.setError("Ingrese su email");
                    etPasswordLogin.requestFocus();
                    return;
                }
                if (passwordL.equals("")) {
                    etPasswordLogin.setError("Ingrese su contraseña");
                    etPasswordLogin.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(emailL).matches()){
                    etEmailLogin.setError("Por favor, proporcione una dirección de correo electrónico válida");
                    etEmailLogin.requestFocus();
                    return;
                }

                myAuth.signInWithEmailAndPassword(emailL, passwordL).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(Login.this, "Inicio de sesion fallido.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}