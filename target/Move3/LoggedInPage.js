"use strict";
function guestToAdminConv() { //needs to check to see what type of account the current user is// 
    
}

function adminToGuestConv() {
    
}

function searchForTeam() {
 let tn = document.getElementById("team-search-input").value;
let tn = {teamName: tn}; 
	 jsonObj = JSON.stringify(tn);
}

//function returnTeamNames() {
//    let listOTeamsJSON = .... // need to get the Java -> json working first.
//    let listOfTeams=JSON.parse(listOTeamsJSON)
//    
//}

function createTeam() {
 let tn = document.getElementByID("team-name").value;
    let tp = document.getElementByID("team-password").value;
    let teamCreate = {teamName: tn, password: tp}
    jsonObj = JSON.stringify(teamCreate);
}