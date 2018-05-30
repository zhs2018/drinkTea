package ${PACKAGE_URL};

${import_URL}

/**
 * @文件名称: ${CLASS}.java
 * @类路径: ${PACKAGE}
 * @作者：${AUTH_NAME}
 * @公司：${COMPANY_NAME}
 */
public class ${CLASS} extends BaseBean {
	private static final long serialVersionUID = 1L;

	<#list properties as prop>
	// ${prop.comment}
	private ${prop.type} ${prop.name};
 	</#list>

    <#list properties as prop>
	public ${prop.type} get${prop.name?cap_first}(){
		return ${prop.name};
	}
	public void set${prop.name?cap_first}(${prop.type} ${prop.name}){
		this.${prop.name} = ${prop.name};
	}
    </#list>

	// 排序规则
	private String orderBy;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}