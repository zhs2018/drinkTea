package ${PACKAGE_URL};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import ${PACKAGE1}
import ${PACKAGE2}
import ${PACKAGE3}

@Service("${CLASS?uncap_first}Service")
public class ${CLASS}ServiceImpl implements ${CLASS}Service {

	@Autowired
	private ${CLASS}Mapper ${CLASS?uncap_first}Mapper;
	
	
	@Override
	public List<${CLASS}> getListBy(${CLASS} clssname) {
		return ${CLASS?uncap_first}Mapper.getListBy(clssname);
	}
	
	@Override
	public List<${CLASS}> getPageListBy(${CLASS} clssname) {
		return ${CLASS?uncap_first}Mapper.getPageListBy(clssname);
	}
		
	@Override
	public int getCountBy(${CLASS} clssname) {
		return ${CLASS?uncap_first}Mapper.getCountBy(clssname);
	}
		
	@Override
	public List<${CLASS}> getPageListLike(${CLASS} clssname) {
		return ${CLASS?uncap_first}Mapper.getPageListLike(clssname);
	}

	@Override
	public List<${CLASS}> getListLike(${CLASS} clssname) {
		return ${CLASS?uncap_first}Mapper.getListLike(clssname);
	}
	
	@Override
	public int getCountLike(${CLASS} clssname) {
		return ${CLASS?uncap_first}Mapper.getCountLike(clssname);
	}
	
	@Override
	public ${CLASS} selectByPrimaryKey(Integer id) {
		return ${CLASS?uncap_first}Mapper.selectByPrimaryKey(id);
	}

	@Override
	public ${CLASS} selectBy(${CLASS} record) {
		return ${CLASS?uncap_first}Mapper.selectBy(record);
	}
	
	@Override
	public int insert(${CLASS} record) {
		return ${CLASS?uncap_first}Mapper.insert(record);
	}
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return ${CLASS?uncap_first}Mapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int delete(${CLASS} record) {
		return ${CLASS?uncap_first}Mapper.delete(record);
	}

	@Override
	public int updateByPrimaryKey(${CLASS} record) {
		return ${CLASS?uncap_first}Mapper.updateByPrimaryKey(record);
	}

}