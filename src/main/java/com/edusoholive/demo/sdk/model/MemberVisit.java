package com.edusoholive.demo.sdk.model;

public class MemberVisit {

    private Long roomId;

    private Long userId;

    private String userName;

    private String role;

    private Long enterAt;

    private Long leaveAt;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getEnterAt() {
        return enterAt;
    }

    public void setEnterAt(Long enterAt) {
        this.enterAt = enterAt;
    }

    public Long getLeaveAt() {
        return leaveAt;
    }

    public void setLeaveAt(Long leaveAt) {
        this.leaveAt = leaveAt;
    }
}
