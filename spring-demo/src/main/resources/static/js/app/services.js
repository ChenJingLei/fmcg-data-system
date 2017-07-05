/**
 * Created by cjl20 on 2016/7/6.
 */

/* Services */

var FMCGSystemServices = angular.module('FMCGSystemServices', ['ngResource']);

FMCGSystemServices.factory('Product', ['$resource',
    function ($resource) {
        return $resource('http://localhost:8088/product/:productId', {}, {
            query: {method: 'GET', params: {productId: 'getAll'}, isArray: true}
        });
    }]);

FMCGSystemServices.factory('Crawler', ['$resource',
    function ($resource) {
        return $resource('http://localhost:8080/good/getAll', {}, {
            query: {method: 'GET', params: {uid: 1}, isArray: true}
        });
    }]);

FMCGSystemServices.factory('TaoBao', ['$resource',
    function ($resource) {
        return $resource('http://localhost:8080/good/getTaoBaoAll', {}, {
            query: {method: 'GET', params: {}, isArray: true}
        });
    }]);

FMCGSystemServices.factory('Mall', ['$resource',
    function ($resource) {
        return $resource('http://localhost:8080/good/getMallAll', {}, {
            query: {method: 'GET', params: {}, isArray: true}
        });
    }]);

FMCGSystemServices.factory('JD', ['$resource',
    function ($resource) {
        return $resource('http://localhost:8080/good/getJDAll', {}, {
            query: {method: 'GET', params: {}, isArray: true}
        });
    }]);