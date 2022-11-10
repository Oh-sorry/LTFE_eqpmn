package com.mtpms.main.service;

import java.util.List;

import com.mtpms.dto.menuManageDto;
import com.mtpms.dto.db.pmsmenuDto;

public interface mainService {

	public List<pmsmenuDto> selectTopMenu() throws Exception;

	public List<menuManageDto> selectLeftMenu(String userid) throws Exception;

}