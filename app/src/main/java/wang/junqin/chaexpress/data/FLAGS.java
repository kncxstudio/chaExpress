package wang.junqin.chaexpress.data;

import java.security.PublicKey;

/**
 * Created by KN on 2017/5/31.
 */

public class FLAGS {


    //About
    public final static String PROJECT_URL = "https://github.com/kncxstudio/chaExpress";


    //Alipay QR Code String
    public final static String ALIPAY_QR_CODE_STR = "TSX05552NYQRGUR97FK14F";

    //Dialog return code
    public final static int DIALOG_RETURN_CODE = 1;


    //用于Dialog判断要执行的逻辑
    public final static String CHOOSE_EXPRESS_COMPANY = "请选择快递公司";
    public final static String CHOOSE_EXPRESS_ITEM_ACTION = "请选择操作";

    //Intent
    public final static String ENTITY = "entity";
    public final static String EXP_NUM = "exp_num";
    public final static String REMARK = "remark";
    public final static int EDIT_REMARK_ACTION = 1000;
    public final static int EDIT_REMARK_SUCCESS = 1001;

    //NetWork
    public final static int NETWORK_NOT_BUSY = 0;
    public final static int NETWORK_IS_BUSY = 1;
}
