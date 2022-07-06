var userid , password;
function connectUser(){
//    var a= document.getElementById("username").value;
//    var b= document.getElementById("password").value;
    userid = $("#username").val();
    password = $("#password").val();
    
    if(validate() == false){
        swal("Access Denied" , "Please enter userid/password" , "error");
        return;
    }
    
        var data = {userid:userid , password:password};
        
        var xhr = $.post("LoginControllerServlet" , data , processResponse);
        xhr.fail(handleError);
        
    
}
function processResponse(responseText){
    
    if(responseText.trim() === "error"){
         swal("Access Denied" , "Invalid userid/password" , "error");
    }
    else if(responseText.trim().indexOf("jsessionid") !== -1){
        
        swal("Success" , "Login Successfull" , "success");
        setTimeout(redirectUser(responseText), 3000);
        
    }
    else{
        swal("Access Denied" , "Some Problem Occurred"+responseText , "error");
    }
}
function handleError(xhr) {
    swal("Error", "Problem in Server Communication : " + xhr.statusText, "error");
}

function validate() {
    if (userid === "" || password === "") {
        swal("Error!", "All fields are Mandatory", "error");
        return false;
    }
    return true;
}
function redirectUser(responseText) {
    window.location = responseText.trim();
}





