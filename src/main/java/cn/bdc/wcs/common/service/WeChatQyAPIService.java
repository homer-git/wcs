package cn.bdc.wcs.common.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;  

@Service
public class WeChatQyAPIService extends BaseService {
	
	@Value("${wcs.wxqy.corpid}")
	private String corpID;
	
	@Value("${wcs.wxqy.secret}")
	private String secret;

	@Value("${wcs.wxqy.agentid}")
    private String agentId;
  
    // 获取访问权限码URL  
    private final static String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
    // 创建会话请求URL  
    private final static String CREATE_SESSION_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";  
  
    // 获取接口访问权限码  
    public String getAccessToken() {
    	String accessTokenUrlAndParm = ACCESS_TOKEN_URL + "?corpid=" + corpID + "&corpsecret=" + secret;
    	HttpClient client = new HttpClient();
    	PostMethod post = new PostMethod(accessTokenUrlAndParm);
        post.releaseConnection();  
        post.setRequestHeader("Content-Type",  
                "application/x-www-form-urlencoded;charset=UTF-8"); 
        // 设置策略，防止报cookie错误  
        DefaultHttpParams.getDefaultParams().setParameter(  
                "http.protocol.cookie-policy",  
                CookiePolicy.BROWSER_COMPATIBILITY);  
    	String result = "";
    	try {
    	client.executeMethod(post);
    	result = new String(post.getResponseBodyAsString().getBytes("gbk"));
    	} catch (IOException e) {
    	e.printStackTrace();
    	}
    	// 将数据转换成 json
    	JSONObject jasonObject;
    	jasonObject = JSONObject.fromObject(result);
    	result = (String) jasonObject.get("access_token");
    	post.releaseConnection();
    	System.out.println(result);
    	return result;
    } 
  
    /** 
     * 此方法可以发送任意类型消息 
     *  
     * @param msgType 
     *            text|image|voice|video|file|news 
     * @param touser 
     *            成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all， 
     *            则向关注该企业应用的全部成员发送 
     * @param toparty 
     *            部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数 
     * @param totag 
     *            标签ID列表，多个接收者用‘|’分隔。当touser为@all时忽略本参数 
     * @param content 
     *            msgType=text时 ,文本消息内容 
     * @param mediaId 
     *            msgType=image|voice|video时 ,对应消息信息ID（--------） 
     * @param title 
     *            msgType=news|video时，消息标题 
     * @param description 
     *            msgType=news|video时，消息描述 
     * @param url 
     *            msgType=news时，消息链接 
     * @param picurl 
     *            msgType=news时，图片路径 
     * @param safe 
     *            表示是否是保密消息，0表示否，1表示是，默认0 
     */  
    public void sendWeChatMsg(String msgType, String touser, String toparty,  
            String totag, String content, String mediaId, String title,  
            String description, String url, String picurl, String safe) {  
  
        URL uRl;  
        String ACCESS_TOKEN = getAccessToken();  
        // 拼接请求串  
        String action = CREATE_SESSION_URL + ACCESS_TOKEN;  
        // 封装发送消息请求json  
        StringBuffer sb = new StringBuffer();  
        sb.append("{");  
        sb.append("\"touser\":" + "\"" + touser + "\",");  
        sb.append("\"toparty\":" + "\"" + toparty + "\",");  
        sb.append("\"totag\":" + "\"" + totag + "\",");  
        if (msgType.equals("text")) {  
            sb.append("\"msgtype\":" + "\"" + msgType + "\",");  
            sb.append("\"text\":" + "{");  
            sb.append("\"content\":" + "\"" + content + "\"");  
            sb.append("}");  
        } else if (msgType.equals("image")) {  
            sb.append("\"msgtype\":" + "\"" + msgType + "\",");  
            sb.append("\"image\":" + "{");  
            sb.append("\"media_id\":" + "\"" + mediaId + "\"");  
            sb.append("}");  
        } else if (msgType.equals("voice")) {  
            sb.append("\"msgtype\":" + "\"" + msgType + "\",");  
            sb.append("\"voice\":" + "{");  
            sb.append("\"media_id\":" + "\"" + mediaId + "\"");  
            sb.append("}");  
        } else if (msgType.equals("video")) {  
            sb.append("\"msgtype\":" + "\"" + msgType + "\",");  
            sb.append("\"video\":" + "{");  
            sb.append("\"media_id\":" + "\"" + mediaId + "\",");  
            sb.append("\"title\":" + "\"" + title + "\",");  
            sb.append("\"description\":" + "\"" + description + "\"");  
            sb.append("}");  
        } else if (msgType.equals("file")) {  
            sb.append("\"msgtype\":" + "\"" + msgType + "\",");  
            sb.append("\"file\":" + "{");  
            sb.append("\"media_id\":" + "\"" + mediaId + "\"");  
            sb.append("}");  
        } else if (msgType.equals("news")) {  
            sb.append("\"msgtype\":" + "\"" + msgType + "\",");  
            sb.append("\"news\":" + "{");  
            sb.append("\"articles\":" + "[");  
            sb.append("{");  
            sb.append("\"title\":" + "\"" + title + "\",");  
            sb.append("\"description\":" + "\"" + description + "\",");  
            sb.append("\"url\":" + "\"" + url + "\",");  
            sb.append("\"picurl\":" + "\"" + picurl + "\"");  
            sb.append("}");  
            sb.append("]");  
            sb.append("}");  
        }  
        sb.append(",\"safe\":" + "\"" + safe + "\",");  
        sb.append("\"agentid\":" + "\"" + agentId + "\",");  
        sb.append("\"debug\":" + "\"" + "1" + "\"");  
        sb.append("}");  
        String json = sb.toString();  
        try {  
  
            uRl = new URL(action);  
  
            HttpsURLConnection http = (HttpsURLConnection) uRl.openConnection();  
  
            http.setRequestMethod("POST");  
  
            http.setRequestProperty("Content-Type",  
  
            "application/json;charset=UTF-8");  
  
            http.setDoOutput(true);  
  
            http.setDoInput(true);  
  
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//  
            // 连接超时30秒  
  
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //  
            // 读取超时30秒  
  
            http.connect();  
  
            OutputStream os = http.getOutputStream();  
  
            os.write(json.getBytes("UTF-8"));// 传入参数  
  
            InputStream is = http.getInputStream();  
  
            int size = is.available();  
  
            byte[] jsonBytes = new byte[size];  
  
            is.read(jsonBytes);  
  
            String result = new String(jsonBytes, "UTF-8");  
  
            System.out.println("请求返回结果:" + result);  
  
            os.flush();  
  
            os.close();  
  
        } catch (Exception e) {  
  
            e.printStackTrace();  
  
        }  
    } 

	public static void main(String[] args) {  
    	WeChatQyAPIService weChat = new WeChatQyAPIService();
//    	corpID = "wwe5d721a7400feb75";
//    	secret = "YDMfqZ3xm7zFEj4KRncPvJkDg7cE0gW5nOoeSyDag4A";
//    	agentId = "1000004";
        // weChat.sendWeChatMsgText("@all", "2", "", "信息中心通知", "0");  
        weChat.sendWeChatMsg("text", "", "3", "", "测试：发给部门3", "", "","", "","", "0");  
    }  

}
