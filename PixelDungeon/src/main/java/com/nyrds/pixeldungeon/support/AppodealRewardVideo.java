package com.nyrds.pixeldungeon.support;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.RewardedVideoCallbacks;
import com.appodeal.ads.utils.Log;
import com.nyrds.pixeldungeon.ml.BuildConfig;
import com.nyrds.pixeldungeon.ml.R;
import com.watabou.noosa.Game;
import com.watabou.noosa.InterstitialPoint;
import com.watabou.pixeldungeon.PixelDungeon;
import com.watabou.pixeldungeon.utils.GLog;

/**
 * Created by mike on 18.02.2017.
 * This file is part of Remixed Pixel Dungeon.
 */

public class AppodealRewardVideo {
	private static InterstitialPoint returnTo;

	public static void initCinemaRewardVideo() {
		Game.instance().runOnUiThread(new Runnable() {
			@Override
			public void run() {

				String appKey = Game.getVar(R.string.appodealRewardAdUnitId);
				Appodeal.disableLocationPermissionCheck();

				if(BuildConfig.DEBUG) {
					Appodeal.setLogLevel(Log.LogLevel.verbose);
					Appodeal.setTesting(true);
				}

				Appodeal.initialize(PixelDungeon.instance(), appKey, Appodeal.REWARDED_VIDEO);
				Appodeal.setRewardedVideoCallbacks(new RewardedVideoCallbacks() {
					@Override
					public void onRewardedVideoLoaded() {
						if(BuildConfig.DEBUG) {GLog.i("onRewardedVideoLoaded");}
					}
					@Override
					public void onRewardedVideoFailedToLoad() {
						if(BuildConfig.DEBUG) {GLog.i("onRewardedVideoFailedToLoad");}
					}
					@Override
					public void onRewardedVideoShown() {
						if(BuildConfig.DEBUG) {GLog.i("onRewardedVideoShown");}
					}
					@Override
					public void onRewardedVideoFinished(int amount, String name) {
						if(BuildConfig.DEBUG) {GLog.i("onRewardedVideoFinished. Reward: %d %s", amount, name);}

					}
					@Override
					public void onRewardedVideoClosed(final boolean finished) {
						if(BuildConfig.DEBUG) {GLog.i("onRewardedVideoClosed,  finished: %s", finished);}
						returnTo.returnToWork(finished);
					}
				});
			}
		});
	}

	public static void showCinemaRewardVideo(InterstitialPoint ret) {
		returnTo = ret;
		Game.instance().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if(Appodeal.isLoaded(Appodeal.REWARDED_VIDEO)) {
					Appodeal.show(PixelDungeon.instance(), Appodeal.REWARDED_VIDEO);
				} else {
					returnTo.returnToWork(false);
				}
			}
		});
	}

	public static boolean isReady() {
		return Appodeal.isLoaded(Appodeal.REWARDED_VIDEO);
	}
}
