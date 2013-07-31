'use strict';

angular.module('initApp', [])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/person', {
        templateUrl: 'views/person.html',
        controller: 'PersonCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
