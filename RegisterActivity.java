package com.annguyen.riki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    TextView HaveAccount;
    EditText inputEmail,inputPassword,inputRewritePassword;
    Button btnRegister;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

      HaveAccount=findViewById(R.id.HaveAccount);

      inputEmail=findViewById(R.id.inputEmail);
      inputPassword=findViewById(R.id.inputPassword);
      inputRewritePassword=findViewById(R.id.inputRewritePassword);
      btnRegister=findViewById(R.id.btnRegister);
      progressDialog=new ProgressDialog(this);
      mAuth=FirebaseAuth.getInstance();
      mUser=mAuth.getCurrentUser();

      HaveAccount.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(RegisterActivity.this,MainActivity.class));
          }
      });

      btnRegister.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              PerforAuth();
          }
      });
    }

    private void PerforAuth() {
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();
        String rewritepassword=inputRewritePassword.getText().toString();


        if(email.matches(emailPattern))
        {
            inputEmail.setError("Enter Connext Email");
        }else if (password.isEmpty() || password.length()<6)
        {
            inputPassword.setError("Enter Proper Password");
        }else if(!password.equals(rewritepassword))
        {
            inputRewritePassword.setError("Password not match both field");
        }else
        {
            progressDialog.setMessage("Please Wait While Registration....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(RegisterActivity.this,"Registration Successful", Toast.LENGTH_SHORT).show();

                    }else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this,""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void sendUserToNextActivity()
    {
        Intent intent=new Intent(RegisterActivity.this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
