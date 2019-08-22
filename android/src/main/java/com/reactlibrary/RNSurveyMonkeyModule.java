
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.surveymonkey.surveymonkeyandroidsdk.SurveyMonkey;
import com.surveymonkey.surveymonkeyandroidsdk.utils.SMError;

import android.app.Activity;
import android.util.Log;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.ActivityEventListener;

public class RNSurveyMonkeyModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  private SurveyMonkey surveyMonkey = new SurveyMonkey();

  public static final int SM_REQUEST_CODE = 0;

  private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent intent) {
      if (resultCode == 0) {
          Log.i("SM-STATUS", "success");
      }else{
          Log.i("SM-STATUS", "error");
      }
    }
    
  };

  public RNSurveyMonkeyModule(ReactApplicationContext reactContext) {
    super(reactContext);
    reactContext.addActivityEventListener(mActivityEventListener);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNSurveyMonkey";
  }

  @ReactMethod
  public void takeSurvey(String appName, String hash) {
    try {
      final Activity activity = getCurrentActivity();
      Log.i("SurveymonkeyModule: ", "takeSurvey");
      surveyMonkey.onStart(activity, appName, SM_REQUEST_CODE, hash);
      surveyMonkey.startSMFeedbackActivityForResult(activity, SM_REQUEST_CODE, hash);
    } catch (Exception e) {
      Log.i("SurveymonkeyModuleError: ", e.toString());
    }
  }
}