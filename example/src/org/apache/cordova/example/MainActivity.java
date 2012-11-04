package org.apache.cordova.example;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main2);
		
		final EditText etAddress = (EditText)findViewById(R.id.etAddress);
		Button btnLaunch = (Button)findViewById(R.id.connect);
		
		btnLaunch.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String addr = etAddress.getText().toString();
				
				Intent intent = new Intent(MainActivity.this, cordovaExample.class);
				intent.putExtra("addr", addr);
				
				startActivity(intent);
			}
		});
	}
}
