package fr.ybo.ybotv.android.util;

import android.content.Context;
import android.content.res.TypedArray;

public class ArraysUtil {
    public static int[] getIdsArray(Context context, int idArray) {
        TypedArray ar = context.getResources().obtainTypedArray(idArray);
        int len = ar.length();
        int[] arrayIds = new int[len];
        for (int i = 0; i < len; i++)
            arrayIds[i] = ar.getResourceId(i, 0);
        ar.recycle();
        return arrayIds;
    }
}
