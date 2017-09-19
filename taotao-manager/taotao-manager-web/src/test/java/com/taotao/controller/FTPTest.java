package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class FTPTest {

	@Test
	public void testFtpClient(){
		// 创建一个FtpClient对象
		FTPClient ftpClient = new FTPClient();
		FileInputStream inputStream = null;
		// 创建ftp连接
		try {
//			ftpClient.connect("192.168.1.109", 21);
			ftpClient.connect("192.168.174.130", 21);
			// 登录ftp服务器，使用用户名和密码
			ftpClient.login("ftpuser", "123456");
			// 上传文件
			inputStream = new FileInputStream(
//					new File("‪D:/test/1.jpg")
//					new File("‪D:\\test\\1.jpg")
//					new File("‪‪C:\\Users\\lpz\\Desktop\\1.jpg")
//					new File(FTPTest.class.getResource("2.jpg").getFile())
//					new File(FTPTest.class.getResource("/1.jpg").getFile())
					new File(FTPTest.class.getClassLoader().getResource("1.jpg").getFile())
					);
			// 设置上传路径
			ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
			// 修改上传文件的格式
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// 第一个参数，服务器文档名；第二个参数，上传文档的inputStream
			ftpClient.storeFile("hello1.jpg", inputStream);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接
				ftpClient.logout();
				if (null != inputStream) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testFtpUtil(){
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(
				new File(FTPTest.class.getClassLoader().getResource("1.jpg").getFile())
			);
			FtpUtil.uploadFile("192.168.174.130", 21, "ftpuser", "123456", 
					"/home/ftpuser/www/images", "/20170312", "hello2.jpg", inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
}
