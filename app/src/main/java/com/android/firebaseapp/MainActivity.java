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

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
EditText editTextName;
Button button1;
DatabaseReference reff;
Member member;
long maxid = 0;
    @Override
    protected void onCreate(Bundle savedInstanceStage) {
        final MyApp myApp = (MyApp) this.getApplication();
        final MyApp myApp1 = (MyApp) this.getApplication();

        super.onCreate(savedInstanceStage);
        setContentView(R.layout.activity_main);
        editTextName=(EditText)findViewById(R.id.editTextName);
        button1=(Button)findViewById(R.id.button1);
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


                double id = myApp.getDouble();
                double kd = myApp1.getDouble1();

                member.setName(editTextName.getText().toString().trim());
                member.setDouble1(id);
                member.setDouble2(kd);
                //今はいらない　reff.push().setValue(member);
                reff.child(String.valueOf(maxid+1)).setValue(member);


                Toast.makeText(MainActivity.this,"登録しました",Toast.LENGTH_LONG).show();
            }
        });

        Button buttonMain =findViewById(R.id.seni);
        buttonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(getApplication(),testmap.class);
                intent.putExtra("latitude_1", _latitude);
                intent.putExtra("longitude_1", _longitude);
                startActivity(intent);
            }
        });

        Button buttonRead =findViewById(R.id.button2);
        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(getApplication(),ReadMap.class);
                intent.putExtra("latitude_1", _latitude);
                intent.putExtra("longitude_1", _longitude);
                intent.putExtra("count",maxid);
                startActivity(intent);
            }
        });


        //緯度と経度を表示するTextViewフィールドの中身を取得。
        _tvLatitude = findViewById(R.id.tvLatitude);
        _tvLongitude = findViewById(R.id.tvLongitude);

        //LocationManagerオブジェクトを取得。
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //位置情報が更新された際のリスナオブジェクトを生成。
        GPSLocationListener locationListener = new GPSLocationListener();
        //ACCESS_FINE_LOCATIONの許可が下りていないなら…
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //ACCESS_FINE_LOCATIONの許可を求めるダイアログを表示。その際、リクエストコードを1000に設定。
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1000);
            //onCreate()メソッドを終了。
            return;
        }
        //位置情報の追跡を開始。
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        // 利用できるGPSを選択してプロバイダを取得
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            // ネットワークプロバイダを使って検索
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // GPSプロバイダを使って検索
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    //緯度フィールド。
    private double _latitude = 0.0;

    //経度フィールド
    private double _longitude = 0.0;

    private double latitude= 0.0;
    private double longitude= 0.0;

    //緯度を表示するTextViewフィールド。
    private TextView _tvLatitude;

    //経度を表示するTextViewフィールド。
    private TextView _tvLongitude;



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //ACCESS_FINE_LOCATIONに対するパーミションダイアログでかつ許可を選択したなら…
        if(requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //LocationManagerオブジェクトを取得。
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            //位置情報が更新された際のリスナオブジェクトを生成。
            GPSLocationListener locationListener = new GPSLocationListener();
            //再度ACCESS_FINE_LOCATIONの許可が下りていないかどうかのチェックをし、降りていないなら処理を中止。
            if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            //位置情報の追跡を開始。
            //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            // 利用できるGPSを選択してプロバイダを取得
            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                // ネットワークプロバイダを使って検索
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                // GPSプロバイダを使って検索
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }


    /**
     * ロケーションリスナクラス。
     */
    private class GPSLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            //引数のLocationオブジェクトから緯度を取得。
            _latitude = location.getLatitude();
            //引数のLocationオブジェクトから経度を取得。
            _longitude = location.getLongitude();
            //取得した緯度をTextViewに表示。
            _tvLatitude.setText(Double.toString(_latitude));
            //取得した経度をTextViewに表示。
            _tvLongitude.setText(Double.toString(_longitude));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onProviderDisabled(String provider) {}
    }


}

