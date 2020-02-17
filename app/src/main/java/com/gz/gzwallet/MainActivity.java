package com.gz.gzwallet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gz.gzwallet.authentication.LoginActivity;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

import static com.gz.gzwallet.authentication.LoginActivity.storename;

public class MainActivity extends AppCompatActivity  {

    private MaterialEditText user_Number, amount;
    private Button paymentButton;
    private HashMap<String, String> user;
    private DatabaseReference mDatabaseReference;
    private float payAmount;
    private String number;
    private String Amount, order_reference_id;
    private String currdatetime;
    private static final String TAG = MainActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private String paymentid;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        user_Number = findViewById(R.id.user_Number);

        sharedPreferences = getSharedPreferences(storename, Context.MODE_PRIVATE);
        if (sharedPreferences.contains("Value")) {
            user_Number.setText(sharedPreferences.getString("Value", ""));
        }
        user_Number.setEnabled(false);
        amount = findViewById(R.id.amount_id);
        paymentButton = findViewById(R.id.paymentButton);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Operation is success", Toast.LENGTH_SHORT).show();
            }
        });

    }



    private boolean validateFields(View view) {

        if (user_Number.getText().toString().length() == 0 || amount.getText().toString().length() == 0) {
            Snackbar.make(view, "Kindly Fill all the fields", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
            return false;
        } else if (user_Number.getText().toString().length() < 4 || user_Number.getText().toString().length() > 12) {
            user_Number.setError("Number Must consist of 10 characters");
            return false;
        }

        return true;
    }

    public UserModel getUserDetails() {
        return new UserModel(paymentid, user_Number.getText().toString(), amount.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.logout);
        //menuItem.setIcon(Converter.convertLayoutToImage(MainActivity.this,cart_count,R.drawable.notification_out));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.logout) {
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            Toast.makeText(MainActivity.this, "Logout Success", Toast.LENGTH_SHORT).show();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
