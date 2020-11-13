package com.liu.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Long id;

    /**
     * 用户名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 用户头像
     *
     * @mbggenerated
     */
    private String icon;

    /**
     * 上传的视频数量
     *
     * @mbggenerated
     */
    private Integer videoCount;

    /**
     * 视频评论数量
     *
     * @mbggenerated
     */
    private Integer videoCommentCount;

    /**
     * 注册时间
     *
     * @mbggenerated
     */
    private Date createTime;

    private String userName;

    private String userPassword;

    private static final long serialVersionUID = 1L;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }

    public Integer getVideoCommentCount() {
        return videoCommentCount;
    }

    public void setVideoCommentCount(Integer videoCommentCount) {
        this.videoCommentCount = videoCommentCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", icon=").append(icon);
        sb.append(", videoCount=").append(videoCount);
        sb.append(", videoCommentCount=").append(videoCommentCount);
        sb.append(", createTime=").append(createTime);
        sb.append(", userName=").append(userName);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}