
// Lab 2 validation rules:
// Customer ID and Password required
// Customer ID must be a 4-digit number (not a string) between 3000 and 3999

function validateLogin() {

    var cid;
    var pwd;

    // Get values from the form
    cid = document.loginForm.customerId.value;
    pwd = document.loginForm.password.value;

    // Check if fields are empty
    if (cid == "" || pwd == "") {
        alert("You must enter a Valid Customer ID and Password.");
        document.loginForm.customerId.focus(); //give focus to a form element (Ch6)
        return false;
    }

    // Temporary for Lab 3 Step 3: allow admin test login to get to the servlet
    if (cid.toLowerCase() === "admin") {
        return true;
    }

    // Customer ID must be a number. Determines whether a value is the special value NaN (Not a Number) (Ch2)
    if (isNaN(cid)) {
        alert("Customer ID must be a Number!");
        document.loginForm.customerId.value = "";
        document.loginForm.customerId.focus();
        return false;
    }

    // Customer ID must be 4 digits
    if (cid.length != 4) {
        alert("Customer ID must be a 4 digit number.");
        document.loginForm.customerId.value = "";
        document.loginForm.customerId.focus();
        return false;
    }

    // Customer ID must be between 3000 and 3999
    if (cid < 3000 || cid > 3999) {
        alert("Customer ID must be between 3000 and 3999.");
        document.loginForm.customerId.value = "";
        document.loginForm.customerId.focus();
        return false;
    }

    // If all checks pass
    return true;
}