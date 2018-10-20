package huang.bling.hackathon.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UIHelper {

    /**
     * 显示Toast
     *
     * @param context
     * @param msg
     */
    public static void showtoast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 判断是否是手机号码
     *
     * @param inputText
     * @return
     */
    public static boolean isPhone(String inputText) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(17[0,0-9])|(18[0,0-9]))\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
    }

    /**
     * 获取时间并将样式格式化为"02/05 12:50"
     *
     * @param str
     * @return
     */
    public static String showTimeOnList(String str) {
        return str.substring(5, 7) + "/" + str.substring(8, 10) + " " + str.substring(11, 16);
    }



    /**
     * 2017-12-20 06:50:00
     * 获取时间并将样式格式化为"06:50"
     *
     * @param str
     * @return
     */
    public static String showHourTime(String str) {
        return str.substring(11, 16);
    }

    /**
     * 判断内容是否为空
     *
     * @param input
     * @return
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }


    /**
     * 将自定义的地址String类型转为LatLng类
     *
     * @param location
     * @return
     */
//    public static LatLng changeAMapLocationToLatLng(String location) {
//        String[] result = location.split(",");
//        Double lng = Double.valueOf(result[0]);
//        Double lat = Double.valueOf(result[1]);
//        LatLng location1 = new LatLng(lat, lng);
//        return location1;
//    }

//    /**
//     * 将服务器获取过来的GPS的location转换为高德坐标的LatLng类
//     *
//     * @param location
//     * @return
//     */
//    public static LatLng changeServiceLocationToLatLng(String location) {
//        String[] result = location.split("\\s");
//        Double lng = Double.valueOf(result[0]);
//        Double lat = Double.valueOf(result[1]);
//        LatLng location1 = new LatLng(lat, lng);
//        return AMapUtil.transformFromWGSToGCJ(location1);
//    }
//
//    /**
//     * 将服务器获取过来的GPS的location转换为高德坐标的LatLng类
//     *
//     * @param location
//     * @return
//     */
//    public static LatLng changeLocationToLatLng(String location) {
//        String[] result = location.split(",");
//        Double lng = Double.valueOf(result[0]);
//        Double lat = Double.valueOf(result[1]);
//        LatLng location1 = new LatLng(lat, lng);
//        return AMapUtil.transformFromWGSToGCJ(location1);
//    }

//
//    /**
//     * 将服务器获取过来的GPS的location转换为高德坐标的LatLng类
//     *
//     * @param location
//     * @return
//     */
//    public static LatLonPoint changeLocationToLatLonPoint(String location) {
//        String[] result = location.split(",");
//        Double lng = Double.valueOf(result[0]);
//        Double lat = Double.valueOf(result[1]);
//        LatLng location1 = AMapUtil.transformFromWGSToGCJ(new LatLng(lat, lng));
//        LatLonPoint locationPoint = new LatLonPoint(location1.latitude, location1.longitude);
//        return locationPoint;
//    }


    /**
     * 获取apk的版本号 currentVersionCode
     *
     * @param ctx
     * @return
     */
    public static String getAPPVersionCodeFromAPP(Context ctx) {
//			int currentVersionCode = 0;
        String appVersionName = null;
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            appVersionName = info.versionName; // 版本名
//				currentVersionCode = info.versionCode; // 版本号
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch blockd
            e.printStackTrace();
        }
        return appVersionName;
    }

//    /**
//     * 获得手机IMEI号
//     *
//     * @param context
//     * @return
//     */
//    public static String getImei(Context context) {
//        String imei = null;
//        try {
//            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//            imei = telephonyManager.getDeviceId();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return imei;
//    }

    /**
     * 将中文字符强制转化为UTF-8编码
     *
     * @param name
     * @return
     */
    public static String changeToUTF8(String name) {

        try {
            name = URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return name;
    }


    /**
     * 隐藏部分手机号码数字
     *
     * @param mobile
     * @return
     */
    public static String hidePhoneNumber(String mobile) {
        if (mobile.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mobile.length(); i++) {
                char c = mobile.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else {
            return mobile;
        }
    }


    /**
     * 掉此方法输入所要转换的时间输入例如（"2016-11-18 14:35:54"）返回时间戳
     *
     * @param time
     * @return
     */
    public static long getTimeInMillis(String time) {
        long times = 0;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time));
            times = calendar.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }

    public static long getTimeInMillis2(String time) {
        long times = 0;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(time));
            times = calendar.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }

    /**
     * 返回当前时间戳
     *
     * @return
     */
    public static long getCurrentTime() {
        return Calendar.getInstance().getTimeInMillis();
    }


    /**
     * sp转px
     *
     * @param spVal
     * @return
     */
    public static int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, Utils.getContext().getResources().getDisplayMetrics());
    }


    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @param context
     * @return true 表示开启
     */
    public static final boolean isOPen(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }

        return false;
    }


    /**
     * 对字符串进行md5加密
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
