
function getChildSaleGridActions(productId) {
    return {
        listAction: function(postData, jtParams) {
            return $.Deferred(function($dfd) {
                $.ajax({
                    url: window.contextPath + '/products/' + productId,
                    type: 'GET',
                    dataType: 'json',
                    data: null,
                    success: function(response) {

                        var result = {
                            Result: 'ERROR',
                            Message: 'Ошибка получения данных',
                            Records: []
                        };

                        if (response) {
                            if (response.success === true) {
                                result.Result = 'OK';

                                //переводим копейки в рубли
                                response.data.price = convertPriceInteger2Float(response.data.price);
                                result.Records.push(response.data);
                            }
                            else
                            {
                                result.Result = 'ERROR';
                                console.log('jtable error:' + response.message);
                            }
                        }

                        $dfd.resolve(result);
                    },
                    error: function() {
                        console.log("jtable: error listAction...");
                        $dfd.reject();
                    }
                });
            });
        }
    };
}

function getChildSaleGridFields(productId) {
    return {
        id: {
            key: true,
            type: 'hidden',
            defaultValue: productId
        },
        name: {
            title: 'Наименование',
            width: '20%'
        },
        price: {
            title: 'Цена',
            width: '30%'
        }
    };
}

function getChildSaleGridTitle(productName) {
    return 'Продукт - ' + productName;
}

$(document).ready(function() {

    var gridId = '#saleGrid';

    var parentFields = {
        id: {
            key: true,
            create: false,
            edit: false,
            list: false
        },
        products: {
            title: '',
            width: '1%',
            sorting: false,
            edit: false,
            create: false,
            display: function(saleData) {
                var $img = $('<img src="' + window.contextPath + '/images/list.png" title="Продукт" style = "cursor: pointer;"/>');
                $img.click(function() {
                    $(gridId).jtable('openChildTable',
                            $img.closest('tr'),
                            {
                                title: getChildSaleGridTitle(saleData.record.productName),
                                actions: getChildSaleGridActions(saleData.record.productId),
                                fields: getChildSaleGridFields(saleData.record.productId),
                                loadingAnimationDelay: 0
                            },
                    function(data) {
                        data.childTable.jtable('load');
                    });
                });

                return $img;
            }
        },
        productName: {
            title: 'Товар',
            width: '20%',
            create: false,
            edit: false
        },
        productId: {
            title: 'Товар',
            create: true,
            edit: true,
            list: false,
            visibility: 'hidden',
            inputClass: 'validate[required]',
            options: function() {
                var products = [];

                $.ajax({
                    url: window.contextPath + '/products/list/all',
                    type: 'GET',
                    dataType: 'json',
                    data: null,
                    async: false,
                    success: function(response) {
                        if (response.success !== true) {
                            return [];
                        }

                        response.data.forEach(function(e) {
                            products.push({
                                DisplayText: e.name,
                                Value: e.id
                            });
                        });
                    }
                });

                return products;
            }
        },
        date: {
            title: 'Дата',
            width: '20%',
            type: 'date',
            displayFormat: 'yy-mm-dd',
            inputClass: 'validate[required,custom[date]]'
        },
        quantity: {
            title: 'Количество',
            width: '30%',
            inputClass: 'validate[required]'
        },
        cost: {
            title: 'Стоимость',
            width: '30%',
            create: false,
            edit: false
        }
    };

    var parentActions = {
        listAction: function(postData, jtParams) {
            return $.Deferred(function($dfd) {
                $.ajax({
                    url: window.contextPath + '/sales/list?' + 'page=' + (Math.floor(jtParams.jtStartIndex / jtParams.jtPageSize) + 1) + '&size=' + jtParams.jtPageSize,
                    type: 'GET',
                    dataType: 'json',
                    data: null,
                    success: function(response) {

                        var result = {
                            Result: 'ERROR',
                            Message: 'Ошибка получения данных',
                            Records: [],
                            TotalRecordCount: 0
                        };

                        if (response) {
                            if (response.success === true) {
                                result.Result = 'OK';

                                //переводим копейки в рубли
                                response.data.forEach(function(obj) {
                                    obj.cost = convertPriceInteger2Float(obj.cost);
                                });

                                result.Records = response.data;
                                result.TotalRecordCount = response.total;
                            }
                            else
                            {
                                result.Result = 'ERROR';
                                console.log('jtable error:' + response.message);
                            }
                        }

                        $dfd.resolve(result);
                    },
                    error: function() {
                        console.log("jtable: error listAction...");
                        $dfd.reject();
                    }
                });
            });
        },
        createAction: function(postData) {
            var dto = $.parseParams(postData);
            return $.Deferred(function($dfd) {
                $.ajax({
                    url: window.contextPath + '/sales/insert',
                    type: 'POST',
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    data: JSON.stringify(dto),
                    success: function(response) {

                        var result = {
                            Result: 'ERROR',
                            Message: 'Ошибка получения данных',
                            Record: null
                        };

                        if (response) {
                            if (response.success === true) {
                                result.Result = 'OK';
                                result.Record = response.data;
                            }
                            else
                            {
                                result.Result = 'ERROR';
                                console.log('jtable errore:' + response.message);
                            }
                        }

                        $dfd.resolve(result);
                    },
                    error: function() {
                        console.log("jtable: error createAction...");
                        $dfd.reject();
                    }
                });
            });
        }
    };

    createJTableGridWithPages(gridId, 'Продажи', parentActions, parentFields);
    $(gridId).jtable('load');
});
