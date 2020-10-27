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
public class RuntimeConfig {

    private String serverHostUrl;
    private String clientHostUrl;
    private IpfsConfig ipfsConfig;
    private String downloadURL;
    private String cdnHostURL;
    private String reportUrl;
    public void setServerHostUrl(String serverHostUrl) {
         this.serverHostUrl = serverHostUrl;
     }
     public String getServerHostUrl() {
         return serverHostUrl;
     }

    public void setClientHostUrl(String clientHostUrl) {
         this.clientHostUrl = clientHostUrl;
     }
     public String getClientHostUrl() {
         return clientHostUrl;
     }

    public void setIpfsConfig(IpfsConfig ipfsConfig) {
         this.ipfsConfig = ipfsConfig;
     }
     public IpfsConfig getIpfsConfig() {
         return ipfsConfig;
     }

    public void setDownloadURL(String downloadURL) {
         this.downloadURL = downloadURL;
     }
     public String getDownloadURL() {
         return downloadURL;
     }

    public void setCdnHostURL(String cdnHostURL) {
         this.cdnHostURL = cdnHostURL;
     }
     public String getCdnHostURL() {
         return cdnHostURL;
     }

    public void setReportUrl(String reportUrl) {
         this.reportUrl = reportUrl;
     }
     public String getReportUrl() {
         return reportUrl;
     }

}