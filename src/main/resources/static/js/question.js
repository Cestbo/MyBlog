function post() {
    var id=$("#question-id").val();
    var comment=$("#comment").val();
    $.ajax({
        type:"POST",
        url:"/comment",
        data:JSON.stringify({
            "parent_id":id,
            "content":comment,
            "type":1
        }),
        datatype:"json",
        contentType:"application/json",
        success:function (response) {
            if(response.code==200)
            {
                alert("回复成功");
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
