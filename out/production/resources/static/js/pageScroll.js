$(document).ready(function () {
    var pageNume=0;
    $('#card').ready(function () {
        console.log("before page call");
        loadPage(pageNume);
    });
    function loadPage(currentPage) {
        console.log("before ajax call");
        $.ajax({
           url:'/page',
            method:'post',
            data:{pageNo:currentPage},
            success:function (data) {
                console.log("hi");
               var card=$('#card');
               console.log(data);

                const getNestedObject = (nestedObj, pathArr) => {
                    return pathArr.reduce((obj, key) =>
                        (obj && obj[key] !== 'undefined') ? obj[key] : undefined, nestedObj);
                };
                 $(data).each(function (transaction) {

                     const image = getNestedObject(transaction, ['receiver', 'imagePath']);
                     const firstName = getNestedObject(transaction, ['receiver', 'firstName']);
                   card.append("<div class='media'><img class='d-flex mr-3'" +
                       " th:src='@{${"+image+"}}' style='height: 50px'>"+
                       "<div class='media-body' >"+"<span th:text='${"+((transaction||{}).receiver||{}).firstName+"}'></span>"+
                       "<span th:text='${"+((transaction||{}).receiver||{}).lastName+"}'></span> has received"+
                       "<span class='badge badge-success' th:text='${"+((transaction||{}).badge||{}).badge+"}'></span> from"+
                       "<span th:text='${"+((transaction||{}).sender||{}).firstName+"}'></span>"+
                       "<span th:text='${"+((transaction||{}).sender||{}).lastName+"}'></span><br/>Reason:" +
                       "<span th:text='${"+transaction.message+"}'></span>"+
                       "<br/><br/></div></div> " +
                       "<p class='card-link' th:text='${"+transaction.date+"}'></p><hr/>");
               });
            }
        });
    }


});