package wang.junqin.chaexpress.data;

import java.security.PublicKey;

/**
 * Created by KN on 2017/5/31.
 */

public class FLAGS {
    //Dialog return code
    public final static int DIALOG_RETURN_CODE = 1;


    //用于Dialog判断要执行的逻辑
    public final static String CHOOSE_EXPRESS_COMPANY = "请选择快递公司";
    public final static String CHOOSE_EXPRESS_ITEM_ACTION = "请选择操作";

    //Intent
    public final static String ENTITY = "entity";
    public final static String EXP_NUM = "exp_num";

    //NetWork
    public final static int NETWORK_NOT_BUSY = 0;
    public final static int NETWORK_IS_BUSY = 1;
}
