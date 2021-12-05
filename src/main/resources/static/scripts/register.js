$('document').ready(function(){
    var password = document.getElementById("password-register")
    var confirmPassword = document.getElementById("confirm-password-register");

    function validatePassword(){
        if(password.value !== confirmPassword.value) {
            confirmPassword.setCustomValidity("Passwords Don't Match");
        } else {
            confirmPassword.setCustomValidity('');
        }
    }
    password.onchange = validatePassword;
    confirmPassword.onkeyup = validatePassword;
});
