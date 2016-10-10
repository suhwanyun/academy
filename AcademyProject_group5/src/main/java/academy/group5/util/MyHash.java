package academy.group5.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyHash {
	
	public static String MD5(String str){
		String md5Str = ""; 
		try{
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(str.getBytes()); 
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer(); 
			// byte code를 hex format으로 변경
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			md5Str = sb.toString();
			
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			md5Str = null; 
		}
		return md5Str;
	}
	
	public static String SHA256(String str){
		String shaStr = ""; 
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
			sh.update(str.getBytes()); 
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer(); 
			// byte code를 hex format으로 변경
			for(int i = 0 ; i < byteData.length ; i++){
				System.out.println(byteData[i]+" : "+Integer.toBinaryString(byteData[i])+" : "+Integer.toBinaryString(byteData[i]&0xff)+" : "+Integer.toBinaryString(byteData[i]&0xff+0x100)+" : "+Integer.toBinaryString(byteData[i]&0xff+0x100).substring(1));
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			shaStr = sb.toString();
			
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			shaStr = null; 
		}
		return shaStr;
	}
}
