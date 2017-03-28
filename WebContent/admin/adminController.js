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
       controller: "coupons"

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
adminController.$inject = ['$http', '$scope' ,'userService'];
function adminController($http, $scope, userService) {

    (
               $scope.name = function () {
                   $scope.name = 'Admin';
               })();


    $scope.getCompany = function () {
        var getC = JSON.stringify($scope.companyID);
        $http.get('/CouponsProjectPhase2/rest/api/Companies/companyID/' + getC).then(function successCall(response) {
            $scope.companyDetail = response.data
        }, function errorCall(response) {
            bootbox.alert(response.data.message);
        });
    }

    $scope.getCoupon = function () {
        var getC = JSON.stringify($scope.couponID);
        $http.get('/CouponsProjectPhase2/rest/api/Coupons/couponID/' + getC).then(function successCall(response) {
            $scope.couponDetails = response.data
        }, function errorCall(response) {
            bootbox.alert(response.data.message);
        });
    }
    $scope.getCustomer = function () {
        var getC = JSON.stringify($scope.customerID);
        $http.get('/CouponsProjectPhase2/rest/api/Customers/customerID/' + getC).then(function successCall(response) {
            $scope.customerDetails = response.data
        }, function errorCall(response) {
            bootbox.alert(response.data.message);
        });
    }

    $scope.logOut = function () {
        $http.post('/CouponsProjectPhase2/rest/Login/logOut').then(function successCall(data) {
            bootbox.alert("<html><body><center>Logout Success</center></body></html>");
        }, function errorCall(response) {
            bootbox.alert(response.data.message);
        })
    }
    

}


angular.module("myApp").controller('companyControllerList', companyControllerList);
companyControllerList.$inject = ['$http', '$scope', '$location'];
function companyControllerList ($http, $scope, $location) {

    (
    $scope.getListOfAllCompanies = function () {
        $http.get('/CouponsProjectPhase2/rest/api/Companies').then(function successCall(response) {
            $scope.company = [];
            $scope.companiesDetails = $scope.company.concat(response.data.company); 
        }, function errorCall(response) {
            bootbox.alert(response.data.message);
        });
    })();


    $scope.deleteCompany = function (companyID) {
        bootbox.confirm({
            message: "Delete Company ?",
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

                    $http.delete('/CouponsProjectPhase2/rest/api/Companies/' + companyID).then(function successCall(data) {
                        bootbox.alert("Remove Done");
                    }, function errorCall(response) {
                        bootbox.alert(response.data.message);
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
        $http.get('/CouponsProjectPhase2/rest/api/Customers').then(function successCall(response) {
            $scope.customer = [];
            $scope.customersDetails = $scope.customer.concat(response.data.customer);
        }, function errorCall(response) {
            bootbox.alert(response.data.message);
        });

    })();


    $scope.deleteCustomer = function (customerID) {
        bootbox.confirm({
            message: "Delete Customer ? ",
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
                    $http.delete('/CouponsProjectPhase2/rest/api/Customers/' + customerID).then(function successCall(data) {
                        bootbox.alert("Remove Done");
                    }, function errorCall(response) {
                        bootbox.alert(response.data.message);
                    })
                }
                return;

            }
        });
    }
}




angular.module("myApp").controller('coupons', coupons);
coupons.$inject = ['$http', '$scope', '$location'];
function coupons($http, $scope, $location) {

    (
    $scope.getListOfAllCoupons = function () {
        $http.get('/CouponsProjectPhase2/rest/api/Coupons').then(function successCall(response) {
            $scope.coupons = [];
            $scope.listOfCoupons = $scope.coupons.concat(response.data.coupon);
        }, function errorCall(response) {
            bootbox.alert(response.data.message);
        });

    })();


    $scope.deleteCoupons = function (couponID) {
        bootbox.confirm({
            message: "Delete Coupon ?",
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
                    $http.delete('/CouponsProjectPhase2/rest/api/Coupons/' + couponID).then(function successCall (data) {
                        bootbox.alert("Remove Done");
                    }, function errorCall(response) {
                        bootbox.alert(response.data.message);
                    })
                }
                return;

            }
        });
    }

    $scope.couponPic = function (couponTypeByNumber) {
        if (couponTypeByNumber == 1) {
            bootbox.alert("<html><body><img src='http://www.restaurantfanatix.com/wp-content/uploads/2015/10/luquin.jpg' alt='Holiday'</body></html>")
        } else if (couponTypeByNumber == 2) {
            bootbox.alert("<html><body><img src='http://www.aviationexplorer.com/airline_coupon_code_promos.png' alt='Holiday'</body></html>")
        } else if (couponTypeByNumber == 3) {
            bootbox.alert("<html><body><img src='http://hunt4freebies.com/coupons/wp-content/uploads/2013/12/Chilis-Holiday.png' alt='Holiday'</body></html>")
        }

    }

}


	
