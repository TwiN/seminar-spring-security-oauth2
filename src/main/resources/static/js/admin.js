$(function () {
    $.ajax({
        method: "GET",
        dataType: "json",
        url: "/api/scores"
    }).done(function(data) {
        console.log(data);
        let buffer = "";
        data.forEach(function(score) {
            console.log(score);
            buffer += ""
                + "<tr id='score-"+score.id+"'>"
                + "  <td>"+score.id+"</td>"
                + "  <td>"+score.username+"</td>"
                + "  <td>"+score.points+"</td>"
                + "  <td><input type='button' class='btn btn-danger' value='Delete' onclick='deleteScore("+score.id+")' /></td>"
                + "</tr>";
        });
        $("#scoreboard").html(buffer);
    });
});


function deleteScore(id) {
    console.log("deleting score ", id);
    $.ajax({
        method: "DELETE",
        dataType: "json",
        url: "/api/scores/" + id
    }).done(function(data) {
        $("#score-"+id).remove();
    });
}