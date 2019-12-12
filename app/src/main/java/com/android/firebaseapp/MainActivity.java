package com.android.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
EditText editTextName,editText01,editText02;
Button button1,button2,seni;
DatabaseReference reff;
Member member;
long maxid = 0;
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
        reff= FirebaseDatabase.getInstance().getReference().child("トイレ");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double Double1=Double.parseDouble(editText01.getText().toString().trim());
                Double Double2=Double.parseDouble(editText02.getText().toString().trim());

                member.setName(editTextName.getText().toString().trim());
                member.setDouble1(Double1);
                member.setDouble2(Double2);
                //今はいらない　reff.push().setValue(member);
                reff.child(String.valueOf(maxid+1)).setValue(member);


                Toast.makeText(MainActivity.this,"登録しました",Toast.LENGTH_LONG).show();
            }
        });

        Button buttonMain =findViewById(R.id.seni);
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(getApplication(),ReadActivity.class);
                startActivity(intent);
            }
        });

    }
}

