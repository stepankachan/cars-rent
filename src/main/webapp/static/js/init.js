
function showLoginError(){
    Materialize.toast('I am a toast!', 4000);
}

// Initialize collapse button
$(".button-collapse").sideNav();
// Initialize collapsible (uncomment the line below if you use the dropdown variation)
//$('.collapsible').collapsible();


function showMenu()
{
    document.getElementById("leftNavButton").click(); // Simulates button click
    document.submitForm.submit(); // Submits the form without the button
}

function getUserDetails(ssoId) {
    var form = document.getElementById('infoForm');
    var url = '/user-details-' + ssoId;
    form.setAttribute('action',url);
    form.submit();
}
