<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${namespace}Mapper">
	<resultMap type="${modelpath}${className}" id="${className?cap_first}Map">
		<id property="${PRIMARY}" column="${PK_colum}" />
		<#list properties as prop>
		<#if prop.property?index_of("ForIn")==-1>
		<result property="${prop.property}" column="${prop.column}" />
		</#if>
 		</#list>
	</resultMap>

	<sql id="all${className}Items">
		${selectALl}
	</sql>

	<sql id="all${className}ByConditions">
		<#list properties as prop>
		<#if prop.column?index_of("END_TIME")!=-1>
		<if test="${prop.property} != null"> (tmp.${prop.column} is null or tmp.${prop.column} ${r'&gt;'}= ${r'#'}{${prop.property}}) AND</if>
		<#elseif prop.column?index_of("START_TIME")!=-1>
        <if test="${prop.property} != null"> (tmp.${prop.column} is null or tmp.${prop.column} ${r'&lt;'}= ${r'#'}{${prop.property}}) AND</if>
		<#elseif prop.property?index_of("ForIn")!=-1>
        <if test="${prop.property} != null"> tmp.${prop.column} in (${r'$'}{${prop.property}}) AND</if>
		<#else>
        <if test="${prop.property} != null"> tmp.${prop.column} = ${r'#'}{${prop.property}} AND</if>
        </#if>
	 	</#list>
	</sql>

	<select id="getListBy" parameterType="${modelpath}${className}" resultMap="${className?cap_first}Map">
		SELECT <include refid="all${className}Items" /> from ${table} tmp
		<trim prefix="WHERE" prefixOverrides="AND" suffixOverrides ="AND">
			<include refid="all${className}ByConditions" />
 		</trim>
 	    <choose>
	 		<when test="orderBy != null and orderBy != ''">
	 			ORDER BY ${r'${orderBy}'}
	 		</when>
	 		<otherwise>
	 		 	ORDER BY ${PK_colum} DESC
	 		</otherwise>
 		</choose>
	</select>

	<select id="getPageListBy" parameterType="${modelpath}${className}" resultMap="${className?cap_first}Map">
		SELECT <include refid="all${className}Items" /> from ${table} tmp
		<trim prefix="WHERE" prefixOverrides="AND" suffixOverrides ="AND">
			<include refid="all${className}ByConditions" />
 		</trim>
 		<choose>
	 		<when test="orderBy != null and orderBy != ''">
	 			ORDER BY ${r'${orderBy}'}
	 		</when>
	 		<otherwise>
	 		 	ORDER BY ${PK_colum} DESC
	 		</otherwise>
 		</choose>
	</select>

	<select id="getCountBy" parameterType="${modelpath}${className}" resultType="java.lang.Integer">
		SELECT count(*) from ${table} tmp
		<trim prefix="WHERE" prefixOverrides="AND" suffixOverrides ="AND">
			<include refid="all${className}ByConditions" />
 		</trim>
	</select>

	<sql id="all${className}LikeConditions">
		<#list properties as prop>
		<#if prop.property?index_of("ForIn")!=-1>
        <if test="${prop.property} != null"> tmp.${prop.column} in ${r'$'}{${prop.property}} AND</if>
        <#else>
		<if test="${prop.property} != null">${prop.column} like '%${r'$'}{${prop.property}}%' AND </if>
        </#if>
	 	</#list>
	</sql>

	<select id="getListLike" parameterType="${modelpath}${className}" resultMap="${className?cap_first}Map">
		SELECT <include refid="all${className}Items" /> from ${table} tmp
		<trim prefix="WHERE" prefixOverrides="AND" suffixOverrides ="AND">
			<include refid="all${className}LikeConditions" />
 		</trim>
 	    <choose>
	 		<when test="orderBy != null and orderBy != ''">
	 			ORDER BY ${r'${orderBy}'}
	 		</when>
	 		<otherwise>
	 		 	ORDER BY ${PK_colum} DESC
	 		</otherwise>
 		</choose>
	</select>

	<select id="getPageListLike" parameterType="${modelpath}${className}" resultMap="${className?cap_first}Map">
		SELECT <include refid="all${className}Items" /> from ${table} tmp
		<trim prefix="WHERE" prefixOverrides="AND" suffixOverrides ="AND">
			<include refid="all${className}LikeConditions" />
 		</trim>
 		<choose>
	 		<when test="orderBy != null and orderBy != ''">
	 			ORDER BY ${r'${orderBy}'}
	 		</when>
	 		<otherwise>
	 		 	ORDER BY ${PK_colum} DESC
	 		</otherwise>
 		</choose>
	</select>

	<select id="getCountLike" parameterType="${modelpath}${className}" resultType="java.lang.Integer">
		SELECT count(*) from ${table} tmp
		<trim prefix="WHERE" prefixOverrides="AND" suffixOverrides ="AND">
			<include refid="all${className}LikeConditions" />
 		</trim>
	</select>

	<select id="selectByPrimaryKey" parameterType="Integer"	resultMap="${className?cap_first}Map">
			SELECT <include refid="all${className}Items" /> FROM ${table} tmp WHERE tmp.${PK_colum} = ${r'#{id}'}
	</select>

	<select id="selectBy" parameterType="${modelpath}${className}" resultMap="${className?cap_first}Map">
		SELECT <include refid="all${className}Items" /> FROM ${table} tmp
		<trim prefix="WHERE" prefixOverrides="AND" suffixOverrides ="AND">
			<include refid="all${className}ByConditions" />
 		</trim>
	</select>

	<insert id="insert" parameterType="${modelpath}${className}" useGeneratedKeys="true" keyProperty="${PRIMARY}">
		INSERT INTO ${table}
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list properties as prop>
			<if test="${prop.property} != null">${prop.column},</if>
	 		</#list>
		</trim>
		values
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list properties as prop>
			<if test="${prop.property} != null">${r'#'}{${prop.property}},</if>
	 		</#list>
		</trim>
	</insert>

	<delete id="deleteByPrimaryKey" parameterType="${modelpath}${className}">
		<![CDATA[delete from ${table} where ${PK_colum}=${r'#{id}'}]]>
	</delete>

	<delete id="delete" parameterType="${modelpath}${className}">
		delete from ${table}
		<trim prefix="WHERE" prefixOverrides="AND" suffixOverrides ="AND">
			<#list properties as prop>
            <if test="${prop.property} != null"> ${prop.column} = ${r'#'}{${prop.property}} AND</if>
            </#list>
 		</trim>
	</delete>

	<update id="updateByPrimaryKey" parameterType="${modelpath}${className}">
		update ${table}
		<set>
			<#list properties as prop>
			<if test="${prop.property} != null">${prop.column}=${r'#'}{${prop.property}},</if>
	 		</#list>
		</set>
		where ${PK_colum} = ${r'#'}{${PRIMARY}}
	</update>

</mapper>