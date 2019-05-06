

$(document).ready(function () {
    var Obj = document.getElementById('itemTable');
    function table(response) {
        var str = '<table><thead>\n' +
            '                        <tr>\n' +
            '                            <td>Item Id</td>\n' +
            '                            <td>Name</td>\n' +
            '                            <td>points Worth</td>\n' +
            '                            <td>Quantity</td>\n' +
            '                            <td>Total points</td>\n' +
            '                        </tr>\n' +
            '                        </thead>\n' +
            '                        <tbody>'; //it can be anything
         //any element to be fully replaced
        for (var i = 0; i < response.length; i++) {
            str = str + "<tr><td>" + response[i].itemId + "</td><td>"
                + response[i].name + "</td><td>"
                + response[i].pointsWorth + "</td><td>"
                + response[i].quantity + "</td><td>"
                + response[i].total + "</td></tr>";
        }
        str = str + "</tbody></table>";
        Obj.innerHTML = str;
    }

    $('body').on("click", ".add", function () {
        var item=$(this).val();

        $.ajax({
                url:"/addItem",
                method:"POST",
                data:{"itemId":item},
                success: function(response) {
                    table(response);
                }
    });

    });

    $('body').on("click", ".remove", function () {
        var item=$(this).val();

            $.ajax({
                url:"/removeItem",
                method:"POST",
                data:{"itemId":item},
                success: function(response) {
                    table(response);
                },
                error:function () {
                    swal("Item not is not added");
                }
            });
    });

    $('body').on("click", ".buy", function () {
        $.ajax({
            url:"/buy",
            method:"POST",
            success: function(response) {
                if (response=="fails"){
                    swal("Fails to Purchase !!!", "Sorry ): \nYour point are less the total point require to buy.");
                }
                else if (response=="listEmpty"){
                    swal("No item !!!!","List is empty.");
                }
                else {
                    Obj.innerHTML="<p>No item</p>";
                    swal("Success !!!", "Order placed successfully. \n THANKYOU :)");
                }

            }
        });
    });
});

