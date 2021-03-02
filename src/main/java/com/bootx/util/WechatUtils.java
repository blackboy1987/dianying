package com.bootx.util;

import com.bootx.entity.App;
import com.bootx.util.wechat.AccessToken;
import com.bootx.util.wechat.BaseResponse;
import com.bootx.util.wechat.QRCodeParam;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public final class WechatUtils {

    /**
     * 不可实例化
     */
    private WechatUtils() {
    }

    public static String getAccessToken(App app) {
        Assert.notNull(app, "[Assertion failed] - app is required; it must not be null");

        AccessToken accessToken = EhCacheUtils.getAccessToken(app.getAppId());
        if (accessToken != null) {
            return accessToken.getAccessToken();
        }
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        url = url.replace("APPID", app.getAppId()).replace("APPSECRET", app.getAppSecret());

        String result = WebUtils.get(url, null);
        System.out.println("===========================================================================");
        accessToken = JsonUtils.toObject(result, AccessToken.class);
        if (accessToken.getErrCode() == null) {
            accessToken.setCreateDate(System.currentTimeMillis());
            EhCacheUtils.setAccessToken(app.getAppId(), accessToken);
        }
        return accessToken.getAccessToken();
    }

    public static String refreshAccessToken(App app) {
        Assert.notNull(app, "[Assertion failed] - app is required; it must not be null");

        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        url = url.replace("APPID", app.getAppId()).replace("APPSECRET", app.getAppSecret());

        String result = WebUtils.get(url, null);
        AccessToken accessToken = JsonUtils.toObject(result, AccessToken.class);
        if (accessToken.getErrCode() == null) {
            accessToken.setCreateDate(System.currentTimeMillis());
            EhCacheUtils.setAccessToken(app.getAppId(), accessToken);
        }
        return accessToken.getAccessToken();
    }

    public static String createQRCode(App app, QRCodeParam qrCodeParam) throws IOException {
        Assert.notNull(app, "[Assertion failed] - app is required; it must not be null");
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN";
        AccessToken accessToken1 = EhCacheUtils.getAccessToken(app.getAppId());
        if (accessToken1 == null) {
            url = url.replace("ACCESS_TOKEN", refreshAccessToken(app));
        } else {
            url = url.replace("ACCESS_TOKEN", accessToken1.getAccessToken());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("scene", qrCodeParam.getScene());
        if (StringUtils.isNotBlank(qrCodeParam.getPage())) {
            map.put("page", qrCodeParam.getPage());
        }
        if (qrCodeParam.getWidth() != null && qrCodeParam.getWidth() > 0) {
            map.put("width", qrCodeParam.getWidth());
        }
        if (qrCodeParam.getAutoColor() != null) {
            map.put("auto_color", qrCodeParam.getAutoColor());
        }
        if (qrCodeParam.getLineColor() != null) {
            map.put("line_color", qrCodeParam.getLineColor());
        }
        if (qrCodeParam.getIsHyaline() != null) {
            map.put("is_hyaline", qrCodeParam.getIsHyaline());
        }
        InputStream inputStream = doWXPost(url, map);

        FileUtils.copyToFile(inputStream, new File("d:/1.png"));
        return "ok";
    }

    /**
     * 订阅消息的发送
     */
    public static BaseResponse subscribeSend(App app, String templateId, String openId, Map<String,Object> params){
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN",getAccessToken(app));
        params.put("touser",openId);
        params.put("template_id",templateId);
        System.out.println(params);
        String result = doWXPost1(url,params);
        return JsonUtils.toObject(result, BaseResponse.class);
    }


    /**
     *
     * @param url
     * @param map
     * @return
     */
    public static String doWXPost1(String url,  Map<String, Object> map) {
        String result = null;
        HttpPost httpRequst = new HttpPost(url);// 创建HttpPost对象
        try {
            StringEntity se = new StringEntity(JsonUtils.toJson(map), "utf-8");
            se.setContentType("application/json");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "UTF-8"));
            httpRequst.setEntity(se);
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    result = EntityUtils.toString(httpEntity);
                    EntityUtils.consume(httpEntity);
                }
            }
        } catch (UnsupportedEncodingException | ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     *
     * @param url
     * @param map
     * @return
     */
    public static InputStream doWXPost(String url,  Map<String, Object> map) {
        InputStream instreams = null;
        HttpPost httpRequst = new HttpPost(url);// 创建HttpPost对象
        try {
            StringEntity se = new StringEntity(JsonUtils.toJson(map), "utf-8");
            se.setContentType("application/json");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "UTF-8"));
            httpRequst.setEntity(se);
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    instreams = httpEntity.getContent();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instreams;
    }
}
