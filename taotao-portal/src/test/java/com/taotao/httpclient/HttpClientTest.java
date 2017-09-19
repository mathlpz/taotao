package com.taotao.httpclient;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {

	@Test
	public void doGet() throws ClientProtocolException, IOException {
		// 创建一个httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建一个GET请求
		HttpGet get = new HttpGet("http://www.baidu.com");
		// 执行请求
		CloseableHttpResponse response = httpclient.execute(get);
		// 取相应结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("111:" + statusCode);
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println("222:" + string);
		// 关闭httpClient
		response.close();
		httpclient.close();
	}
	
	// 带参数的get请求
	public static void main(String[] args) throws Exception {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 定义请求的参数
        URI uri = new URIBuilder("http://www.baidu.com/s").setParameter("wd", "java").build();
        System.out.println("11:" + uri);
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("22:" + content);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpclient.close();
        }
    }
	
	/**
	 * post请求，一般浏览器不支持post请求，需要表单
	 * @throws Exception
	 */
	@Test
	public void doPost() throws Exception {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个post对象
		HttpPost post = new HttpPost("http://localhost:82/httpclient/post.action");
		//执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		response.close();
		httpClient.close();
	}
	
	
	@Test
	public void doPostWithParam() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个post对象
		HttpPost post = new HttpPost("http://localhost:82/httpclient/post.action");
		//创建一个Entity。模拟一个表单
		List<NameValuePair> kvList = new ArrayList<>();
		kvList.add(new BasicNameValuePair("username", "张三"));
		kvList.add(new BasicNameValuePair("password", "123"));
		//包装成一个Entity对象
		StringEntity entity = new UrlEncodedFormEntity(kvList, "utf-8");
		//设置请求的内容
		post.setEntity(entity);
		//执行post请求
		CloseableHttpResponse response = httpClient.execute(post);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		response.close();
		httpClient.close();
	}
	
	
	
	 @Test
	 public void doPost2() throws Exception {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http POST请求
        HttpPost httpPost = new HttpPost("http://www.oschina.net/");
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpclient.close();
        }
    }
	 
	 @Test
	 public void doPost22() throws Exception {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http POST请求
        HttpPost httpPost = new HttpPost("http://www.oschina.net/search");
        // 设置2个post参数，一个是scope、一个是q
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("scope", "project"));
        parameters.add(new BasicNameValuePair("q", "java"));
        // 构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
        // 将请求实体设置到httpPost对象中
        httpPost.setEntity(formEntity);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpclient.close();
        }
    }



	
	
}
