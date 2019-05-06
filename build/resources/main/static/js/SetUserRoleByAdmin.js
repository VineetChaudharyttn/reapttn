$(document).ready(function(){
    $("select.adminSetUserRole").change(function(){

        var selectedRole = $(this).children("option:selected").val();
        var userId = $(this).children("option:selected").data('userid');
        console.log(userId);
        console.log(selectedRole);

        if (selectedRole!=null) {
            $.ajax({
                url: "/changeUserRole",
                method:"POST",
                data: {"userId": userId,"selectedRole":selectedRole},
                success: (function () {
                    $('#result').html("<span class='alert alert-success'>Role Changed Successfully</span>");
                })
            });
        }
        
    });
});