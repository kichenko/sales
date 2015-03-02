(function($) {
    var re = /([^&=]+)=?([^&]*)/g;
    var decodeRE = /\+/g; // Regex for replacing addition symbol with a space
    var decode = function(str) {
        return decodeURIComponent(str.replace(decodeRE, " "));
    };
    $.parseParams = function(query) {
        var params = {}, e;
        while (e = re.exec(query)) {
            var k = decode(e[1]), v = decode(e[2]);
            if (k.substring(k.length - 2) === '[]') {
                k = k.substring(0, k.length - 2);
                (params[k] || (params[k] = [])).push(v);
            }
            else
                params[k] = v;
        }
        return params;
    };
})(jQuery);

function convertPriceFloat2Integer(price) {
    return price * 100;
}

function convertPriceInteger2Float(price) {
    return price / 100;
}

function createJTableGridWithPages(gridId, title, actions, fields) {
    $(gridId).jtable({
        title: title,
        paging: true,
        pageSize: 10,
        pageSizes: [5, 10, 25, 50, 100],
        pageSizeChangeArea: true,
        selectOnRowClick: true,
        loadingAnimationDelay: 0,
        actions: actions,
        fields: fields,
        formCreated: function(event, data) {
            data.form.validationEngine();
        },
        formSubmitting: function(event, data) {
            return data.form.validationEngine('validate');
        },
        formClosed: function(event, data) {
            data.form.validationEngine('hide');
            data.form.validationEngine('detach');
        }

    }


    );
}

