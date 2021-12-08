package com.teamcode.creamy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.teamcode.creamy.Models.User;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    private EditText etNameR, etEmailR, etPasswordR, etAddressR;

    private ArrayList<User> arrayList;
    private FirebaseDatabase firebaseDatabase;
    //private DatabaseReference databaseReference;
    private User user;
    //private String path = "user";

    FirebaseAuth myAuth;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myAuth = FirebaseAuth.getInstance();



        etNameR = findViewById(R.id.etNombreRegister);
        etEmailR = findViewById(R.id.etEmailR);
        etPasswordR = findViewById(R.id.etPasswordRegister);
        etAddressR = findViewById(R.id.etAddressRegister);

        btnRegister = findViewById(R.id.btnRegister);

        firebaseDatabase = FirebaseDatabase.getInstance();

        //databaseReference = firebaseDatabase.getReference(path);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    myAuth = FirebaseAuth.getInstance();

                    String nameString = etNameR.getText().toString().trim();
                    String emailString = etEmailR.getText().toString().trim();
                    String passwordString = etPasswordR.getText().toString().trim();
                    String addressString = etAddressR.getText().toString().trim();

                    if (nameString.equals("")) {
                        etNameR.setError("Ingrese su nombre");
                        etNameR.requestFocus();
                        return;
                    }
                    if (emailString.equals("")) {
                        etEmailR.setError("Ingrese su correo");
                        etEmailR.requestFocus();
                        return;
                    }
                    if (!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()){
                        etEmailR.setError("Por favor, proporcione una dirección de correo electrónico válida");
                        etEmailR.requestFocus();
                        return;
                    }
                    if (passwordString.equals("")) {
                        etPasswordR.setError("Ingrese su contraseña");
                        etPasswordR.requestFocus();
                        return;
                    }
                    if (addressString.equals("")) {
                        etAddressR.setError("Ingrese su dirección");
                        etAddressR.requestFocus();
                        return;
                    }


                    FirebaseAuth.getInstance()
                            .createUserWithEmailAndPassword(emailString,passwordString).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        User user = new User(nameString, emailString, passwordString, addressString);

                                        FirebaseDatabase.getInstance().getReference("user")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(Register.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(Register.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                                    } else {
                                        Toast.makeText(Register.this, "Registro fallido 1", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                } catch (Exception ex) {
                    Toast.makeText(Register.this, ex.toString(), Toast.LENGTH_SHORT).show();
                }
                    /*String nameString = etNameR.getText().toString().trim();
                    String emailString = etEmailR.getText().toString().trim();
                    String passwordString = etPasswordR.getText().toString().trim();
                    String addressString = etAddressR.getText().toString().trim();

                    if (nameString.equals("")) {
                        etNameR.setError("Ingrese su nombre");
                        etNameR.requestFocus();
                        return;
                    }
                    if (emailString.equals("")) {
                        etEmailR.setError("Ingrese su correo");
                        etEmailR.requestFocus();
                        return;
                    }
                    if (passwordString.equals("")) {
                        etPasswordR.setError("Ingrese su contraseña");
                        etPasswordR.requestFocus();
                        return;
                    }
                    if (addressString.equals("")) {
                        etAddressR.setError("Ingrese su dirección");
                        etAddressR.requestFocus();
                        return;
                    }

                    String idString = databaseReference.push().getKey();
                    user = new User(idString, nameString, emailString, passwordString, addressString);

                    databaseReference.child(idString).setValue(user);
                    Toast.makeText(Register.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
*/


                /*Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);*/
            }
        });
    }


}