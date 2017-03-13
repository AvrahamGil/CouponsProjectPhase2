var app = angular.module("myApp").config(function ($routeProvider) {
    $routeProvider
   .when("/admin/companies", {
       templateUrl: "admin/companies.htm",
       controller: "companyControllerList"
   })
   .when("/admin/customers", {
       templateUrl: "admin/customers.htm",
       controller: "customersControllerList"
   })
   .when("/admin/coupons", {
       templateUrl: "admin/coupons.htm",
       controller: "couponList"

   })
    .when("/admin/getCompany", {
        templateUrl: "admin/getCompany.htm",
        controller: "adminController"

    }).when("/admin/getCustomer", {
        templateUrl: "admin/getCustomer.htm",
        controller: "adminController"

    }).when("/admin/getCoupon", {
        templateUrl: "admin/getCoupon.htm",
        controller: "adminController"

    }).when("/admin/myProfile", {
        templateUrl: "admin/getCompany.htm",
        controller: "companyController"

    }).when("/admin/myCustomers", {
        templateUrl: "admin/updateCustomer.htm",
        controller: "companyController"

    }).when("/admin/myCoupons", {
        templateUrl: "admin/updateCustomer.htm",
        controller: "companyController"

    });



});





angular.module("myApp").controller('adminController', adminController);
adminController.$inject = ['$http', '$scope'];
function adminController($http, $scope) {

    (
               $scope.name = function () {
                   $scope.name = 'Admin';
               })();


    $scope.getCompany = function () {
        var getC = JSON.stringify($scope.companyID);
        $http.get('/CouponsProjectPhase2/rest/api/Companies/companyID/' + getC).then(function (response) {
            $scope.companyDetail = response.data
        });
    }

    $scope.getCoupon = function () {
        var getC = JSON.stringify($scope.couponID);
        $http.get('/CouponsProjectPhase2/rest/api/Coupons/couponID/' + getC).then(function (response) {
            $scope.couponDetails = response.data
        });
    }
    $scope.getCustomer = function () {
        var getC = JSON.stringify($scope.customerID);
        $http.get('/CouponsProjectPhase2/rest/api/Customers/customerID/' + getC).then(function (response) {
            $scope.customerDetails = response.data
        });
    }

    $scope.logOut = function () {
        $http.post('/CouponsProjectPhase2/rest/Login/logOut').then(function (success) {
            alert("logout success");
        })
    }
    

}


angular.module("myApp").controller('companyControllerList', companyControllerList);
companyControllerList.$inject = ['$http', '$scope', '$location'];
function companyControllerList ($http, $scope, $location) {

    (
    $scope.getListOfAllCompanies = function () {
        $http.get('/CouponsProjectPhase2/rest/api/Companies').then(function (response) {
            $scope.companiesDetails = response.data.company;
        });
    })();


    $scope.deleteCompany = function (companyID, companyTitle) {
        bootbox.confirm({
            message: "Are you sure you want to delete " + companyTitle + " coupon?",
            buttons: {
                confirm: {
                    label: 'Yes',
                    className: 'btn-success',
                },
                cancel: {
                    label: 'No',
                    className: 'btn-danger'
                }
            }, callback: function (result) {
                if (result == true)  {
                    var deleteCo = JSON.stringify($scope.deleteCompanies);
                    var deleteCu = JSON.stringify($scope.deleteCustomers);

                    $http.delete('/CouponsProjectPhase2/rest/api/Companies/' + deleteCu + '/' + deleteCo).then(function (success)
                    {
                        bootbox.alert("Remove Done");
                    }).then(function (error) {
                        bootbox.alert("Remove Failed");
                    })

                }
                return;

            }
        });
    }

}


angular.module('myApp').controller('customersControllerList', customersControllerList);
customersControllerList.$inject = ['$http', '$scope', '$location'];
function customersControllerList($http, $scope, $location) {

    (
    $scope.getListOfAllCustomers = function () {
        $http.get('/CouponsProjectPhase2/rest/api/Customers').then(function (response) {
            $scope.customersDetails = response.data.customer;
        });

    })();


    $scope.deleteCustomer = function (customerID, customerName) {
        bootbox.confirm({
            message: "Are you sure you want to delete " + customerName + " coupon?",
            buttons: {
                confirm: {
                    label: 'Yes',
                    className: 'btn-success',
                },
                cancel: {
                    label: 'No',
                    className: 'btn-danger'
                }
            }, callback: function (result) {
                if (result == true) {
                    $http.delete('/CouponsProjectPhase2/rest/api/Customers/' + customerID).then(function (success) {
                        bootbox.alert("Remove Done");
                    }).then(function (error) {
                        bootbox.alert("Remove Failed");
                    })

                }
                return;

            }
        });
    }
}




angular.module("myApp").controller('couponList', couponControllerList);
couponControllerList.$inject = ['$http', '$scope', '$location'];
function couponControllerList($http, $scope, $location) {

    (
    $scope.getListOfAllCoupons = function () {
        $http.get('/CouponsProjectPhase2/rest/api/Coupons').then(function (response) {
            $scope.listOfCoupons = response.data.coupon;
        });

    })();


    $scope.deleteCoupon = function (couponID, couponTitle) {
        bootbox.confirm({
            message: "Are you sure you want to delete " + couponTitle + " coupon?",
            buttons: {
                confirm: {
                    label: 'Yes',
                    className: 'btn-success',
                },
                cancel: {
                    label: 'No',
                    className: 'btn-danger'
                }
            }, callback: function (result) {
                if (result == true) {
                    //       var deleteCo = JSON.stringify($scope.couponID);
                    $http.delete('/CouponsProjectPhase2/rest/api/Coupons/' + couponID).then(function (success) {
                        bootbox.alert("Remove Done");
                    }).then(function (error) {
                        console.error("Remove Failed");
                    })
                }
                return;

            }
        });
    }

}


	
