package com.onurisys.yciucatitlantis;

import android.app.Activity;
import android.os.*;
import android.util.Log;

public class MainActivity extends Activity
{
	Bundle extras;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
    	Log.v("MAIN","Iniciando Actividad");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
