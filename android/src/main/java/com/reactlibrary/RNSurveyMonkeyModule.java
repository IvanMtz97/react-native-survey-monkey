
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

import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.ActivityEventListener;

import static android.app.Activity.RESULT_OK;

public class RNSurveyMonkeyModule extends ReactContextBaseJavaModule {

  private SurveyMonkey surveyMonkey = new SurveyMonkey();
  private Callback lastSurveyCallback = null;

  public static final int SM_REQUEST_CODE = 1111;
  public static final String SM_ERROR = "smError";

  private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent intent) {
      super.onActivityResult(requestCode, resultCode, intent);

      if(requestCode == SM_REQUEST_CODE){
        boolean wasCompleted = true;

        // We are getting a non-OK result even for successful completes, thus failing only on cancellation
        if(resultCode != RESULT_OK){
          SMError e = (SMError) intent.getSerializableExtra(SM_ERROR);
          if(e.errorCode == 1 || e.description.equals("The user canceled out of the survey.")){
            wasCompleted = false;
          }
        }

        if(lastSurveyCallback != null) {
          lastSurveyCallback.invoke(wasCompleted);
        }
      }
    }

  };

  public RNSurveyMonkeyModule(ReactApplicationContext reactContext) {
    super(reactContext);
    reactContext.addActivityEventListener(mActivityEventListener);
  }

  @Override
  public String getName() {
    return "RNSurveyMonkey";
  }

  @ReactMethod
  public void takeSurvey(String appName, String hash, Callback callback) {
    try {
      final Activity activity = getCurrentActivity();
      Log.i("RNSurveyMonkey: ", "takeSurvey");
      surveyMonkey.onStart(activity, appName, SM_REQUEST_CODE, hash);
      surveyMonkey.startSMFeedbackActivityForResult(activity, SM_REQUEST_CODE, hash);
      lastSurveyCallback = callback;
    } catch (Exception e) {
      Log.e("RNSurveyMonkey: ", e.toString());
    }
  }
}