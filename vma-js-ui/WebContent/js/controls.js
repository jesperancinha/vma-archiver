/**
 * 
 */

var vmaApp = angular.module('vmaApp',['smart-table']);

vmaApp.controller('titlesCtrl', ['$scope', function (scope) {
    scope.titlesCollections = [
        {id: '1', title: 'A new hope'},
        {id: '2', title: 'Empire strikes back'},
        {id: '3', title: 'The return of the Jedi'}
    ];
}]);