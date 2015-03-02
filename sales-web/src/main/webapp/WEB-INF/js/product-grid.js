
function getChildProductGridActions(productId) {
    return {
        listAction: function(postData, jtParams) {
            return $.Deferred(function($dfd) {
                $.ajax({
                    url: window.contextPath + '/sales/list/' + productId,
                    type: 'GET',
                    dataType: 'json',
                    data: null,
                    success: function(response) {

                        var result = {
                            Result: 'ERROR',
                            Message: 'Ошибка получения данных tr ',
                            Records: []
                        };

                        if (response) {
                            if (response.success === true) {
                                result.Result = 'OK';

                                //переводим копейки в рубли
                                response.data.forEach(function(obj) {
                                    obj.cost = convertPriceInteger2Float(obj.cost);
                                });

                                result.Records = response.data;
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

function getChildProductGridFields(saleId) {
    return {
        id: {
            key: true,
            type: 'hidden',
            defaultValue: saleId
        },
        productName: {
            title: 'Товар',
            width: '20%'
        },
        date: {
            title: 'Дата',
            width: '20%',
            type: 'date',
            displayFormat: 'yy-mm-dd',
            create: false,
            edit: false
        },
        quantity: {
            title: 'Количество',
            width: '30%'
        },
        cost: {
            title: 'Стоимость',
            width: '30%'
        }
    };
}

function getChildProductGridTitle(productName) {
    return 'Продажи - ' + productName;
}

$(document).ready(function() {

    var gridId = '#productGrid';

    var parentFields = {
        id: {
            key: true,
            create: false,
            edit: false,
            list: false
        },
        sales: {
            title: '',
            width: '1%',
            sorting: false,
            edit: false,
            create: false,
            display: function(productData) {
                var $img = $('<img src="' + window.contextPath + '/images/list.png" title="Продажи" style = "cursor: pointer;"/>');
                $img.click(function() {
                    $(gridId).jtable('openChildTable',
                            $img.closest('tr'),
                            {
                                title: getChildProductGridTitle(productData.record.name),
                                actions: getChildProductGridActions(productData.record.id),
                                fields: getChildProductGridFields(productData.record.id),
                                loadingAnimationDelay: 0
                            },
                    function(data) {
                        data.childTable.jtable('load');
                    });
                });

                return $img;
            }
        },
        name: {
            title: 'Наименование',
            width: '70%',
            inputClass: 'validate[required]'
        },
        price: {
            title: 'Цена',
            inputClass: 'validate[required]'
        }
    };

    var parentActions = {
        listAction: function(postData, jtParams) {
            return $.Deferred(function($dfd) {
                $.ajax({
                    url: window.contextPath + '/products/list?' + 'page=' + (Math.floor(jtParams.jtStartIndex / jtParams.jtPageSize) + 1) + '&size=' + jtParams.jtPageSize,
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
                                    obj.price = convertPriceInteger2Float(obj.price);
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
            //рубли в копейки
            dto.price = convertPriceFloat2Integer(dto.price);

            return $.Deferred(function($dfd) {
                $.ajax({
                    url: window.contextPath + '/products/insert',
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
        },
        updateAction: function(postData) {
            var dto = $.parseParams(postData);
            //рубли в копейки
            dto.price = convertPriceFloat2Integer(dto.price);

            return $.Deferred(function($dfd) {
                $.ajax({
                    url: window.contextPath + '/products/update',
                    type: 'POST',
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'json',
                    data: JSON.stringify(dto),
                    success: function(response) {

                        var result = {
                            Result: 'ERROR',
                            Message: 'Ошибка получения данных'
                        };

                        if (response) {
                            if (response.success === true) {
                                result.Result = 'OK';
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
                        console.log("jtable: error createAction...");
                        $dfd.reject();
                    }
                });
            });
        }
    };

    createJTableGridWithPages(gridId, 'Продукция', parentActions, parentFields);
    $(gridId).jtable('load');
});