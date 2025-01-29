<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">

<head>
<title>MONEyeASY - Home</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
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

.welcomeMsg {
	margin-left: 2%;
	color: gray;
}

.toast {
	height: 7%;
	margin-bottom: 2.5%;
	margin-left: 40%;
	text-align: center;
	margin-bottom: 2.5%;
}

#alignmentCondn {
	margin-left: 2%;
	margin-bottom: 5%;
}

.cardRowH {
	height: 100% !important;
	width: 100% !important;
	padding-right: -20%;
	margin-left: -16%;
	margin-right: -20%;
}

.cardRowH1 {
	height: 100% !important;
	width: 80% !important;
	padding-right: 1%;
	margin-right: 10%;
}

.innerCard {
	padding: -10%;
	margin: -2%;
	background-color: rgb(255, 255, 255, 0.8);
	border-color: gray;
}

body {
	background-color: #e4e4e4;
	/* 	background-image: 
	url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTbeEsFZLRIPuhtcwRwCpSmw631Zh9l17LWww&usqp=CAU');
	background-repeat: no-repeat;
	background-size: cover; */
}

#card1 {
	background-image:
		url('https://images.unsplash.com/photo-1567427017947-545c5f8d16ad?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8ZmluYW5jZXxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60');
	max-height: 30rem !important;
}

#card2 {
	background-image:
		url('https://images.unsplash.com/photo-1427751840561-9852520f8ce8?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fGZpbmFuY2V8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60');
	max-height: 30rem !important;
}

#card3 {
	background-image:
		url('https://media.istockphoto.com/photos/happy-couple-with-laptop-spending-time-together-at-home-picture-id1285914519?b=1&k=20&m=1285914519&s=170667a&w=0&h=GpoBNqgcH4edkC5Db2Sdn699njEf9ShQF_02p1Fd-Ns=');
	max-height: 30rem !important;
}

#sellBtn {
	float: right;
	margin-right: 3.2%;
}

.carousel {
	/* any dimensions are fine, it can be responsive with max-width */
	width: 100%;
	height: 100%;
}

.carousel-inner {
	/* make sure your .items will get correct height */
	height: 100%;
}

.slidingImg {
	background-size: cover;
	background-position: 50% 50%;
	width: 100%;
	height: 100%;
	visibility: hidden;
}

.item {
	visibility: hidden;
}

.divide2 {
	margin-top: 2%;
	margin-bottom: 3%;
}

.lastDivide {
	background-color: rgb(250, 250, 250);
	padding-top: 1%;
}

.lastDivide2 {
	background-color: rgb(250, 250, 250);
	padding-top: 1%;
}

.icon-size {
	color: gray;
}

#aboutUs {
	/* background-image: url('aboutUs.jpg');
	background-repeat: no-repeat;
	background-size: cover; */
	background-color: light-gray;
	padding-bottom: 5%;
}

.whyMutual {
	background-color: white;
	padding-top: 1%;
	padding-bottom: 1%;
	padding-left: 3%;
}

.whyMutualHeading {
	margin-left: 26%;
}

.whyus {
	background-color: #88aad0;
	padding-top: 1%;
	padding-bottom: 1%;
	padding-left: 3%;
	color: white;
}

.whyusHeading {
	margin-left: 29%;
	color: white;
}

.faceCard {
	margin-left: 8% !important;
	height: 20%;
	margin-right: 0.9% !important;
}

.faceCard1 {
	margin-left: 1% !important;
	height: 20%;
	margin-right: 0.9% !important;
}

.card-topsive {
	height: auto;
	width: 70%;
}

#heading {
	color: black;
	padding-left: 33.8% !important;
}

#passwd, #uname {
	background-color: white;
	width: 20rem !important;
	color: black;
	/* 	float:left; */
	padding-right: 0rem;
	margin-right: 0rem;
	font-size: larger;
}

#passwd, #uname {
	border-color: black;
}

.passwdLbl {
	color: black;
	font-size: larger;
	/* float: left;*/
	/* text-align: inherit; !important; */
	text-align: center !important;
}

.carousel-caption {
	top: 0;
	bottom: auto;
	left: auto;
	margin-left: 5%;
	margin-top: 5%;
	margin-right: 0.7%;
	width: 28%;
	align-content: center !important;
	background-color: rgb(255, 255, 255);
	text-align: center !important;
}

.submitButton {
	border-color: gray;
	width: 100%;
	margin-left: 5.5rem !important;
	font-weight: 640;
	height: 3rem !important;
	text-align: center !important;
}

form {
	justify-content: center;
	display: inline-block;
}

#home {
	font-size: 100%;
}

}
table {
	background-color: rgb(255, 255, 255, 2) !important;
}

.imgLogo {
	height: 12%;
	width: 12%;
}

.error {
	background-color: rgb(255, 255, 255) !important;
	color: #5cb85c !important;
	padding-left: 2.5rem;
	border-radius: 2%;
	/* padding-top: 1%;
	padding-bottom: 1%; */
	width: 20rem !important;
	height: 3.5rem !important;
	/* 	text-align: center !important; */
	text-align: center !important;
	border-width: 0.2rem;
	border-color: #5cb85c;
}

#myBtn {
	display: none;
	position: fixed;
	bottom: 8px;
	z-index: 99;
	border: none;
	outline: 0;
	background-color: #3b72bf;
	color: #fff;
	cursor: pointer;
	padding: 14px;
	font-size: 16px;
	border-radius: 20px;
	-webkit-transition-duration: .4s;
	transition-duration: .4s;
	cursor: pointer;
	text-decoration: none;
	overflow: hidden;
	margin-left: 85%;
	
}
</style>
</head>

<body>
	<div class="container-fluid bg-light">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="#"><img
				src="https://static.wixstatic.com/shapes/4244a0fbdc6a458590c532a40a6140bb.svg"
				alt="Moneyeasy" class="imgLogo"> Mone<span
				style="color: lightgray">ye</span>asy</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link"
						href="/loginForm"><span class="material-icons" id="home">
								home </span>&nbsp;Home <span class="sr-only">(current)</span></a></li>
					<li class="nav-item"><a class="nav-link" href="#">Sell
							your assets </a></li>
							<li class="nav-item"><a class="nav-link" href="#whyMutual">Why
							Invest? </a></li>
							<li class="nav-item"><a class="nav-link" href="#whyus">Why
							Us? </a></li>
					<li class="nav-item"><a class="nav-link" href="#aboutUs">About
							Us </a></li>
					<li class="nav-item"><a class="nav-link" href="#">Logout </a></li>
				</ul>
			</div>
		</nav>
	</div>
	<button onclick="topFunction()" id="myBtn" style="display: block; float: right;">
		SCROLL TO TOP</button>
	<script>
		//Get the button
		var mybutton = document.getElementById("myBtn");

		// When the user scrolls down 20px from the top of the document, show the button
		window.onscroll = function() {
			scrollFunction()
		};

		function scrollFunction() {
			if (document.body.scrollTop > 20
					|| document.documentElement.scrollTop > 20) {
				mybutton.style.display = "block";
			} else {
				mybutton.style.display = "none";
			}
		}

		// When the user clicks on the button, scroll to the top of the document
		function topFunction() {
			document.body.scrollTop = 0;
			document.documentElement.scrollTop = 0;
		}
	</script>
	<div id="carouselExampleIndicators" class="carousel slide"
		data-ride="carousel">
		<!-- <ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		</ol> -->
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img class="d-block w-100"
					src="https://wallpapercave.com/wp/wp2164154.jpg" alt="First slide">
				<div class="carousel-caption rounded">
					<div class="row">
						<div class="col-md-6 col-sm-12 col-xs-12 col-lg-8 display-4"
							id="heading">Login</div>
					</div>
					<!-- <h4>${msg }</h4>	-->
					<BR>
					<form action="/login" method="post">
						<div class="form-row">
							<div class="col-md-6 col-sm-12 col-xs-12 col-lg-6">
								<div class="form-group">
									<label class="passwdLbl">Username </label> <input type="text"
										class="form-control" name="username" id="uname"
										placeholder="Enter username">
								</div>
							</div>
						</div>

						<div class="form-row">
							<div class="col-md-6 col-sm-12 col-xs-12 col-lg-6">
								<div class="form-group">
									<label class="passwdLbl">Password </label> <input
										type="password" class="form-control" name="password"
										id="passwd" placeholder="Enter password">
								</div>
								<c:if test="${not empty msg}">
									<div class="alert alert-danger error"
										style="color: #5cb85c !important;" role="alert">${msg }
									</div>
								</c:if>
							</div>
						</div>

						<div class="form-row">
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<BR> <input class="submitButton rounded" type="submit"
										value="Login" />
									<!-- <a href="/registrationForm">New User?</a> -->
								</div>
							</div>
						</div>
					</form>
				</div>

			</div>
			<div class="carousel-item">
				<img class="d-block w-100"
					src="https://wallpapercave.com/wp/wp2446334.jpg" alt="Second slide">
				<div class="carousel-caption d-none d-md-block">
					<div class="row">
						<div class="col-md-6 col-sm-12 col-xs-12 col-lg-8 display-4"
							id="heading">Login</div>
					</div>
					<!-- <h4>${msg }</h4>	-->
					<BR>
					<form action="/login" method="post">
						<div class="form-row">
							<div class="col-md-6 col-sm-12 col-xs-12 col-lg-6">
								<div class="form-group">
									<label class="passwdLbl">Username </label> <input type="text"
										class="form-control" name="username" id="uname"
										placeholder="Enter username">
								</div>
							</div>
						</div>

						<div class="form-row">
							<div class="col-md-6 col-sm-12 col-xs-12 col-lg-6">
								<div class="form-group">
									<label class="passwdLbl">Password </label> <input
										type="password" class="form-control" name="password"
										id="passwd" placeholder="Enter password">
								</div>
								<c:if test="${not empty msg}">
									<div class="alert alert-danger error" role="alert">${msg }
									</div>
								</c:if>
							</div>
						</div>

						<div class="form-row">
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<BR> <input class="submitButton rounded" type="submit"
										value="Login" />
									<!-- <a href="/registrationForm">New User?</a> -->
								</div>
							</div>
						</div>
					</form>
				</div>

			</div>
			<div class="carousel-item">
				<img class="d-block w-100"
					src="https://wallpapercave.com/wp/wp2446322.jpg" alt="Third slide">
				<div class="carousel-caption d-none d-md-block">
					<div class="row">
						<div class="col-md-6 col-sm-12 col-xs-12 col-lg-8 display-4"
							id="heading">Login</div>
					</div>
					<!-- <h4>${msg }</h4>	-->
					<BR>
					<form action="/login" method="post">
						<div class="form-row">
							<div class="col-md-6 col-sm-12 col-xs-12 col-lg-6">
								<div class="form-group">
									<label class="passwdLbl">Username </label> <input type="text"
										class="form-control" name="username" id="uname"
										placeholder="Enter username">
								</div>
							</div>
						</div>

						<div class="form-row">
							<div class="col-md-6 col-sm-12 col-xs-12 col-lg-6">
								<div class="form-group">
									<label class="passwdLbl">Password </label> <input
										type="password" class="form-control" name="password"
										id="passwd" placeholder="Enter password">
								</div>
								<c:if test="${not empty msg}">
									<div class="alert alert-danger error" role="alert">${msg }
									</div>
								</c:if>
							</div>
						</div>

						<div class="form-row">
							<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
								<div class="form-group">
									<BR> <input class="submitButton rounded" type="submit"
										value="Login" />
									<!-- <a href="/registrationForm">New User?</a> -->
								</div>
							</div>
						</div>
					</form>
				</div>

			</div>
		</div>
		<!-- <a class="carousel-control-prev" href="#carouselExampleIndicators"
			role="button" data-slide="prev"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a> -->
	</div>

	<div class="container-fluid whyMutual" id="whyMutual">
		<%-- Your networth is ${networth } INR. <BR> <BR> --%>
		<div class="row whyMutual">
			<div class="display-4 whyMutual whyMutualHeading">Why invest
				your money?</div>
			<BR>
			<div>
				<p>
					<BR>
				<h5>What are the benefits of investing in stocks?</h5>
				<UL>
					<LI>The potential to earn higher returns than alternatives
						like bank CDs, gold, and government bonds.
					<LI>The ability to protect your wealth from inflation, as the
						returns often significantly outpace the rate of inflation.
					<LI>The ability to own a tiny slice of a company whose
						products or services you love.
					<LI>The ease of buying and selling, which makes stocks a more
						liquid investment compared to other options like real estate.
				</UL>
				<BR>
				<h5>What are the benefits of investing in mutual funds?</h5>
				<UL>
					<LI>Mutual funds offer investors a great way to diversify
						their holdings instantly.
					<LI>Unlike stocks, investors can put a small amount of money
						into one or more funds and access a diverse pool of investment
						options. <br>So you can buy units in a mutual fund that
						invests in as many as 20 to 30 different securities.
					<LI>The ability to own a tiny slice of a company whose
						products or services you love.
					<LI>The ease of buying and selling, which makes stocks a more
						liquid investment compared to other options like real estate.
				</UL>
				</p>
			</div>
		</div>

		<%-- <div class="container-fluid">
		<p class="welcomeMsg display-4">Hi ${username },
		<span id="sellBtn"> 
		<a class="btn btn-lg btn-light" href="/sellAssets">Sell your assets</a>
		 </span>
		</p>
	</div> --%>
	</div>
	<div class="container-fluid whyus" id="whyus">
		<%-- Your networth is ${networth } INR. <BR> <BR> --%>
		<div class="row whyus">
			<div class="display-4 whyus whyusHeading">Why invest with us?</div>
			<BR> <BR> <BR> <BR> <BR> <span
				style="font-size: x-large; line-height: 1.7; padding-bottom: 1.8%; padding-top: 1.5%;">
				<UL>
					<LI>We judge <span style="font-weight: bold;"> OUR
							SUCCESS</span> by how much <span style="font-weight: bold;">MONEY
							YOU MAKE.</span></LI>
					<LI>We have a platform for every kind of investor.</LI>
					<LI>We simplify everything for you in simple English.</LI>
					<LI>Track your networth anytime, anywhere.</LI>
					<LI>Easy selling options. Just click on sell and we will buy
						it for you.</LI>
					<LI>Convenient to use.</LI>
					<LI>We are technology driven.</LI>
					<LI>We have experienced people working behind the scenes to
						grow your money and suggest you about the <BR>investments
						that will return best profits.
					</LI>
				</UL>
		</div>
	</div>

	<div class="container-fluid ">
		<div class="row" id="aboutUs">
			<span style="padding-top: 2%; padding-left: 43%;"><div
					class="col display-4 aboutUsHeading">About Us</div></span>

			<div class="row">
				<BR> <BR> <span
					style="padding-left: 8%; font-size: larger; padding-right: 10%; padding-bottom: 2.5%;">It
					started with a team of five - 4 engineers having a great CA friend.
					One who led us into this, <span style="font-weight: bold;">
						SHREYA</span>. One techie who wouldn't leave his laptop even on trips, <span
					style="font-weight: bold;"> RAVITEJA</span>. One with passion for
					designing but became an engineer mistakenly, our website designer,
					<span style="font-weight: bold;"> ARSHITA</span>. One with all the
					brains behind our website - Miss Advisor. <span
					style="font-weight: bold;"> SONY</span>. To bring this together we
					needed funds and someone who can convey our idea correctly to all.<span
					style="font-weight: bold;"> MANOJ</span>.<BR> <BR> And
					this was the output,<span style="font-weight: bold;">
						MONEYEASY</span>.
				</span>
			</div>
			<div class="row ">
				<div class="col-lg-2 faceCard">
					<div class="card"
						style="width: 14.5rem; height: 22.5rem; padding-right: 0%; margin-right: 0.5% !important;">
						<br> <img class="card-img-top rounded img-responsive"
							src="https://media-exp1.licdn.com/dms/image/C4E03AQGuY57aG32UTQ/profile-displayphoto-shrink_800_800/0/1594824022774?e=1638403200&v=beta&t=TdE1UuHb2mZi-iZ-I_6jCTj62Swff6lIFgZHxZ_jCwk"
							alt="Our CEO">
						<div class="card-body">
							<h5 class="card-title">Shreya Agrawal</h5>
							<p class="card-text">CEO</p>
						</div>
					</div>
				</div>
				<div class="col-lg-2 faceCard1">
					<div class="card"
						style="width: 14.5rem; height: 22.5rem; padding-right: 2.5%; margin-right: 1%; padding-left: 2.5%;">
						<br> <img class="card-img-top rounded img-responsive"
							src="raviteja.jpg" alt="Our Developer">
						<div class="card-body">
							<h5 class="card-title">Raviteja Reddy</h5>
							<p class="card-text">Developer</p>
						</div>
					</div>
				</div>
				<div class="col-lg-2 faceCard1">
					<div class="card" style="width: 14.5rem; height: 22.5rem;">
						<br> <img class="card-img-top rounded img-responsive"
							src="arshita.jpg" alt="Our Website Designer">
						<div class="card-body">
							<h5 class="card-title">Arshita Garg</h5>
							<p class="card-text">Website Designer</p>
						</div>
					</div>
				</div>
				<div class="col-lg-2 faceCard1">
					<div class="card"
						style="width: 14.5rem; padding-right: 2.5%; margin-right: 1%; padding-left: 2.5%; height: 22.5rem;">
						<br> <img class="card-img-top rounded img-responsive"
							src="sony.jpg" alt="Our Financial Advisor">
						<div class="card-body">
							<h5 class="card-title">Sony Bhashyam</h5>
							<p class="card-text">Financial Advisor</p>
						</div>
					</div>
				</div>
				<div class="col-lg-2 faceCard1">
					<div class="card"
						style="width: 14.5rem; padding-right: 2.5%; margin-right: 1%; padding-left: 2.5%; height: 22.5rem;">
						<br> <img class="card-img-top rounded img-responsive"
							src="manoj.jpg" alt="Our Communications and Marketing Head">
						<div class="card-body">
							<h5 class="card-title">Manoj Behera</h5>
							<p class="card-text">Communications and Marketing Head</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid lastDivide">
		<div class="row lastDivide">
			<div class="col-12 col-sm-12 col-md-12 col-lg-12 lastDivide"></div>
			<p style="padding-left: 35%; font-weight: bold; font-size: 25px;">
				Moneyease Financial Advisors Pvt. Ltd.</p>
			<p style="padding-left: 38.5%;">Toll Free: 1 (855) 866-8089 |
				Fax: (562) 392-8218
			<p>
		</div>
		<div class="lastDivide"
			style="padding-left: 47%; padding-top: 0.5%; padding-bottom: 0.5%;">
			<a href="https://www.facebook.com/" class="lastDivide"><i
				class="material-icons" class="icon-size">facebook</i></a> <a
				href="https://accounts.google.com/"><i class="material-icons"
				class="icon-size">mail</i></a> <a href="#"><i class="material-icons"
				class="icon-size">phone</i></a>

		</div>
	</div>

	<div class="row lastDivide2">
		<div class="col-12 col-sm-12 col-md-12 col-lg-12"
			style="background-color: #88aad0;">
			<p style="padding-left: 38.5%; padding-top: 1%;">©2021 by
				Moneyease Financial Advisors Pvt. Ltd.</p>
		</div>
	</div>
</body>

</html>

<%-- <div class="container-fluid">
		<p class="welcomeMsg display-4">Hi ${username },
		<span id="sellBtn"> 
		<a class="btn btn-lg btn-light" href="/sellAssets">Sell your assets</a>
		 </span>
		</p>
	</div> --%>