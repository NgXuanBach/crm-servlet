$(document).ready(function () {
    //lắng nghe sự kiện click cho thẻ có id là btn-delete-user
    $(".btn-delete-project").click(function () {
        var id = $(this).attr("project-id")
        var This = $(this)
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/demoservlet/project/delete?projectId=" + id,
        }).done(function (result) {
            This.closest("tr").remove();
            bootbox.alert("Xoá thành công !")
        });
    })
})