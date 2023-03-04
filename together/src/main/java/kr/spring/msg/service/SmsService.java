package kr.spring.msg.service;

import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;


@Service
public class SmsService {

public String PhoneNumberCheck(String to, String partyName, String url) throws CoolsmsException {
		
		String api_key = "NCSYXIR5FDEQDHSO";
		String api_secret = "SFSM7XYCCBTEYPNRVYXCDPZGHUOBDUTH";
		Message coolsms = new Message(api_key, api_secret);
		
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("to", to);    // 수신전화번호 (ajax로 view 화면에서 받아온 값으로 넘김)
    params.put("from", "01083083477");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
    params.put("type", "sms"); 
    params.put("text", partyName +"에 당신을 초대합니다"+ "\n" + url);
    
    coolsms.send(params); // 메시지 전송
	return partyName;

}

}
