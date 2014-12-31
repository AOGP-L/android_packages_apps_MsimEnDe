package com.ashish.msimende.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ashish.msimende.services.MsimService;

/**
 * Created by ashish on 30/12/14.
 */
public class BootFinishedReceiver extends BroadcastReceiver {

    private static final String TAG = "BootFinishedReceiver";
    private static final String MSIM_INTENT = "com.ashish.msimende.MsimService";

    @Override
    public void onReceive(Context context, Intent intent) {

            Intent i = new Intent(context, MsimService.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startService(i);
        MsimService.msimPropBoolean = true;
        Log.i(TAG, "MsimEnDe: BroadCast Receiver invoked. Starting MsimService");
    }
}
