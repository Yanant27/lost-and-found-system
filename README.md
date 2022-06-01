# Lost & Found System
The system provides CRUD operations (create, read, update and delete data from database), role-based access control (RBAC) and login, logout functionality. To protect from too many login attempt with incorrect password, account will be locked for more than 3 failed log in. It will be automatically unlocked after one hour.

## Table of Contents
- [Technologies](#technologies)  
- [Setup](#setup)  
- [System Overview](#system-overview)  
- [System Captures](#system-captures)  
- [Ideas for Enhancement](#ideas-for-enhancement)  

## Technologies
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- Project Lombok
- H2 Database
- Thymeleaf
- HTML 5
- CSS 3
- Bootstrap 5
- Maven

## Setup
1. Clone github repository
2. Import project into IDE.
3. Hit run button

## System Overview
This system is aimed to help people finding their lost properties. If they lost or found something, they can report by using this system. It provides two roles (admin, user) to access the system. 

Admin can
- Manage users – (CRUD – create, read, update, and delete) user detail and information.
- Manage user roles.
- Manage lost/found item reports of all users.

User can
- Create lost/found item reports.
- Read all lost/found item reports.
- Edit and delete their own reported items.
- Edit user information.

_System contains validation for all user inputs and all validations are performed in server side._

<a name="system-captures"></a>
## System Captures
#### Login
![This is an image](/capture/login.PNG)

#### Registration
![This is an image](/capture/register.PNG)

#### Registration Validation
All input items are validated and related error messages are displayed.
![This is an image](/capture/register-validation-1.PNG)
![This is an image](/capture/register-validation-2.PNG)

#### Admin Home
![This is an image](/capture/admin-home.png)

#### User Home
Side bar menu is different from admin's side bar. There is no "User Management" for user role.
![This is an image](/capture/user-home.PNG)

#### Profile Detail
![This is an image](/capture/profile-detail.PNG)

#### Profile Update and Validation
![This is an image](/capture/profile-edit-error.PNG)

#### All Lost/Found Items List for Admin
Admin can operate CRUD operations on all user's reported items. There is three action buttons for all rows.
![This is an image](/capture/admin-all-items.PNG)

#### All Lost/Found Items List for User
Users can operate edit and delete only on their reported items. Therefore, all action buttons are not shown for all rows.
![This is an image](/capture/user-all-items.PNG)

#### Report New Lost/Found Item
Same for both admin and user
![This is an image](/capture/create-item.PNG)
All input items are validated and related error messages are displayed.
![This is an image](/capture/create-item-validation-1.PNG)
![This is an image](/capture/create-item-validation-2.PNG)

#### Update Lost/Found Item
Validation is also same with creating item.
![This is an image](/capture/update-item.PNG)

#### Delete Lost/Found Item
![This is an image](/capture/delete-item.PNG)

#### User Manangement (Admin Only)
All registered users list
![This is an image](/capture/admin-all-users.PNG)
Create Operation
![This is an image](/capture/admin-user-create.PNG)
Validation of Create Operation
![This is an image](/capture/admin-user-create-validation-1.PNG)
![This is an image](/capture/admin-user-create-validation-2.PNG)
Update Operation
![This is an image](/capture/admin-user-edit.PNG)
Delete Operation
![This is an image](/capture/admin-user-delete.PNG)

#### Access Denied
Although UI components are restricted using Thymeleaf spring security dialect, user might enter URL and try to access unauthorized page. For example, user don't have the right to modify or delete other user's reported items but try to perform that operation from URL.
![This is an image](/capture/user-access-denied.PNG)

#### Bad Request
For malformed request syntax, customized bad request page will be displayed.
![This is an image](/capture/bad-request.PNG)

#### Resource Not Found
For not existed resource, customized resource not found page will be displayed. For example, user searched all items and look details of one item without refreshing the page and that item was accidently deleted from database.
![This is an image](/capture/resource-not-found.PNG)

#### Account Lock
If user attempts to log in with incorrect password more than 3 times, account will be locked for 1 hour.
![This is an image](/capture/account-lock.PNG)

## Ideas for Enhancement
1. Integrate with Google Maps to show the lost/found location.
2. Add images of lost/found items.
3. Add comments on lost/found posts.
4. Add role management.
5. Add forget password functionality.
