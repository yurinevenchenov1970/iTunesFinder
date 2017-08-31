package yurinevenchenov1970.github.com.itunesfinder.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * @author Yuri Nevenchenov on 8/31/2017.
 */

public class Utils {

    private Utils(){
        throw new IllegalStateException("Can't create object");
    }

    /**
     * Method to check network accessibility
     * @param context Context
     * @return true if there is connection, false otherwise
     */
    public static boolean isNetworkConnected(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}