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

package com.ashish.msimende;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ashish.msimende.helper.CommandShell;


public class MainActivity extends Activity {

    private static String cGetMsim = "getprop persist.radio.multisim.config";
    private static String cSetDualMsim = "setprop persist.radio.multisim.config dsds";
    private static String cSetSingleMsim = "setprop persist.radio.multisim.config none";
    private static String cReboot = "reboot";
    private static final String MAIN = "MainActivity";
    private final String URL = "http://aogp-devs.com";
    private static boolean rebBool = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentMsimStatus();
        rebBool = false;

        TextView statusTv = (TextView) findViewById(R.id.status_text);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/gothic.ttf");
        if(currentMsimStatus()) {

            statusTv.setText("Current Sim Status: Dual");
            statusTv.setTypeface(tf, Typeface.NORMAL);

        }
        else {

            statusTv.setText("Current Sim Status: Single");
            statusTv.setTypeface(tf, Typeface.NORMAL);

        }
    }

    private boolean currentMsimStatus() {
        String msimValue = "dsds";
        String msimStatus = CommandShell.normalShell(cGetMsim);
        return msimStatus.equals(msimValue);

    }

    public void EnableDualSim(View view) {

        //if sim status is already enabled show toast
        if(currentMsimStatus()){

            Toast.makeText(getApplicationContext(), getString(R.string.already_enabled), Toast.LENGTH_SHORT).show();

        }

        //else execute command to enable sim
        else {

            boolean finalOutput = CommandShell.executeCommand(cSetDualMsim);

            //if final output boolean is true then show toast
            if(finalOutput) {

                Toast.makeText(getApplicationContext(), getString(R.string.enMsim_successful), Toast.LENGTH_SHORT).show();

                ImageButton rebButton = (ImageButton) findViewById(R.id.fabbutton3);
                rebButton.setBackgroundColor(getResources().getColor(R.color.rebRed));
                rebBool = true;
            }


            //Log respective function
            else {

                Log.e(MAIN, "Error in EnableDualSim function");
            }


        }

    }

    public void DisableDualSim(View view) {

        if(currentMsimStatus()) {

            boolean finalDoutput = CommandShell.executeCommand(cSetSingleMsim);

            if(finalDoutput) {

                Toast.makeText(getApplicationContext(), getString(R.string.deMsim_successful), Toast.LENGTH_SHORT).show();

                ImageButton rebButton = (ImageButton) findViewById(R.id.fabbutton3);
                rebButton.setBackgroundColor(getResources().getColor(R.color.rebRed));
                rebBool = true;
            }

            else {

                Log.e(MAIN, "Error in DisableDualSim function");
            }

        }
        else {

            Toast.makeText(getApplicationContext(), getString(R.string.already_disabled), Toast.LENGTH_SHORT).show();

        }


    }


    public void RebootDevice(View view) {



        if(rebBool) {

            Toast.makeText(getApplicationContext(), getString(R.string.reboot_text), Toast.LENGTH_SHORT).show();
            boolean rebStatus = CommandShell.executeCommand(cReboot);

            if(rebStatus) {

                Log.d(MAIN, "Reboot Successfull");

            }

            else {

                Log.e(MAIN, "Error in RebootDevice function");

            }


        }

        else {

            Toast.makeText(getApplicationContext(),getString(R.string.disabled_reboot_text), Toast.LENGTH_SHORT).show();

        }


    }

    private void goTo(String url) {

        Intent webPage = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(webPage);

    }

    public void GoToPage(View view) {

        goTo(URL);
    }

}
