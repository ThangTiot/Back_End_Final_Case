function init() {
    $.ajax({
        url: "http://localhost:8080/user/findAll",
        type: "GET",
        success: function (user) {
            userPresent = user;
            let account = "";
            account += `<img src="${user.avatar}" style="height: 70px; width: 70px">`;
            document.getElementById("userName").innerHTML = account;
            document.getElementById("user-img").src = user.avatar;
            display();
        }
    })
}