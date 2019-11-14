$(document).ready(function () {

    var table = $('#product-table').DataTable({
        "ajax": {
            "url": "/general/ringing/",
            "type": "get",
            "dataType": "json"
        },
        "columns": [
            {"data": "id"},
            {"data": "ringing"},
            {"data": "validityDate"},
            {"data": "documentType"},
            {
                "data": "action",
                "className": "center",
                "defaultContent": '<button class="btn btn-primary edit"><i class="fa fa-edit"></i></button> <button href="" class="btn btn-danger delete"><i class="fa fa-trash"></i></button>'
            }
        ]

    });



    $('body').on('click', '.edit', function(){
        var row  = $(this).parents('tr')[0];
        var data = table.row( row ).data();
        var editForm = "/general/ringing/ringing-form?id="+data.id;
        window.location.href = editForm;
    });

});