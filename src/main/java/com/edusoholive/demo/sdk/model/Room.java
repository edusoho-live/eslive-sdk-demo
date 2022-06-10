package com.edusoholive.demo.sdk.model;

public class Room {
    private Long id;

    private String name;

    private Long startAt;

    private Long endAt;

    private Long actualStartAt;

    private Long actualEndAt;

    private String status;

    private Boolean isDeleted;

    private Boolean isReplayGenerated;

    private Long userTotalNum;

    private Long userEnterTotalNum;

    private Long maxOnlineNum;

    private Long chatTotalNum;

    private Long userAvgOnlineDuration;

    private Long createdAt;

    private Long updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStartAt() {
        return startAt;
    }

    public void setStartAt(Long startAt) {
        this.startAt = startAt;
    }

    public Long getEndAt() {
        return endAt;
    }

    public void setEndAt(Long endAt) {
        this.endAt = endAt;
    }

    public Long getActualStartAt() {
        return actualStartAt;
    }

    public void setActualStartAt(Long actualStartAt) {
        this.actualStartAt = actualStartAt;
    }

    public Long getActualEndAt() {
        return actualEndAt;
    }

    public void setActualEndAt(Long actualEndAt) {
        this.actualEndAt = actualEndAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getReplayGenerated() {
        return isReplayGenerated;
    }

    public void setReplayGenerated(Boolean replayGenerated) {
        isReplayGenerated = replayGenerated;
    }

    public Long getUserTotalNum() {
        return userTotalNum;
    }

    public void setUserTotalNum(Long userTotalNum) {
        this.userTotalNum = userTotalNum;
    }

    public Long getUserEnterTotalNum() {
        return userEnterTotalNum;
    }

    public void setUserEnterTotalNum(Long userEnterTotalNum) {
        this.userEnterTotalNum = userEnterTotalNum;
    }

    public Long getMaxOnlineNum() {
        return maxOnlineNum;
    }

    public void setMaxOnlineNum(Long maxOnlineNum) {
        this.maxOnlineNum = maxOnlineNum;
    }

    public Long getChatTotalNum() {
        return chatTotalNum;
    }

    public void setChatTotalNum(Long chatTotalNum) {
        this.chatTotalNum = chatTotalNum;
    }

    public Long getUserAvgOnlineDuration() {
        return userAvgOnlineDuration;
    }

    public void setUserAvgOnlineDuration(Long userAvgOnlineDuration) {
        this.userAvgOnlineDuration = userAvgOnlineDuration;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
