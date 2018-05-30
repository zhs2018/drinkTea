package com.wxsoft.drinkTea.platform.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.drinkTea.platform.weixin.mapper.RedEnvelopeMapper;
import com.wxsoft.drinkTea.platform.weixin.model.RedEnvelope;

@Service
public class RedEnvelopeService {
	@Autowired
	private RedEnvelopeMapper redEnvelopeMapper;

	public int save(RedEnvelope envelope) {
		if(envelope!=null){
			return redEnvelopeMapper.insert(envelope);
		}else{
			return 0;
		}
	}

	public RedEnvelope findEnvelope(RedEnvelope envelope) {
		return redEnvelopeMapper.selectBy(envelope);
	}

	public Integer getCount(RedEnvelope redEnvelope) {
		return redEnvelopeMapper.getCountBy(redEnvelope);
	}
}
