package wang.junqin.chaexpress.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import java.util.List;

import wang.junqin.chaexpress.data.ComCodeIcoMap;
import wang.junqin.chaexpress.model.bean.ExpressComBean;

/**
 * Created by KN on 2017/5/29.
 */

public class MyUtils {
    public static Context context;
    static Handler handler = new Handler();

    public static void initMyUtils(Context context){
        MyUtils.context = context;
    }

    public static void showToast(final String str){
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static Intent shareInfoToFriends(String str){
        Intent intent = new Intent();
        intent.setType("text/plain");
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_SUBJECT, "share");
        intent.putExtra(Intent.EXTRA_TEXT, str);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return Intent.createChooser(intent, "分享物流信息");

    }

    public static RequestBuilder<Drawable> loadImage(String comCode){
        int comIcoRes = ComCodeIcoMap.getComIcoByCode(comCode);
        return Glide.with(context).load(comIcoRes);
    }


    public static String getVerisonInfo(Context context) throws Exception{
        PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
        String verisonInfo = info.versionName;
        return verisonInfo;
    }
}
