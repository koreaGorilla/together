package kr.spring.partymember.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.spring.partymember.service.PartyMemberService;

public class PartyMemberController {
	private static final Logger logger = LoggerFactory.getLogger(PartyMemberController.class);
	
	@Autowired
	private PartyMemberService partyMemberService;
}
