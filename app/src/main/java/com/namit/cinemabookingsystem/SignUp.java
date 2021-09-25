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

public class SignUp extends AppCompatActivity {
    private EditText name_EditText, email_EditText, password_EditText, phone_EditText;
    private Button submit_Button;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name_EditText=findViewById(R.id.editText_Name);
        email_EditText=findViewById(R.id.editTextEmail);
        password_EditText=findViewById(R.id.editTextPassword);
        phone_EditText=findViewById(R.id.editTextPhoneNo);
        submit_Button=findViewById(R.id.login_submit);
        progressBar=findViewById(R.id.progressBar_signup);

        mAuth=FirebaseAuth.getInstance();

        submit_Button.setOnClickListener(v -> {
            String name=name_EditText.getText().toString().trim();
            String email=email_EditText.getText().toString().trim();
            String password=password_EditText.getText().toString().trim();
            String phone=phone_EditText.getText().toString().trim();
            if(emptyField(name, name_EditText) || emptyField(email, email_EditText) ||
               emptyField(password, password_EditText) || emptyField(phone, phone_EditText))
                return;
            progressBar.setVisibility(View.VISIBLE);
            createAccount(email, password, name, phone);


        });

    }

    public void createAccount(String email, String pass, String name, String phone){
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("signUp", "Succeed: createAccount");
                    FirebaseUser user=mAuth.getCurrentUser();
                    Intent intent=new Intent(SignUp.this, InsideBody.class);
                    intent.putExtra("name", name);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                    finish();
                }else{
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.w("signUp", "Failed: createAccount", task.getException());
                    Toast.makeText(SignUp.this, "Unable to Create Account!!",
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