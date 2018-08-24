"use strict";
function createTeam() {
	let teamName = document.getElementById("team-name").value;
	let password = document.getElementById("team-password").value;
	let confirmpswd = document.getElementById("confirm-password").value;
	let data = JSON.stringify({"teamName" : teamName, "password" : password});


	if (password === confirmpswd) {
	}
	else {
		return alert("Passwords do not match");
	}

	if (sessionStorage.accountType === "2") {
		let requestURL = "http://localhost:8080/Move3/jaxAct/team/createTeam";
		let xhr = new XMLHttpRequest();
		xhr.open("POST", requestURL, true);
		xhr.setRequestHeader('Content-Type','application/json');
		xhr.send(data);
		xhr.onload = function () {
			let reply = JSON.parse(xhr.response);
			localStorage.setItem("teamID", reply.teamID);
			alert("Team creation successful!");
		}
	}
	else { 
		return alert("Can't create team as you do not have the minerals");
	}
}