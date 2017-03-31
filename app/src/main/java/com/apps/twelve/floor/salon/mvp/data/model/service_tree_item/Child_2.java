
package com.apps.twelve.floor.salon.mvp.data.model.service_tree_item;

import com.choiintack.recursiverecyclerview.RecursiveItem;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Child_2 implements RecursiveItem {

    @SerializedName("service_id")
    @Expose
    private Integer serviceId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("parent_id")
    @Expose
    private Integer parentId;
    @SerializedName("is_service")
    @Expose
    private Boolean isService;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("img_href")
    @Expose
    private String imgHref;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("children")
    @Expose
    private List<Child_3> children = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Child_2() {
    }

    /**
     * 
     * @param parentId
     * @param time
     * @param title
     * @param price
     * @param isService
     * @param serviceId
     * @param description
     * @param imgHref
     * @param children
     */
    public Child_2(Integer serviceId, String title, Integer parentId, Boolean isService, Object price, String imgHref, String description, Integer time, List<Child_3> children) {
        super();
        this.serviceId = serviceId;
        this.title = title;
        this.parentId = parentId;
        this.isService = isService;
        this.price = price;
        this.imgHref = imgHref;
        this.description = description;
        this.time = time;
        this.children = children;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Boolean getIsService() {
        return isService;
    }

    public void setIsService(Boolean isService) {
        this.isService = isService;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public String getImgHref() {
        return imgHref;
    }

    public void setImgHref(String imgHref) {
        this.imgHref = imgHref;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public List<Child_3> getChildren() {
        return children;
    }

    public void setChildren(List<Child_3> children) {
        this.children = children;
    }

}
