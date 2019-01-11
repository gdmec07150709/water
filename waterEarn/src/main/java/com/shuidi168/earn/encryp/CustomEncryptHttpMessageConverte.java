package com.shuidi168.earn.encryp;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class CustomEncryptHttpMessageConverte extends MappingJackson2HttpMessageConverter {

	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		System.out.println("readInternal");
		return super.readInternal(clazz, inputMessage);
	}
	
	@Override
	protected void writeInternal(Object arg0, Type arg1, HttpOutputMessage arg2)
			throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		System.out.println("writeInternal");
		super.writeInternal(arg0, arg1, arg2);
	}

}
