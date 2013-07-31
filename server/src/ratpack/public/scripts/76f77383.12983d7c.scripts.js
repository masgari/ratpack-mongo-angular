'use strict';
angular.module('initApp', []).config([
  '$routeProvider',
  function (a) {
    a.when('/', {
      templateUrl: 'views/main.html',
      controller: 'MainCtrl'
    }).when('/person', {
      templateUrl: 'views/person.html',
      controller: 'PersonCtrl'
    }).otherwise({ redirectTo: '/' });
  }
]), angular.module('initApp').controller('MainCtrl', [
  '$scope',
  function (a) {
    a.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  }
]), angular.module('initApp').controller('PersonCtrl', [
  '$scope',
  function (a) {
    a.personList = [
      {
        name: 'Thor',
        availabilityCount: 9
      },
      {
        name: 'Captain America',
        availabilityCount: 8
      },
      {
        name: 'Black Widow',
        availabilityCount: 7
      },
      {
        name: 'Hawkeye',
        availabilityCount: 6
      },
      {
        name: 'Hulk',
        availabilityCount: 5
      },
      {
        name: 'Ant Man',
        availabilityCount: 5
      }
    ];
  }
]);