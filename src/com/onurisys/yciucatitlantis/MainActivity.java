package com.onurisys.yciucatitlantis;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.*;
import android.content.Context;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity
{
	public static final String AUTHORITY = "com.onurisys.yciucatitlantis.datasync.provider";
	public static final String ACCOUNT_TYPE = "com.onurisys.yciucatitlantis.datasync";
	public static final String ACCOUNT = "dummyaccount";
	Account mAccount;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mAccount = CreateSyncAccount(this);
    }
    public void iniciarActualizador(View boton){
    	Log.v("MAIN", "Iniciando actualizador");
    }
    
    public static Account CreateSyncAccount(Context context) {
        Account newAccount = new Account(ACCOUNT, ACCOUNT_TYPE);
        AccountManager accountManager =(AccountManager) context.getSystemService(ACCOUNT_SERVICE);
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            
        } else {
        	
        }
        return newAccount;
    }
}
