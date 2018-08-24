"use strict";
function createTeam() {
	let teamName = document.getElementById("team-name").value;
	let password = document.getElementById("team-password").value;
	let confirmpswd = document.getElementById("confirm-password").value;

	if (pswd === confirmpswd) {
	}
	else {
		return alert("Passwords do not match");
	}
		if (sessionStorage.accountType = 1) {
			return alert("Can't create team as you do not have the minerals");
		}
		else {
		}
		let requestURL = "http://localhost:8080/Move3/jaxAct/register/registerGuestAttempt";
	    let xhr = new XMLHttpRequest();
	    xhr.open("POST", requestURL, true);
	    xhr.setRequestHeader('Content-Type','application/json');
	    xhr.send(data);
	}