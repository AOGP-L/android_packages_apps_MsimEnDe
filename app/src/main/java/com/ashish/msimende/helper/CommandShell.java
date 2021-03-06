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

package com.ashish.msimende.helper;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

/**
 * Created by ashish on 27/12/14.
 */
public class CommandShell {

    public static final String APP = "MsimEnDeCommand";

    public static boolean executeCommand(String command) {

        try {
            Process getRootAccess = Runtime.getRuntime().exec("su");
            DataOutputStream getRootAccessDos = new DataOutputStream(getRootAccess.getOutputStream());
            getRootAccessDos.writeBytes(command + "\n");
            getRootAccessDos.writeBytes("exit\n");
            getRootAccessDos.flush();
            getRootAccess.waitFor();

            if (getRootAccess.exitValue() == 1) {

                Log.e(APP, "CommandShell: SuperUser Access demied.");
                return false;

            } else {


                Log.i(APP, "CommandShell: SuperUser permission granted.");
                return true;
            }

        } catch (IOException e) {

            Log.e(APP, "CommandShell: Unable to execute command as superuser!");
            return false;

        } catch (InterruptedException e) {

            Log.e(APP, "CommandShell: executeCommand method interrupted");
            return false;

        }


        }

    public static String normalShell(String normalCommand) {

        String rOutput = null;
        try {
            Process mProcess = Runtime.getRuntime().exec(normalCommand);
            mProcess.waitFor();
            BufferedReader mReader = new BufferedReader(new InputStreamReader(mProcess.getInputStream()));
            rOutput = mReader.readLine();
            Log.i(APP, "Output from normalShell = " + rOutput);
        } catch (IOException e) {
            Log.e(APP, "CommandShell: Unable to execute command at shell without root - I/O Exception");
        } catch (InterruptedException e) {
            Log.e(APP, "CommandShell: Unable to execute command at shell without root - Process interrupted");
        }

        return rOutput;



    }


    }
