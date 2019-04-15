

$(document).ready(function () {
    $('body').on("click", ".add", function () {
        var item=$(this).val();

            $.ajax({
                url:"/addItem",
                method:"POST",
                data:{"itemId":item}
            });
    });

    $('body').on("click", ".remove", function () {
        var item=$(this).val();

            $.ajax({
                url:"/removeItem",
                method:"POST",
                data:{"itemId":item}
            });
    });
});

