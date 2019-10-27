$(document).ready(function () {
    $('#product-table').DataTable({
        "ajax": {
            "url": "http://localhost:8081/purchase/products/",
            "type": "get",
            "dataType": "json"
        },
        "columns": [
            {"data": "id"},
            {"data": "product_name"},
            {"data": "brand.description"},
            {"data": "category.description"},
            {
                "data": "action",
                "className": "center",
                "defaultContent": '<a href="" class="btn btn-primary"><i class="fa fa-edit"></i></a> <a href="" class="btn btn-danger"><i class="fa fa-trash"></i></a>'
            }
        ]
    });
});