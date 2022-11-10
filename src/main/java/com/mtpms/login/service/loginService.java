package com.mtpms.login.service;

import com.mtpms.dto.loginDto;

public interface loginService {

	public int selecLoginChk(loginDto loginDto) throws Exception;

	public loginDto selecLoginInfo(loginDto loginDto) throws Exception;


}