$(document).ready(function () {
    CourierService.getTotals();

    $('#btnShow').click(function () {
      CourierService.getAll();
    });

    $('#btnAdd').click(function () {
      $('.table').empty();
      $('.table').hide();
      $('#saveForm').show();
    });

    $('#saveForm').submit(function (event) {
        event.preventDefault();

        var id = document.getElementById('courierId').value;
        var denomination = document.getElementById('denomination').value;
        var tax_code = document.getElementById('tax_code').value;

        var formData = JSON.stringify({
          'denomination': denomination,
          'tax_code': tax_code
        });

        var formDataId = JSON.stringify({
          'id': id,
          'denomination': denomination,
          'tax_code': tax_code
        });

        if (id) {
          CourierService.put(formDataId);
        } else {
          CourierService.post(formData);
        }
    });
});