$(document).ready(function () {
    $('#product-table').DataTable({
        "ajax": {
            "url": "http://localhost:8081/purchase/products/",
            "type": "get",
            "dataType": "json"
        },
        "columns": [
            {"data": "eraser"},
            {"data": "bar_code"}
        ]
    });
});