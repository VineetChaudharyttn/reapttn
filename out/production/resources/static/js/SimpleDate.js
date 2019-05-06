$('#date').ready(function () {
    var div = document.getElementById("date");
    var p = div.getElementsByTagName("p");

    for(i=0;i<p.length;i++)
    {
        p[i].textContent=moment(p[i].innerText, "YYYYMMDDhhmma").fromNow();

    }
});