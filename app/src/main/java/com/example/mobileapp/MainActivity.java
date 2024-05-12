package com.example.mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity{
    private static final String LOG_TAG = MainActivity.class.getName();
    private static final int SECRET_KEY = 99;

    EditText userNameET, passWordET;

    private FirebaseAuth auth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameET = findViewById(R.id.editTextUserName);
        passWordET = findViewById(R.id.editTextPassword);

        auth = FirebaseAuth.getInstance();

    }

    public void login(View view) {

        String username = userNameET.getText().toString();
        String password = passWordET.getText().toString();
        //Log.i(LOG_TAG, "Bejelentkezett"+username+"   "+password);

        auth.signInWithEmailAndPassword(username,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()){
                    installMainPage();
                }else {
                    Toast.makeText(MainActivity.this, "Sikertelen belépés: "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void regist(View view) {

        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("SECRET_KEY",99);
        startActivity(intent);

    }

    public void guestLogin(View view) {
        auth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    installMainPage();
                }else {
                    Toast.makeText(MainActivity.this, "Sikertelen belépés: "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void installMainPage() {
        Intent intent = new Intent(this,MainPageActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
