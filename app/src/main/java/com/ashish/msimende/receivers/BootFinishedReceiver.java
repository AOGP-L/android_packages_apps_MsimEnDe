/*
        * Msim Ende
        *
        * Helps to switch between single and dual sim only for GT-I9082. Flexible code to show only if GT-I9082.
        *
        * Copyright (c) 2014 Ashish Shekar
        *
        * This program is free software: you can redistribute it and/or modify
        * it under the terms of the GNU General Public License as published by
        * the Free Software Foundation, either version 3 of the License, or
        * (at your option) any later version.
        *
        * This program is distributed in the hope that it will be useful,
        * but WITHOUT ANY WARRANTY; without even the implied warranty of
        * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        * GNU General Public License for more details.
        *
        * You should have received a copy of the GNU General Public License
        * along with this program. If not, see <http://www.gnu.org/licenses/>.
        */

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
