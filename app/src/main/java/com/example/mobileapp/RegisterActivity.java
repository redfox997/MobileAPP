package com.example.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getName();

    EditText userNameET, emailET, passwordET, passwordAgainET;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Bundle bundle = getIntent().getExtras();
        userNameET = findViewById(R.id.UserName);
        emailET = findViewById(R.id.userEmail);
        passwordET = findViewById(R.id.passwordReg);
        passwordAgainET = findViewById(R.id.passwordAgainReg);
        if (bundle.getInt("SECRET_KEY") != 99){finish();}

        auth = FirebaseAuth.getInstance();
    }

    public void sendRegist(View view) {

        String username = userNameET.getText().toString();
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        String passwordAgain = passwordAgainET.getText().toString();
        Log.i(LOG_TAG, "Bejelentkezett  "+username+"   "+email+"   "+password+"   "+passwordAgain);

        if (!password.equals(passwordAgain)){Log.i(LOG_TAG,"Nem egyezik a jelszó és a jelszó megerősítés!"); return;}

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener< AuthResult >(){

            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(LOG_TAG, "Sikeres regisztráció!");
                    installMainPage();
                }else {
                    Log.d(LOG_TAG, "Sikertelen regisztráció!");
                    Toast.makeText(RegisterActivity.this, "Sikertelen regisztráció: "+ task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void installMainPage() {
        Intent intent = new Intent(this,MainPageActivity.class);
        startActivity(intent);
    }
}