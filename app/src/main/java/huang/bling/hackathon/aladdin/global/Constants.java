package huang.bling.hackathon.aladdin.global;

import android.Manifest;

/**
 * Created by 沈东 on 2016/11/9.
 */

public class Constants {
    public static final String APP_BASE_URL = "http://service.yongxt.com/cxf/";
    /**图片上传地址*/
    public static final String HOSTURL = "http://service.yongxt.com/";
    /**apk下载地址*/
    public static final String UPDATEURL = "http://download.yongxt.com/95128.apk";
    /**自行车出行服务地址*/
    public static final String BICYCLEURL = "http://192.168.0.123:8080/";
    /**地铁出行服务地址*/
    public static final String SUBWAY = "http://192.168.0.123:8083/";

    /**查询车票信息服务（本地）接口地址*/
    public static final String TICKET = "http://192.168.0.133:8085/";

    /**位置信息权限请求标志*/
    public static final int QUEST_CODE_LOCTION = 1;
    /**发送短信权限请求标志*/
    public static final int QUEST_CODE_SEND_SMS = 2;
    /**摄像头权限标志*/
    public static final int QUEST_CODE_CAMERA = 3;
    /**拨打电话权限*/
    public static final int QUEST_CODE_CALL_PHONE = 4;
    /**存储文件权限*/
    public static final int QUEST_CODE_EXTERNAL = 5;
    /**批量请求权限*/
    public static final int QUEST_CODE_ALL  = 6;

    /**批量请求权限*/
    public static final int QUEST_CODE_CAMERA_EXTERNAL  = 7;

    /**存储文件权限*/
    public static final int QUEST_CODE_EXTERNAL2 = 8;

    /**要申请的权限*/
    public static final  String[] permArray =
            { Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.CALL_PHONE,Manifest.permission.READ_PHONE_STATE};

    /**要申请的权限*/
    public static final  String[] permArray2 =
            { Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA};

    /**长途售票常量*/
    public static final String TICKETUSER = "zz01";
    public static final String TICKETKEY = "E10ADC3949BA59ABBE56E057F20F883E";
    public static final String TICKETURL = "http://115.231.208.137:8081/";
    public static final String RETURNMODE_NUM = "0";
    public static final String RETURNMODE_INF = "1";
}
