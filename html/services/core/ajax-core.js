const USERNAME = 'academy';
const PASSWORD = 'academy';
const BASE = 'http://localhost';
const PORT = ':8100';
const SERVICE_NAME = '/academy-cloud'
const URL = BASE + PORT + SERVICE_NAME;

class AjaxCore {

    constructor() { }

    static callByUrl(verb, path, id) {
        const urlParam = (id ? '/' + id : '');
        return $.ajax({
            type: verb,
            url: URL + path + urlParam,
            crossDomain: true,
            crossOrigin: true,
            contentType: 'application/json',
            headers: {
                'Authorization': 'Basic ' + btoa(USERNAME + ':' + PASSWORD)
            },
            error: function (xhr, status, errorThrown) {
                alert(status, errorThrown);
                console.log('xhr: ' + JSON.stringify(xhr.responseJSON));
                console.log('status: ' + status);
                console.log('errorThrown: ' + errorThrown);
            }
        });
    }


    static callByBody(verb, path, formData) {
        return $.ajax({
            type: verb,
            url: URL + path,
            crossDomain: true,
            crossOrigin: true,
            data: formData,
            contentType: 'application/json',
            headers: {
                'Authorization': 'Basic ' + btoa(USERNAME + ':' + PASSWORD)
            },
            error: function (xhr, status, errorThrown) {
                alert(status, errorThrown);
                console.log('xhr: ' + JSON.stringify(xhr.responseJSON));
                console.log('status: ' + status);
                console.log('errorThrown: ' + errorThrown);
            }
        });
    }
}