package com.stylefeng.guns.sharecore.modular.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class BaseService {

	@Autowired
	@Qualifier("common.SequenceService")
	protected SequenceService  seqService;
}
