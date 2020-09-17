package com.bootx.miniprogram;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.bootx.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {
        Map<String,Object> params = new HashMap<>();
        params.put("appid","wx17ad5f31816d0202");
        params.put("secret","8ef9842fb84c36d5107252b35442b5cf");
        params.put("code","IEC3OARSJZAB4SG3TU");

        Digester digester = DigestUtil.digester("sm3");
        String digestHex = digester.digestHex(JsonUtils.toJson(params));
        System.out.println(digestHex);






        String content = "test中文";

// 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();

// 构建
        AES aes = SecureUtil.aes(key);

// 加密
        byte[] encrypt = aes.encrypt(content);
// 解密
        byte[] decrypt = aes.decrypt(encrypt);

// 加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        System.out.println(encryptHex);
// 解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_GBK);
        System.out.println(decryptStr);







    }

}
