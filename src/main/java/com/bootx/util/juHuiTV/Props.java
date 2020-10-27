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
public class Props {

    private PageProps pageProps;
    private InitialReduxState initialReduxState;
    public void setPageProps(PageProps pageProps) {
         this.pageProps = pageProps;
     }
     public PageProps getPageProps() {
         return pageProps;
     }

    public void setInitialReduxState(InitialReduxState initialReduxState) {
         this.initialReduxState = initialReduxState;
     }
     public InitialReduxState getInitialReduxState() {
         return initialReduxState;
     }

}