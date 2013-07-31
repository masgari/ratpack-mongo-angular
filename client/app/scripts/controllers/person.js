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
    
    $scope.selectedPerson = {
      picture : "http://static.comicvine.com/uploads/original/11/117763/2786642-tumblr_mg2iesn3go1qiknbco1_1280.jpg",
      name : "Thor",
      department : "Avengers",
      job : "God of Thunder",
      availability : [
         'jan 2, 2014',
         'may 4, 2014'
      ]
    }
    
  });
