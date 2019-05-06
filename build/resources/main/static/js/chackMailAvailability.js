$(document).ready(function () {
    $('#inputEmail').blur(function () {
        var userName=$('#inputEmail').val();
        $.ajax({
                url:"/checkAvailability",
                method:"POST",
                data:{username:userName},
                datatype:"text",
                success:function(html) {
                    $('#unavail').html(html);
                    if (html!="") {
                        $('#inputEmail').val("");
                    }
                }
            }
        )
    })

});