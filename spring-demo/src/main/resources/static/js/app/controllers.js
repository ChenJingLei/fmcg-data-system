'use strict';

/* Controllers */

var FMCGSystemControllers = angular.module('FMCGSystemControllers', []);


FMCGSystemControllers.controller('FollowCtrl', ['$scope', '$http', 'Crawler',
    function ($scope, $http, Crawler) {
        $scope.goods = Crawler.query();

        $scope.deleteGoodByUidAndGid = function (uid, gid) {
            $http({
                method: 'GET',
                url: '/good/deleteByUidAndGid',
                params: {
                    'uid': uid, 'gid': gid
                },
                headers: {
                    'Content-Type': "application/json;charset=UTF-8"
                },
            }).then(function () {

            }, function () {

            });
        }

        $scope.getGoodsByAddress = function (address, platform) {
            if (address) {
                //数据请求
                $http({
                    method: 'GET',
                    url: '/good/addUrl',
                    params: {
                        'url': address, 'uid': 1, 'platform': platform
                    },
                    headers: {
                        'Content-Type': "application/json;charset=UTF-8"
                    },
                }).then(function () {

                }, function () {

                });

                $scope.error = "";
                $scope.address = "";
                $('.bs-address-modal-sm').modal('hide')

                window.location.href = "http://localhost:8080/user/main#!/follow/jd";
            } else {
                $scope.error = "请输入正确的网址";
            }
        };

        $scope.getGoodsByKeyword = function (keyword, platform) {

            $('.bs-keyword-modal-sm').modal('hide')
        };

        $scope.getLocalTime = function (nS) {
            return new Date(nS).toLocaleString().replace(/年|月/g, "-").replace("日", "");
        }
    }]
);

FMCGSystemControllers.controller('JDGoodListCtrl', ['$scope',
    function ($scope) {

        $scope.getJdGoodByName = function (keyword, platform) {
            $http({
                method: 'GET',
                url: '/good/addUrl',
                params: {
                    'url': address, 'uid': 1, 'platform': platform
                },
                headers: {
                    'Content-Type': "application/json;charset=UTF-8"
                },
            }).then(function () {

            }, function () {

            });

        };

        $scope.getLocalTime = function (nS) {
            return new Date(nS).toLocaleString().replace(/年|月/g, "-").replace("日", "");
        }
    }]
);

FMCGSystemControllers.controller('TaoBaoGoodListCtrl', ['$scope', 'TaoBao',
    function ($scope, TaoBao) {

        $scope.goods = TaoBao.query();

        $scope.getLocalTime = function (nS) {
            return new Date(nS).toLocaleString().replace(/年|月/g, "-").replace("日", "");
        }
    }]
);

FMCGSystemControllers.controller('MallGoodListCtrl', ['$scope', 'Mall',
    function ($scope, Mall) {

        $scope.goods = Mall.query();

        $scope.getLocalTime = function (nS) {
            return new Date(nS).toLocaleString().replace(/年|月/g, "-").replace("日", "");
        }
    }]
);

FMCGSystemControllers.controller('JDGoodCtrlListCtrl', ['$scope', 'JD',
    function ($scope, JD) {

        $scope.goods = JD.query();

        $scope.getLocalTime = function (nS) {
            return new Date(nS).toLocaleString().replace(/年|月/g, "-").replace("日", "");
        }
    }]
);


FMCGSystemControllers.controller('ProductListCtrl', ['$scope', 'Product',
    function ($scope, Product) {
        $scope.products = Product.query();

        $scope.getLocalTime = function (nS) {
            return new Date(nS).toLocaleString().replace(/年|月/g, "-").replace("日", "");
        }
    }]
);

FMCGSystemControllers.controller('ProductDetailCtrl', ['$scope', '$routeParams', 'Product',
    function ($scope, $routeParams, Product) {
        $scope.products = Product.get({productId: $routeParams.productId}, function (product) {
            $scope.product = product;

            $scope.getLocalTime = function (nS) {
                return new Date(nS).toLocaleString().replace(/年|月/g, "-").replace("日", "");
            }
        })
    }]
);

