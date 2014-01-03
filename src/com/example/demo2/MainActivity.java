package com.example.demo2;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int TAKE_PICTURE = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button takePicture = (Button) findViewById(R.id.btn_take_picture);
		takePicture.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				takePicture();

			}
		});

	}

	@Override
	protected void onPause() {

		Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
		super.onPause();
	}

	@Override
	protected void onStop() {

		Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
		super.onStop();
	}

	private void takePicture() {
		Intent intent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, getUri());

		startActivityForResult(intent, TAKE_PICTURE);

	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == TAKE_PICTURE) {

			Toast.makeText(this, "Chup xong", Toast.LENGTH_SHORT).show();
		}


		super.onActivityResult(requestCode, resultCode, data);
	}

	private Uri getUri() {
		File rootFolder = Environment.getExternalStorageDirectory();
		File tempPhoto = new File(rootFolder.getAbsoluteFile() + "/tmp.jpg");

		try {
			tempPhoto.createNewFile();

			return Uri.fromFile(tempPhoto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
