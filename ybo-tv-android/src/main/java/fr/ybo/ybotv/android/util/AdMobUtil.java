package fr.ybo.ybotv.android.util;

import android.app.Activity;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import fr.ybo.ybotv.android.R;

public class AdMobUtil {

    public static void manageAds(Activity activity) {
        // Look up the AdView as a resource and load a request.
        AdView adView = (AdView)activity.findViewById(R.id.adView);
        adView.loadAd(new AdRequest());
    }
}
