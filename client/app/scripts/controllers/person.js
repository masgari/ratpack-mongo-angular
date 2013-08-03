'use strict';

angular.module('initApp')
  .controller('PersonCtrl', function ($scope) {
    $scope.personList = [
      {name:'Thor', image:''},
      {name:'Captain America', image:''},
      {name:'Black Widow', image:''},
      {name:'Hawkeye', image:''},
      {name:'Hulk', image:''},
      {name:'Ant Man', image:''}
    ];
    
    $scope.selectedPerson = {
      picture : "http://static.comicvine.com/uploads/original/11/117763/2786642-tumblr_mg2iesn3go1qiknbco1_1280.jpg",
      name : "Thor",
      properties : {
        department : "Avengers",
        job : "God of Thunder",
        sex: 'male',
        origin: 'Asgard',
        dislikes: 'loki'
      },
      questions:[{
        q:'Favorite Movie?',
        a:'Avengers Assemble'
      },{
        q:'Favorite Tool',
        a:'Hammer'
      },{
        q:'Last great holiday',
        a:'not Florida'
      }
      ]
    }
    
});