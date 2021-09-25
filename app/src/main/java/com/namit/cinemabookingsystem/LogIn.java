package com.namit.cinemabookingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {
    private EditText email_ET, pass_ET;
    private Button submit_btn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email_ET=findViewById(R.id.editTextEmail);
        pass_ET=findViewById(R.id.editTextPassword);
        submit_btn=findViewById(R.id.login_submit);
        progressBar=findViewById(R.id.progressBar_login);

        mAuth=FirebaseAuth.getInstance();

        submit_btn.setOnClickListener(v -> {
            String email=email_ET.getText().toString().trim();
            String password=pass_ET.getText().toString().trim();
            if(emptyField(email, email_ET) ||
                    emptyField(password, pass_ET))
                return;
            progressBar.setVisibility(View.VISIBLE);
            userSignIn(email, password);

        });
    }

    public void userSignIn(String email, String pass){
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("signIn", "Succeed: loginAccount");
                    FirebaseUser user=mAuth.getCurrentUser();
                    startActivity(new Intent(LogIn.this, InsideBody.class));
                    finish();
                }else{
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.w("signIn", "Failed: loginAccount", task.getException());
                    Toast.makeText(LogIn.this, "Unable to Login!!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean emptyField(String string, EditText et){
        if(string.isEmpty()){
            et.setError("This field can't be empty");
            return true;
        }
        return false;
    }
}