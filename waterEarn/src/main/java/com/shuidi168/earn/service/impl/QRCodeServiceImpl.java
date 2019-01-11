package com.shuidi168.earn.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shuidi168.earn.service.IQRCodeService;
import com.shuidi168.earn.util.QRCodeUtil;

@Service
@Transactional
public class QRCodeServiceImpl implements IQRCodeService {

	@Override
	public void generalQRCode(String url,String logoImgPath, File file) {
		// TODO Auto-generated method stub
		//生成二维码并保存
		try {
			QRCodeUtil.encode(url, logoImgPath, new FileOutputStream(file), true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
