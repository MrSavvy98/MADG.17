package com.example.workfromhome;

public class AddLeaveHelper {

    String id, depart, leaveType, duration, frm, to, reason;

    public AddLeaveHelper() {
    }

    public AddLeaveHelper(String id, String depart, String leaveType, String duration, String frm, String to, String reason) {
        this.id = id;
        this.depart = depart;
        this.leaveType = leaveType;
        this.duration = duration;
        this.frm = frm;
        this.to = to;
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFrm() {
        return frm;
    }

    public void setFrm(String frm) {
        this.frm = frm;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
