package com.wechat.recycle.common.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

/**
 * 封装对外访问方法
 * @author liuyazhuang
 *
 */
public class WXCore {

    private static final String WATERMARK = "watermark";
    private static final String APPID = "appid";
    /**
     * 解密数据
     * @return
     * @throws Exception
     */
    public static String decrypt(String appId, String encryptedData, String sessionKey, String iv){
        String result = "";
        try {
            AES aes = new AES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                result = new String(WxPKCS7Encoder.decode(resultByte));
                JSONObject jsonObject = JSONObject.parseObject(result);
                String decryptAppid = jsonObject.getJSONObject(WATERMARK).getString(APPID);
                if(!appId.equals(decryptAppid)){
                    result = "";
                }
            }
        } catch (Exception e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) throws Exception{
        String appId = "";
        String encryptedData = "r++3s5dWh+tCb6bzB4E2tcBUaNe5lbQjZj5oaZq9dBZcvebZPjLqcRR8jStrS8OQye+0N+SjgFFIeOj2tQ+hO2cFEnZo9yzqaJiNlq0Fqs/ruAzCax5BaQ0MMuvV2Y/UehqR1YnUYc/kPt+1sgAyddPAA/dnzdvcb7WvsXNBQZwOyRCvHPLNMg//rj2keaGnHUNXKvt2gOiTMzqrs4Uw7aaGz+VWy3EjCUSL30/OPcTv7yMHJUqFCVaZn7N1Padli/pB7kgKdGVxGaShyhPqoNrtst/BrqhfIhpFP4dkTLVoF/nl++QIMlORKa2MeQs5d4Tpvt6pfvqLLUb1KBtOpqlZgb6SsbzYrY++jQ963ScRnksPShkRT1LW4X8cJPGuuFh5dZjSHgeWTcpAeT3VeUp41belQ6/AlZ5Hnp0wb8RpImsKuLF9GnVc0hf8Q1cmvUgqgibzvmgkv4UKdkUcaA=";
        String sessionKey = "";
        String iv = "";
        System.out.println(decrypt(appId, encryptedData, sessionKey, iv));
    }
}