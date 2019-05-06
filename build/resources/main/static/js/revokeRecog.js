$(document).ready(function () {
    var transactionId;
    $('body').on("click", ".revokeRecognition", function () {
        transactionId= $(this).data('id');
    });
    $('body').on("click", ".submitRevoke", function () {
        var reason=$("input[name='reason']:checked").val();
        var other=$("input[name='other']").val();
        if(reason==null){
            alert("No reason is selected, Cann't revoke without reason.")
        }
        else
        $.ajax({
            url:"/revokeBadge",
            method:"POST",
            data:{"transactionId":transactionId,"reason":reason,"other":other}
        });
    });
});

