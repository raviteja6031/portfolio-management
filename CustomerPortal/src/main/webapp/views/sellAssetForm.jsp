<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<title>MONEyeASY - Home</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css"
	rel="stylesheet" type="text/css" />
<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js"
	type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$('.toast').toast('show');
	});
	$.toast({
		delay : 100000
	});
</script>
<style>
#home {
	font-size: 100%;
}

#alignmentCondn {
	margin: 2%;
}

body {
	/* background-color: #5781a9; */
	background-image: url('https://wallpapercave.com/wp/wp6611148.jpg');
	background-repeat: no-repeat;
	background-size: cover;
}

/* .container-fluid,.row{
	border-top-color: black background;
} */
.submitButton {
	border-color: gray;
	width: 100%;
	margin-left: 37%;
	font-weight: 640;
	height: 45px !important;
	background-color: #88aad0;
	/* 	background-color: #5882b1; */
	color: white;
	font-size: large;
}

a:link, a:visited {
	color: black;
}

a:hover {
	text-decoration: none;
	text-shadow: 10%;
}

table {
	width: 150% !important;
	margin-left: 18% !important;
	background-color: #5882aa;
	/* table-layout: inherit; */
}

.alignTable {
	background-color: rgb(255, 255, 255, 0.7);
	width: 100%;
}

.alignTable1 {
	white-space: nowrap;
	background-color: rgb(255, 255, 255, 0.7);
	width: 100%;
}

.alignTable2 {
	white-space: nowrap;
	background-color: rgb(255, 255, 255, 0.7);
	width: 100%;
}

th, td {
	font-weight: bold;
	color: black;
}
</style>
</head>

<body>

	<jsp:include page="navbar.jsp"></jsp:include>

	<div class="container-fluid" id="alignmentCondn">
		<div class="row">



			<form action="/soldAssets" onSubmit=" return checker();"
				method="post">

				<table class="table">
					<caption>For selling assets</caption>
					<thead class="thead-light">
						<tr>
							<th scope="col" class="alignTable">Names of shares owned by
								you</th>
							<th scope="col" class="alignTable2">Units owned</th>
							<th scope="col" class="alignTable1">Units you wish to sell</th>
						</tr>
					</thead>


					<div class="form-row">
						<div class="form-group">
							<!-- 	<tbody> -->
							<c:forEach items="${shareDetails }" var="shareDetail">
								<tr>
									<td scope=row class="alignTable"><input type="checkbox"
										value="${shareDetail.shareName}" name="selected">&nbsp;&nbsp;&nbsp;${shareDetail.shareName }
									</td>
									<td scope=row class="alignTable2">&nbsp;${shareDetail.count }</td>
									<td scope=row class="alignTable1"><input type="number"
										id="quantity" name="quantity" value="0" min="0"
										max="${shareDetail.count}"></td>
							</c:forEach>
						</div>
					</div>

					<div class="form-row">
						<div class="form-group">
							<!-- 	<tbody> -->
							<c:forEach items="${mutualFundDetails }" var="mutualFundDetail">
								<tr>
									<td scope=row class="alignTable"><input type="checkbox"
										value="${mutualFundDetail.mutualFundName}" name="selected">&nbsp;&nbsp;&nbsp;${mutualFundDetail.mutualFundName }
									</td>
									<td scope=row class="alignTable2">&nbsp;${mutualFundDetail.count }</td>
									<td scope=row class="alignTable1"><input type="number"
										id="quantity" name="quantity" value="0" min="0"
										max="${mutualFundDetail.count}"></td>
							</c:forEach>
						</div>
					</div>

				</table>

				<div class="form-row">
					<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
						<div class="form-group">
							<BR> <input class="submitButton rounded" type="submit"
								value="SELL" /> <BR>
							<!-- <BR> <a href="/loginForm">Old User?</a> -->
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		var a = document.getElementById("quantity")
		function checker() {
			var count = 0;
			var l = document.getElementsByName("selected");
			for (var i = 0; i < l.length; i++) {
				if (l[i].checked)
					count++;
			}
			//console.log(count);
			if (count == 0) {
				alert("Please select a value");
				return false;
			} else
				return true;
		}
	</script>

</body>

</html>