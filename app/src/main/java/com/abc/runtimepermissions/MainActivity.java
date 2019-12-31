package com.abc.runtimepermissions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.abc.runtimepermissions.databinding.ActivityMainBinding;

import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public static final int Requestcode=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView
                (MainActivity.this,R.layout.activity_main);

        binding.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkPermission()) {
                    Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();

                }else {
                    requestPermission();

                }

            }
        });
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{WRITE_EXTERNAL_STORAGE,
                READ_EXTERNAL_STORAGE,RECORD_AUDIO,
                READ_CONTACTS,LOCATION_SERVICE},Requestcode);

    }

    public boolean checkPermission(){

        int result=ContextCompat.checkSelfPermission(MainActivity.this,
                WRITE_EXTERNAL_STORAGE);
        int result1=ContextCompat.checkSelfPermission(MainActivity.this,
                READ_EXTERNAL_STORAGE);
        int result2=ContextCompat.checkSelfPermission(MainActivity.this,
                RECORD_AUDIO);
        int result3=ContextCompat.checkSelfPermission(MainActivity.this,
                READ_CONTACTS);
        int result4=ContextCompat.checkSelfPermission(MainActivity.this,
                LOCATION_SERVICE);

        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED &&
                result2==PackageManager.PERMISSION_GRANTED&&
                result3==PackageManager.PERMISSION_GRANTED &&
                result4==PackageManager.PERMISSION_GRANTED;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Requestcode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean Readcontact = grantResults[2] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean recordaudio = grantResults[3] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean location = grantResults[4] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission && Readcontact && recordaudio && location) {
                        Toast.makeText(MainActivity.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
}
