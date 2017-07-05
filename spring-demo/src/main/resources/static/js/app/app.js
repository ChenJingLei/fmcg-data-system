'use strict';

/* App Module */

var FMCGSystemApp = angular.module('FMCGSystemApp', [
    'ngRoute',
    'FMCGSystemControllers',
    'FMCGSystemServices'
]);

FMCGSystemApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: 'index/index.html',
        }).when('/follow/taobao', {
            templateUrl: 'follow/taobao.html',
            controller: 'TaoBaoGoodListCtrl'
        }).when('/follow/tmall', {
            templateUrl: 'follow/tmall.html',
            controller: 'MallGoodListCtrl'
        }).when('/follow/jd', {
            templateUrl: 'follow/jd.html',
            controller: 'JDGoodCtrlListCtrl'
        }).when('/follow/yhd', {
            templateUrl: 'follow/yhd.html',
        }).when('/follow/suning', {
            templateUrl: 'follow/suning.html',
        }).when('/follow/womai', {
            templateUrl: 'follow/womai.html',
        }).when('/follow/chblt', {
            templateUrl: 'follow/chblt.html',
        }).when('/follow/amazon', {
            templateUrl: 'follow/amazon.html',
        }).when('/keyword/jd', {
            templateUrl: 'keyword/jd.html'
        }).otherwise({
            redirectTo: '/'
        });
    }]);
