package com.namit.cinemabookingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button signUp, logIn;
    private LoginButton fbLogin;
    private FirebaseAuth mAuth;
    private final String fbTAG="FB Login";
    private CallbackManager mCallbackManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUp=findViewById(R.id.sign_up);
        logIn=findViewById(R.id.log_in);
        fbLogin=findViewById(R.id.fb_login);
        progressBar=findViewById(R.id.progressBar_loginFB);

        signUp.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SignUp.class)));
        logIn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LogIn.class)));


        // ...
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize Facebook Login button
        fbLogin.setOnClickListener(v -> progressBar.setVisibility(View.VISIBLE));

        mCallbackManager = CallbackManager.Factory.create();
        fbLogin=findViewById(R.id.fb_login);
        //fbLogin.setReadPermissions("email", "public_profile");
        fbLogin.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //progressBar.setVisibility(View.VISIBLE);
                Log.d(fbTAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                progressBar.setVisibility(View.INVISIBLE);
                Log.d(fbTAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.d(fbTAG, "facebook:onError", error);
            }
        });
        // ...

//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//        if(!isLoggedIn){
//            Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
//            //LoginManager.getInstance().logOut();
//        }else{
//            Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
//        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(fbTAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(fbTAG, "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.w(fbTAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if(user!=null) {
            startActivity(new Intent(MainActivity.this, InsideBody.class));
            finish();
        }
    }

}