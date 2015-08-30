package es.unex.afrancodq.prop1vuf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;

/**
 * @deprecated Use UnityPlayerNativeActivity instead.
 */
public class UnityPlayerProxyActivity extends Activity
{
	FloatingActionButton settingsButton;
	boolean sesionIniciada = false;

	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);


		settingsButton = (FloatingActionButton) findViewById(R.id.floating_button_settings);
		settingsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				callDialogActivity();
			}
		});

		Intent intent = new Intent(this, es.unex.afrancodq.prop1vuf.UnityPlayerNativeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		Bundle extras = getIntent().getExtras();
		if (extras != null)
			intent.putExtras(extras);
		startActivity(intent);
	}


	public void callDialogActivity(){
		Intent i = new Intent(this, DialogActivity.class);
		i.putExtra("sesionIniciada", sesionIniciada);
		startActivity(i);
	}
}
