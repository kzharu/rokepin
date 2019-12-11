package com.android.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
EditText editTextName,editText01,editText02;
Button button1,button2;
DatabaseReference reff;
Member member;
    @Override
    protected void onCreate(Bundle savedInstanceStage) {
        super.onCreate(savedInstanceStage);
        setContentView(R.layout.activity_main);
        editTextName=(EditText)findViewById(R.id.editTextName);
        editText01=(EditText)findViewById(R.id.editText01);
        editText02=(EditText)findViewById(R.id.editText02);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        member=new Member();
        reff= FirebaseDatabase.getInstance().getReference().child("Member");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double Double1=Double.parseDouble(editText01.getText().toString().trim());
                Double Double2=Double.parseDouble(editText02.getText().toString().trim());

                member.setName(editTextName.getText().toString().trim());
                member.setDouble1(Double1);
                member.setDouble2(Double2);
                reff.push().setValue(member);

            }
        });
    }
}

