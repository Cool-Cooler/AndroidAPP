package com.example.openbook;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class activity_login extends AppCompatActivity {

    TextView btn;
    EditText inputEmailL,inputPasswordL;
    Button Loginbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         btn=findViewById(R.id.SignUp);
         inputEmailL=findViewById(R.id.inputEmailL);
         inputPasswordL =findViewById(R.id.inputPasswordL);
        Loginbtn =findViewById(R.id.Loginbtn);
        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials(v);

            }
        });


        btn.setOnClickListener(v -> startActivity(new Intent(activity_login.this,activity_register.class)));
    }
    //Method to check credentials below

    private void checkCredentials(View v) {
        String email=inputEmailL.getText().toString();
        String password=inputPasswordL.getText().toString();

        if (email.isEmpty()|| !email.contains("@"))
        {
            showError(inputEmailL, "Email is not valid");
        }
        else if (password.isEmpty()||password.length()<7)
        {
            showError(inputPasswordL, "Password is invalid, must be at least 7 characters");
        }

        else
        {
            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(v.getContext(), MainActivity.class);
            startActivityForResult(myIntent, 0);

        }
    }


    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}
