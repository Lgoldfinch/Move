function submitRegGuest() {
    let eml = document.getElementById("guest-email").value;
    let pswd = document.getElementById("guest-password").value;
    let confirmpswd = document.getElementById("guest-confirm-password").value;
    let fn = document.getElementById("guest-first-name").value;
    let ln = document.getElementById("guest-last-name").value;
    let data = JSON.stringify({"email" : eml, "password" : pswd, "firstName" : fn, "lastName" :ln});
	
    if (pswd === confirmpswd) {
    }
    else {
    	return alert("Passwords do not match");
    }
    
    let requestURL = "http://localhost:8080/Move3/jaxAct/register/registerGuestAttempt";
    let xhr = new XMLHttpRequest();
    xhr.open("POST", requestURL, true);
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.send(data);
    xhr.onload = function () {
    	let reply = JSON.parse(xhr.response);
    	if (reply.register === "success") {
    		sessionStorage.setItem("accountType", 1); 
    		window.location.href = 'LoggedInPage.html';
    	}
    	else {
    		alert("You mugged it");
    	}
    }
}

function submitRegAdmin() {
    let eml = document.getElementById("admin-email").value; // need to update with first and last name. 
    let pswd = document.getElementById("admin-password").value;
    let confirmpswd = document.getElementById("admin-confirm-password").value;
    let fn = document.getElementById("admin-first-name").value;
    let ln = document.getElementById("admin-last-name").value;
    let data = JSON.stringify({"email" : eml, "password" : pswd, "firstName" : fn, "lastName" : ln});
    
    if (pswd === confirmpswd) {
    }
    else {
    	return alert("Passwords do not match");
    }
    
    let requestURL = "http://localhost:8080/Move3/jaxAct/register/registerAdminAttempt";
    let xhr = new XMLHttpRequest();
    xhr.open("POST", requestURL, true);
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.send(data);
    xhr.onload = function () {
    	let reply = JSON.parse(xhr.response);
    	if (reply.register === "success") {
    		sessionStorage.setItem("accountType", 2);
    		window.location.href = 'LoggedInPage.html';
    	}
    	else {
    		alert("You mugged it");
    	}
    }
}

function back() {
	window.location.href = 'register2.html';
}

//$('#register-as-guest-form #tab-registerAA').click(function() {
//
//			$('#register-as-guest-form').fadeToggle();
//	    	$(".form-background").animate(
//	            {
//	            	"top": "-342px",
//	            	"width": "400px",
//	            	"height": "410px"
//	            },
//	            "slow", function(){
//    				$('#register-as-admin-form').fadeToggle();
//	            });
//	   	});
//
//	 /*  	$('#register-as-admin-form #tab-registerAG').click(function() {
//
//	   		$('#register-as-admin-form').fadeToggle();
//	    	$(".form-background").animate(
//	            {
//	            	"top": "-214px",
//	            	"width": "400px",
//	            	"height": "290px"
//	            },
//	            "slow", function(){
//	                $('#register-as-guest-form').fadeToggle();
//	            });
//	   	});
//
//	   	// Tips
//	   	$('#register-as-admin-form .email-tip-icon').hover(function(){
//			$('#message-email').fadeToggle();
//		});
//
//		$('#register-as-admin-form .password-tip-icon').hover(function(){
//			$('#message-password').fadeToggle();
//		});
//
//
///*    
