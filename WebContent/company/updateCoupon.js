angular.module('MyApp', ['ngMaterial', 'ngMessages', 'material.svgAssetsCache'])
  .config(function ($mdThemingProvider) {
      $mdThemingProvider.theme('red')
        .primaryPalette('red');

      $mdThemingProvider.theme('blue')
        .primaryPalette('blue');

  })
.controller('AppCtrl', function ($scope,  $interval) {
    $scope.theme = 'red';

    var isThemeRed = true;

    $interval(function () {
        $scope.theme = isThemeRed ? 'blue' : 'red';

        isThemeRed = !isThemeRed;
    }, 2000);

    $scope.showAdvanced = function (ev) {
        $mdDialog.show({
            controller: DialogController,
            templateUrl: 'dialog1.tmpl.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true
        })
        .then(function (answer) {
            $scope.status = 'You said the information was "' + answer + '".';
        }, function () {
            $scope.status = 'You cancelled the dialog.';
        });
    };

    function DialogController($scope, $mdDialog) {
        $scope.hide = function () {
            $mdDialog.hide();
        };

        $scope.cancel = function () {
            $mdDialog.cancel();
        };

        $scope.answer = function (answer) {
            $mdDialog.hide(answer);
        };
    }
});


/**
Copyright 2016 Google Inc. All Rights Reserved. 
Use of this source code is governed by an MIT-style license that can be foundin the LICENSE file at http://material.angularjs.org/HEAD/license.
**/