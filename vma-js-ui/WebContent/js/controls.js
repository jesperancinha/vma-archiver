/**
 * 
 */

var vmaApp = angular.module('vmaApp',['smart-table','originalInfoServices','ngRoute']);

vmaApp.controller('titlesCtrl', ['$scope', function ($scope) {
    $scope.titlesCollections = [
        {id: '1', title: 'A new hope'},
        {id: '2', title: 'Empire strikes back'},
        {id: '3', title: 'The return of the Jedi'}
    ];
}]);


vmaApp.controller('titlesRestCtrl', ['$scope', 'OriginalInfo', function($scope, OriginalInfo) {
  $scope.titlesCollections = OriginalInfo.query();
  $scope.orderProp = 'id';
}]);

vmaApp.controller('titlesRestDetailCtrl', ['$scope', '$routeParams', 'OriginalInfo', function($scope, $routeParams, OriginalInfo) {
  $scope.originalInfo = OriginalInfo.get({id: $routeParams.id}, function(OriginalInfo) {
    $scope.title = originalInfo.title;
  });
}]);