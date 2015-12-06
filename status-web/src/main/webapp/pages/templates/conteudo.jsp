<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<body>

	<tiles:insertAttribute name="cabecalho" />
	
	<tiles:insertAttribute name="conteudo"/>

	<tiles:insertAttribute name="rodape"/>
</body>