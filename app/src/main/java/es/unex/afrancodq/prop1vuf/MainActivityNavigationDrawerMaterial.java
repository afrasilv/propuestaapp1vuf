package es.unex.afrancodq.prop1vuf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.unity3d.player.UnityPlayer;

public class MainActivityNavigationDrawerMaterial extends Activity implements View.OnTouchListener {
    boolean sesionIniciada = false;
    FloatingActionButton settingsButton;
    public static Context mContext;
    protected UnityPlayer mUnityPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_material);

        settingsButton = (FloatingActionButton) findViewById(R.id.floating_button_settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivityNavigationDrawerMaterial.this, DialogActivity.class);
                i.putExtra("sesionIniciada", sesionIniciada);
                startActivity(i);
            }
        });

        mContext = this;
        getWindow().takeSurface(null);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);
        getWindow().setFormat(PixelFormat.RGBX_8888);

        mUnityPlayer = new UnityPlayer(this);
        if (mUnityPlayer.getSettings ().getBoolean ("hide_status_bar", true))
            getWindow ().setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
        boolean trueColor8888 = false;
        mUnityPlayer.init(glesMode, trueColor8888);

        View playerView = mUnityPlayer.getView();

        FrameLayout layout = (FrameLayout) findViewById(R.id.fragment_container);

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        layout.addView(playerView, 0, lp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void iniciarSesion(){
        sesionIniciada = true;
    }

    public void cerrarSesion(){
        sesionIniciada = false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }


    protected void onDestroy ()
    {
        mUnityPlayer.quit();
        super.onDestroy();
    }

    // onPause()/onResume() must be sent to UnityPlayer to enable pause and resource recreation on resume.
    protected void onPause()
    {
        super.onPause();
        mUnityPlayer.pause();
    }
    protected void onResume()
    {
        super.onResume();
        mUnityPlayer.resume();
    }
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mUnityPlayer.configurationChanged(newConfig);
    }
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
            return mUnityPlayer.onKeyMultiple(event.getKeyCode(), event.getRepeatCount(), event);
        return super.dispatchKeyEvent(event);
    }

}
