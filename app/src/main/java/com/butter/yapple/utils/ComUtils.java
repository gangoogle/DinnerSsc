package com.butter.yapple.utils;

import android.content.Context;
import android.util.Log;

import com.butter.yapple.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zgyi on 2018-01-16.
 */

public class ComUtils {


    public static User getLoginInfo(Context ctx) {
        User user = new User("", "");
        try {
            String json = ctx.getSharedPreferences(Constant.LOGIN_INFO, Context.MODE_PRIVATE).getString(Constant
                    .LOGIN_E_P, "");
            user = new Gson().fromJson(json, User.class);
        } catch (Exception e) {
            user = new User("", "");
        }
        if (user == null) {
            user = new User("", "");
        }
        return user;
    }

    public static void saveLoginInfo(Context ctx, String email, String pwd) {
        Gson gson = new Gson();
        String json = gson.toJson(new User(email, pwd));
        ctx.getSharedPreferences(Constant.LOGIN_INFO, ctx.MODE_PRIVATE).edit().putString(Constant.LOGIN_E_P, json)
                .commit();
    }

    public static ArrayList<User> getRegisterUserList(Context ctx) {
        ArrayList<User> users = new ArrayList<>();
        try {

            String json = ctx.getSharedPreferences(Constant.LOGIN_INFO, Context.MODE_PRIVATE).getString(Constant
                    .Login_User_List, "");
            users = new Gson().fromJson(json, new TypeToken<ArrayList<User>>() {
            }.getType());
        } catch (Exception e) {
        }
        if (users == null) {
            users = new ArrayList<>();
        }

        return users;
    }

    public static void saveToRegisterUserList(Context ctx, String email, String pwd) {
        ArrayList<User> s = getRegisterUserList(ctx);
        boolean flag = false;
        for (User u : s) {
            if (u.getEmail().equals(email)) {
                flag = true;
            }
        }
        if (!flag) {
            s.add(new User(email, pwd));
        }
        Gson gson = new Gson();
        String json = gson.toJson(s);
        ctx.getSharedPreferences(Constant.LOGIN_INFO, ctx.MODE_PRIVATE).edit().putString(Constant.Login_User_List, json)
                .commit();
    }


    public static ArrayList<String> getCollections(Context ctx) {
        ArrayList<String> list = new ArrayList<>();
        try {
            String json = ctx.getSharedPreferences(Constant.SP_COLLECTION_MAIN, ctx.MODE_PRIVATE).getString(Constant
                    .SP_COLLECTION_List, "");

            list = new Gson().fromJson(json, new TypeToken<ArrayList<String>>() {
            }.getType());
        } catch (Exception e) {

        }
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    public static void saveCollection(Context ctx, String title) {
        ArrayList<String> s = getCollections(ctx);
        boolean flag = false;
        for (String u : s) {
            if (u.equals(title)) {
                flag = true;
            }
        }
        if (!flag) {
            s.add(title);
        }
        Gson gson = new Gson();
        String json = gson.toJson(s);
        ctx.getSharedPreferences(Constant.SP_COLLECTION_MAIN, ctx.MODE_PRIVATE).edit().putString(Constant
                .SP_COLLECTION_List, json)
                .commit();

    }

    public static void deleteCollection(Context ctx, String title) {
        ArrayList<String> s = getCollections(ctx);
        if (s.contains(title)) {
            s.remove(title);
        }
        Gson gson = new Gson();
        String json = gson.toJson(s);
        ctx.getSharedPreferences(Constant.SP_COLLECTION_MAIN, ctx.MODE_PRIVATE).edit().putString(Constant
                .SP_COLLECTION_List, json)
                .commit();
    }

    public static Boolean getNotificationSetting(Context ctx) {
        return ctx.getSharedPreferences(Constant.SP_NOTIFICATION_SWITCH, ctx.MODE_PRIVATE)
                .getBoolean(Constant.SP_NOTIFICATION_SWITCH, false);
    }

    public static void setNotificationSetting(Context ctx, boolean sw) {
        ctx.getSharedPreferences(Constant.SP_NOTIFICATION_SWITCH, ctx.MODE_PRIVATE)
                .edit()
                .putBoolean(Constant.SP_NOTIFICATION_SWITCH, sw)
                .commit();
    }

    public static Integer getScore(Context ctx) {
        return ctx.getSharedPreferences(Constant.SP_SCORE, ctx.MODE_PRIVATE)
                .getInt(Constant.SP_SCORE, 0);
    }

    public static void addScore(Context ctx, int score) {
        ctx.getSharedPreferences(Constant.SP_SCORE, ctx.MODE_PRIVATE)
                .edit()
                .putInt(Constant.SP_SCORE, getScore(ctx) + score)
                .commit();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static String getYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        String yesterday = "";
        try {
            calendar.setTime(date);
            int day = calendar.get(Calendar.DATE);
            calendar.set(Calendar.DATE, day - 1);
            yesterday = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("error", "getYesterday异常");
        }
        return yesterday;
    }

    public static Date stringToDate(String source, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(source);
        } catch (ParseException e) {
            Log.e("error", "字符串转换日期异常");
        }
        return date;
    }
}
