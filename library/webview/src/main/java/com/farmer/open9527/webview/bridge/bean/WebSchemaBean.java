package com.farmer.open9527.webview.bridge.bean;


public class WebSchemaBean extends WebBaseBean {
    //valid: true,    //支持:true，不支持:false
    private boolean valid;
    private String schema;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
