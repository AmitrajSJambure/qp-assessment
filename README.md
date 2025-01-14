# qp-assessment
Grocery Management Service

# For data base creation in MySQL
```
CREATE SCHEMA grocery;
```

# Env Variables
1. **PORT** - Provide port for running this application
2. **DB-USER** - Provide username to connect to database
3. **DB-PASS** - Provide password to connect to database

# API details
1. **/grocery/add**
   1. **Uses**: To add new grocery item.
   2. **Role**: Only Admin can access.
   3. **Sample Input**: 
      ```json
      {
       "name": "Apple",
       "price": 30.0,
       "quantity": 10 
      }
      ```
   4. **Response**: Returns saved grocery
   5. **Note**: Throws error if the grocery already exists


2. **/grocery/get**
   1. **Uses**: To get all the grocery items.
   2. **Role**: Anyone can access.
   3. **Sample Response**: 
      ```json
      [
        {
          "name": "Apple",
          "price": 30.0,
          "quantity": 10
       }
      ]
      ```

3. **/grocery/get/{name}**
   1. **Uses**: To get the grocery item by name.
   2. **Role**: Anyone can access.
   3. **Sample Response**:
      ```json
        {
          "name": "Apple",
          "price": 30.0,
          "quantity": 10
       }
      ```
   4. **Note**: Throws error if the grocery not found.


4. **/grocery/update**
   1. **Uses**: To update the details of grocery item.
   2. **Role**: Only Admin can access.
   3. **Sample Input**:
      ```json
      {
        "name": "Apple",
        "price": 30.0,
        "quantity": 10
      }
       ```
   4. **Response** : Returns saved item
   5. **Note**: Throws error if the grocery not found.



5. **/grocery/delete**
   1. **Uses**: To delete all the grocery items.
   2. **Role**: Only Admin can access.
    
6. **/grocery/delete/{name}**
    
   1. **Uses**: To delete specific grocery item by name.
   2. **Role**: Only Admin can access.

7. **/grocery/book**
   1. **Uses**: To book one or more grocery items.
   2. **Role**: Only Admin and User can access.
   3. **Sample Input**:
   ```json
    {
    "Bread": 5,
    "Apple": 5
    }
    ```
   4. **Note**: Throws error if grocery not found or if order for the user already exists or if the quantity of item booked is more than available quantity.


# User Security Details
1. Type: Basic authentication
2. Storage: "In Memory"
3. Available Roles: ADMIN, USER

**For testing:**
1. ADMIN:
   1. username: admin
   2. password: imadmin

2. USER:
    1. username: user
    2. password: imuser

  


       
