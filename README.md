# Backend of Student Management System



> ### **VERSIONS:**
>
> **Spring Boot version is 3.0.0**
>
> **and**
>
> **Java version 17**


> ### Schema of Student Management System 

![schema of semester](https://github.com/MarinaBeder/semester./assets/66501215/abb36666-938b-4abf-8c69-0f4000e1e8ae)

#####     **`Note` the table of `messages` is used to store all messages that we will send to the frontend from the backend  **

#####     **`Please` make import for this file in database to show for you messages that come from backend**

##### this file is in folder called `messages`



### In this project I create controller classes in two forms 

- **First form using @controller**

- **Second form using @RestController** 

  ##### **`Note`I use two annotations to confirm that I can write code using @RestController and @controller**
  
- ##### `Note` I make validation on requests 


### In this project I integrate Swagger for API documentation.

- ##### To use swagger you can use this link 

  #### [http://localhost:9090/swagger-ui/index.html#/](http://localhost:9090/swagger-ui/index.html#/ ) 

  

> ### **If you want to try this project on localhost**



- #### Go to application properties :

  1. ##### put username of your database

  2. #####  put password of your database

  3. ##### create new schema with name:  `semester`

  4. ##### this application will run on server 9090 **you can change it
  
  5. ##### there is file called `messages` in folder called `messages` please make import this file in database to show for you `Messages that come from backend`
