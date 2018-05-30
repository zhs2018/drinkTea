package com.wxsoft.drinkTea.platform.weixin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxsoft.drinkTea.framework.utils.DateUtils;
import com.wxsoft.drinkTea.framework.utils.MoneyUtils;
import com.wxsoft.drinkTea.platform.system.qrcode.mapper.PackageSubjectMapper;
import com.wxsoft.drinkTea.platform.system.qrcode.mapper.SmallPackageMapper;
import com.wxsoft.drinkTea.platform.system.qrcode.model.PackageSubject;
import com.wxsoft.drinkTea.platform.system.qrcode.model.SmallPackage;
import com.wxsoft.drinkTea.platform.system.sysconfig.mapper.SubjectKuMapper;
import com.wxsoft.drinkTea.platform.system.sysconfig.model.SubjectKu;
import com.wxsoft.drinkTea.platform.weixin.mapper.KingUserAnswerMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.RedEnvelopeMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.SubOptionMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.SubUserAnswerMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.TwentyRecordMapper;
import com.wxsoft.drinkTea.platform.weixin.mapper.WebUserMapper;
import com.wxsoft.drinkTea.platform.weixin.model.KingUserAnswer;
import com.wxsoft.drinkTea.platform.weixin.model.RedEnvelope;
import com.wxsoft.drinkTea.platform.weixin.model.SubOption;
import com.wxsoft.drinkTea.platform.weixin.model.SubUserAnswer;
import com.wxsoft.drinkTea.platform.weixin.model.TwentyRecord;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 * 答题用Service
 */
@Service
public class TeaKingService {

	@Autowired
	private PackageSubjectMapper packageSubjectMapper;

	@Autowired
	private SubjectKuMapper subjectKuMapper;

	@Autowired
	private SubUserAnswerMapper subUserAnswerMapper;

	@Autowired
	private KingUserAnswerMapper kingUserAnswerMapper;

	@Autowired
	private WebUserMapper webUserMapper;

	@Autowired
	private SubOptionMapper subOptionMapper;

	@Autowired
	private TwentyRecordMapper twentyRecordMapper;

	@Autowired
	private SmallPackageMapper smallPackageMapper;

	@Autowired
	private RedEnvelopeMapper redEnvelopeMapper;

	/**
	 * 根据小包查询题目(一道固定的题目3问)
	 *
	 * @param integer
	 * @param smallPackage
	 * @return
	 */
	public SubjectKu findSubjects(Integer packId) {
		SubjectKu subjectKu = null;
		PackageSubject packSub = new PackageSubject();
		// 获取题目
		packSub.setSmlPakId(packId);
		List<PackageSubject> subjects = packageSubjectMapper.getListBy(packSub);
		if (subjects != null && subjects.size() >= 3) {
			for (PackageSubject packageSubject : subjects) {
				if (subjectKu == null) {
					subjectKu = subjectKuMapper.selectByPrimaryKey(packageSubject.getSubId());
				}
				if (packageSubject.getSignId() == 1) {
					SubOption option = new SubOption();
					option.setType(0);
					option.setSubId(subjectKu.getId());
					option.setVisable(1);
					option.setSign(packageSubject.getSignId());
					List<SubOption> subList1 = subOptionMapper.getListBy(option);
					subjectKu.setOpList1(subList1);
				} else if (packageSubject.getSignId() == 2) {
					SubOption option = new SubOption();
					option.setType(0);
					option.setSubId(subjectKu.getId());
					option.setVisable(1);
					option.setSign(packageSubject.getSignId());
					List<SubOption> subList2 = subOptionMapper.getListBy(option);
					subjectKu.setOpList2(subList2);
				} else if (packageSubject.getSignId() == 3) {
					SubOption option = new SubOption();
					option.setType(0);
					option.setSubId(subjectKu.getId());
					option.setVisable(1);
					option.setSign(packageSubject.getSignId());
					List<SubOption> subList3 = subOptionMapper.getListBy(option);
					subjectKu.setOpList3(subList3);
				} else {
					subjectKu = null;
				}

			}
		}
		return subjectKu;
	}

	/**
	 * 扫码答题中根据小包id查询所有信息
	 *
	 * @param subjectKu
	 * @return
	 */
	public List<PackageSubject> findOptionBySubject(Integer packageId) {
		List<PackageSubject> subjects = null;
		if (packageId != null) {
			PackageSubject packageSubject = new PackageSubject();
			packageSubject.setSmlPakId(packageId);
			packageSubject.setVisable(1);
			subjects = packageSubjectMapper.getListBy(packageSubject);
			if (subjects != null && subjects.size() > 0) {
				for (int j = 0; j < subjects.size(); j++) {
					SubjectKu sub = new SubjectKu();
					if (subjects.get(j).getSubId() != null) {
						sub = subjectKuMapper.selectByPrimaryKey(subjects.get(j).getSubId());
						SubOption option = new SubOption();
						// 0 是扫码答题 1是茶王争霸赛
						option.setType(0);
						option.setSubId(sub.getId());
						option.setVisable(1);
						sub.setOptions(subOptionMapper.getListBy(option));
						subjects.get(j).setSubjectKu(sub);
					}
				}
			}
		}
		return subjects;
	}

	/**
	 * 检查答题情况
	 *
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public WebUser checkAnswerCount(WebUser user) {
		if (user != null) {
			TwentyRecord twentyRecord = new TwentyRecord();
			twentyRecord.setUserId(user.getId());
			twentyRecord.setVisable(1);
			twentyRecord = twentyRecordMapper.selectBy(twentyRecord);
			if (twentyRecord != null && twentyRecord.getCount() >= 15) {
				user.setAnswerCount(user.getAnswerCount() - 1);
				webUserMapper.updateByPrimaryKey(user);
				twentyRecord.setVisable(0);
				twentyRecord.setEndTime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
				twentyRecordMapper.updateByPrimaryKey(twentyRecord);
			}
		}
		return user;
	}

	/**
	 * 预先保存用户的答题情况，避免用户退出重新答题
	 *
	 * @param subjects
	 * @param user
	 * @param smallPackage
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveAnswersResult(SubjectKu subjects, WebUser user, SmallPackage smallPackage) {
		// 修改题目小包（将小包中的用户id填写）
		smallPackage.setUserId(user.getId());
		smallPackageMapper.updateByPrimaryKey(smallPackage);
		// 获取15道题目的信息
		TwentyRecord twentyRecord = new TwentyRecord();
		twentyRecord.setUserId(user.getId());
		twentyRecord.setVisable(1);
		twentyRecord = twentyRecordMapper.selectBy(twentyRecord);
		if (twentyRecord == null || twentyRecord.getCount() == null) {
			user.setAnswerCount(user.getAnswerCount() - 3);
			webUserMapper.updateByPrimaryKey(user);
			twentyRecord = new TwentyRecord();
			twentyRecord.setUserId(user.getId());
			twentyRecord.setStartTime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
			twentyRecord.setCount(3);
			twentyRecord.setVisable(1);
			twentyRecord.setMoney(0.00f);
			twentyRecordMapper.insert(twentyRecord);
			twentyRecord = twentyRecordMapper.selectBy(twentyRecord);
		} else {
			if (twentyRecord.getCount() >= 15) {
				// 修改答题次数
				user.setAnswerCount(user.getAnswerCount() - 3);
				webUserMapper.updateByPrimaryKey(user);
				twentyRecord.setVisable(0);
				twentyRecord.setEndTime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
				twentyRecordMapper.updateByPrimaryKey(twentyRecord);
				twentyRecord = new TwentyRecord();
				twentyRecord.setUserId(user.getId());
				twentyRecord.setStartTime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
				twentyRecord.setCount(3);
				twentyRecord.setVisable(1);
				twentyRecord.setMoney(0.00f);
				twentyRecordMapper.insert(twentyRecord);
				twentyRecord = twentyRecordMapper.selectBy(twentyRecord);
			} else {
				user.setAnswerCount(user.getAnswerCount() - 3);
				webUserMapper.updateByPrimaryKey(user);
				twentyRecord.setCount(twentyRecord.getCount() + 3);
				twentyRecordMapper.updateByPrimaryKey(twentyRecord);
				twentyRecord = twentyRecordMapper.selectBy(twentyRecord);
			}
		}
		int count = 0;
		// 保存用户答题详情
		List<SubOption> op1 = subjects.getOpList1();
		if (op1 != null && op1.size() > 0) {
			SubUserAnswer userAnswer = new SubUserAnswer();
			userAnswer.setUserId(user.getId());
			userAnswer.setPacketId(smallPackage.getId());
			userAnswer.setSubjectKuId(subjects.getId());
			userAnswer.setRightOrWrong(1);// 对或错
			userAnswer.setUserAnswer(-1);
			userAnswer.setSubjectSign(op1.get(0).getSign());
			userAnswer.setTwentyRecordId(twentyRecord.getId());
			subUserAnswerMapper.insert(userAnswer);
			count++;
		}
		List<SubOption> op2 = subjects.getOpList2();
		if (op2 != null && op2.size() > 0) {
			SubUserAnswer userAnswer2 = new SubUserAnswer();
			userAnswer2.setUserId(user.getId());
			userAnswer2.setPacketId(smallPackage.getId());
			userAnswer2.setSubjectKuId(subjects.getId());
			userAnswer2.setRightOrWrong(1);// 对或错
			userAnswer2.setUserAnswer(-1);
			userAnswer2.setSubjectSign(op2.get(0).getSign());
			userAnswer2.setTwentyRecordId(twentyRecord.getId());
			subUserAnswerMapper.insert(userAnswer2);
			count++;
		}
		List<SubOption> op3 = subjects.getOpList3();
		if (op3 != null && op3.size() > 0) {
			SubUserAnswer userAnswer3 = new SubUserAnswer();
			userAnswer3.setUserId(user.getId());
			userAnswer3.setPacketId(smallPackage.getId());
			userAnswer3.setSubjectKuId(subjects.getId());
			userAnswer3.setRightOrWrong(1);// 对或错
			userAnswer3.setUserAnswer(-1);
			userAnswer3.setSubjectSign(op3.get(0).getSign());
			userAnswer3.setTwentyRecordId(twentyRecord.getId());
			subUserAnswerMapper.insert(userAnswer3);
			count++;

		}
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 保存用户扫码答题的结果(存到实体类中)
	 *
	 * @param subjects
	 * @param user
	 * @param smallPackage
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public TwentyRecord updateAnswersResult(List<SubjectKu> subjects, WebUser user, SmallPackage smallPackage) {
		// 获取15道题目的信息
		TwentyRecord twentyRecord = new TwentyRecord();
		twentyRecord.setUserId(user.getId());
		twentyRecord.setVisable(1);
		WebUser user2 = new WebUser();
		user2.setId(user.getId());
		twentyRecord = twentyRecordMapper.selectBy(twentyRecord);
		if (twentyRecord == null) {
			return null;
		}
		int su = 0;// 插入多少条
		int nowRight = 0;
		// 查询用户在本次15道题目中已经答对多少道(不包括本次答题)，用于计算奖励
		SubUserAnswer userAnswer = new SubUserAnswer();
		userAnswer.setRightOrWrong(0);
		userAnswer.setTwentyRecordId(twentyRecord.getId());
		userAnswer.setVisable(1);
		int rightAll = subUserAnswerMapper.getCountBy(userAnswer);
		// 保存用户答题详情
		for (SubjectKu subjectKu : subjects) {
			userAnswer = new SubUserAnswer();
			userAnswer.setUserId(user.getId());
			userAnswer.setPacketId(smallPackage.getId());
			userAnswer.setSubjectKuId(subjectKu.getId());
			userAnswer.setTwentyRecordId(twentyRecord.getId());
			userAnswer = subUserAnswerMapper.selectBy(userAnswer);
			userAnswer.setRightOrWrong(subjectKu.getType());// 对或错
			userAnswer.setUserAnswer(subjectKu.getUserAnswer().getId());
			userAnswer.setSubjectKuId(subjectKu.getId());
			subUserAnswerMapper.updateByPrimaryKey(userAnswer);
			if (subjectKu.getType() == 0) {
				nowRight++;
			}
			su++;
		}
		int count = twentyRecord.getCount();
		float nowMoney = MoneyUtils.getMoneyByRight(nowRight, rightAll);
		// 如果答对题目不为0，发送红包
		if (nowRight > 0) {
			RedEnvelope redEnvelope = new RedEnvelope();
			redEnvelope.setAnswerTime(new Date());
			redEnvelope.setMoney(Double.parseDouble(nowMoney + ""));
			redEnvelope.setPackageId(smallPackage.getId());
			redEnvelope.setUserId(user.getId());
			redEnvelope.setType(0);// 扫码答题
			redEnvelopeMapper.insert(redEnvelope);
			double rMoney = user.getRestMoney() == null ? 0.0 : user.getRestMoney();
			double reMoney = Double.parseDouble(nowMoney + "");
			double restMoney = rMoney + reMoney;
			user.setRestMoney(restMoney);
			user2.setRestMoney(user.getRestMoney());
			webUserMapper.updateByPrimaryKey(user2);
		}
		twentyRecord.setNowMoney(nowMoney);
		twentyRecord.setMoney(twentyRecord.getMoney() + nowMoney);
		if (count >= 15) {
			// 修改答题次数
			user.setAnswerCount(user.getAnswerCount() - 1);
			user2.setAnswerCount(user.getAnswerCount());
			webUserMapper.updateByPrimaryKey(user2);
			twentyRecord.setVisable(0);
			twentyRecord.setEndTime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
			twentyRecordMapper.updateByPrimaryKey(twentyRecord);
			su++;
		} else {
			twentyRecordMapper.updateByPrimaryKey(twentyRecord);
			su++;
		}
		if (su == subjects.size() + 1) {
			twentyRecord.setAllRight(rightAll + nowRight);
			twentyRecord.setNowRight(nowRight);
			return twentyRecord;
		}
		return null;
	}

	@Transactional()
	public int saveKingAnswerResult(KingUserAnswer userAnswer) {
		if (userAnswer != null) {
			return kingUserAnswerMapper.insert(userAnswer);
		}
		return 0;
	}

	/**
	 * 获取答题争霸的用户排名情况
	 *
	 * @param subjectKing
	 * @return
	 */
	public List<KingUserAnswer> getRankingList(KingUserAnswer userAnswer) {
		System.out.println(userAnswer);
		List<KingUserAnswer> list = kingUserAnswerMapper.getListBy(userAnswer);
		Integer mSize = 0;
		if (list != null && list.size() > 0) {
			if (list.size() > 100) {
				mSize = 100;
			} else {
				mSize = list.size();
			}
			for (int i = 0; i < mSize; i++) {
				list.get(i).setUser(webUserMapper.selectByPrimaryKey(list.get(i).getUserId()));
			}
			return list;
		}
		return null;
	}

	/**
	 * 保存用户扫码答题的结果(存到实体类中)
	 *
	 * @param subjects
	 * @param user
	 * @param smallPackage
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public TwentyRecord updateAnswersResults(SubjectKu subjects, WebUser user, SmallPackage smallPackage, Integer type,
			Integer sign, Integer answer, Integer num) {
		// 获取15道题目的信息
		TwentyRecord twentyRecord = new TwentyRecord();
		twentyRecord.setUserId(user.getId());
		twentyRecord.setVisable(1);
		WebUser user2 = new WebUser();
		user2.setId(user.getId());
		twentyRecord = twentyRecordMapper.selectBy(twentyRecord);
		if (twentyRecord == null) {
			return null;
		}
		int su = 0;// 插入多少条
		int nowRight = 0;
		// 查询用户在本次15道题目中已经答对多少道(不包括本次答题)，用于计算奖励
		SubUserAnswer userAnswer = new SubUserAnswer();
		userAnswer.setRightOrWrong(2);
		userAnswer.setTwentyRecordId(twentyRecord.getId());
		userAnswer.setVisable(1);
		int rightAll = subUserAnswerMapper.getCountBy(userAnswer);
		if (sign == 1) {
			userAnswer = new SubUserAnswer();
			userAnswer.setUserId(user.getId());
			userAnswer.setPacketId(smallPackage.getId());
			userAnswer.setSubjectKuId(subjects.getId());
			userAnswer.setTwentyRecordId(twentyRecord.getId());
			userAnswer.setSubjectSign(sign);
			userAnswer = subUserAnswerMapper.selectBy(userAnswer);
			userAnswer.setRightOrWrong(type);// 对或错
			userAnswer.setUserAnswer(subjects.getOpList1().get(answer).getId());
			userAnswer.setSubjectKuId(subjects.getId());
			subUserAnswerMapper.updateByPrimaryKey(userAnswer);
			if (type == 2) {
				nowRight++;
			}
		} else if (sign == 2) {
			userAnswer = new SubUserAnswer();
			userAnswer.setUserId(user.getId());
			userAnswer.setPacketId(smallPackage.getId());
			userAnswer.setSubjectKuId(subjects.getId());
			userAnswer.setTwentyRecordId(twentyRecord.getId());
			userAnswer.setSubjectSign(sign);
			userAnswer = subUserAnswerMapper.selectBy(userAnswer);
			userAnswer.setRightOrWrong(type);// 对或错
			userAnswer.setUserAnswer(subjects.getOpList2().get(answer).getId());
			userAnswer.setSubjectKuId(subjects.getId());
			subUserAnswerMapper.updateByPrimaryKey(userAnswer);
			if (type == 2) {
				nowRight++;
			}
		} else if (sign == 3) {
			userAnswer = new SubUserAnswer();
			userAnswer.setUserId(user.getId());
			userAnswer.setPacketId(smallPackage.getId());
			userAnswer.setSubjectKuId(subjects.getId());
			userAnswer.setTwentyRecordId(twentyRecord.getId());
			userAnswer.setSubjectSign(sign);
			userAnswer = subUserAnswerMapper.selectBy(userAnswer);
			userAnswer.setRightOrWrong(type);// 对或错
			userAnswer.setUserAnswer(subjects.getOpList3().get(answer).getId());
			userAnswer.setSubjectKuId(subjects.getId());
			subUserAnswerMapper.updateByPrimaryKey(userAnswer);
			if (type == 2) {
				nowRight++;
			}
		} else {
			System.out.println("有错误");
		}

		// 另一部分
		int count = 0;
		if (num == 2) {
			count = twentyRecord.getCount();
		}
		float nowMoney = MoneyUtils.getMoneyByRight(nowRight, rightAll);
		// 如果答对题目不为0，发送红包
		if (nowRight == 1) {
			RedEnvelope redEnvelope = new RedEnvelope();
			redEnvelope.setAnswerTime(new Date());
			redEnvelope.setMoney(Double.parseDouble(nowMoney + ""));
			redEnvelope.setPackageId(smallPackage.getId());
			// redEnvelope.setSubjectsId(subjects.getId());
			redEnvelope.setUserId(user.getId());
			redEnvelope.setType(0);// 扫码答题
			redEnvelopeMapper.insert(redEnvelope);
			double rMoney = user.getRestMoney() == null ? 0.0 : user.getRestMoney();
			double reMoney = Double.parseDouble(nowMoney + "");
			double restMoney = rMoney + reMoney;
			user.setRestMoney(restMoney);
			user2.setRestMoney(user.getRestMoney());
			webUserMapper.updateByPrimaryKey(user2);
		}
		twentyRecord.setNowMoney(nowMoney);
		twentyRecord.setMoney(twentyRecord.getMoney() + nowMoney);
		if (count >= 15) {
			// 修改答题次数
			user.setAnswerCount(user.getAnswerCount() - 1);
			user2.setAnswerCount(user.getAnswerCount());
			webUserMapper.updateByPrimaryKey(user2);
			twentyRecord.setVisable(0);
			twentyRecord.setEndTime(DateUtils.getCurrentDateFormat("yyyyMMddHHmmss"));
			twentyRecordMapper.updateByPrimaryKey(twentyRecord);
			su++;
		} else {
			twentyRecordMapper.updateByPrimaryKey(twentyRecord);
			su++;
		}
		if (su == 1) {
			twentyRecord.setAllRight(rightAll + nowRight);
			twentyRecord.setNowRight(nowRight);
			return twentyRecord;
		}
		return null;

	}
}