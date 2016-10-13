package academy.group5.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;

class GCM {
	private final String GCM_TITLE = "MCM";

	GCM(String msg, String submsg, List<String> userIdList) {

		String title = null;
		
		try {
			title = java.net.URLEncoder.encode(GCM_TITLE, "UTF-8");
			msg = java.net.URLEncoder.encode(msg,"UTF-8");
			if(submsg!=null){
				submsg = java.net.URLEncoder.encode(submsg, "UTF-8");
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		Sender sender = new Sender("AIzaSyBOaaj7W42iQ2YZPzqbQXK4539K3pyPvk8");
		Message message = new Message.Builder()
				//.collapseKey(String.valueOf(Math.random() % 100 + 1)) // 중복체크
				//.timeToLive(3600) //  대기시간
				//.delayWhileIdle(false)
				.addData("title", title)
				.addData("message", msg)
				.addData("submsg", submsg)
				.build();

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