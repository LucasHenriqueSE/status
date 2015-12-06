<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<meta charset=UTF-8 />
<title>Sistema Status</title>
<script type="text/javascript">
$(document).ready(function() {
	setTimeout('location.reload();', 60000);
});
</script>
</head>
<body style="font-size: 13px;">
	<div class="container">
		<div class="row-fluid">
			<div style="float: left; margin-left: 10%">
				<img id="internet" src="/status/resources/images/internet.jpg"
					alt="internet" width="80px;" height="80px;"> <img id="barra1"
					src="/status/resources/images/barra.png" alt="barra" width="300px;"
					height="5px;"> <br>
				<h5>${internet.nome}</h5>
				<c:choose>
					<c:when test="${internet.status == '200'}">
						<img style="margin-left: 2%;"
							src="/status/resources/images/ok.png" alt="ok" width="50px;"
							height="50px;">
					</c:when>
					<c:otherwise>
						<img style="margin-left: 2%;"
							src="/status/resources/images/erro.png" alt="erro" width="50px;"
							height="50px;">
					</c:otherwise>
				</c:choose>
			</div>
			<div style="float: left; margin-left: 0%">
				<img id="fornax" src="/status/resources/images/fornax.jpg"
					alt="fornax" width="80px;" height="80px;"> <img id="barra2"
					src="/status/resources/images/barra.png" alt="barra" width="300px;"
					height="5px;"> <br>
				<h5>FORNAX</h5>
				<img style="margin-left: 2%;" src="/status/resources/images/ok.png"
					alt="ok" width="50px;" height="50px;">
			</div>
			<div style="margin-left: 0%;">
				<img id="porto" src="/status/resources/images/porto.png" alt="porto"
					width="80px;" height="80px;"> <br>
				<h5>${vpn.nome}</h5>
				<c:choose>
					<c:when test="${vpn.status == '200'}">
						<img src="/status/resources/images/ok.png" alt="ok" width="50px;"
							height="50px;">
					</c:when>
					<c:otherwise>
						<img src="/status/resources/images/erro.png" alt="erro"
							width="50px;" height="50px;">
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>