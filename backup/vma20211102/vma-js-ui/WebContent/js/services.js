var originalInfoServices = angular.module('originalInfoServices', ['ngResource']);

originalInfoServices.factory('OriginalInfo', ['$resource',
  function($resource){
    return $resource('originalInfo/OriginalInfo.json', {}, {
      query: {method:'GET', params:{id:'id'}, isArray:true},
      transformResponse: []
    });
  }]);