/**
  * Copyright 2020 json.cn 
  */
package com.bootx.util.juHuiTV;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Auto-generated: 2020-10-24 22:11:45
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpfsConfig {

    private String swarmkey;
    private List<String> bootstrap;
    public void setSwarmkey(String swarmkey) {
         this.swarmkey = swarmkey;
     }
     public String getSwarmkey() {
         return swarmkey;
     }

    public void setBootstrap(List<String> bootstrap) {
         this.bootstrap = bootstrap;
     }
     public List<String> getBootstrap() {
         return bootstrap;
     }

}