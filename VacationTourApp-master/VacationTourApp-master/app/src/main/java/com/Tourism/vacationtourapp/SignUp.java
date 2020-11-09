package com.Tourism.vacationtourapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    public EditText emailid, password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        btnSignUp = findViewById(R.id.button2);
        tvSignIn = findViewById(R.id.textView5);
        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    emailid.setError("Please enter email id");
                    emailid.requestFocus();

                }
                else if(pwd.isEmpty()){
                    password.setError("Please enter password");
                    password.requestFocus();

                }

                else if(! (email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SignUp.this,"Signup Unsuccessful, Please try Agian", Toast.LENGTH_SHORT).show();
                                Log.i("reg unsuccess", task.getException().toString());

                            }
                            else {
                                Intent signin = new Intent(SignUp.this,MainActivity.class);
                                startActivity(signin);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(SignUp.this,"Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this,Login.class);
                startActivity(i);
            }
        });
        }
    }
