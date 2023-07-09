//Получение авторизованного пользователя (для панели юзера)
function getUser() {
    $.get('/users/authorized', function (user_data) {
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

//Заполнение таблицы всех юзеров
function getAllUsers() {
    $.get('/users', function (users_data) {
        console.log(users_data);

        let table = "";

        for (i = 0; i < users_data.length; i++) {
            table = table + "<tr>" +
                "<td>" + users_data[i].id + "</td>" +
                "<td>" + users_data[i].username + "</td>" +
                "<td>"  + users_data[i].age + "</td>" +
                "<td>" + users_data[i].email + "</td>" +
                "<td>" + users_data[i].roles + "</td>" +
                "<td><button onclick='editUser(" + users_data[i].id + ")' " +
                    "type='button' " +
                    "class='btn btn-success' " +
                    "data-bs-toggle='modal' " +
                    "data-bs-target='#modalEdit'>Edit</button></td>" +
                "<td><button onclick='deleteUser(" + users_data[i].id + ")' " +
                    "type='button' " +
                    "class='btn btn-danger' " +
                    "data-bs-toggle='modal' " +
                    "data-bs-target='#modalDelete'>Delete</button></td>" +
                "</tr>";
        }

        $('#all_users').html(table);
    });
}

//Заполнение таблицы реквестов
function getAllRequests() {
    $.get('/users/requests', function (requests_data) {
        console.log(requests_data);

        let table = "";

        for (i = 0; i < requests_data.length; i++) {
            table = table + "<tr>" +
                "<td>" + requests_data[i].username + "</td>" +
                "<td><button onclick='approveRequest(" + requests_data[i].id + ")' " +
                "type='button' " +
                "class='btn btn-success'>Approve</button></td>" +
                "<td><button onclick='denyRequest(" + requests_data[i].id + ")' " +
                "type='button' " +
                "class='btn btn-danger'>Deny</button></td>" +
                "</tr>";
        }

        $('#admin_requests').html(table);
    });
}

function editUser(id) {

    $('#modalEdit').modal({
        show: true
    })

    $.get('/users/' + id, function (user_data) {
        $('#editId').val(user_data.id).attr('disabled', 'disabled');
        $('#editName').val(user_data.username);
        $('#editPassword').val(user_data.password);
        $('#editAge').val(user_data.age);
        $('#editEmail').val(user_data.email);
        if(user_data.roles.includes('ROLE_USER') && user_data.roles.includes('ROLE_ADMIN')) {
            $('#editRoles').selectpicker('selectAll');
            $('.dropdown-toggle').css("display","none");
            $('.dropdown-menu').css("display","none");
        } else {
            $('#editRoles').val(user_data.roles);
        }
    })
}

function deleteUser(id) {

    $('#modalDelete').modal({
        show: true
    })

    $.ajax({
        url: '/users/' + id,
        type: 'GET',
        success: function (user_data) {
            $('#deleteId').val(user_data.id).attr('disabled', 'disabled');
            $('#deleteName').val(user_data.username).attr('disabled', 'disabled');
            $('#deletePassword').val(user_data.password).attr('disabled', 'disabled');
            $('#deleteAge').val(user_data.age).attr('disabled', 'disabled');
            $('#deleteEmail').val(user_data.email).attr('disabled', 'disabled');
            if(user_data.roles.includes('ROLE_USER') && user_data.roles.includes('ROLE_ADMIN')) {
                $('#deleteRoles').selectpicker('selectAll').attr('disabled', 'disabled');
                $('.dropdown-toggle').css("display","none");
                $('.dropdown-menu').css("display","none");
            } else {
                $('#deleteRoles').val(user_data.roles).attr('disabled', 'disabled');
            }
        }
    });
}

function approveRequest(id) {
    $.ajax({
        url:'/users/requests/' + id,
        type: 'GET',
        cache: false,
        async: false,
        contentType: 'application/json',
        error : [function(jqXHR, textStatus, errorThrown){
            alert(textStatus)
        }],
        success: [function () {
            getAllRequests();
            getAllUsers();
        }]
    })
}

function denyRequest(id) {
    $.ajax({
        url:'/users/requests/' + id,
        type: 'DELETE',
        cache: false,
        async: false,
        contentType: 'application/json',
        error : [function(jqXHR, textStatus, errorThrown){
            alert(textStatus)
        }],
        success: [function () {
            getAllRequests();
        }]
    })
}

function getRequestStatus() {
    $.get('/users/authorized', function (user_data) {
        console.log(user_data);

        if (user_data.request) {
            $('#getAdminRightsButton').addClass('disabled');
        } else
            $('#getAdminRightsButton').removeClass('disabled');
    });
}

function getAuthUserEmailAndRoles() {
    $.get('/users/authorized', function (user_data) {
        $('#authUserEmail').html(user_data.email);
        $('#authUserRoles').html(user_data.roles);
    })
}

function closeEditModal() {
    $('#modalEdit').modal({
        show: false,
        hide: true
    })
}

$('#editButton').click(function () {
    var id = $('#editId').val();
    var name = $('#editName').val();
    var password = $('#editPassword').val();
    var age = $('#editAge').val();
    var email = $('#editEmail').val();
    var roles = $('#editRoles').val().join(", ");

    $.ajax({
        url:'/users/' + id,
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
            closeEditModal();
            getAllUsers();
        }]
    })
});

$('#deleteButton').click(function () {
    var id = $('#deleteId').val();

    $.ajax({
        url:'/users/' + id,
        type: 'DELETE',
        cache: false,
        async: false,
        contentType: 'application/json',
        error : [function(jqXHR, textStatus, errorThrown){
            alert(textStatus)
        }],
        success: [function () {
            $('#modalDelete').modal({
                show: false
            })

            getAllUsers();
        }]
    })
});

$('#addButton').click(function () {
   var name = $('#name').val();
   var password = $('#password').val();
   var age = $('#age').val();
   var email = $('#email').val();
   var roles = $('#roles').val().join(", ");

   $.ajax({
       url:'/users',
       type: 'PUT',
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

$('#getAdminRightsButton').click(function () {
    $.get('/users/authorized', function (user_data) {
        var id = user_data.id;
        $.ajax({
            url:'/users/requests/' + id,
            type: 'PUT',
            cache: false,
            async: false,
            contentType: 'application/json',
            error : [function(jqXHR, textStatus, errorThrown){
                alert(textStatus)
            }],
            success: [function () {
                $('#getAdminRightsButton').addClass('disabled');
            }]
        })
    })

})

$(document).ready(function () {
    getUser();
    getRequestStatus();
    getAuthUserEmailAndRoles();
    getAllUsers();
    getAllRequests();

    $('#addUserButton').click(function () {
        console.log("addUserButton нажата");

        $('#usersTablePage').addClass('d-none');
        $('#adminRequestsPage').addClass('d-none');
        $('#addUserPage').removeClass('d-none');

        $('#usersTableButton').removeClass('active');
        $('#adminRequestsButton').removeClass('active');
        $('#addUserButton').addClass('active');
    });

    $('#usersTableButton').click(function () {
        console.log("usersTableButton нажата");

        $('#addUserPage').addClass('d-none');
        $('#adminRequestsPage').addClass('d-none');
        $('#usersTablePage').removeClass('d-none');

        $('#addUserButton').removeClass('active');
        $('#adminRequestsButton').removeClass('active');
        $('#usersTableButton').addClass('active');
    });

    $('#adminRequestsButton').click(function () {
        console.log("adminRequestsButton нажата");

        $('#usersTablePage').addClass('d-none');
        $('#addUserPage').addClass('d-none');
        $('#adminRequestsPage').removeClass('d-none');

        $('#addUserButton').removeClass('active');
        $('#usersTableButton').removeClass('active');
        $('#adminRequestsButton').addClass('active');
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