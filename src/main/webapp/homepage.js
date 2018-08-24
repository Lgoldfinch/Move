"use strict";
function login() {
	let eml = document.getElementById("login-email").value;
	let pswd = document.getElementById("login-password").value;
	let data = JSON.stringify({"email" : eml, "password" : pswd});

	let requestURL = "http://localhost:8080/Move3/jaxAct/login/loginAttempt";
	let xhr = new XMLHttpRequest();
	xhr.open("POST", requestURL, true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(data);
	xhr.onload = function () {
		let reply = JSON.parse(xhr.response);
		if (reply.login === "success") {
			sessionStorage.setItem("accountType", reply.accountType);
			window.location.href = 'LoggedInPage.html';
		}
		else {
			alert("Login failed, try a new email and password");
		}
	}
}
