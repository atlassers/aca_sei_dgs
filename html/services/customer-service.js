class CustomerService {
    constructor() { }

    static getAll() {
        BaseService.getAll('/customers/v1')
            .then(function (customerDtoList) {
                var content = '<table class="aca-fill">';
                content += '<tr>'
                    + '<th scope="col">Id</th>'                   
                    + '<th scope="col">Name</th>'
                    + '<th scope="col">Surname</th>'
                    + '<th scope="col">Birth Date</th>'
					+ '<th scope="col">Tax Code</th>'
                    + '<th scope="col">Detail</th>'
                    + '<th scope="col">Action</th>'
                    + '</tr>';
                jQuery.each(customerDtoList, function (i, val) {
                    content += '<tr>'
                        + '<td>' + val.id + '</td>'
                        + '<td>' + val.name + '</td>'
                        + '<td>' + val.surname + '</td>'
                        + '<td>' + val.birthDate + '</td>'
						+ '<td>' + val.taxCode + '</td>'
                        + '<td><button id="btnDetail-' + val.id + '" class="btn btn-warning" onclick="CustomerService.detail(' + val.id + ')">detail</button></td>'
                        + '<td><button id="btnDelete-' + val.id + '" class="btn btn-danger" onclick="CustomerService.delete(' + val.id + ')">delete</button></td>'
                        + '</tr>';
                });
                content += '</table>';

                $('#saveForm').trigger('reset');
                $('#saveForm').hide();
                $('#customers').empty();
                $('#customers').append(content);
                $('#customers').show();
            });
    }

    static post(formData) {
        BaseService.post(CustomerService.path, formData)
            .then(function (customerDto) {
                window.alert("L'utente è stato salvato? " + customerDto.id !== null)
                $('#saveForm').trigger('reset');
                $('#saveForm').hide();

                CustomerService.getAll();
                CustomerService.getNumberCustomers();
            });
    }

    static put(formData) {
        BaseService.put(CustomerService.path, formData)
            .then(function (customerDto) {
                window.alert("L'utente è stato Aggiornato")
                $('#saveForm').trigger('reset');
                $('#saveForm').hide();
                CustomerService.getAll();
                CustomerService.getNumberCustomers();
            });
    }

    static delete(id) {
        BaseService.delete(CustomerService.path, id)
            .then(function (result) {
                window.alert('Utente ' + id + ' eliminato con successo!')

               CustomerService.getAll();
               CustomerService.getNumberCustomers();
            });
    }


    static detail(id) {
        BaseService.get('/customers/v1', id)
            .then(function (customerDto) {
                document.getElementById('customerId').value =customerDto.id;
                document.getElementById('name').value =customerDto.name;
                document.getElementById('surname').value =customerDto.surname;
                document.getElementById('birthDate').value = moment(customerDto.birthDate).format('YYYY-MM-DD');
                document.getElementById('taxCode').value =customerDto.taxCode;

                $('.table').empty();
                $('.table').hide();
                $('#saveForm').show();
                CustomerService.statistics(id);
            });
    }

    static statistics(id) {
        $('#statisticalForm').show();
        CustomerService.getNumberPurchasesByMonthForCustomer(id).then(function(purchasesAndMonthList){
            var content = '<table class="aca-fill">';
                content += '<tr>'
                    + '<th scope="col">Month</th>'                   
                    + '<th scope="col">Purchases</th>'
                    + '</tr>';
            jQuery.each(purchasesAndMonthList, function (i, result) {
                content += '<tr>'
                        + '<td>' + CustomerService.monthNames[result.month+1] + '</td>'
                        + '<td>' + result.purchases + '</td>'
                        + '</tr>';
            });
            content += '</table>';

            $('#statistics').empty();
            $('#statistics').append(content);
        });

        CustomerService.getNumberProductsByMonthForCustomer(id).then(function(productsAndMonthList){
            var content = '<table class="aca-fill">';
                content += '<tr>'
                    + '<th scope="col">Month</th>'                   
                    + '<th scope="col">Products</th>'
                    + '</tr>';
            jQuery.each(productsAndMonthList, function (i, result) {
                content += '<tr>'
                        + '<td>' + CustomerService.monthNames[result.month+1] + '</td>'
                        + '<td>' + result.products + '</td>'
                        + '</tr>';
            });
            content += '</table>';

            $('#statistics').append(content);
            $('#statistics').show();
        });
    }

	static getTotalCustomers(){
		BaseService.get('/customers/v1/all')
        .then(function (customerDtoList) {
            var content = '<table class="aca-fill">';
            content += '<tr>'
                + '<th scope="col">Id</th>'                   
                + '<th scope="col">Name</th>'
                + '<th scope="col">Surname</th>'
                + '<th scope="col">Birth Date</th>'
                + '<th scope="col">Tax Code</th>'
                + '<th scope="col">Detail</th>'
                + '<th scope="col">Action</th>'
                + '</tr>';
            jQuery.each(customerDtoList, function (i, val) {
                content += '<tr>'
                    + '<td>' + val.id + '</td>'
                    + '<td>' + val.name + '</td>'
                    + '<td>' + val.surname + '</td>'
                    + '<td>' + val.birthDate + '</td>'
                    + '<td>' + val.taxCode + '</td>'
                    + '<td><button id="btnDetail-' + val.id + '" class="btn btn-warning" onclick="CustomerService.detail(' + val.id + ')">detail</button></td>'
                    + '<td><button id="btnDelete-' + val.id + '" class="btn btn-danger" onclick="CustomerService.delete(' + val.id + ')">delete</button></td>'
                    + '</tr>';
            });
            content += '</table>';

            $('#saveForm').trigger('reset');
            $('#saveForm').hide();
            $('.table').empty();
            $('.table').append(content);
            $('.table').show();
        });
	}

    static getTotalValidCustomers(){
		BaseService.get('/customers/v1/valid')
        .then(function (customerDtoList) {
            var content = '<table class="aca-fill">';
            content += '<tr>'
                + '<th scope="col">Id</th>'                   
                + '<th scope="col">Name</th>'
                + '<th scope="col">Surname</th>'
                + '<th scope="col">Birth Date</th>'
                + '<th scope="col">Tax Code</th>'
                + '<th scope="col">Detail</th>'
                + '<th scope="col">Action</th>'
                + '</tr>';
            jQuery.each(customerDtoList, function (i, val) {
                content += '<tr>'
                    + '<td>' + val.id + '</td>'
                    + '<td>' + val.name + '</td>'
                    + '<td>' + val.surname + '</td>'
                    + '<td>' + val.birthDate + '</td>'
                    + '<td>' + val.taxCode + '</td>'
                    + '<td><button id="btnDetail-' + val.id + '" class="btn btn-warning" onclick="CustomerService.detail(' + val.id + ')">detail</button></td>'
                    + '<td><button id="btnDelete-' + val.id + '" class="btn btn-danger" onclick="CustomerService.delete(' + val.id + ')">delete</button></td>'
                    + '</tr>';
            });
            content += '</table>';

            $('#saveForm').trigger('reset');
            $('#saveForm').hide();
            $('.table').empty();
            $('.table').append(content);
            $('.table').show();
        });
	}

    static getTotalInvalidCustomers(){
		BaseService.get('/customers/v1/invalid')
        .then(function (customerDtoList) {
            var content = '<table class="aca-fill">';
            content += '<tr>'
                + '<th scope="col">Id</th>'                   
                + '<th scope="col">Name</th>'
                + '<th scope="col">Surname</th>'
                + '<th scope="col">Birth Date</th>'
                + '<th scope="col">Tax Code</th>'
                + '<th scope="col">Detail</th>'
                + '<th scope="col">Action</th>'
                + '</tr>';
            jQuery.each(customerDtoList, function (i, val) {
                content += '<tr>'
                    + '<td>' + val.id + '</td>'
                    + '<td>' + val.name + '</td>'
                    + '<td>' + val.surname + '</td>'
                    + '<td>' + val.birthDate + '</td>'
                    + '<td>' + val.taxCode + '</td>'
                    + '<td><button id="btnDetail-' + val.id + '" class="btn btn-warning" onclick="CustomerService.detail(' + val.id + ')">detail</button></td>'
                    + '<td><button id="btnDelete-' + val.id + '" class="btn btn-danger" onclick="CustomerService.delete(' + val.id + ')">delete</button></td>'
                    + '</tr>';
            });
            content += '</table>';

            $('#saveForm').trigger('reset');
            $('#saveForm').hide();
            $('.table').empty();
            $('.table').append(content);
            $('.table').show();
        });
	}

    static getNumberCustomers(){
        $("#btnTotals").empty;
        $("#btnValidTotals").empty;
        $("#btnInvalidTotals").empty;
        BaseService.getAll('/customers/v1/totals').then(function(customersTotal){
            $("#btnTotals").text(customersTotal + ' Total Customers');
        })
        BaseService.getAll('/customers/v1/totals/valid').then(function(numberValidCustomers){
            $("#btnValidTotals").text(numberValidCustomers + ' Total Valid Customers');
        })
        BaseService.getAll('/customers/v1/totals/invalid').then(function(numberInvalidCustomers){
            $("#btnInvalidTotals").text(numberInvalidCustomers + ' Total Invalid Customers');
        });
    }

    static getNumberPurchasesByMonthForCustomer(id) {
        return BaseService.get('/customers/v1/purchases/month', id);
    }

    static getNumberProductsByMonthForCustomer(id) {
        return BaseService.get('/customers/v1/products/month', id);
    }
}

CustomerService.path = '/customers/v1';
CustomerService.monthNames = ["January", "February", "March", "April", "May", "June",
  "July", "August", "September", "October", "November", "December"
];