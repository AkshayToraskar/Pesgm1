package io.ak.pesgm.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dg hdghfd on 29-11-2016.
 */

public class SessionManager {

    private static String TAG = SessionManager.class.getSimpleName();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "pesgm";
    private static final String LANGUAGE = "language";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLanguage(String lang) {
        editor.putString(LANGUAGE, lang);
        editor.commit();
    }

    public String getLanguage() {
        return pref.getString(LANGUAGE, "en");
    }
}
