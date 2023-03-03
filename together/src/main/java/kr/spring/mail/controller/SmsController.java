package kr.spring.mail.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.mail.service.SmsService;
import lombok.AllArgsConstructor;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
@AllArgsConstructor
public class SmsController {
	
	@Autowired
    private final SmsService smsService;
    
 // coolSMS 테스트 화면
    @GetMapping("/sms")
    public String mySms() {
    	return "message/sms";
    }
    
    // coolSMS 구현 로직 연결  
    @PostMapping("/sms")
    public @ResponseBody String sendSMS(@RequestParam(value="to") String to, String message, HttpServletRequest request) throws CoolsmsException {  	
    	 String url = request.getRequestURL().toString();
    	
    	return smsService.PhoneNumberCheck(to, message, url);
    }
}