/**
  * Copyright 2020 json.cn 
  */
package com.bootx.util.juHuiTV;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Auto-generated: 2020-10-24 22:11:45
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonRootBean {

    private Props props;
    private String page;
    private Query query;
    private String buildId;
    private RuntimeConfig runtimeConfig;
    private boolean isFallback;
    private boolean customServer;
    private boolean gip;
    private boolean appGip;
    public void setProps(Props props) {
         this.props = props;
     }
     public Props getProps() {
         return props;
     }

    public void setPage(String page) {
         this.page = page;
     }
     public String getPage() {
         return page;
     }

    public void setQuery(Query query) {
         this.query = query;
     }
     public Query getQuery() {
         return query;
     }

    public void setBuildId(String buildId) {
         this.buildId = buildId;
     }
     public String getBuildId() {
         return buildId;
     }

    public void setRuntimeConfig(RuntimeConfig runtimeConfig) {
         this.runtimeConfig = runtimeConfig;
     }
     public RuntimeConfig getRuntimeConfig() {
         return runtimeConfig;
     }

    public void setIsFallback(boolean isFallback) {
         this.isFallback = isFallback;
     }
     public boolean getIsFallback() {
         return isFallback;
     }

    public void setCustomServer(boolean customServer) {
         this.customServer = customServer;
     }
     public boolean getCustomServer() {
         return customServer;
     }

    public void setGip(boolean gip) {
         this.gip = gip;
     }
     public boolean getGip() {
         return gip;
     }

    public void setAppGip(boolean appGip) {
         this.appGip = appGip;
     }
     public boolean getAppGip() {
         return appGip;
     }

}