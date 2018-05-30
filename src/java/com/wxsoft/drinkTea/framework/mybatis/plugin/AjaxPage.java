/**
 * 公共分类页实现
 */
package com.wxsoft.drinkTea.framework.mybatis.plugin;

import com.wxsoft.drinkTea.framework.base.BaseBean;

/**
 *
 * @类功能说明：分页公共类声明
 * @类修改者：kyz @修改日期：2013-3-19 @修改说明：
 * @公司名称：wxltsoft
 * @作者：kyzs @创建时间：2013-3-19 下午04:07:10
 */
public class AjaxPage extends BaseBean {
	private static final long serialVersionUID = -8294642605483418585L;
	private int showCount = 20; // 每页显示记录数
	private int totalPage; // 总页数
	private int totalResult; // 总记录数
	private int currentPage; // 当前页
	private int currentResult; // 当前记录起始索引
	private boolean entityOrField; // true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private StringBuffer pageStr; // 最终页面显示的底部翻页导航，详细见：getPageStr();
	private String functionName = "getComment";
	private int tagNum = 5;// 中间需要显示的标签数量
	private int defaultTagNum = 2;// 当超出标签数量时当前页两边需要显示的数量
	private String upNote = "«";// 上一页标签
	private String downNote = "»";// 下一页标签
	private boolean tiaozhuan;//true需要跳转 false不需要跳转


	public int getTotalPage() {
		if (totalResult % showCount == 0)
			totalPage = totalResult / showCount;
		else
			totalPage = totalResult / showCount + 1;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurrentPage() {
		if (currentPage <= 0)
			currentPage = 1;
		if (currentPage > getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageStr() {
		pageStr = new StringBuffer();
		if (totalResult > 0) {// 如果总记录数大于0
			int prePage = currentPage - 1 == 0 ? currentPage : currentPage - 1;
			pageStr.append("<div class=\"col-md-12 col-sm-12\"><ul class=\"pagination pull-right\">");
			if (currentPage == 1) {

			} else {
				pageStr.append("<li><a href=\"#@\" onclick=\"" + functionName + "(" + prePage + ")\">" + upNote
						+ "</a></li>\n");
			}

			for (int i = 1; i <= totalPage; i++) {
				if (i == currentPage) {
					pageStr.append("<li><span style='background-color:#EDEDED'>" + i + "</span></li>\n");
				} else if (currentPage > tagNum) {// 如果当前页大于中间要显示的标签数，如第6页大于5
					// 显示第6页前两个和第六页后两个,45 78，1 2 必须显示
					if ((i < defaultTagNum + 1) || (i >= currentPage - defaultTagNum && i < currentPage)
							|| (i > currentPage && i <= currentPage + defaultTagNum)) {
						pageStr.append("<li><a href=\"#@\"  onclick=\"" + functionName + "(" + (i) + ")\">" + (i)
								+ "</a></li>\n");
					} else if ((i > defaultTagNum && i == currentPage - defaultTagNum - 1)
							|| (i > currentPage + defaultTagNum && i == currentPage + defaultTagNum + 1)) {
						// 需要显示省略号的
						pageStr.append("<li><a href=\"#@\"  onclick=\"javascript:void(0)\">···</a></li>\n");
					}
				} else if (currentPage <= tagNum) {// 如果当前页小于中间要显示的标签数
					if (i <= tagNum + defaultTagNum) {// 需要显示1-7
						pageStr.append(
								"<li><a href=\"#@\" onclick=\"" + functionName + "(" + (i) + ")\">" + (i) + "</a>\n");
					} else if (i == tagNum + defaultTagNum + 1) {// 大于7需要显示省略号的，显示一个
						pageStr.append("<li><a href=\"#@\"  onclick=\"javascript:void(0)\">···</a></li>\n");
					}
				}
			}
			int nextPage = currentPage + 1 > totalPage ? currentPage : currentPage + 1;
			if (nextPage == currentPage) {

			} else {
				pageStr.append("<li><a href=\"#@\" class=\"selected2\" onclick=\"" + functionName + "(" + nextPage
						+ ")\">" + downNote + "</a></li>\n");
			}
			pageStr.append("<span class=\" items-info line_height \">共" + totalPage
					+ "页</span>");
			if (tiaozhuan) {
				pageStr.append(" <span class='hidden-xs hidden-sm'>到第" + "<input class='rescuelist_skipPageNum' id='" + functionName + "_skipPageNum' value='" + currentPage
						+ "'  size='3'/>" + "页");
				pageStr.append("<input  type='button' value='确定' onclick=\"isNaN(document.getElementById('"
						+ functionName + "_skipPageNum').value)?0:" + functionName + "(document.getElementById('"
						+ functionName + "_skipPageNum').value); \"/ ></span>");

			}
			pageStr.append("</div></ul></div></div> ");

		}
		return pageStr.toString();
	}

	public void setPageStr(StringBuffer pageStr) {
		this.pageStr = pageStr;
	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public int getCurrentResult() {
		currentResult = (getCurrentPage() - 1) * getShowCount();
		if (currentResult < 0)
			currentResult = 0;
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public boolean isEntityOrField() {
		return entityOrField;
	}

	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public boolean isTiaozhuan() {
		return tiaozhuan;
	}

	public void setTiaozhuan(boolean tiaozhuan) {
		this.tiaozhuan = tiaozhuan;
	}
	// public static void main(String[] args) {
	// AjaxPage pa = new AjaxPage();
	// pa.setCurrentPage(5);
	// pa.setTotalPage(100);
	// pa.setTotalResult(10);
	// System.out.println(pa.getPageStr());
	// }

	public String getUpNote() {
		return upNote;
	}

	public void setUpNote(String upNote) {
		this.upNote = upNote;
	}

	public String getDownNote() {
		return downNote;
	}

	public void setDownNote(String downNote) {
		this.downNote = downNote;
	}
}
