function post(e) {
    var btn_id=e.id;
    var type=1;
    if (btn_id=="btn-comment")
    {
        var id=$("#question-id").val();
        var comment=$("#comment").val();
    }
    else if (btn_id=="btn-comment2")
    {
        var id=$("#comment-id").val();
        var comment=$("#second-comment").val();
        type=2
    }
    $.ajax({
        type:"POST",
        url:"/comment",
        data:JSON.stringify({
            "parent_id":id,
            "content":comment,
            "type":type
        }),
        datatype:"json",
        contentType:"application/json",
        success:function (response) {
            if(response.code==200)
            {
                alert("回复成功");
                location.reload();
            }
            else if (response.code == 2000) {
                var a = confirm(response.msg);
                if (a == true) {
                    window.open("https://github.com/login/oauth/authorize?" +
                        "client_id=e5361a72c6d25c61b247&redirect_uri=http://localhost:8080/callback&scope=user&state=1")
                    window.localStorage.setItem("close", "true");
                }
            }
            console.log(response);
        }
    })
}
function applaud(e) {
    var comment_id=e.getAttribute("data-id");
    $.ajax({
        type:"POST",
        url:"/comment_like",
        data:{
            "id":comment_id
        },

        success:function (response) {
            var like_count=$("#like_count_"+comment_id);
            like_count.text(response);
        }
    })
}
