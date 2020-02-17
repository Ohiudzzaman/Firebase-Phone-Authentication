package com.gz.gzwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gz.gzwallet.authentication.LoginActivity;

public class SplashScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAuth = FirebaseAuth.getInstance();

        final FirebaseUser user = mAuth.getCurrentUser();

        new Thread((new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ae) {
                    Toast.makeText(SplashScreen.this, "Error while Opening the App", Toast.LENGTH_SHORT).show();
                }
                if (user!=null)
                {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();

                }else
                {
                    //check user login or not
                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);
                    //When going to login activity than splash screen is close for these finish.
                    finish();
                }
            }
        })).start();
    }
}
