package com.example.hsg.lotto_project;

import android.app.Activity;

/**
 * Created by hsg on 2017. 10. 15..
 */

public class ActivityName {
    private String name;
    private Class<? extends Activity>  ActivityClass;

    public String getName() {
        return name;
    }

    public Class getActivityClass() {
        return ActivityClass;
    }

    public ActivityName(String name, Class activityClass) {
        this.name = name;
        ActivityClass = activityClass;
    }


}
