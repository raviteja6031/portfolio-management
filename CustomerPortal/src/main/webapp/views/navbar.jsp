<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">

<head>
<title>MONEyeASY - Home</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
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
<style>
#home {
	font-size: 100%;
}

}
table {
	background-color: rgb(255, 255, 255, 2) !important;
}

.imgLogo{
	height: 12%;
	width: 12%;
}
</style>
</head>

<body>

	<div class="container-fluid bg-light">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="#"><img src="https://static.wixstatic.com/shapes/4244a0fbdc6a458590c532a40a6140bb.svg" alt="Moneyeasy" class="imgLogo"> Mone<span style="color:lightgray">ye</span>asy</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link"
						href="/welcome"><span class="material-icons" id="home">
								home </span>&nbsp;Home <span class="sr-only">(current)</span></a></li>
								<li class="nav-item"><a class="nav-link" href="#">Your assets </a></li>
					<li class="nav-item"><a class="nav-link" href="/sellAssets">Sell your assets
					</a></li>
					<!-- <li class="nav-item"><a class="nav-link" href="/yourBookings">Your
							Bookings</a></li>
					<li class="nav-item"><a class="nav-link" href="/checkBalance">Your
							Wallet</a></li> -->
					<li class="nav-item"><a class="nav-link" href="/logout">Logout
					</a></li>
				</ul>
			</div>
		</nav>
	</div>
	
	<!-- <div class="y">
	<img class="img-responsive" src="https://media.istockphoto.com/photos/woman-working-at-home-picture-id1256311279?b=1&k=20&m=1256311279&s=170667a&w=0&h=GWkaxCGztPpFawBf_AlPX3NL7FS7snUKW8OM-PpeHT0="> 
	</img>
	</div> -->
</body>
</html>