//Функция для получения авторизованного пользователя (для панели юзера)
function getUser() {
    $.get('/users/authorized', function (user_data) {
        console.log(user_data);

        let table = "<tr>" +
            "<td>" + user_data.id + "</td>" +
            "<td>" + user_data.vkId + "</td>" +
            "<td>" + user_data.username + "</td>" +
            "<td>" + user_data.age + "</td>" +
            "<td>" + user_data.email + "</td>" +
            "<td>" + user_data.roles + "</td>" +
            "</tr>";

        $("#user_table").html(table);
    });
}

//Функция для заполнения таблицы всех юзеров
function getAllUsers() {
    $.get('/users', function (users_data) {

        let table = "";

        for (i = 0; i < users_data.length; i++) {
            if (users_data[i].vkId !== null && users_data[i].vkId !== "") {
                (function (m, k) {
                    $.ajax({
                        method: 'GET',
                        async: 'false',
                        url: 'https://api.vk.com/method/users.get?user_ids=' + m[k].vkId + '&fields=photo_100' + '&access_token=vk1.a.huFNRBA07z5EULGjuojDbctNxXJukv8Yd3xncm0Mbbh5y93QrAWK8VY-9NXxxwA4vhnInEMBiUqZYrI1e-RH2_-XE0whQO7n1mc67AZWlO2mj3LxMCA9vVpaeGtjrqvi8Rd2fSCNXHHkVlp5Kwar3YG4h6KKv7qgbJ-uVcIouonfLu8ecuJ6yQQV1luTzbjkfmz_2N-4WAAPF7L4AjhTgw'
                            + '&v=5.131',
                        dataType: 'jsonp',
                        success:
                            function (data) {
                                $('#picture_' + m[k].vkId + '').attr('src', data.response[0].photo_100);
                            },
                    })
                })(users_data, i);
            }

            table = table + "<tr>" +
                "<td>" + users_data[i].id + "</td>" +
                "<td><img id='picture_" + users_data[i].vkId + "' alt=''></td>" +
                "<td>" + users_data[i].username + "</td>" +
                "<td>" + users_data[i].age + "</td>" +
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

//Функция для заполнения таблицы реквестов
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

//Функция для отображения модального окна edit
function editUser(id) {

    $('#modalEdit').modal({
        show: true
    })

    $.get('/users/' + id, function (user_data) {
        $('#editId').val(user_data.id).attr('disabled', 'disabled');
        $('#editName').val(user_data.username);
        $('#editPassword').val(user_data.password);
        $('#editVkID').val(user_data.vkId);
        $('#editAge').val(user_data.age).attr('disabled', 'disabled');
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

//Функция для отображения модального окна delete
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
            $('#deleteVkID').val(user_data.vkId).attr('disabled', 'disabled');
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

//функция для кнопки Approve
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
            getRequestsCount();
        }]
    })
}

//функция для кнопки Deny
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
            getRequestsCount();
        }]
    })
}

//функция для получения состояния реквеста (нужна для дизейбла кнопки Get admin rights)
function getRequestStatus() {
    $.get('/users/authorized', function (user_data) {
        console.log(user_data);

        if (user_data.request) {
            $('#getAdminRightsButton').addClass('disabled');
        } else
            $('#getAdminRightsButton').removeClass('disabled');
    });
}

//Функция для отображения данных авторизованного пользователя в шапке
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

//WebSocket
ws = new WebSocket("ws://localhost:8080/ws")

ws.onopen = function () {
    getRequestsCount();
    console.log("WS Opened");
};

ws.onmessage = function (ev) {
    getRequestsCount();
    console.log("MESSAGE ARRIVED");
}

ws.onclose = function (err) {
    console.log("WS Closed")
}

//Функция для получения количества реквестов и записи в counter
function getRequestsCount() {
    $.get('/users/requests', function (requests_data) {
        console.log(requests_data);

        if(requests_data.length > 0) {
            $('#requestNotificationCounter').text(requests_data.length);
        } else {
            $('#requestNotificationCounter').text('');
        }
    });
}

//Click для кнопки Edit внутри модалки для сохранения изменений в базу
$('#editButton').click(function () {
    var id = $('#editId').val();
    var name = $('#editName').val();
    var password = $('#editPassword').val();
    var vkId = $('#editVkID').val();
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
            'vkId': vkId,
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

//Click для кнопки Delete внутри модалки для удаления пользователя из базы
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

//Click для кнопки Add в поле создания нового пользователя для записи его в базу
$('#addButton').click(function () {
   var name = $('#name').val();
   var password = $('#password').val();
   var vkId = $('#vkId').val();
   var dateOfBirth = $('#age').val();
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
           'vkId': vkId,
           'dateOfBirth': dateOfBirth,
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

//Click для кнопки Get admin rights для отправки реквеста на получение админских прав
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
                ws.send("null");
            }]
        })
    })
})

$(document).ready(function () {
    //получение полной инфы о юзере
    getUser();

    //Enable/Disable кнопки на получение админки (актуально только юзерам без админки)
    getRequestStatus();

    //Заполнение шапки
    getAuthUserEmailAndRoles();

    //Заполнение таблицы юзеров
    getAllUsers();

    //Заполнение таблицы реквестов
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

        getAllRequests();

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