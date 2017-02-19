<!doctype html>
<html ng-app="timeseriesFrontend">
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular.min.js"></script>
		<script src="timeseries.js"></script>
	</head>
	<body>
		<div ng-controller="timeseriesController">
			<h2>Identifier: {{head.identifier}}</h2>
		</div>
	</body>
</html>