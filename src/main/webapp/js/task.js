$(document).ready(function () {
    $(".btn-delete-job").click(function () {
        var id = $(this).attr("job-id")
        var This = $(this)
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/demoservlet/job/delete?jobId=" + id,
        }).done(function (result) {
            This.closest("tr").remove();
            bootbox.alert("Xoá thành công !")
        });
    })
})