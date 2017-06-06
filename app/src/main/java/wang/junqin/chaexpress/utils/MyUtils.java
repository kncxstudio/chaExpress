package wang.junqin.chaexpress.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.List;

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

}
