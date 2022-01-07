# Demostar (Android Application)

# Demo Video

https://user-images.githubusercontent.com/44563718/148518051-8266c2fe-f062-4187-8f63-5b06b5c91635.mp4

# Features of the application :

* Every information of the user and details of the movies can be stored in #Room Database (offline) <br/>
* Application is implemented in offline <br/>
* User can sort the content based on movie names , ratings , recently added and etc.. <br/>
* User can only view the content uploaded by Admin and they can also rate the movie using rating bar <br/>
* Admin can upload the information about movies <br/>
* Login Authentication of subscribers can be done using their email address and password<br/>
* Admin can login directly using these credentials (email : admin , password : admin) <br/>


# Steps to be followed for Admin : 

* Admin can login directly in login page using the above given credentials <br/>
* Admin can upload the content by entering the movie details and click upload button to upload the database <br/> 
* Check button in admin page is to check the page of the user to be displayed <br/>
* Initially, user viewing page will be empty , because , only after the admin upload the data , it will be visible <br/>


# Steps to be followed for User :

* User should signup before login by entering their details <br/>
* User can view the content uploaded by the admin <br/>
* User can also sort and filter the content <br/>

# Room Database :

* Room Database is more efficient for offline storage <br/>
* It creates the room insdie app which is not visible , but they can be viewed by "Device File Explorer -> Select Device -> app -> app -> project name -> databases -> files" <br/>
* I used two databases for storing the user information and movie information separately <br/>
* Using SQL, we can manipulate data in room database more efficient <br/>

