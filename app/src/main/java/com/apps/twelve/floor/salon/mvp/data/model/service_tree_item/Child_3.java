
package com.apps.twelve.floor.salon.mvp.data.model.service_tree_item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Child_3 {

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
    private String price;
    @SerializedName("children")
    @Expose
    private Boolean children;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Child_3() {
    }

    /**
     * 
     * @param parentId
     * @param title
     * @param price
     * @param isService
     * @param serviceId
     * @param children
     */
    public Child_3(Integer serviceId, String title, Integer parentId, Boolean isService, String price, Boolean children) {
        super();
        this.serviceId = serviceId;
        this.title = title;
        this.parentId = parentId;
        this.isService = isService;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getChildren() {
        return children;
    }

    public void setChildren(Boolean children) {
        this.children = children;
    }

}
