$(document).ready(function () {
    $('#recognize').click(function () {
        var sender=document.getElementById("senderId");
        var receiverId=document.getElementById("receiverId");
        var badgeId=document.getElementById("badgeId");
        var message=document.getElementById("message");
        obj={"senderId": sender.value,"receiverId":receiverId.value,"badgeId":badgeId.value,"message":message.value,"date":moment().format('YYYYMMDDhhmma')};
        $.ajax( {
            url:"/writeComment",
            method:"POST",
            data:obj,
            success:function (html) {
            }
        })
    });


    $('#date').ready(function () {
        var div = document.getElementById("date");
        var p = div.getElementsByTagName("p");

        for(i=0;i<p.length;i++)
        {
            p[i].textContent=moment(p[i].innerText, "YYYYMMDDhhmma").fromNow();

        }
    })
});
