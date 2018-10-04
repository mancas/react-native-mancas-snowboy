
package com.reactlibrary;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import main.java.com.reactlibrary.Constants;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

public class RNMancasSnowboyModule extends ReactContextBaseJavaModule {

  private static final String TAG = RNMancasSnowboyModule.class.getSimpleName();
  private final ReactApplicationContext reactContext;
  private static final String DEFAULT_SAMPLE_RATE = "DEFAULT_SAMPLE_RATE";
  private static final String DEFAULT_SENSITIVITY = "DEFAULT_SENSITIVITY";
  private RecordingThread recordingThread;

  public RNMancasSnowboyModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;

    AppResCopy.copyResFromAssetsToSD(reactContext);
  }

  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put(DEFAULT_SAMPLE_RATE, Constants.DEFAULT_SAMPLE_RATE);
    constants.put(DEFAULT_SENSITIVITY, Constants.DEFAULT_SENSITIVITY);
    return constants;
  }

  @ReactMethod
  public void initHotword(String sensitivity, Promise promise) {
    if (ActivityCompat.checkSelfPermission(reactContext,
        Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
      try {
        this.recordingThread = new RecordingThread(sensitivity, handle);
        promise.resolve(true);
      } catch (Exception e) {
        String errorMessage = e.getMessage();
        Log.e(TAG, errorMessage);
        promise.reject(errorMessage);
      }
    }
  }

  @ReactMethod
  public void startRecording() {
    Log.v(TAG, "Start recording");
    if (null != this.recordingThread) {
      this.recordingThread.startRecording();
    }
  }

  @ReactMethod
  public void stopRecording() {
    Log.v(TAG, "Stop recording");
    if (null != this.recordingThread) {
      this.recordingThread.stopRecording();
    }
  }

  @ReactMethod
  public void destroy() {
    Log.v(TAG, "Destroy");
    if (null != this.recordingThread) {
      this.recordingThread.stopRecording();
    }
  }

  private Handler handle = new Handler(Looper.getMainLooper()) {
    @Override
    public void handleMessage(Message msg) {
      MsgEnum message = MsgEnum.getMsgEnum(msg.what);
      switch (message) {
      case MSG_ACTIVE:
        sendEvent("HOTWORD_DETECTED", null);
        break;
      case MSG_INFO:
        sendEvent("HOTWORD_INFO", null);
        break;
      case MSG_VAD_SPEECH:
        sendEvent("HOTWORD_SPEECH", null);
        break;
      case MSG_VAD_NOSPEECH:
        sendEvent("HOTWORD_SILENCE", null);
        break;
      case MSG_ERROR:
        sendEvent("HOTWORD_ERROR", null);
        break;
      default:
        super.handleMessage(msg);
        break;
      }
    }
  };

  private void sendEvent(String eventName, @Nullable WritableMap params) {
    reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
  }

  @Override
  public String getName() {
    return "RNMancasSnowboy";
  }
}
