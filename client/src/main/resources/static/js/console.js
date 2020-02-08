var deleteUrl;
function deletePerson(url, name) {

    deleteUrl = url;
    event.preventDefault();
    $('#deleteModalMessage').text('Do you want to delete Person  '+ name + ' ?');
    $('#deleteModal').modal('show');

}

function deletePersonData() {
    location.href = deleteUrl;
}

$(document).ready(function () {

    $("#submitPersonBtn").click(function (event) {

        return true;
    });
});

function setIcon(element, order) {

    var iconSpan = $(element).find('div');
    if (order == "ASC" ) {
        $(iconSpan).removeClass();
        $(iconSpan).addClass('pe-7s-angle-up');
    } else {
        $(iconSpan).removeClass();
        $(iconSpan).addClass('pe-7s-angle-down');
    }
    $(element).siblings().find('div').removeClass();
    $(element).siblings().data("order", "");
    $(element).data("order", order);
}

function sortTable(element, tableId) {
    var id =  $(element).attr("id");
    var order  = $(element).data("order");

    if (order != "ASC" ) {
        order = "ASC";
    } else {
        order = "DESC";
    }

    sortColumn = id, sortOrder = order;

    $.post( url , { page: 0, searchString: search, sortColumn: sortColumn, sortOrder: order}, function(response) {
        $("#"+ tableId).html(response);
        var total =  $("#" + tableId).find('table').data("total")
        console.log(total);
        $("#pagination").pagination('updateItems', total);
        $("#pagination").pagination('drawPage', 1);
        element = $("#"+id);
        setIcon(element, sortOrder);

    });


}