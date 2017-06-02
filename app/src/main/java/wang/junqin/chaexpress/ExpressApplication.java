package wang.junqin.chaexpress;

import android.app.Application;
import android.content.Context;

import com.objectbox.gen.MyObjectBox;

import io.objectbox.BoxStore;
import wang.junqin.chaexpress.DAO.DAOUtils;
import wang.junqin.chaexpress.data.ComCodeNameMap;
import wang.junqin.chaexpress.utils.MyUtils;

/**
 * Created by KN on 2017/5/29.
 */

public class ExpressApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ComCodeNameMap.init();
        MyUtils.initMyUtils(context);
        DAOUtils.init(context);
    }

    public static Context getContext(){
        return context;

    }
}
