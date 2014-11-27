package com.bradensmith.Natnot;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import android.os.Vibrator;
//import android.content.Context;

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
		if (message != null && message.length() > 0) {
			callbackContext.success(message);
		} else {
			callbackContext.error("Expected one non-empty string argument.");
		}
	}
}
