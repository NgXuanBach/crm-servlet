$(document).ready(function () {
    $(".btn-delete-role").click(function () {
        var id = $(this).attr("role-id")
        var This = $(this)
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/demoservlet/role/delete?roleId=" + id,
        }).done(function (result) {
            This.closest("tr").remove();
            bootbox.alert("Xoá thành công !")
        });
    })
})