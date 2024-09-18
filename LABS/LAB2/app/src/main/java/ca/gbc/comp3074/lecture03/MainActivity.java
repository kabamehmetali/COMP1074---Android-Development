// Student Name : Mehmet Ali KABA
// Student Number : 101453763

package ca.gbc.comp3074.lecture03;

import android.annotation.SuppressLint;
import android.app.UiAutomation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btn_activity,btn_browser,btn_map,btn_alarm,btn_camera;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_activity = findViewById(R.id.btn_activity);
        btn_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
        btn_browser = findViewById(R.id.btn_browser);

        btn_browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowser();
            }
        });
        btn_camera = findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage();
            }
        });
        btn_map = findViewById(R.id.btn_map);

        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
        btn_alarm = findViewById(R.id.btn_alarm);
        btn_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm();
            }
        });

    }

    public void openActivity(){
        Intent i = new Intent(this, MyActivity2.class);
        startActivity(i);

    }

    public void openBrowser(){
        Uri url = Uri.parse("https://google.ca/");
        Intent i = new Intent(Intent.ACTION_VIEW,url);
        // if(i.resolveActivity(getPackageManager()) != null){
            startActivity(i);
       //  } else {
        //    Toast.makeText(MainActivity.this,"NO App To Perform Activity",Toast.LENGTH_LONG);
        //}
    }
    public void openMap(){
        Uri location = Uri.parse("geo:43.6780371,-79.4094439?q=Casa+Loma");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(location);
        startActivity(mapIntent);

    }
    public void setAlarm() {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, "Wake Up!")
                .putExtra(AlarmClock.EXTRA_HOUR, 9)
                .putExtra(AlarmClock.EXTRA_MINUTES, 32)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "No app available to set an alarm", Toast.LENGTH_LONG).show();
        }
    }

    static final int REQUEST_CAPTURE_IMAGE = 1;

    public void captureImage(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.withAppendedPath(location, fileName));
        startActivityForResult(i, REQUEST_CAPTURE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CAPTURE_IMAGE && resultCode == RESULT_OK) {
            Log.d("IMAGE", "Got data");
            Bitmap img = data.getParcelableExtra("data");
            ImageView i = findViewById(R.id.imageView);
            i.setImageBitmap(img);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}