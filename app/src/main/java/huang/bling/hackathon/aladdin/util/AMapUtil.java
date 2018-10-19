package huang.bling.hackathon.aladdin.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.sunlandgroup.aladdin.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by 沈东 on 2016/5/13.
 */
public class AMapUtil {
    private static double PI = Math.PI;
    private static double EE = 0.00669342162296594323;
    private static double A = 6378245.0;

    private static boolean isOutOfChina(double lat, double lng) {
        if (lng < 72.004 || lng > 137.8347) {
            return true;
        }
        if (lat < 0.8293 || lat > 55.8271) {
            return true;
        }
        return false;
    }

    private static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * PI) + 40.0 * Math.sin(y / 3.0 * PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * PI) + 320 * Math.sin(y * PI / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static double transformLng(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * PI) + 40.0 * Math.sin(x / 3.0 * PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * PI) + 300.0 * Math.sin(x / 30.0 * PI)) * 2.0 / 3.0;
        return ret;
    }

    /**
     * GPS坐标转高德地图坐标
     *
     * @param wgLoc
     * @return
     */
    public static LatLng transformFromWGSToGCJ(LatLng wgLoc) {

        //如果在国外，则默认不进行转换
        if (isOutOfChina(wgLoc.latitude, wgLoc.longitude)) {
            return new LatLng(wgLoc.latitude, wgLoc.longitude);
        }
        double dLat = transformLat(wgLoc.longitude - 105.0,
                wgLoc.latitude - 35.0);
        double dLon = transformLng(wgLoc.longitude - 105.0,
                wgLoc.latitude - 35.0);
        double radLat = wgLoc.latitude / 180.0 * Math.PI;
        double magic = Math.sin(radLat);
        magic = 1 - EE * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((A * (1 - EE)) / (magic * sqrtMagic) * Math.PI);
        dLon = (dLon * 180.0) / (A / sqrtMagic * Math.cos(radLat) * Math.PI);
        return new LatLng(wgLoc.latitude + dLat, wgLoc.longitude + dLon);
    }

    /**
     * 高德地图坐标转为GPS坐标
     *
     * @param wgLoc
     * @return
     */
    public static String transformFromGCJToWGS(LatLng wgLoc) {
        if (wgLoc.longitude == 0 || wgLoc.latitude == 0) {
            return null;
        }
        LatLng latLng = transformFromWGSToGCJ(wgLoc);
        double longitude = wgLoc.longitude * 2 - latLng.longitude;
        double latitude = wgLoc.latitude * 2 - latLng.latitude;
        return longitude + "," + latitude;
    }


    /**
     * 友好展示距离
     *
     * @param lenMeter
     * @return
     */
    public static String getFriendlyLength(int lenMeter) {
        if (lenMeter > 10000) // 10 km
        {
            float dis = (float) lenMeter / 1000;
            DecimalFormat fnum = new DecimalFormat("##0.0");
            String dstr = fnum.format(dis);
            return dstr + ChString.Kilometer;
        }

        if (lenMeter > 1000) {
            float dis = (float) lenMeter / 1000;
            DecimalFormat fnum = new DecimalFormat("##0.0");
            String dstr = fnum.format(dis);
            return dstr + ChString.Kilometer;
        }

//        if (lenMeter > 100) {
//            int dis = lenMeter / 50 * 50;
//            return dis + ChString.Meter;
//        }

        int dis = lenMeter / 10 * 10;
        if (dis == 0) {
            dis = 10;
        }

        return dis + ChString.Meter;
    }

    /**
     * 预估距离
     *
     * @param lenMeter
     * @return List<String>
     */
    public static List<String> getEstimateLengthList(int lenMeter) {
        List<String> list = new ArrayList<>();
        if (lenMeter > 10000) // 10 km
        {
            float dis = (float) lenMeter / 1000;
            DecimalFormat fnum = new DecimalFormat("##0.0");
            String dstr = fnum.format(dis);
            list.add(dstr);
            list.add(ChString.Kilometer);
            return list;
        }

        if (lenMeter > 1000) {
            float dis = (float) lenMeter / 1000;
            DecimalFormat fnum = new DecimalFormat("##0.0");
            String dstr = fnum.format(dis);
            list.add(dstr);
            list.add(ChString.Kilometer);
            return list;
        }

        if (lenMeter > 100) {
            int dis = lenMeter / 50 * 50;
            list.add(dis + "");
            list.add(ChString.Meter);
            return list;
        }

        int dis = lenMeter / 10 * 10;
      /*  if (dis == 0) {
            dis = 10;
        }*/

        list.add(dis + "");
        list.add(ChString.Meter);
        return list;
    }

    /**
     * 友好展示时间
     *
     * @param second
     * @return
     */
    public static List<String> getEstimateTimeList(int second) {
        List<String> list = new ArrayList<>();
        if (second > 3600) {
            int hour = second / 3600;
            int minute = (second % 3600) / 60;
            list.add(hour + "");
            list.add("小时");
            list.add(minute + "");
            list.add("分钟");
            return list;
        }
        if (second >= 60) {
            int minute = second / 60;
            list.add("");
            list.add("");
            list.add(minute + "");
            list.add("分钟");
            return list;
        }

        list.add("");
        list.add("");
        list.add(second + "");
        list.add("秒");
        return list;
    }


    /**
     * 友好展示时间
     *
     * @param second
     * @return
     */
    public static String getFriendlyTime(int second) {
        if (second > 3600) {
            int hour = second / 3600;
            int miniate = (second % 3600) / 60;
            return hour + "小时" + miniate + "分钟";
        }
        if (second >= 60) {
            int miniate = second / 60;
            return miniate + "分钟";
        }
        return second + "秒";
    }


    /* 起步价11元3公里，超3公里后车公里价格2.4元；
     空驶费：单程载客在20公里以上，空驶费为车公里价格60%,
             10-20公里，为车公里价格40%；
     夜间行车补贴：22时至次日5时，补贴标准为0.6元/公里。
   举例：21.5公里  ：
   未算夜间行车补贴 11 + ( 21.5 - 3 ) * 2.4 + 10 * 2.4 * 0.4 +(21.5 - 20) * 2.4 * 0.6
   算夜间行车补贴 11 + ( 21.5 - 3 ) * 2.4 +(10 - 3) * 2.4 * 0.6 +(21.5 - 20) * 2.4 * 0.4 + 21.5 * 0.6

     */
    public static double getEstimateMoney(int dis) {
        double money = 11;
        float route = (float) dis / 1000;
        if (route > 3) {
            money = money + (route - 3) * 2.4;
        }
        if ((route) > 20) {
            money = money + ((route - 20) * 0.6 + 10 * 0.4) * 2.4;
        } else if ((route) > 10) {
            money = money + (route - 10) * 0.4 * 2.4;
        }
        Calendar now = Calendar.getInstance();
        int time = now.get(Calendar.HOUR_OF_DAY);
        if ((time >= 0 && time < 5) || (time >= 22 && time < 24)) {
            money = money + route * 0.6;
        }
        return money;
    }


    public static SpannableStringBuilder getEstimateInfo(int dis, int dur, Context context) {
        SpanUtils builder = new SpanUtils();
        builder.append("预估").setFontSize(UIHelper.sp2px(15)).setForegroundColor(ContextCompat.getColor(context, R.color.secondary_text))
                .append(AMapUtil.getEstimateLengthList(dis).get(0)).setFontSize(UIHelper.sp2px(22)).setForegroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .append(AMapUtil.getEstimateLengthList(dis).get(1) + ",").setFontSize(UIHelper.sp2px(15)).setForegroundColor(ContextCompat.getColor(context, R.color.secondary_text))
                .append(AMapUtil.getEstimateTimeList(dur).get(0)).setFontSize(UIHelper.sp2px(22)).setForegroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .append(AMapUtil.getEstimateTimeList(dur).get(1)).setFontSize(UIHelper.sp2px(15)).setForegroundColor(ContextCompat.getColor(context, R.color.secondary_text))
                .append(AMapUtil.getEstimateTimeList(dur).get(2)).setFontSize(UIHelper.sp2px(22)).setForegroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .append(AMapUtil.getEstimateTimeList(dur).get(3) + "到达 | ").setFontSize(UIHelper.sp2px(15)).setForegroundColor(ContextCompat.getColor(context, R.color.secondary_text))
                .append((int) AMapUtil.getEstimateMoney(dis) + "").setFontSize(UIHelper.sp2px(22)).setForegroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .append(" 元").setFontSize(UIHelper.sp2px(15)).setForegroundColor(ContextCompat.getColor(context, R.color.secondary_text));
        return builder.create();
    }


    /**
     * 把LatLonPoint对象转化为LatLon对象
     */
    public static LatLng convertToLatLng(LatLonPoint latLonPoint) {
        return new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
    }

    public static String getSimpleBusLineName(String busLineName) {
        if (busLineName == null) {
            return String.valueOf("");
        }
        return busLineName.replaceAll("\\(.*?\\)", "");
    }

    /**
     * 把LatLng对象转化为LatLonPoint对象
     */
    public static LatLonPoint convertToLatLonPoint(LatLng latLng) {
        return new LatLonPoint(latLng.latitude, latLng.longitude);
    }

    /**
     * 把集合体的LatLonPoint转化为集合体的LatLng
     */
    public static ArrayList<LatLng> convertArrList(List<LatLonPoint> shapes) {
        ArrayList<LatLng> lineShapes = new ArrayList<LatLng>();
        for (LatLonPoint point : shapes) {
            LatLng latLngTemp = AMapUtil.convertToLatLng(point);
            lineShapes.add(latLngTemp);
        }
        return lineShapes;
    }

}
