## Voting system for deciding where to have lunch
## API Reference

## Users 

#### Create a new user    
    curl -s -X POST http://localhost:8080/rest/admin/users/create --user admin@gmail.com:admin 
    -H 'Content-Type:application/json' 
    -d '{"email":"user1@gmail.com","password":"user1","role":"USER"}'       
#### Update the user
    curl -s -X PUT http://localhost:8080/rest/admin/users/100002 --user admin@gmail.com:admin 
    -H 'Content-Type:application/json'    
    -d '{"id":100002,"email":"user@gmail.com","password":"user","role":"USER"}'
#### Delete the user
    curl -s -X DELETE http://localhost:8080/rest/admin/users/100002 --user admin@gmail.com:admin
#### Get all users
    curl -s http://localhost:8080/rest/admin/users --user admin@gmail.com:admin
#### Get a single user by id
    curl -s http://localhost:8080/rest/admin/users/100001 --user admin@gmail.com:admin
#### Get a single user by e-mail
    curl -s http://localhost:8080/rest/admin/by?email=user@gmail.com --user admin@gmail.com:admin
## Restaurants
#### Create a new restaurant
    curl -s -X POST http://localhost:8080/rest/admin/bars --user admin@gmail.com:admin 
        -H 'Content-Type:application/json' 
        -d '{"name":"new name","address":"new address"}'
#### Update the restaurant
    curl -s -X PUT http://localhost:8080/rest/admin/bars/100044 --user admin@gmail.com:admin 
        -H 'Content-Type:application/json'    
        -d '{"id":100044,"name":"new name","address":"new address"}'
#### Delete the restaurant
    curl -s -X DELETE http://localhost:8080/rest/admin/bars/100044 --user admin@gmail.com:admin
#### Get all restaurants
    curl -s http://localhost:8080/rest/bars --user user@gmail.com:user
#### Get a single restaurant by id
    curl -s http://localhost:8080/rest/bars/100044 --user user@gmail.com:user
#### Get a single restaurant by name
    curl -s http://localhost:8080/rest/bars/by?name@'bar name' --user user@gmail.com:user
## Menu
#### Create or update a new item in the menu
    curl -s -X PUT http://localhost:8080/rest/admin/bars/100044/meals --user admin@gmail.com:admin 
        -H 'Content-Type:application/json' 
        -d '{"mealName":"Meal name","price":150.7}'
#### Delete the all menu items in the restaurant on current date 
    curl -s -X DELETE http://localhost:8080/rest/admin/bars/100044/meals --user admin@gmail.com:admin
#### Delete menu items by
    curl -s -X DELETE http://localhost:8080/rest/admin/bars/100044/meals/by?date=2017-11-18@name='meal name' --user admin@gmail.com:admin
#### Get all menu items
    curl -s http://localhost:8080/rest/user/bars/100044/meals --user user@gmail.com:user
#### Get all menu items by id or name meal  
    curl -s http://localhost:8080/rest/user/bars/100044/meals/by?id=100087 --user user@gmail.com:user
    curl -s http://localhost:8080/rest/user/bars/100044/meals/by?name='meal name' --user user@gmail.com:user
## Vote
##### Vote for the restaurant
    curl -s -X PUT http://localhost:8080/rest/user/votes/bar/100044 --user admin@gmail.com:admin
##### Get curent vote results
    curl -s http://localhost:8080/rest/user/votes/results --user user@gmail.com:user
##### Get vote results by date
    curl -s http://localhost:8080/rest/user/votes/results/by?date=2017-11-17 --user user@gmail.com:user
 
