package com.wxsoft.drinkTea.platform.system.sysconfig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxsoft.drinkTea.platform.system.sysconfig.mapper.SubjectKingMapper;
import com.wxsoft.drinkTea.platform.system.sysconfig.mapper.SubjectKuMapper;
import com.wxsoft.drinkTea.platform.system.sysconfig.model.SubjectKing;
import com.wxsoft.drinkTea.platform.system.sysconfig.model.SubjectKu;
import com.wxsoft.drinkTea.platform.weixin.mapper.SubOptionMapper;
import com.wxsoft.drinkTea.platform.weixin.model.SubOption;

@Service
public class SubjectService {
	@Autowired
	private SubjectKuMapper subjectKuMapper;
	@Autowired
	private SubjectKingMapper subjectKingMapper;
	@Autowired
	private SubOptionMapper subOptionMapper;

	public int updateByPrimaryKey(SubjectKu record){
		return subjectKuMapper.updateByPrimaryKey(record);
	}

	public int insert(SubjectKu record, Integer type){
		if(type == null){
			return 0;
		}else if(type == 0){
			return subjectKuMapper.insert(record);
		}else if(type == 1){
			SubjectKing subjectKing = new SubjectKing();
			return subjectKingMapper.insert(subjectKing);
		}else{
			return 0;
		}

	}

	public int deleteByPrimaryKey(SubjectKu subjectKu) {
		return subjectKuMapper.delete(subjectKu);
	}

	public int update(SubjectKu record, Integer type) {
		if(type == null){
			return 0;
		}else if(type == 0){
			return subjectKuMapper.updateByPrimaryKey(record);
		}else if(type == 1){
			SubjectKing subjectKing = new SubjectKing();
			return subjectKingMapper.updateByPrimaryKey(subjectKing);
		}else{
			return 0;
		}
	}

	public int deleteByPrimaryKey(SubjectKing subjectKing) {
		return subjectKingMapper.delete(subjectKing);
	}

	/**
	 * 更新选项（后期需要修改！！^_^）
	 * @param id
	 * @param arr
	 * @param type
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateOption(Integer id, String[] arr, Integer type,Integer sign) {
		int count = 0;
		if(id !=null && arr != null && arr.length > 0 && type != null){
			SubOption option = new SubOption();
			option.setSubId(id);
			option.setType(type);
            option.setSign(sign);
			option.setVisable(1);
			List<SubOption> options = subOptionMapper.getListBy(option);
			if(options == null || options.size()<=0){
				for(int i = 0; i < arr.length;i++){
					option = new SubOption();
					option.setSubId(id);
					option.setType(type);
					option.setSign(sign);
					option.setVisable(1);
					option.setOption(arr[i]);
					subOptionMapper.insert(option);
					count ++;
				}
			}else{
				if(options.size()>arr.length){
					return 0;
				}else{
					for (int i=0;i<options.size();i++) {
						if(!options.get(i).getOption().equals(arr[i].trim())){
							options.get(i).setOption(arr[i]);
							subOptionMapper.updateByPrimaryKey(options.get(i));
						}
					}
					for(int i = options.size(); i < arr.length;i++){
						option = new SubOption();
						option.setSubId(id);
						option.setType(type);
						option.setSign(sign);
						option.setVisable(1);
						option.setOption(arr[i]);
						subOptionMapper.insert(option);
						count ++;
					}
				}
			}
		}
		return 1;
	}
}
