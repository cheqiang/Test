package com.file.util;

import com.file.config.RSAConstant;
import org.junit.Test;

public class RSASignatureTest {

    @Test
    public void testSign(){
        String sid = "abcdefg";

        String sign = RSASignature.sign(sid, RSAConstant.PRIVATE_KEY, "UTF-8");

        boolean b = RSASignature.doCheck(sid, sign, RSAConstant.PUBLIC_KEY, "UTF-8");
        System.out.println("验证签名成功！");
    }

}
