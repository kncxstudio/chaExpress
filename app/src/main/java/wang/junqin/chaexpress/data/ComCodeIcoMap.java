package wang.junqin.chaexpress.data;

import java.util.HashMap;

import wang.junqin.chaexpress.R;

/**
 * Created by KN on 2017/6/7.
 */


/**
 * Created by KN on 2017/5/30.
 */

public class ComCodeIcoMap {

    private static HashMap<String, Integer> map = new HashMap<>();

    public static void init() {
        map.put("shunfeng", R.drawable.express_shunfeng);
        map.put("zhongtong", R.drawable.express_zhongtong);
        map.put("ems", R.drawable.express_ems);
        map.put("yuantong", R.drawable.express_yuantong);
        map.put("yunda", R.drawable.express_yunda);
        map.put("shentong", R.drawable.express_shentong);
        map.put("jd", R.drawable.express_jingdong);
        map.put("tiantian", R.drawable.express_tiantian);
        map.put("dhl", R.drawable.express_dhl);
        map.put("dhlen", R.drawable.express_dhl);
        map.put("debangwuliu", R.drawable.express_debang);
        map.put("youzhengguonei", R.drawable.express_pingyou);
        map.put("youzhengguoji", R.drawable.express_pingyou);
        map.put("kuaijiesudi", R.drawable.express_kuaijie);
        map.put("minghangkuaidi", R.drawable.express_minhang);
        map.put("huitongkuaidi", R.drawable.express_huitong);
        map.put("baisiwuliu", R.drawable.express_huitong);
    }

    public static int getComIcoByCode(String comCode) {
        if (!map.containsKey(comCode)) return R.drawable.logo;
        return map.get(comCode);
    }


}


