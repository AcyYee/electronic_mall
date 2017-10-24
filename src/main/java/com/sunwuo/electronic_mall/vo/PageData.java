package com.sunwuo.electronic_mall.vo;

public class PageData {

    //分页信息
    private PageModel pageModel;

    //当前数据
    private Object modelData;

    public PageModel getPageModel() {
        return pageModel;
    }

    public void setPageModel(PageModel pageModel) {
        this.pageModel = pageModel;
    }

    public Object getModelData() {
        return modelData;
    }

    public void setModelData(Object modelData) {
        this.modelData = modelData;
    }

    public PageData() {}

    public PageData(PageModel pageModel, Object modelData) {
        this.pageModel = pageModel;
        this.modelData = modelData;
    }

}
