

var username, password, cpassword, city, address, adhar, email, mobile;


function addUser() {
    username = $ ( "#username" ).val();
    password = $ ( "#password" ).val();
    cpassword = $ ( "#cpassword" ).val();
    city = $ ( "#city" ).val();
    address = $ ( "#address" ).val();
    adhar = $ ( "#adhar" ).val();
    email = $ ("#email" ).val();
    mobile = $ ("#mobile").val();
    gender = $("input[name='gender']:checked").val();
    alert(gender);
    if (validateUser) {
        if (password !== cpassword) {
            swal("Error!", "Password and Confirm Password must be same .. ", "error");
            return;
        } else {
            if (checkEmail() === false)
                return;
            var data = {
                username: username,
                password: password,
                city: city,
                address: address,
                userid: adhar,
                email: email,
                mobile: mobile,
                gender: gender
            };
            var xhr = $.post("RegistrationControllerServlet", data, processResponse);
            xhr.fail(handleError);
        }
    }


}

function processResponse(responseText, textStatus, xhr) {
    var str = responseText.trim();
    if (str === "success") {
        swal("Success", "Registration Done Successfully You Can now Login !", "success");
        setTimeout(redirectUser(), 3000);
    } else if (str === "uap")
        swal("Error", "Sorry the userid is already present ", "error");
    else
        swal("Error", "Registration Failed ! Try Again .. ", "error");

}

function handleError(xhr) {
    swal("Error", "Problem in Server Communication : " + xhr.statusText, "error");
}

function validateUser() {
    if (username === "" || password === "" || cpassword === "" || city === "" || address === "" || adhar === "" || email === "" || mobile === "") {
        swal("Error!", "All fields are Mandatory", "error");
        return false;
    }
    return true;
}

function checkEmail() {
    var attheratepos = email.indexOf("@");
    var dotpos = email.indexOf(".");
    if (attheratepos < 1 || dotpos < attheratepos + 2 || dotpos + 2 >= email.length) {
        swal("Error!", "Please Enter a valid email .. ", "error");
        return false;
    }
    true;
}

function redirectUser() {
    window.location = "login.html";
}