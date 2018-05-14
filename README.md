To run project you should execute: 
  mvn spring-boot:run  
Application will start at port 8080.  
  
There are folowing endpoints:  
1.  Create new user  
    URL /user/register  
    METHOD POST  
    PARAMETERS email, password  
      
2.  Login  
    URL /user/login  
    METHOD POST  
    PARAMETERS email, password  
      
3.  Logout  
    URL /user/logout  
    METHOD POST  
    PARAMETERS -  
  
4.  Get list of actors by given query  
    URL /actor/search  
    METHOD GET  
    PARAMETERS query, page(optional)  
  
5.  Add favourite actor for logged user  
    URL /actor/addFavourite  
    METHOD POST  
    PARAMETERS actorId  
      
6.  Remove favourite actor for logged user  
    URL /actor/removeFavourite  
    METHOD POST  
    PARAMETERS actorId  
      
7.  Get list of movies by given query  
    URL /movie/search  
    METHOD GET  
    PARAMETERS query, page(optional)  
  
8.  Mark movie as watched  
    URL /movie/markAsWatched  
    METHOD POST  
    PARAMETERS movieId  
      
9.  Get movie recommendations  
    URL /movie/getRecommendations  
    METHOD POST  
    PARAMETERS year, month  
  
