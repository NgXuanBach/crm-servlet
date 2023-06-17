$(document).ready(function () {
    //lắng nghe sự kiện click cho thẻ có id là btn-delete-user
    $(".btn-delete-role").click(function () {
        var id = $(this).attr("role-id")
        var This = $(this)
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/demoservlet/role/delete?roleId=" + id,
        }).done(function (result) {
            This.closest("tr").remove();
            console.log("Ket qua ", result)
        });
    })
})