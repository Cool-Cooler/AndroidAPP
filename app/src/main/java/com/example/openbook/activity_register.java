package com.example.openbook;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class activity_register extends AppCompatActivity {

    private EditText inputFullName,inputEmailR,inputPasswordR,inputConfirmPassword;

    Button RegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView btn=findViewById(R.id.Already_have_an_acc);//created button for those who already have an account
        inputFullName = findViewById(R.id.inputFullName);
        inputEmailR = findViewById(R.id.inputEmailL);
        inputPasswordR = findViewById(R.id.inputPasswordL);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);

        RegisterBtn = findViewById(R.id.RegisterBtn);
        RegisterBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();

            }
        });




        btn.setOnClickListener(v ->  {
            startActivity(new Intent(activity_register.this,activity_login.class));

        });
    }


    //Method to check credentials below

    private void checkCredentials() {
        String fullname=inputFullName.getText().toString();
        String email=inputEmailR.getText().toString();
        String password=inputPasswordR.getText().toString();
        String confirmPassword=inputConfirmPassword.getText().toString();

        if(fullname.isEmpty()|| fullname.length()<7)
        {
            showError(inputFullName,"Your name is not valid!");
        }
        else if (email.isEmpty()|| !email.contains("@"))
        {
            showError(inputEmailR, "Email is not valid");
        }
        else if (password.isEmpty()||password.length()<7)
        {
            showError(inputPasswordR, "Password is invalid, must be at least 7 characters");
        }
        else if (confirmPassword.isEmpty()|| !confirmPassword.equals(password))
        {
            showError(inputConfirmPassword, "Password does not match!");
        }
        else
            {
                Toast.makeText(this," Registration Successful! Welcome to Open Book!",Toast.LENGTH_SHORT).show();
            }
        }


    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}


