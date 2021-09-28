$(document).ready(function() {
	CityService.getTotals();
	
	$('#btnShow').click(function() {
		CityService.getAll();
	});

	$('#btnAdd').click(function() {
		$('table').empty();
		$('table').hide();
		$('#saveForm').show();
	})

	$('#saveForm').submit(function(event) {
		event.preventDefault();

		var id = document.getElementById('cityId').value
		var provinceId = document.getElementById('provinceId').value;
		var denomination = document.getElementById('denomination').value;

		var formData = JSON.stringify({
			'id': id,
			'provinceId': provinceId,
			'denomination': denomination
		})

		var action;
		if (id) {
			formData.id = id;
			action = CityService.put;
		} else {
			action = CityService.post;
		}
		action(JSON.stringify(formData))
	});
});