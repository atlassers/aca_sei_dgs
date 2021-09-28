class BaseService {

    constructor() { }

    static getAll(path) {
        return AjaxCore.callByUrl('GET', path, null);
    }

    static get(path, id) {
        return AjaxCore.callByUrl('GET', path, id);
    }

    static post(path, formData) {
        return AjaxCore.callByBody('POST', path, formData);
    }

    static put(path, formData) {
        return AjaxCore.callByBody('PUT', path, formData);
    }

    static patch(path, formData) {
        return AjaxCore.callByBody('PATCH', path, formData);
    }

    static delete(path, id) {
        return AjaxCore.callByUrl('DELETE', path + '/' + id);
    }
}