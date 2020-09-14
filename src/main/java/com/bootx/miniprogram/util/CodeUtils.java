package com.bootx.miniprogram.util;

import java.util.Random;

public final class CodeUtils {

    public static final String[] CODE = new String[]{
            "1","2","3","4","5","6","7","8","9","0",
            "A","B","C","D","E","F","G","H","I","J",
            "K","L","M","N","O","P","Q","R","S","T",
            "U","V","W","X","Y","Z"
    };

    public static final Random RANDOM = new Random();


    private CodeUtils(){}


    public static String getCode(Integer length){
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<length;i++){
            sb.append(CODE[RANDOM.nextInt(CODE.length)]);
        }
        return sb.toString();
    }

}
