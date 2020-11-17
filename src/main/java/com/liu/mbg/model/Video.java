package com.liu.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class Video implements Serializable {
    private Long id;

    /**
     * 作者id
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * 作者名字
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * 视频名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 视频地址
     *
     * @mbggenerated
     */
    private String path;

    /**
     * 视频封面地址,如果该值为null的话，那么就以视频的第一帧为封面
     *
     * @mbggenerated
     */
    private String picPath;

    /**
     * 视频的播放量
     *
     * @mbggenerated
     */
    private Long viewCounts;

    /**
     * 视频的标签
     *
     * @mbggenerated
     */
    private String tags;

    /**
     * 视频的创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 视频的修改时间，默认和创建时间一样
     *
     * @mbggenerated
     */
    private Date modifiedTime;

    /**
     * 视频的点赞数
     *
     * @mbggenerated
     */
    private Long stars;

    private String category;

    /**
     * 视频的描述信息
     *
     * @mbggenerated
     */
    private String description;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public Long getViewCounts() {
        return viewCounts;
    }

    public void setViewCounts(Long viewCounts) {
        this.viewCounts = viewCounts;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getStars() {
        return stars;
    }

    public void setStars(Long stars) {
        this.stars = stars;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", name=").append(name);
        sb.append(", path=").append(path);
        sb.append(", picPath=").append(picPath);
        sb.append(", viewCounts=").append(viewCounts);
        sb.append(", tags=").append(tags);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", stars=").append(stars);
        sb.append(", category=").append(category);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}