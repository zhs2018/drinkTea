<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%response.addHeader("pragma", "no-cache");
response.addHeader("cache-control", "no-cache,must-revalidate");
response.addHeader("expires", "0");%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<c:set var="basePath">${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}</c:set>