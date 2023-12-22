package com.example.projecttask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView googlebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configures Google Sign-In with the requested email. It sets up the Google Sign-In options and client.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        googlebtn = findViewById(R.id.googlebtn);
        googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

//        TextView username = (TextView)findViewById(R.id.username);
//        TextView password = (TextView)findViewById(R.id.password);
//        MaterialButton loginbtn = (MaterialButton)findViewById(R.id.loginbtn);
//
//        loginbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
//                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(MainActivity.this, "Login Failed...!!!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
    void signIn(){
        // Initiates the Google Sign-In process
        Intent signInintent = gsc.getSignInIntent();
        startActivityForResult(signInintent,1000);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            // Obtain the signed-in account from the intent data
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try{
                // Attempt to get the result of the Google Sign-In activity & If successful, call the secondActivity method
                task.getResult(ApiException.class);
                secondActivity();
            }
            catch(ApiException e){
                Toast.makeText(this, "Something Went Wrong...!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    void secondActivity(){
        finish();
        Intent i = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(i);
    }
}