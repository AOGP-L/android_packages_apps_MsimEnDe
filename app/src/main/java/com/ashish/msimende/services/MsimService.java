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

package com.ashish.msimende.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.ashish.msimende.helper.CommandShell;

/**
 * Created by ashish on 30/12/14.
 */
public class MsimService extends Service {

    public static boolean msimPropBoolean = false;
    private static String cGetMsimProp = "getprop ro.aogp.msimende";

    private static final String TAG = "MsimService";
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    public void onDestroy() {
        Log.d(TAG, "Destroyed");
    }

    @Override
    public void onStart(Intent intent, int startid)
    {
        setPackageHidden();
        Log.d(TAG, "Service started. App will be hidden if prop returns 1");
    }

    private void setPackageHidden() {

        //component to hide
        ComponentName componentName = new ComponentName("com.ashish.msimende","com.ashish.msimende.MainActivity");

        if(msimPropBoolean) {

            String one = "1";
            String zero = "0";
            String propOut = CommandShell.normalShell(cGetMsimProp);
            Toast.makeText(getApplicationContext(), "got prop output", Toast.LENGTH_SHORT).show();

            if (propOut.equals(one)) {

                Log.i(TAG, "MsimEnDe: propOut returned 1");
                    PackageManager pm = getPackageManager();
                    pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

            } //safe-code the propOut value to enable when normalShell returns 0
            else if(propOut.equals(zero)) {

                Log.i(TAG, "MsimEnDe: propOut returned 0");
                PackageManager pm = getPackageManager();
                pm.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

            }

    }
            else {

                msimPropBoolean = false;
                Log.i(TAG, "propValue returns 0 so it'll not be hidden no service to invoke.");

            }
        }


        }
