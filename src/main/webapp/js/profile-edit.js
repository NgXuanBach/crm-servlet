$(document).ready(function () {
    //lắng nghe sự kiện click cho thẻ có id là btn-delete-user
    $(".btn-update-jobstatus").click(function () {
        var id = $(this).attr("job-id")
        console.log("id" + id)
        var request = $.ajax({
            type: "post",
            url: "http://localhost:8080/demoservlet/profile/edit?jobId=" + id,
        });
        request.done(function () {
            bootbox.alert("Cập nhật trạng thái thành công")
        });
        request.fail(function () {
            bootbox.alert("Cập nhật trạng thái không thành công")
        });
    })
})