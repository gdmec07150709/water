package com.shuidi168.earn.converter;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuidi168.earn.util.AESUtil;
import com.shuidi168.earn.util.RSAUtil;
public class MyJson2Converter extends MappingJackson2HttpMessageConverter{
	private String aesKey;
	@Override
	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		//获取header通过RSA公钥加密的AES秘钥		
		System.out.println("hello");
		List<String> list = inputMessage.getHeaders().get("rsa+aes");
		//头信息
		 String headerMsg = null;
		 for(String attribute : list) {
			  System.out.println(attribute);
			  headerMsg = attribute;
			}
		//AES秘钥(随机码)
		 aesKey = RSAUtil.decryptDataOnJava(headerMsg, "私钥");
		//RSA解密
		String json = AESUtil.decrypt(AESUtil.inputStream2String(inputMessage.getBody()),aesKey);
		JavaType javaType = getJavaType(clazz, null);
	    //转换
	    return this.objectMapper.readValue(json, javaType);
	}
	 
	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
	    //使用Jackson的ObjectMapper将Java对象转换成Json String
		System.out.println("writeInternal...");
	    ObjectMapper mapper = new ObjectMapper();
	    String json = mapper.writeValueAsString(object);
	    //使用客户端返回的随机码加密
	    String result = AESUtil.encrypt(json,aesKey);
	    //输出加密数据
	    outputMessage.getBody().write(result.getBytes());
	}

}
