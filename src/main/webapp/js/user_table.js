$(document).ready(function () {
    $(".btn-delete-user").click(function () {
        var id = $(this).attr("user-id")
        var This = $(this)
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/demoservlet/user/delete?userId=" + id,
        }).done(function (result) {
            This.closest("tr").remove();
            bootbox.alert("Xoá thành công !")
        });
    })
})