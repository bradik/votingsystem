# Voting system for deciding where to have lunch
# API Reference

>1. [Users](#users)
>   - [Create a new user by admin](#createUser)
>   - [Update the user by admin](#updateUser)
>   - [Enable/disable the user by admin](#enableUser)
>   - [Delete the user by admin](#deleteUser)
>   - [Get a single user by id](#getUserById)
>   - [Get a single user by e-mail](#getUserByEmail)

>2. [Restaurants](#restaurants)
>   - [Get all restaurants](#getAllRestaurants)
>   - [Get a single restaurant by id](#getRestaurantById)
>   - [Get a single restaurant by name](#getRestaurantByName)
>   - [Create a new restaurant](#createRestaurant)
>   - [Update restaurant](#updateRestaurant)
>   - [Delete restaurant](#deleteRestaurant)

>3. [Menu](#menu)

>4. [Vote](#vote)

>5. [Errors](#errors)


# <a name="users">1. Users</a>


## <a name="createUser">Create a new user by admin</a>
Creates a new user by admin (requires authentication through basic auth and authorization as an admin).
#### HTTP request
    POST /rest/admin/users/create
#### Parameters
    No parameters.
#### Request body
    {
        "email":"user1@gmail.com",
        "password":"user1",
        "role":"USER"
    }    
    
In the JSON request body, include the following object properties:
- **email** | string | **required**  
The user e-mail.  
Must be unique.
- **password** | string | **required**  
The user password.  
Minimum length: 5.  
Maximum length: 64.
- **role** | user role.  
`["USER"]` - is the regular user.
#### Success response
    Status: 201 Created            
    {
      "id":3,
      "email":"user1@gmail.com",
      "password":"$2a$10$PuJvuFU00IKq72rOEpKGGuBC8FaubnldGmvSPq1TJ5l3RwVTkCpxC",
      "role":"USER"      
    }
Returns a user object in JSON format:
- **id** | number  
The ID of the user.  
- **email** | string  
The user e-mail (unique).  
- **password** | string  
The encoded user password.
- **role** | user role.  
Possible values:  
`["USER"]` - is the regular user.  
`["ADMIN"]` - is admin.
#### Error response
Status: 401 Unauthorized

Status: 409 Conflict.  
Returns an Error JSON object ([see here](#errors)).  

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s -X POST http://localhost:8080/votingsystem/rest/admin/users/create --user admin@gmail.com:admin \
    -H "Content-Type:application/json;charset=UTF-8" \
    -d '{
          "email": "user1@gmail.com",
          "password": "user1"
          "role":"USER"
        }'


## <a name="updateUser">Update the user by admin</a>
Updates the user details by admin (requires authentication through basic auth and authorization as an admin).
#### HTTP request
    PUT /rest/admin/users/:id
#### Parameters
Path parameters:
- **id** | string  
The ID of the user to update.
#### Request body
    {
        "id":3,
        "email":"user3@gmail.com",
        "password":"user333",
        "role":"ADMIN"
    }
In the JSON request body, include the following object properties:
- **id** | number  
The ID of the user to update.
- **name** | string | **required**  
The user e-mail.  
Must be unique.
- **password** | string | **required**  
The user password.  
Minimum length: 5.  
Maximum length: 64.
- **role** | value of role | **required**
Possible values:  
`["USER"]` - is the regular user.  
`["ADMIN"]` - is admin.
#### Success response
    Status: 200 OK
#### Error response
Status: 401 Unauthorized

Status: 409 Conflict.  
Returns an Error JSON object ([see here](#errors)).  

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s -X PUT http://localhost:8080/votingsystem/rest/admin/users/2 --user admin@gmail.com:admin \
    -H "Content-Type:application/json;charset=UTF-8" \
    -d '{
            "id":3,
            "email":"user3@gmail.com",
            "password":"user333",
            "role":"ADMIN"
         }'


## <a name="deleteUser">Delete the user by admin</a>
Deletes the user by admin (requires authentication through basic auth and authorization as an admin).
#### HTTP request
    DELETE /rest/admin/users/:id
#### Parameters
Path parameters:
- **id** | string  
The ID of the user to delete.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
#### Error response
Status: 401 Unauthorized

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s -X DELETE http://localhost:8080/votingsystem/rest/admin/users/2 \
    --user admin@gmail.com:admin


## <a name="getAllUsers">Get all users</a>
Lists all users  when authenticated through basic auth and authorized as an admin.
#### HTTP request
    GET /rest/admin/users
#### Parameters
    No parameters.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
        
    
Returns an array of user objects in JSON format:
- **id** | number  
The ID of the user.  
- **name** | string  
The user e-mail (unique).  
- **password** | string  
The encoded user password.
- **enabled** | boolean  
- **role** | value of role | **required**
Possible values:  
`["USER"]` - is the regular user.  
`["ADMIN"]` - is admin.

#### Error response
Status: 401 Unauthorized
#### Sample call
    curl -s http://localhost:8080/votingsystem/rest/admin/users --user admin@gmail.com:admin


## <a name="getUserById">Get a single user by id</a>
Shows details for a user, by ID. Requires authentication through basic auth and authorization as an admin .
#### HTTP request
    GET /rest/admin/users/:id
#### Parameters
Path parameters:
- **id** | string  
The ID of the user to show.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK
    {    
        "id":2,
        "email":"user@gmail.com",
        "password":"$2a$10$arAXFp.OY1WzRmEGyoovz.CLPSl/gWDxJXPDcir1mg1jBrP.lsHam",
        "role":"USER"
    }    
Returns an array of user objects in JSON format:
- **id** | number  
The ID of the user.  
- **name** | string  
The user e-mail (unique).  
- **password** | string  
The encoded user password.
- **enabled** | boolean  
- **role** | value of role | **required**
Possible values:  
`["USER"]` - is the regular user.  
`["ADMIN"]` - is admin.

#### Error response
Status: 401 Unauthorized

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s http://localhost:8080/rest/admin/users/2 --user admin@gmail.com:admin
    


## <a name="getUserByEmail">Get a single user by e-mail</a>
Shows details for a user, by e-mail. Requires authentication through basic auth and authorization as an admin.
#### HTTP request
    GET /rest/admin/users/by
#### Parameters
Query parameters:
- **email** | string | **required**  
The e-mail of the user to show.
#### Request body
    Do not supply a request body with this method.
#### Success response
    Status: 200 OK        
    
    {
        "id":2,
        "email":"user@gmail.com",
        "password":"$2a$10$arAXFp.OY1WzRmEGyoovz.CLPSl/gWDxJXPDcir1mg1jBrP.lsHam",
        "role":"USER"
    }
Returns a user object in JSON format:
- **id** | number  
The ID of the user.  
- **name** | string  
The user e-mail (unique).  
- **password** | string  
The encoded user password.
- **enabled** | boolean  
- **role** | value of role | **required**
Possible values:  
`["USER"]` - is the regular user.  
`["ADMIN"]` - is admin.
#### Error response
Status: 401 Unauthorized

Status: 422 Unprocessable Entity.  
Returns an Error JSON object ([see here](#errors)).
#### Sample call
    curl -s http://localhost:8080/rest/admin/users/by?email=user@gmail.com \
    --user admin@gmail.com:admin


# <a name="restaurants">2. Restaurants</a>







# <a name="errors">5. Errors</a>
This API returns standard HTTP status codes for error responses.  

The response body for all errors except Identity errors includes additional error details in this format:

    {   
        "url":"http://localhost/rest/admin/users/create",
        "type":"VALIDATION_ERROR",
        "details":
        [
            "password length must be between 5 and 64"
        ]
    }
    
Where:  
- **url** | stirng  
Error documentation link.  
- **type** | string  
Error name.  
- **details** | array of strings  
Error detailed description.