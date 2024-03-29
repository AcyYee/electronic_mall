package com.sunwuo.electronic_mall.wxpay;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author onepieces
 */
public class WXpayUtil {

    private static final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private static String nonce_str = "";
    private static String sign = "";
    private static final String notify_url = "https://www.tongzhuhe.com/elMall/notify";
    private static final String trade_type = "JSAPI";

    public static Map<String,NotifyImple> notifyimple=new HashMap<>();

    public static Object payrequest(String body, String out_trade_no, String total_fee, 
    		String openid, String addr,String notifyName,String appid
            ,String mch_id,String key,Integer orderId,NotifyImple notify) {
    	
        nonce_str = getUUID();
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
        params.put("attach", notifyName);
        params = PayUtil.paraFilter(params);
        String rs1 = PayUtil.createLinkString(params);
        String rs2 = "&key=" + key;
        sign = PayUtil.sign(rs1, rs2, "utf-8").toUpperCase();
        params.put("sign", sign);
        PaymentEntity pay = new PaymentEntity(params);
        String respXml = MessageUtil.messageToXML(pay);
        respXml = respXml.replace("__", "_");
        String result = PayUtil.httpRequest(url, "POST", respXml);
        Map<String, String> map = new HashMap<String, String>(10);
        map.put("orderId",String.valueOf(orderId));
        map.put("orderTag",out_trade_no);
        InputStream in = new ByteArrayInputStream(result.getBytes());
        // 读取输入
        SAXReader reader = new SAXReader();
        reader.setEncoding("GB18030");
        org.dom4j.Document document = null;
        try {
            document = reader.read(in);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // 得到xml根元素
        if (document == null){
            return map;
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
        if ("SUCCESS".equals(return_code)) {
        	if(!notifyimple.containsKey(notifyName))
        	{
        		notifyimple.put(notifyName, notify);
        	}
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
    
    
    public interface NotifyImple {
    	/**
    	 * 支付回调逻辑的实现
    	 * @param map 支付参数集合
    	 * @return 是否成功
    	 */
	     boolean notifcation(Map<String, String> map);
	}
    
    /**
	 * 回调方法封装
	 */
	public static void notify(HttpServletRequest req,HttpServletResponse rep) throws UnsupportedEncodingException, IOException{
		NotifyImple notify=null;
		  // 解析结果存储在HashMap
        Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = req.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document=null;
		try {
			document = reader.read(inputStream);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList){
            map.put(e.getName(), e.getText());
        }
        // 释放资源
        inputStream.close();
        inputStream = null;
        // 返回状态码
        String return_code = map.get("return_code");
        // 返回信息
        String return_msg = map.get("return_msg");
        // 业务结果,判断交易是否成功
        String result_code = map.get("result_code");
        //out_trade_no用户订单�?
      /*  String out_trade_no=map.get("out_trade_no");
        String openid=map.get("openid"); 
        String total_fee=map.get("total_fee");*/
        String attach=map.get("attach");
        if((notify=WXpayUtil.notifyimple.get(attach))!=null)
        {
	        if(return_code=="SUCCESS"||return_code.equals("SUCCESS"))
	        {
	        	//处理业务逻辑
	            if(result_code.equals("SUCCESS"))
	            {	
	            	if(notify.notifcation(map))
	            	{
	            		PrintWriter out=rep.getWriter();
	        			out.print(setXML("SUCCESS", "SUCCESS"));
	        			out.flush();
	        			out.close();
	            	}
	            }
	        }
        }else
        {
        	System.out.println("回调失败  未找到实现方法   订单号："+map.get("out_trade_no"));
        }
	}
	
	public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code>"
        		+ "<return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
    }
	
	
	
	

}
