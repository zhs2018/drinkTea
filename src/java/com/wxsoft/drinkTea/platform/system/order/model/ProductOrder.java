package com.wxsoft.drinkTea.platform.system.order.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.wxsoft.drinkTea.framework.base.BaseBean;
import com.wxsoft.drinkTea.framework.mybatis.plugin.CommonPage;
import com.wxsoft.drinkTea.platform.system.login.model.ManageProducts;
import com.wxsoft.drinkTea.platform.weixin.model.RedEnvelope;
import com.wxsoft.drinkTea.platform.weixin.model.RefundMessage;
import com.wxsoft.drinkTea.platform.weixin.model.UserAddress;
import com.wxsoft.drinkTea.platform.weixin.model.WebUser;

/**
 * @文件名称: ProductOrder.java
 * @类路径: com/wxsoft/drinkTea/datemodel/autocreate/model/ @作者： @公司：
 */
public class ProductOrder extends BaseBean {
	private static final long serialVersionUID = 1L;

	// 订单
	private Integer id;
	//
	private Long orderNumber;
	//
	private Integer userId;
	//
	private Integer giftBoxId;
	// 购买包数
	private Double redMoney;
	//
	private Integer count;
	// 总价格
	private Double price;
	// 订单时间
	private Date orderTime;
	// 支付时间
	private Date applyTime;
	// 发货时间
	private Date goodsTime;
	//
	private String goodsAddress;
	//
	private Integer addressId;
	//
	private Integer orderState;

	// 不通过原因
	private String noReason;

	// List集合
	List<ProductOrder> list;

	//
	private String customerMessage;
	//
	private Integer orderSerialNum;
	//
	private Integer applyMethod;
	// 临时价格
	private BigDecimal tempprice;

	//
	private Integer companyId;
	//
	private Date startTime;
	//
	private Date endTime;
	// 收货时间
	private Date acceptTime;
	// 财务id
	private Integer caiwuId;
	// //
	private Integer shouhouId;
	// 普通分页
	private CommonPage page;
	// 系统时间
	private Date sysTime;
	// //类型
	private String type;

	private WebUser webUser;

	private List<ManageProducts> products;

	private ProductOrder order;

	private UserAddress userAddress;
	// 红包
	private RedEnvelope redEnvelope;

	private ManageProducts manageProducts;

	private RefundMessage refundMessage;

	private String mpName;

	private String uaName;

	private String uaPhone;

	// 排序规则
	private String orderBy;

	private ProductOrder productOrder;

	private String sysNumber;

	private String sTime;
	// 通联订单单号
	private String tlorderNumbers;
	// 微信平台订单单号
	private String wxorderNumbers;
	// 用户使用单次红包数
	private Double useRedMoney;

  //用户免费的订单
	private Integer tabs;

	public Integer getTabs() {
		return tabs;
	}

	public void setTabs(Integer tabs) {
		this.tabs = tabs;
	}

	public String getTlorderNumbers() {
		return tlorderNumbers;
	}

	public void setTlorderNumbers(String tlorderNumbers) {
		this.tlorderNumbers = tlorderNumbers;
	}

	public String getWxorderNumbers() {
		return wxorderNumbers;
	}

	public void setWxorderNumbers(String wxorderNumbers) {
		this.wxorderNumbers = wxorderNumbers;
	}

	public Double getUseRedMoney() {
		return useRedMoney;
	}

	public void setUseRedMoney(Double useRedMoney) {
		this.useRedMoney = useRedMoney;
	}

	public List<ProductOrder> getList() {
		return list;
	}

	public void setList(List<ProductOrder> list) {
		this.list = list;
	}

	public String getNoReason() {
		return noReason;
	}

	public void setNoReason(String noReason) {
		this.noReason = noReason;
	}

	public BigDecimal getTempprice() {
		return tempprice;
	}

	public void setTempprice(BigDecimal tempprice) {
		this.tempprice = tempprice;
	}

	public WebUser getWebUser() {
		return webUser;
	}

	public void setWebUser(WebUser webUser) {
		this.webUser = webUser;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public RefundMessage getRefundMessage() {
		return refundMessage;
	}

	public void setRefundMessage(RefundMessage refundMessage) {
		this.refundMessage = refundMessage;
	}

	public ManageProducts getManageProducts() {
		return manageProducts;
	}

	public void setManageProducts(ManageProducts manageProducts) {
		this.manageProducts = manageProducts;
	}

	public RedEnvelope getRedEnvelope() {
		return redEnvelope;
	}

	public void setRedEnvelope(RedEnvelope redEnvelope) {
		this.redEnvelope = redEnvelope;
	}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public String getUaName() {
		return uaName;
	}

	public void setUaName(String uaName) {
		this.uaName = uaName;
	}

	public String getUaPhone() {
		return uaPhone;
	}

	public void setUaPhone(String uaPhone) {
		this.uaPhone = uaPhone;
	}

	public String getMpName() {
		return mpName;
	}

	public void setMpName(String mpName) {
		this.mpName = mpName;
	}

	public List<ManageProducts> getProducts() {
		return products;
	}

	public void setProducts(List<ManageProducts> products) {
		this.products = products;
	}

	public ProductOrder getOrder() {
		return order;
	}

	public void setOrder(ProductOrder order) {
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGiftBoxId() {
		return giftBoxId;
	}

	public void setGiftBoxId(Integer giftBoxId) {
		this.giftBoxId = giftBoxId;
	}

	public Double getRedMoney() {
		return redMoney;
	}

	public void setRedMoney(Double redMoney) {
		this.redMoney = redMoney;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getGoodsTime() {
		return goodsTime;
	}

	public void setGoodsTime(Date goodsTime) {
		this.goodsTime = goodsTime;
	}

	public String getGoodsAddress() {
		return goodsAddress;
	}

	public void setGoodsAddress(String goodsAddress) {
		this.goodsAddress = goodsAddress;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public String getCustomerMessage() {
		return customerMessage;
	}

	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}

	public Integer getOrderSerialNum() {
		return orderSerialNum;
	}

	public void setOrderSerialNum(Integer orderSerialNum) {
		this.orderSerialNum = orderSerialNum;
	}

	public Integer getApplyMethod() {
		return applyMethod;
	}

	public void setApplyMethod(Integer applyMethod) {
		this.applyMethod = applyMethod;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public Integer getCaiwuId() {
		return caiwuId;
	}

	public void setCaiwuId(Integer caiwuId) {
		this.caiwuId = caiwuId;
	}

	public Integer getShouhouId() {
		return shouhouId;
	}

	public void setShouhouId(Integer shouhouId) {
		this.shouhouId = shouhouId;
	}

	public CommonPage getPage() {
		return page;
	}

	public void setPage(CommonPage page) {
		this.page = page;
	}

	public Date getSysTime() {
		return sysTime;
	}

	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public ProductOrder getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(ProductOrder productOrder) {
		this.productOrder = productOrder;
	}

	public String getSysNumber() {
		return sysNumber;
	}

	public void setSysNumber(String sysNumber) {
		this.sysNumber = sysNumber;
	}

	public String getsTime() {
		return sTime;
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

}