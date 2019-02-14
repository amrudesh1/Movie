package com.application.amrudesh.moviedb_udacity.Util;

import android.app.Activity;
import android.content.SharedPreferences;

public class Prefs {

    SharedPreferences sharedPreferences;


    public Prefs(Activity activity) {
       sharedPreferences = activity.getPreferences(activity.MODE_PRIVATE);
    }

    public void setSearch(String s)
    {
        sharedPreferences.edit().putString("search",s).commit();
    }
    public String getSearch()
    {
        return sharedPreferences.getString("search","Tom");
    }


}
