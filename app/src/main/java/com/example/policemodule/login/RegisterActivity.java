package com.example.policemodule.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.policemodule.MainActivity;
import com.example.policemodule.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.activity_register_tv_email)
     EditText email;
    @BindView(R.id.activity_register_tv_password)
    EditText password;
    @BindView(R.id.activity_register_tv_confirmpass)
    EditText cnfpassword;
    @BindView(R.id.activity_register_tv_login)
    TextView login;
    @BindView(R.id.activity_register_btn_register)
    Button reister;
    @BindView(R.id.activity_register_poicename)
    EditText policename;
    @BindView(R.id.activity_register_phno)
    EditText phone_no;


    @BindView(R.id.progress_bar)
    ProgressBar progressBar;


    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        firebaseAuth = FirebaseAuth.getInstance();
        reister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=policename.getText().toString();
                String phno=phone_no.getText().toString();
                String emailId = email.getText().toString();
                String pwd = password.getText().toString();

                String cfmpwd = cnfpassword.getText().toString();
                if (TextUtils.isEmpty(emailId)) {
                    Toast.makeText(RegisterActivity.this, "Please enter the email", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(RegisterActivity.this, "Please enter the password", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(cfmpwd)) {
                    Toast.makeText(RegisterActivity.this, "Please enter the  confirmed password", Toast.LENGTH_SHORT).show();
                }
                if (pwd.equals(cfmpwd)) {
                    firebaseAuth.createUserWithEmailAndPassword(emailId, pwd)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(RegisterActivity.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                    } else {
                                        //Toast.makeText(RegisterActivity.this, "Authentication InComplete", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);//have to  be removed
        startActivity(intent);//have to be removed
    }
}