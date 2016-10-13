/*package academy.group5.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

class GCM {
	String regId;
	String title = "MCM";
	String msg;
	String submsg;

	GCM(String msgType) {

		try {
			title = java.net.URLEncoder.encode(title, "UTF-8");
			msg = java.net.URLEncoder.encode(msg,"UTF-8");
			if(submsg!=null)
			submsg = java.net.URLEncoder.encode(submsg, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Sender sender = new Sender("AIzaSyBOaaj7W42iQ2YZPzqbQXK4539K3pyPvk8");
		Message message = new Message.Builder().collapseKey(String.valueOf(Math.random() % 100 + 1)) // 중복체크
				.delayWhileIdle(false).timeToLive(1800) // 절전모드사용여부? 대기시간
				.addData("title", title).addData("message", msg).addData("submsg", submsg).build();
		List<String> userIdList = new ArrayList<String>();
	
		
		MulticastResult multiResult;

		try {
			multiResult = sender.send(message, userIdList, 5);
			if (multiResult != null) {
				List<Result> resultList = multiResult.getResults();
				for (Result result : resultList) {
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}*/