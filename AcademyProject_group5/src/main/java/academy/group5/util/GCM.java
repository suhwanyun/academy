package academy.group5.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;

public class GCM {
	
	public static final String TYPE_NOTICE = "send notice";
	public static final String TYPE_NO_SOUND = "no sound";
	public static final String TYPE_SETTING = "alarm setting";
	public static final String TYPE_RESET = "setting reset";
	
	private final String GCM_TITLE = "MCM";	
	
	public GCM(String msg, String submsg, Set<String> userIdSet, String msgtype) {
		this(msg, submsg, new ArrayList<>(userIdSet), msgtype);
	}
	
	public GCM(String msg, String submsg, List<String> userIdList, String msgtype) {

		String title = null;
		
		try {
			title = java.net.URLEncoder.encode(GCM_TITLE, "UTF-8");
			if(msg!=null){
				msg = java.net.URLEncoder.encode(msg,"UTF-8");
			}
			if(submsg!=null){
				submsg = java.net.URLEncoder.encode(submsg, "UTF-8");
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		Sender sender = new Sender("AIzaSyAZzYD08MWTKY_AmD-PXJ2qcbq3Uh4-0so");
		Message.Builder builder = new Message.Builder()
				//.collapseKey(String.valueOf(Math.random() % 100 + 1)) // 중복체크
				//.timeToLive(3600) //  대기시간
				//.delayWhileIdle(false)
				.addData("title", title)
				.addData("msgtype", msgtype);
		
		if(msg != null){
			builder.addData("message", msg);
		}
		if(submsg != null){
			builder.addData("submsg", submsg);
		}
		
		Message message = builder.build();
		
		try {
			/*MulticastResult multiResult = */
			sender.send(message, userIdList, 5);
			/*if (multiResult != null) {
				List<Result> resultList = multiResult.getResults();
				for (Result result : resultList) {
				}
			}*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}