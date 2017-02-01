/* global $ */
var i, e;
for (i = 0; i < 10; i++)
{
    $("#table").append("<tr class='tableRow' id='tableRow" + i + "'></tr>");
    for (e = 0; e < 10; e++)
    {
        $("#tableRow" + i).append("<td class='tableElement' id='tableElement" + i + "-" + e + "'><div id='tableDiv" + i + "-" + e + "'></div></tr>");
    }
}

function draw(startX, startY, n)
{
    var x = parseInt(startX);
    var y = parseInt(startY);
    
    insertN(x, y, 1);
    for (var i = 0; i < n.length; i++)
    {
        var move = n.substr(i, 1);
        if (move == 0)
        {
            y = y - 3;
        }
        else if (move == 1)
        {
            y = y - 2;
            x = x + 2;

        }
        else if (move == 2)
        {
            x = x + 3;
        }
        else if (move == 3)
        {
            y = y + 2;
            x = x + 2;
        }
        else if (move == 4)
        {
            y = y + 3;
        }
        else if (move == 5)
        {
            y = y + 2;
            x = x - 2;

        }
        else if (move == 6)
        {
            x = x - 3;
        }
        else if (move == 7)
        {
            y = y - 2;
            x = x - 2;
        }
        
        insertN(x, y, i + 2);
    }
}

function insertN(x, y, n)
{
    x = parseInt(x);
    y=parseInt(y);
    
    console.log(x);
    console.log(y);
    console.log(n);
    console.log("------------------------------------------------------------------------------------");
    
    console.log("#tableDiv" + x + "-" + y);
    $("#tableDiv" + x + "-" + y).text(n);
}

function go()
{
    var n = $("#order").val();
    //n= parseInt(n,16);
    //n=n.toString(8);
    //console.warn(n);
    draw($("#y").val(),$("#x").val(),n);
}
