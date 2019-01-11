package com.shuidi168.earn.util;

import java.util.Random;

public class GeneralInviteCodeUtil {
	/** 自定义进制（选择你想要的进制数，不能重复且最好不要0、1这些容易混淆的字符） */
    private static final char[] r=new char[]{'Q', 'W', 'E', '8', 'S', '2', 'D', 'Z', 'X', 
    		'9', 'C', '7', 'P', '5', 'K', '3', 'M', 'J', 'U', 'F', 'R', '4', 'V', 'Y',
    		'T', 'N', '6', 'B', 'G', 'H','2','3','4','5','6','7','8','9','2','3','4','5','6','7','8','9'};

    /** 定义一个字符用来补全邀请码长度（该字符前面是计算出来的邀请码，后面是用来补全用的） */
    private static final char b='A';

    /** 进制长度 */
    private static final int binLen=r.length;

    /** 邀请码长度 */
    private static final int s=6;



    public static String toSerialCode(int id) {
        char[] buf=new char[48];
        int charPos=48;

        while((id / binLen) > 0) {
            int ind=(id % binLen);
            buf[--charPos]=r[ind];
            id /= binLen;
        }
        buf[--charPos]=r[(id % binLen)];
        String str=new String(buf, charPos, (48 - charPos));
        // 不够长度的自动随机补全
        if(str.length() < s) {
            StringBuilder sb=new StringBuilder();
            sb.append(b);
            Random rnd=new Random();
            for(int i=1; i < s - str.length(); i++) {
            sb.append(r[rnd.nextInt(binLen)]);
            }
            str+=sb.toString();
        }
        return str;
    }

    /**
     * 根据随机码生成ID
     * @param 随机码
     * @return ID
     */
    public static long codeToId(String code) {
        char chs[]=code.toCharArray();
        int res=0;
        for(int i=0; i < chs.length; i++) {
            int ind=0;
            for(int j=0; j < binLen; j++) {
                if(chs[i] == r[j]) {
                    ind=j;
                    break;
                }
            }
            if(chs[i] == b) {
                break;
            }
            if(i > 0) {
                res=res * binLen + ind;
            } else {
                res=ind;
            }
        }
        return res;
    }
}
