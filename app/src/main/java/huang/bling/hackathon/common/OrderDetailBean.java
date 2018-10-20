package huang.bling.hackathon.common;

import java.io.Serializable;

/**
 * Created by zjl on 2017/8/18.
 * Description:订单详细内容
 */

public class OrderDetailBean implements Serializable {

    /**
     * sn : I53752A6DF
     * clientSn : 13757423695:30
     * location : 121.511794,29.907326
     * poi : 速尔快递北仑站
     * destination : 121.509289,29.863821
     * destination_poi : 速尔快递北仑站鄞州站
     * timeCreated : 2015-06-09 10:32:48
     * timeAssigned : 2015-06-09 10:32:48
     * timePicked : 2015-06-09 10:33:48
     * timeArrived : 2015-06-09 10:34:08
     * vehicleNumber : 浙BT7642
     * driverName : 范伟乐
     * driverPhone : 15325744882
     * status : 9
     * statusDesc : 业务成功
     * money : 45.0
     * refDistance : 0
     * feeDistance : 22000
     * timeReserved :
     */

    private String sn;
    private String clientSn;
    private String location;
    private String poi;
    private String destination;
    private String destination_poi;
    private String timeCreated;
    private String timeAssigned;
    private String timePicked;
    private String timeArrived;
    private String vehicleNumber;
    private String driverName;
    private String driverPhone;
    private int status;
    private String statusDesc;
    private double money;
    private int refDistance;
    private int feeDistance;
    private String timeReserved;
    private String missionType;

    public String getMissionType() {
        return missionType;
    }

    public void setMissionType(String missionType) {
        this.missionType = missionType;
    }


    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getClientSn() {
        return clientSn;
    }

    public void setClientSn(String clientSn) {
        this.clientSn = clientSn;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPoi() {
        return poi;
    }

    public void setPoi(String poi) {
        this.poi = poi;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination_poi() {
        return destination_poi;
    }

    public void setDestination_poi(String destination_poi) {
        this.destination_poi = destination_poi;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getTimeAssigned() {
        return timeAssigned;
    }

    public void setTimeAssigned(String timeAssigned) {
        this.timeAssigned = timeAssigned;
    }

    public String getTimePicked() {
        return timePicked;
    }

    public void setTimePicked(String timePicked) {
        this.timePicked = timePicked;
    }

    public String getTimeArrived() {
        return timeArrived;
    }

    public void setTimeArrived(String timeArrived) {
        this.timeArrived = timeArrived;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getRefDistance() {
        return refDistance;
    }

    public void setRefDistance(int refDistance) {
        this.refDistance = refDistance;
    }

    public int getFeeDistance() {
        return feeDistance;
    }

    public void setFeeDistance(int feeDistance) {
        this.feeDistance = feeDistance;
    }

    public String getTimeReserved() {
        return timeReserved;
    }

    public void setTimeReserved(String timeReserved) {
        this.timeReserved = timeReserved;
    }
}
