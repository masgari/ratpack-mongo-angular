'use strict';

angular.module('initApp')
  .controller('PersonCtrl', function ($scope) {
    $scope.personList = [
      {name:'Thor', availabilityCount: 9},
      {name:'Captain America', availabilityCount: 8},
      {name:'Black Widow', availabilityCount: 7},
      {name:'Hawkeye', availabilityCount: 6},
      {name:'Hulk', availabilityCount: 5},
      {name:'Ant Man', availabilityCount: 5}
    ];
  });
