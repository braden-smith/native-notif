package com.bradensmith.Natnot;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import android.os.Vibrator;
//import android.content.Context;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
//import android.view.View;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;

/**
 * This class echoes a string called from JavaScript.
 */
public class Natnot extends CordovaPlugin {

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("createBasic")) {
			String message = args.getString(0);
			this.createBasic(message, callbackContext);
			return true;
		}
		return false;
	}

	private void createBasic(String message, CallbackContext callbackContext) {
		//Vibrator vibrator = (Vibrator) this.cordova.getActivity().getSystemService(Context.VIBRATOR_SERVICE);
		//vibrator.vibrate(1000);
		genAlert(message);
		if (message != null && message.length() > 0) {
			callbackContext.success(message);
		} else {
			callbackContext.error("Expected one non-empty string argument.");
		}
	}
	
	private void genAlert(String message) {
	        //build notification
	        int notificationId = 001;
	        //Intent viewIntent = new Intent(this, Natnot.class);
	        //Context context=this.cordova.getActivity().getApplicationContext();
    		Context context=cordova.getActivity().getApplicationContext();
    		Intent viewIntent=new Intent(context,Natnot.class);
	        
	        PendingIntent viewPendingIntent =
	                PendingIntent.getActivity(this.cordova.getActivity(), 0, viewIntent, 0);
	
	        NotificationCompat.Builder notificationBuilder =
	                new NotificationCompat.Builder(this.cordova.getActivity())
	                        .setSmallIcon(android.R.drawable.stat_notify_sync_noanim)
	                        .setContentTitle("ContentTitle")
	                        .setContentText("ContentText")
	                        .setContentIntent(viewPendingIntent);
	
	        // Get an instance of the NotificationManager service
	        NotificationManagerCompat notificationManager =
	                NotificationManagerCompat.from(this);
	        // Build the notification and issues it with notification manager.
	        notificationManager.notify(notificationId, notificationBuilder.build());
	}
	
}
