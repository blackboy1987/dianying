package com.bootx;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

public class Demo {

    public static void main(String[] args) {
        String content = "IEC3OARSJZAB4SG3TU";
        SymmetricCrypto sm4 = new SymmetricCrypto("SM4");

        String encryptHex = sm4.encryptHex(content);
        System.out.println(encryptHex);
        String decryptStr = sm4.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);//test中文
        System.out.println(decryptStr);
    }
}