package com.sunwuo.electronic_mall.wxpay;

import com.google.gson.Gson;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WXpayUtil {

    private final static String appid = "wxa75115dccbe8ecec";//wx750e248ada94beee,wxa75115dccbe8ecec
    private static final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private static final String mch_id = "1450755702";
    private static final String device_info = "WEB";
    private static String nonce_str = "";
    private static final String key = "medusa18058505737medusa180585057"; //medusa18058505737medusa180585057
    private static String sign = "";
    private static final String sign_type = "MD5";
    private static final String attach = "";
    private static final String fee_type = "CNY";
    private static String spbill_create_ip = "";
    private static String goods_tag = "WXG";
    private static final String notify_url = "https://www.medusachina.com/beauty/";
    private static final String trade_type = "JSAPI";
    private static String limit_pay = "no_credit";

    public static Object payrequest(String body, String out_trade_no, String total_fee, String openid, String notify, String addr, String attach) {
        nonce_str = getUUID();
        /*String stringSignTemp="appid="+appid+"&body="+body+"&device_info="+device_info+"&mch_id="+mch_id+"&nonce_str="+nonce_str+"&key="+secert;
		  sign=getMD5(stringSignTemp).toUpperCase();*/
        Map<String, String> params = new HashMap<String, String>();

        params.put("appid", appid);
        params.put("mch_id", mch_id);
        params.put("nonce_str", nonce_str);
        params.put("body", body);
        params.put("out_trade_no", out_trade_no);
        params.put("total_fee", total_fee);
        params.put("spbill_create_ip", addr);
        params.put("notify_url", notify_url+notify);
        params.put("trade_type", trade_type);
        params.put("openid", openid);
        params.put("attach", attach);
        params = PayUtil.paraFilter(params);
        String rs1 = PayUtil.createLinkString(params);
        String rs2 = "&key=" + key;
        sign = PayUtil.sign(rs1, rs2, "utf-8").toUpperCase();
        params.put("sign", sign);
        PaymentEntity pay = new PaymentEntity(params);
        String respXml = MessageUtil.messageToXML(pay);
        respXml = respXml.replace("__", "_");
        String result = PayUtil.httpRequest(url, "POST", respXml);
        Map<String, String> map = new HashMap<String, String>();
        InputStream in = new ByteArrayInputStream(result.getBytes());
        // 读取输入�??
        SAXReader reader = new SAXReader();
        reader.setEncoding("GB18030");
        org.dom4j.Document document = null;
        try {
            document = reader.read(in);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // 得到xml根元�??
        if (document == null){
            return "xml为空";
        }
        Element root = document.getRootElement();
        // 得到根元素的�??有子节点
        List<Element> elementList = root.elements();
        for (Element element : elementList) {
            map.put(element.getName(), element.getText());
        }
        // 返回信息
        String return_code = map.get("return_code");//返回状�?�码
        String return_msg = map.get("return_msg");//返回信息
        Gson gson = new Gson();
        if (return_code.equals("SUCCESS")) {
            // 业务结果
            String nonceStr = UUIDHexGenerator.generate();
            map.put("nonceStr", nonceStr);
            Long timeStamp = System.currentTimeMillis() / 1000;
            map.put("time", timeStamp + "");
            String stringSignTemp = "appId=" + appid + "&nonceStr=" + nonceStr + "&package=prepay_id=" + map.get("prepay_id") + "&signType=MD5&timeStamp=" + timeStamp;
            //再次签名
            String paySign = PayUtil.sign(stringSignTemp, "&key="+key, "utf-8").toUpperCase();
            map.put("paySign", paySign);
            map.put("total_fee", total_fee);
        }
        return map;
    }

    /**
     * 对字符串md5加密
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        try {
            // 生成�??个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()�??后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就�??8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash�??
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }


    /**
     * 生成32位编�??
     *
     * @return string
     */
    private static String getUUID() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

}
