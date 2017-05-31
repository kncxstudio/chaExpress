package wang.junqin.chaexpress.data;

import java.util.HashMap;

/**
 * Created by KN on 2017/5/30.
 */

public class  ComCodeNameMap{

    private static HashMap<String,String> map = new HashMap<>();

    public static void init(){
        map.put("shunfeng","顺丰");
        map.put("zhongtong","中通");
        map.put("ems","EMS");
        map.put("yuantong","圆通");
        map.put("yunda","韵达");
        map.put("shentong","申通");
        map.put("jd","京东");
        map.put("tiantian","天天快递");
        map.put("dhl","DHL中国件");
        map.put("dhlen","DHL国际件");
        map.put("debangwuliu","德邦物流");
        map.put("youzhengguonei","邮政包裹(国内)");
        map.put("youzhengguoji","邮政包裹(国际)");
        map.put("kuaijiesudi","快捷速递");
        map.put("minghangkuaidi","民航快递");
    }

    public static String getComNameByCode(String comCode){
        if (!map.containsKey(comCode))return comCode;
        return map.get(comCode);
    }



}
