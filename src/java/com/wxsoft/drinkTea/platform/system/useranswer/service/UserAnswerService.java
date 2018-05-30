package com.wxsoft.drinkTea.platform.system.useranswer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxsoft.drinkTea.platform.weixin.mapper.RedEnvelopeMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.SubUserAnswerMapper;
import com.wxsoft.drinkTea.platform.weixin.model.RedEnvelope;
import com.wxsoft.drinkTea.platform.weixin.model.SubUserAnswer;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 * 用户答题情况
 *
 */
@Service
public class UserAnswerService {

	@Autowired
	private RedEnvelopeMapper redEnvelopeMapper;

	@Autowired
	private SubUserAnswerMapper subUserAnswerMapper;

	/**
	 * 获取答题总数以及红包总数
	 * 
	 * @param webUser
	 * @return
	 */
	public List<WebUser> getAnswerCountAndMonday(List<WebUser> webUsers) {
		if (webUsers != null && webUsers.size() > 0) {
			for (int i = 0; i < webUsers.size(); i++) {
				SubUserAnswer subUserAnswer = new SubUserAnswer();
				subUserAnswer.setUserId(webUsers.get(i).getId());
				webUsers.get(i).setAnswerAllCount(subUserAnswerMapper.getCountBy(subUserAnswer));
				subUserAnswer.setRightOrWrong(0);
				webUsers.get(i).setRightAllCount(subUserAnswerMapper.getCountBy(subUserAnswer));
				// 查询红包
				RedEnvelope redEnvelope = new RedEnvelope();
				redEnvelope.setUserId(webUsers.get(i).getId());
				redEnvelope.setType(0);
				List<RedEnvelope> redEnvelopes = redEnvelopeMapper.getListBy(redEnvelope);
				Double money = 0.0;
				if (redEnvelopes != null && redEnvelopes.size() > 0) {
					for (RedEnvelope redEnvelope2 : redEnvelopes) {
						money += redEnvelope2.getMoney();
					}
				}
				webUsers.get(i).setRedMoney(money);
			}
			return webUsers;
		}
		return null;
	}

}
