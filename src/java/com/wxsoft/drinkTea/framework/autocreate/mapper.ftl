package ${PACKAGE_URL};

import java.util.List;
import org.springframework.stereotype.Repository;
import ${PACKAGE};

/**
 * @文件名称: ${CLASS}Mapper.java
 * @类路径: ${CLASSPATH}
 * @作者：${AUTH_NAME}
 * @公司：${COMPANY_NAME}
 */
@Repository
public interface  ${CLASS}Mapper {
	// 根据条件全部查询
	List<${CLASS}> getListBy(${CLASS} clssname);

	// 根据条件分页查询
	List<${CLASS}> getPageListBy(${CLASS} clssname);

	// 查询条数
	int getCountBy(${CLASS} clssname);

	// 根据条件模糊查询全部
	List<${CLASS}> getListLike(${CLASS} clssname);

	// 根据条件模糊分页查询
	List<${CLASS}> getPageListLike(${CLASS} clssname);

	// 查询条数
	int getCountLike(${CLASS} clssname);

	// 根据主键查询实例
	${CLASS} selectByPrimaryKey(Integer id);

	// 根据所需查询实例
	${CLASS} selectBy(${CLASS} record);

	// 根据所需插入
	int insert(${CLASS} record);

	// 根据主键删除
	int deleteByPrimaryKey(Integer id);

	// 根据所需删除
	int delete(${CLASS} record);

	// 根据所需更新
	int updateByPrimaryKey(${CLASS} record);

}