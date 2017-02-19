angular.module('timeseriesFrontend', []).controller('timeseriesController', function($scope, $http) {
	$scope.name = 'Sven';
	$http.get('http://localhost:8080/timeseries-webservice/DomainService/TimeseriesHead/Uuid/abc').then(function(response) {
		 $scope.head = angular.fromJson(response.data).TimeseriesHead;
	});
});