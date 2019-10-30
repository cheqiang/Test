package com.demo.util;

import com.demo.config.RSAConstant;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RSAHeadersUtil {

    public static Map getRSAHeaders() throws Exception {
        String sid = getSid(5);
        //私钥加密
        String signature = RSASignature.sign(sid,RSAConstant.PRIVATE_KEY,"UTF-8");
        Map<String,String> headers = new HashMap();
        headers.put("X-SID",sid);
        headers.put("X-Signature",signature);
        System.out.println(sid);
        System.out.println(signature);
        return headers;
    }

    private static String getSid(int length){
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append(String.valueOf((char) result));
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append(String.valueOf((char) result));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String sid = RSAHeadersUtil.getSid(5);

        String sign = RSASignature.sign(sid, RSAConstant.PRIVATE_KEY, "UTF-8");
        System.out.println(sign);

        boolean b = RSASignature.doCheck(sid, sign, RSAConstant.PUBLIC_KEY, "UTF-8");
        System.out.println(b);


    }

}
