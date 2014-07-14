package com.onurisys.yciucatitlantis.sync.cuentas;

import com.onurisys.yciucatitlantis.LoginActivity;
import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Autentificador extends AbstractAccountAuthenticator {
    // Simple constructor
	Context contexto;
	AccountAuthenticatorResponse responseCallback;
	ContentResolver mResolver;
	
    public Autentificador(Context context) {
        super(context);
        contexto=context;
    	Log.v("AUTENTIFICADOR","Instanciando");
    }
    // Editing properties is not supported
    @Override
    public Bundle editProperties(
            AccountAuthenticatorResponse r, String s) {
    	Log.v("AUTENTIFICADOR","editando propiedades");
        throw new UnsupportedOperationException();
    }
    // Don't add additional accounts
    @Override
    public Bundle addAccount(
            AccountAuthenticatorResponse response,
            String s,
            String s2,
            String[] strings,
            Bundle bundle) throws NetworkErrorException {
    	 Bundle reply = new Bundle();
    	 responseCallback=response;
    	Intent intent = new Intent(contexto, LoginActivity.class);

    	intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
    	
    	 reply.putParcelable(AccountManager.KEY_INTENT, intent);
    	Log.v("AUTENTIFICADOR","agregando cuenta");
    	crearNuevaCuenta();
    	return null;
    }
    private Account crearNuevaCuenta(){
    	String user="usuariodummy";
    	AccountManager am = AccountManager.get(contexto);
    	Account account = new Account(user, "com.onurisys.yciucatitlantis.user");
    	boolean accountCreated = am.addAccountExplicitly(account, "nada", null);
    	
    	
    	if(accountCreated){
    		Bundle result = new Bundle();
    		result.putString(AccountManager.KEY_ACCOUNT_NAME, user);
    		result.putString(AccountManager.KEY_ACCOUNT_TYPE, "com.onurisys.yciucatitlantis.user");
    		responseCallback.onResult(result);
    		
        	mResolver = contexto.getContentResolver();
        	mResolver.addPeriodicSync(account, "com.onurisys.yciucatitlantis.sync.Provider", null, 1L);
        	mResolver.setIsSyncable(account, "com.onurisys.yciucatitlantis.sync.Provider", 1);
        	mResolver.setSyncAutomatically(account, "com.onurisys.yciucatitlantis.sync.Provider", true);
    	}
    	return null;
    }
    // Ignore attempts to confirm credentials
    @Override
    public Bundle confirmCredentials(
            AccountAuthenticatorResponse r,
            Account account,
            Bundle bundle) throws NetworkErrorException {
    	Log.v("AUTENTIFICADOR","confirmar credenciales");
        return null;
    }
    // Getting an authentication token is not supported
    @Override
    public Bundle getAuthToken(
            AccountAuthenticatorResponse r,
            Account account,
            String s,
            Bundle bundle) throws NetworkErrorException {
    	Log.v("AUTENTIFICADOR","obteniendo token");
        throw new UnsupportedOperationException();
    }
    // Getting a label for the auth token is not supported
    @Override
    public String getAuthTokenLabel(String s) {
        throw new UnsupportedOperationException();
    }
    // Updating user credentials is not supported
    @Override
    public Bundle updateCredentials(
            AccountAuthenticatorResponse r,
            Account account,
            String s, Bundle bundle) throws NetworkErrorException {
    	Log.v("AUTENTIFICADOR","actualizando credenciales");
        throw new UnsupportedOperationException();
    }
    // Checking features for the account is not supported
    @Override
    public Bundle hasFeatures(
        AccountAuthenticatorResponse r,
        Account account, String[] strings) throws NetworkErrorException {
    	Log.v("AUTENTIFICADOR","consultando caracteristicas");
        throw new UnsupportedOperationException();
    }
}
