package com.android.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReadActivity extends AppCompatActivity {
TextView ViewName,ViewDouble1,ViewDouble2;
Button buttonRead;
DatabaseReference reff;

        String a = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        ViewName=(TextView)findViewById(R.id.ViewName);
        ViewDouble1=(TextView)findViewById(R.id.ViewDouble1);
        ViewDouble2=(TextView)findViewById(R.id.ViewDouble2);
        buttonRead=(Button)findViewById(R.id.buttonRead);

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff= FirebaseDatabase.getInstance().getReference().child("Member").child(a);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Double関数でのデータベースからの抜き出し
                       // Double ido = (Double) dataSnapshot.child("double1").getValue();
                        String double1=dataSnapshot.child("double1").getValue().toString();
                        String double2=dataSnapshot.child("double2").getValue().toString();
                        String name=dataSnapshot.child("name").getValue().toString();
                        ViewName.setText(name);
                        ViewDouble1.setText(double1);
                        ViewDouble2.setText(double2);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
