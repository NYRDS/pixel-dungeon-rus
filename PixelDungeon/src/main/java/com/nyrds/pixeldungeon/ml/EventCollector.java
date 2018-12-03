package com.nyrds.pixeldungeon.ml;

import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.Trace;
import com.watabou.noosa.Game;
import com.watabou.pixeldungeon.Preferences;

import java.util.HashMap;

/**
 * Created by mike on 09.03.2016.
 */
public class EventCollector {
	public static final String BUG = "bug";

	static private FirebaseAnalytics mFirebaseAnalytics;
	static private boolean mDisabled = true;

	static private HashMap<String,Trace> timings = new HashMap<>();

	private static boolean analyticsUsable() {
		return Preferences.INSTANCE.getInt(Preferences.KEY_COLLECT_STATS,1) > 0;
	}

	static public void init() {
	    if(analyticsUsable()) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(Game.instance());
            mDisabled = false;
        }
	}

	static public void logEvent(String category, String event) {
		if (!mDisabled) {
			Crashlytics.log(category+":"+event);

			Bundle params = new Bundle();
			params.putString("event", event);
			mFirebaseAnalytics.logEvent(category, params);
		}
	}

	static public void logEvent(String category, String event, String label) {
		if (!mDisabled) {
			Crashlytics.log(category+":"+event+":"+label);

			Bundle params = new Bundle();
			params.putString("event", event);
			params.putString("label", label);
			mFirebaseAnalytics.logEvent(category, params);
		}
	}

	static public void logScene(String scene) {
		if (!mDisabled) {
			mFirebaseAnalytics.setCurrentScreen(Game.instance(), scene, null);
		}
	}

	static public void logException(Exception e) {
		if(!mDisabled) {
			Crashlytics.logException(e);
		}
	}

	static public void logException(Throwable e, String desc) {
		if(!mDisabled) {
			Crashlytics.log(desc);
			Crashlytics.logException(e);
		}
	}

	static public void startTiming(String id) {
		if(!mDisabled) {
			Trace trace = FirebasePerformance.getInstance().newTrace(id);
			trace.start();
			timings.put(id,trace);
		}
	}



	static public void stopTiming(String id, String category, String variable, String label) {

		if(!mDisabled) {

		    Trace trace = timings.get(id);
			if (trace==null) {
				logException(new Exception("attempt to stop null timer:"+id));
			}

		    trace.putAttribute("category", category);
            trace.putAttribute("variable", variable);
            trace.putAttribute("label",    label);

		    trace.stop();
		    timings.remove(id);
		}
	}

	public static void collectSessionData(String key, String value) {
		Crashlytics.setString(key,value);
	}
}
