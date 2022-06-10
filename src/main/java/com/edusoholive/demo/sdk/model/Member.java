package com.edusoholive.demo.sdk.model;

import lombok.Data;

public class Member {

    private Long roomId;

    private Long userId;

    private String userName;

    private String role;

    private Long firstEnterAt;

    private Long lastEnterAt;

    private Long lastLeaveAt;

    private Long checkinNum;

    private Long answerNum;

    private Long chatNum;

    private Long onlineDuration;

    private Long watchDuration;

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

    public Long getFirstEnterAt() {
        return firstEnterAt;
    }

    public void setFirstEnterAt(Long firstEnterAt) {
        this.firstEnterAt = firstEnterAt;
    }

    public Long getLastEnterAt() {
        return lastEnterAt;
    }

    public void setLastEnterAt(Long lastEnterAt) {
        this.lastEnterAt = lastEnterAt;
    }

    public Long getLastLeaveAt() {
        return lastLeaveAt;
    }

    public void setLastLeaveAt(Long lastLeaveAt) {
        this.lastLeaveAt = lastLeaveAt;
    }

    public Long getCheckinNum() {
        return checkinNum;
    }

    public void setCheckinNum(Long checkinNum) {
        this.checkinNum = checkinNum;
    }

    public Long getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Long answerNum) {
        this.answerNum = answerNum;
    }

    public Long getChatNum() {
        return chatNum;
    }

    public void setChatNum(Long chatNum) {
        this.chatNum = chatNum;
    }

    public Long getOnlineDuration() {
        return onlineDuration;
    }

    public void setOnlineDuration(Long onlineDuration) {
        this.onlineDuration = onlineDuration;
    }

    public Long getWatchDuration() {
        return watchDuration;
    }

    public void setWatchDuration(Long watchDuration) {
        this.watchDuration = watchDuration;
    }
}
