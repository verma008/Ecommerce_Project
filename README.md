 **************************** Backend REST API Documentation *******************************************
This project is a secure backend REST API for an e-commerce application that supports user authentication, product management, and database integration. Also, I have enabled proper logging  using SLF4J/logback for debugging and monitoring

Database Configuration:
    The database schema for the Ecommerce_Project contains two tables:
    User Table: Stores user details for authentication.
    Product Table: Stores product information such as name, price, and image URL.

Authentication Workflow:
   1. User Registration (Sign-up API): Endpoint for user registration to enable authentication functionality.
      Post API: http://localhost:9999/user/signup
      Request Body:
      {
          "firstName":"Archana",
          "lastName":"Verma",
          "email":"archana@gmail.com",
          "password":"archana"
      }

  2. User Login & Token Generation (Login API): Endpoint for user login, which generates a JWT token upon successful authentication.
     POST: http://localhost:9999/user/login
     Request Body:
      {
          "email": "archana@gmail.com",
          "password": "archana"
      }

**************************Product Management **************************
CRUD operations for managing products. All endpoints are secured, requiring a valid JWT token in the request header.

1. Create product
   POST API: http://localhost:9999/products/create
   Request Body:
   {
    "name":"Mobile",
    "price":25000,
    "image":"https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fmobile%2520phone%2F&psig=AOvVaw2NTDTZiXRjMy-ICdvKaeAO&ust=1736310977583000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCLCc4snk4ooDFQAAAAAdAAAAABAE"
  }


3. API to retrieve All Products
   GET API: http://localhost:9999/products/

4. API to Retrieve a Product by ID
    GET API: http://localhost:9999/products/{productId}

5. API to update product by ID
   PUT API: http://localhost:9999/products/{productId}
   Request Body:
    {
    "name":"Mobile",
    "price":25000,
    "image":"https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fmobile%2520phone%2F&psig=AOvVaw2NTDTZiXRjMy-ICdvKaeAO&ust=1736310977583000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCLCc4snk4ooDFQAAAAAdAAAAABAE"
   }

7.  API to delete product by ID
   DELETE API: http://localhost:9999/products/{productId}




