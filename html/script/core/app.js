function include(file, isModule) {
    var script = document.createElement('script');
    script.src = file;
    if (isModule) {
        script.type = 'module';
        script.defer = false;
    } else {
        script.defer = true;
    }
    script.type = 'text/javascript';

    document.getElementsByTagName('head').item(0).appendChild(script);
}

function module(file) {
    this.include(file, true);
}
function script(file) {
    this.include(file, false);
}

module('https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js');
module('services/core/ajax-core.js');
module('services/core/base-service.js');
module('script/index.js');

/**
 * Services
 */
module('services/detail-service.js');
module('services/address-service.js');
module('services/nation-service.js');
module('services/region-service.js');
module('services/province-service.js');
module('services/delivery-service.js');
module('services/customer-service.js');
module('services/purchase-service.js');
module('services/contact-service.js');
module('services/product-service.js');
/**
 * Components
 */
module('component/detail/detail.js');
module('component/address/address.js');
module('component/nation/nation.js');
module('component/region/region.js');
module('component/province/province.js');
module('component/delivery/delivery.js');
module('component/customer/customer.js');
module('component/purchase/purchase.js');
module('component/contact/contact.js');
module('component/product/product.js')


function inject(component, destination) {
    $('#mainContainer').load('component/' + component, function () {
        destination.load();
    });
}