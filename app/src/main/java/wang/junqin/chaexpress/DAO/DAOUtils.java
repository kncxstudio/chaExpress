package wang.junqin.chaexpress.DAO;

import android.content.Context;

import com.objectbox.gen.MyObjectBox;

import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * Created by KN on 2017/6/2.
 */

public class DAOUtils {
    private static BoxStore boxStore;

    public static void init(Context context){
        boxStore = MyObjectBox.builder().androidContext(context).build();
    }

    public static BoxStore getBoxStore() {
        return boxStore;
    }

    public static void setBoxStore(BoxStore boxStore) {
        DAOUtils.boxStore = boxStore;
    }

    public static <T> Box<T> getClassBox(Class<T> dataClass){
        if (boxStore != null)return boxStore.boxFor(dataClass);
        return null;
    }
}
