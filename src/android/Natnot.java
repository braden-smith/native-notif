package com.bradensmith.Natnot;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//Notification specific
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
//QR specific
import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
//Vibration specific
//import android.os.Vibrator;

/**
 * This class echoes a string called from JavaScript.
 */
public class Natnot extends CordovaPlugin {

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("createBasic")) {
			String contentTitle = args.getString(0);
			String contentText = args.getString(1);
			String contentTitleBig = args.getString(2);
			String contentQR = args.getString(3);
			this.createBasic(contentTitle, contentText, contentTitleBig, contentQR, callbackContext);
			return true;
		}
		return false;
	}

	private void createBasic(String contentTitle,String contentText,String contentTitleBig,String contentQR, CallbackContext callbackContext) {
		//Vibrator vibrator = (Vibrator) this.cordova.getActivity().getSystemService(Context.VIBRATOR_SERVICE);
		//vibrator.vibrate(1000);
		genAlert(contentTitle, contentText, contentTitleBig, contentQR);
		if (contentQR != null && contentQR.length() > 0) {
			callbackContext.success(contentQR);
		} else {
			callbackContext.error("Expected one non-empty string argument.");
		}
	}
	
	private void genAlert(String contentTitle,String contentText,String contentTitleBig,String contentQR) {
		//prepare QR
		String qrData = contentQR;
        	int qrCodeDimension = 250;
		QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrData, null,
                	Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(), qrCodeDimension);
                
	        //build notification
	        int notificationId = 001;
    		Context context=cordova.getActivity().getApplicationContext();
    		Intent viewIntent=new Intent(context,Natnot.class);
	        
	        PendingIntent viewPendingIntent =
	                PendingIntent.getActivity(this.cordova.getActivity(), 0, viewIntent, 0);
	                
		try {
			Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
			
			NotificationCompat.Builder notificationBuilder =
			    new NotificationCompat.Builder(this.cordova.getActivity())
			            .setSmallIcon(android.R.drawable.stat_notify_sync_noanim)
			            .setLargeIcon(bitmap)
			            .setContentTitle(contentTitle)
			            .setContentText(contentText)
			            .setContentIntent(viewPendingIntent)
			            .setStyle(new NotificationCompat.BigPictureStyle()
			                    .bigPicture(bitmap)
			                    .bigLargeIcon(bitmap)
			                    .setBigContentTitle(contentTitleBig))
			;
			// Get an instance of the NotificationManager service
		        NotificationManagerCompat notificationManager =
		                NotificationManagerCompat.from(this.cordova.getActivity());
		        // Build the notification and issues it with notification manager.
		        notificationManager.notify(notificationId, notificationBuilder.build());
	        } catch (WriterException e) {
	            e.printStackTrace();
	        }

		/*
	        NotificationCompat.Builder notificationBuilder =
	                new NotificationCompat.Builder(this.cordova.getActivity())
	                        .setSmallIcon(android.R.drawable.stat_notify_sync_noanim)
	                        .setContentTitle("ContentTitle")
	                        .setContentText("ContentText")
	                        .setContentIntent(viewPendingIntent);
	        */

	}
	
}
