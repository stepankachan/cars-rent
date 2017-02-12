$(document).ready(function(){
    $('.modal').modal();
    $('select').material_select();
    $('.tooltipped').tooltip({delay: 50});
    $(".no-sorting").off("click");

    var requestTable =  $("#requests-table");
    requestTable.tablesorter();
    requestTable.DataTable();

    var activitiesTable =  $("#activities-table");
    activitiesTable.tablesorter();
    activitiesTable.DataTable();

    var usersTable =  $("#users-table");
    usersTable.tablesorter();
    usersTable.DataTable();
});

function getUserDetails(ssoId) {
    var form = document.getElementById('infoForm');
    var url = '/user-details-' + ssoId;
    form.setAttribute('action',url);
    form.submit();
}

function getFromDate() {
    return document.getElementById('from-date').innerHTML;
}