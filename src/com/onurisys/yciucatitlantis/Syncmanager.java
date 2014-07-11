package com.onurisys.yciucatitlantis;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

public class Syncmanager extends AbstractThreadedSyncAdapter{

	ContentResolver mContentResolver;

	public Syncmanager(Context context, boolean autoInitialize) {
		super(context, autoInitialize);
		mContentResolver = context.getContentResolver();
	}
	public Syncmanager(
            Context context,
            boolean autoInitialize,
            boolean allowParallelSyncs) {
		
		super(context, autoInitialize,allowParallelSyncs);
		mContentResolver = context.getContentResolver();
	}

	@Override
	public void onPerformSync(
			Account account,
            Bundle extras,
            String authority,
            ContentProviderClient provider,
            SyncResult syncResult) {
		Log.v("Syncmanager","Sincronizando");
		
	}

}