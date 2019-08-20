package com.stylefeng.guns.rest.modular.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stylefeng.guns.sharecore.modular.system.dao.ChannelCapitalRecordMapper;
import com.stylefeng.guns.sharecore.modular.system.model.ChannelCapitalRecord;
import com.stylefeng.guns.sharecore.modular.system.service.BaseService;


/**
 *
 */
@Service
public class ChannelCapitalService extends BaseService {

	@Autowired
	private ChannelCapitalRecordMapper channelCapitalRecordMapper;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, timeout = 3, rollbackFor = Exception.class)
	public int updateChannelCapitalRecordById(Long chcrId,Integer reconStatus){
		ChannelCapitalRecord record = new ChannelCapitalRecord();
		record.setRecordId(chcrId);
		record.setReconStatus(reconStatus);
		return channelCapitalRecordMapper.updateByPrimaryKeySelective(record);
		
	}

}
