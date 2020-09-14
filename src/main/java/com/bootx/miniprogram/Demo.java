package com.bootx.miniprogram;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import com.bootx.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {
        Map<String,Object> params = new HashMap<>();
        params.put("appid","wxbc9f9c568f6d5e30");
        params.put("secret","77d6b9554a19f3b9d35203206f3f961b");
        params.put("code","IEC3OARSJZAB4SG3TU");

        Digester digester = DigestUtil.digester("sm3");
        String digestHex = digester.digestHex(JsonUtils.toJson(params));
        System.out.println(digestHex);
    }

}
