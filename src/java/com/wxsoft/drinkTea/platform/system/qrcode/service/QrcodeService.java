package com.wxsoft.drinkTea.platform.system.qrcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxsoft.drinkTea.platform.system.qrcode.mapper.PackageSubjectMapper;
import com.wxsoft.drinkTea.platform.system.qrcode.mapper.SmallPackageMapper;
import com.wxsoft.drinkTea.platform.system.qrcode.model.PackageSubject;
import com.wxsoft.drinkTea.platform.system.qrcode.model.SmallPackage;

@Service
public class QrcodeService {

	@Autowired
	private PackageSubjectMapper packageSubjectMapper;

	@Autowired
	private SmallPackageMapper smallPackageMapper;

	@Transactional
	public int save(SmallPackage param){
		int result = 0;
		String subOneId = param.getSubOneId();
		String opOneId = param.getOpOneId();
		String opTwoId = param.getOpTwoId();
		String opThreeId = param.getOpThreeId();
		//先把小包名字和创建时间传过去
		int a = smallPackageMapper.insert(param);
		if(a == 1){
			 result = result + 1;
		}
		SmallPackage smallPackage = smallPackageMapper.selectBy(param);
		if(smallPackage != null){
			Integer Id = smallPackage.getId();
			Integer spId = Id;
			PackageSubject psOne = new PackageSubject();
			psOne.setSmlPakId(spId);
			psOne.setSubId(Integer.parseInt(subOneId));
			psOne.setOptionId(Integer.parseInt(opOneId));
			psOne.setSignId(1);
			int b = packageSubjectMapper.insert(psOne);
			if(b == 1){
				result = result + 1;
			}
			PackageSubject psTwo = new PackageSubject();
			psTwo.setSmlPakId(spId);
			psTwo.setSubId(Integer.parseInt(subOneId));
			psTwo.setOptionId(Integer.parseInt(opTwoId));
			psTwo.setSignId(2);
			int c = packageSubjectMapper.insert(psTwo);
			if(c == 1){
				result = result + 1;
			}
			PackageSubject psThree = new PackageSubject();
			psThree.setSmlPakId(spId);
			psThree.setSubId(Integer.parseInt(subOneId));
			psThree.setOptionId(Integer.parseInt(opThreeId));
			psThree.setSignId(3);
			int d = packageSubjectMapper.insert(psThree);
			if(d == 1){
				result = result + 1;
			}
		}
		return result;
	}

	public int update(SmallPackage param){
		int result = 0;
		String id = param.getFalseId();
		String subOneId = param.getSubOneId();
		String opOneId = param.getOpOneId();
		String opTwoId = param.getOpTwoId();
		String opThreeId = param.getOpThreeId();
		param.setId(Integer.parseInt(id));
		int a = smallPackageMapper.updateByPrimaryKey(param);
		if(a == 1){
			 result = result + 1;
		}
		SmallPackage smallPackage = smallPackageMapper.selectBy(param);
		if(smallPackage != null){
			PackageSubject ps = new PackageSubject();
			ps.setSmlPakId(smallPackage.getId());
			List<PackageSubject> paSub = packageSubjectMapper.getListBy(ps);
			for (PackageSubject packageSubject : paSub) {
				if(packageSubject.getSignId()==1){
					packageSubject.setSubId(Integer.valueOf(subOneId));
					packageSubject.setOptionId(Integer.parseInt(opOneId));
					int b = packageSubjectMapper.updateByPrimaryKey(packageSubject);
					if(b==1){
						result=result+1;
					}
				}else if(packageSubject.getSignId()==2){
					packageSubject.setSubId(Integer.valueOf(subOneId));
					packageSubject.setOptionId(Integer.parseInt(opTwoId));
					int b = packageSubjectMapper.updateByPrimaryKey(packageSubject);
					if(b==1){
						result=result+1;
					}
				}else if(packageSubject.getSignId()==3){
					packageSubject.setSubId(Integer.valueOf(subOneId));
					packageSubject.setOptionId(Integer.parseInt(opThreeId));
					int b = packageSubjectMapper.updateByPrimaryKey(packageSubject);
					if(b==1){
						result=result+1;
					}
				}else{
					result =0;
				}

			}

		}
		return result;
	}
}
