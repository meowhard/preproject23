function getUser() {
    $.get('/get_authorized_user', function (user_data) {
        console.log(user_data);

        let table = "<tr>" +
            "<td>" + user_data.id + "</td>" +
            "<td>" + user_data.username + "</td>" +
            "<td>" + user_data.age + "</td>" +
            "<td>" + user_data.email + "</td>" +
            "<td>" + user_data.roles + "</td>" +
            "</tr>";

        $("#user_table").html(table);
    });
}

function getAllUsers() {
    $.get('/get_all_users', function (users_data) {
        console.log(users_data);

        let table = "";

        for (i = 0; i < users_data.length; i++) {
            table = table + "<tr>" +
                "<td>" + users_data[i].id + "</td>" +
                "<td>" + users_data[i].username + "</td>" +
                "<td>"  + users_data[i].age + "</td>" +
                "<td>" + users_data[i].email + "</td>" +
                "<td>" + users_data[i].roles + "</td>" +
                "<td><button onclick='editUser(" + users_data[i].id + ")' type=\"button\" class=\"btn btn-success\">Edit</button></td>" +
                "<td><button id=\"delete" + users_data[i].id + "\" type=\"button\" class=\"btn btn-danger\">Delete</button></td>" +
                "</tr>";
        }

        $('#all_users').html(table);
    });
}

function editUser(id) {

        $('#modalEdit').modal

}

$('#addButton').click(function () {
   var name = $('#name').val();
   var password = $('#password').val();
   var age = $('#age').val();
   var email = $('#email').val();
   var roles = $('#roles').val().join(", ");

   $.ajax({
       url:'/saveUser',
       type: 'POST',
       cache: false,
       async: false,
       data: JSON.stringify({
           'username': name,
           'password': password,
           'age': age,
           'email': email,
           'roles': roles
       }),
       contentType: 'application/json',
       error : [function(jqXHR, textStatus, errorThrown){
        alert(textStatus)
       }],
       success: [function () {
           getAllUsers();
       }]
   })
});

$(document).ready(function () {

    getAllUsers();

    $('#addUserButton').click(function () {

        console.log("addUserButton нажата");

        $('#usersTablePage').addClass('d-none');
        $('#addUserPage').removeClass('d-none');

        $('#usersTableButton').removeClass('active');
        $('#addUserButton').addClass('active');


    });

    $('#usersTableButton').click(function () {

        console.log("usersTableButton нажата");

        $('#addUserPage').addClass('d-none');
        $('#usersTablePage').removeClass('d-none');

        $('#addUserButton').removeClass('active');
        $('#usersTableButton').addClass('active');
    });

    $('#userPanelButton').click(function () {

        console.log("userPanelButton нажата");

        $('#adminPanel').addClass('d-none');
        $('#userPanel').removeClass('d-none');

        $('#userPanelButton').removeClass('btn-link').addClass('btn-primary');
        $('#adminPanelButton').removeClass('btn-primary').addClass('btn-link');
    });

    $('#adminPanelButton').click(function () {

        console.log("userPanelButton нажата");

        $('#userPanel').addClass('d-none');
        $('#adminPanel').removeClass('d-none');

        $('#adminPanelButton').removeClass('btn-link').addClass('btn-primary');
        $('#userPanelButton').removeClass('btn-primary').addClass('btn-link');
    });
});