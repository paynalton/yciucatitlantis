package com.onurisys.yciucatitlantis;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	Intent intent;
	AccountAuthenticatorResponse response;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		intent = getIntent();
		Bundle extras = intent.getExtras();
		response = extras.getParcelable(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE);
	}

	public void registrar(View view){
		EditText editor=(EditText)findViewById(R.id.editText1);
		String nombre=(String)editor.getText().toString();
		AccountManager am = AccountManager.get(this);
		Account account = new Account(nombre, "com.onurisys.yciucatitlantis.user");
		
		boolean accountCreated = am.addAccountExplicitly(account, "nada", null);
		
		Bundle result = new Bundle();
		  result.putString(AccountManager.KEY_ACCOUNT_NAME, nombre);
		  result.putString(AccountManager.KEY_ACCOUNT_TYPE, "com.onurisys.yciucatitlantis.user");
		
		response.onResult(result);
		Log.v("Nombre Recopilado",nombre);
	}
}
