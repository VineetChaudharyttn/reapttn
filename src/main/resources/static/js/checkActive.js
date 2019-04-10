 $(document).ready(function () {
    $('body').on("change", ".checkActive", function () {
       var checked = $(this).is(':checked');
       var userId = $(this).val();
       // console.log(checked);
       // console.log(userId);

        if (!checked) {
            $.ajax({
                url: "/deactivateUser",
                method:"POST",
                data: {"userId": userId},
                success: (function () {
                    $('#result').html("<span class='alert alert-success'>Successfully Deactivated</span>");
                })
            });
        } else {
            $.ajax({
                url: "/activateUser",
                method:"POST",
                data: {"userId": userId},
                success: (function () {
                    $('#result').html("<span class='alert alert-success'>Successfully Activated</span>");
                })
            });
        }
    });
});
