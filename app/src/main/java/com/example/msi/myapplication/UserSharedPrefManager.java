package com.example.msi.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.msi.myapplication.datamodel.User;

/**
 * Created by msi on 5/31/2018.
 */
public class UserSharedPrefManager {

    private static final String SHARED_PREF_NAME = "user_shared_pref";

    private SharedPreferences sharedPreferences;

    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_HTML = "is_html_expert";
    private static final String KEY_CSS = "is_css_expert";
    private static final String KEY_JAVA = "is_java_expert";
    private static final String KEY_GENDER = "gender";


    public UserSharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUser(User user) {
        if (user != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_FIRST_NAME, user.getFirstName());
            editor.putString(KEY_LAST_NAME, user.getLastName());
            editor.putBoolean(KEY_HTML, user.isHtmlExpert());
            editor.putBoolean(KEY_CSS, user.isCssExpert());
            editor.putBoolean(KEY_JAVA, user.isJavaExpert());
            editor.putInt(KEY_GENDER, user.getGender());
            editor.apply();

            /*apply() would work in a separate Thread */
            /*commit() would work in current Thread and it may causes some delay and pauses, so its
            * gonna use when we want to force the commit line then let other lines*/
        }
    }

    public User getUser() {
        User user = new User();
        user.setFirstName(sharedPreferences.getString(KEY_FIRST_NAME, ""));
        user.setLastName(sharedPreferences.getString(KEY_LAST_NAME, ""));
        user.setIsHtmlExpert(sharedPreferences.getBoolean(KEY_HTML, false));
        user.setIsCssExpert(sharedPreferences.getBoolean(KEY_CSS, false));
        user.setIsJavaExpert(sharedPreferences.getBoolean(KEY_JAVA, false));
        user.setGender((byte) sharedPreferences.getInt(KEY_GENDER, user.MALE));
        return user;
    }
}
