package kr.spring.msg.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.msg.service.SmsService;
import lombok.AllArgsConstructor;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
@AllArgsConstructor
public class SmsController {
	
	@Autowired
    private final SmsService smsService;
    
    
    // coolSMS 구현 로직 연결  
    @PostMapping("/party/detail.do")
    public @ResponseBody String sendSMS(@RequestParam(value="to") String to,String partyName, String url) throws CoolsmsException {  	
    	
    	
    	return smsService.MessageSend(to, partyName, url);
    }
        
    // coolSMS 구현 로직 연결  
    @PostMapping("/find/checkSms")
    public @ResponseBody String sendSMS(@RequestParam(value="to") String to) throws CoolsmsException {  	
    	
    	return smsService.CellNumberCheck(to);
    }
}