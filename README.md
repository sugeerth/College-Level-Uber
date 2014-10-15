Introduction 
The application is designed to aid the people who are in search of rides and people who want their trip to be shared. We help them save money by encouraging carpooling. 

Team members
Triet Nguyen
Sugeerth
Deneb Chen
Duc Hoang

 Activity Screens & Logic Code
Activity Main/activity_register
This screen is where users are able to login and register for a new account. An error message will display if incorrect username or password is inputted. The register screen allows users to register for a new account. The input information will be pushed to database. We also have various fields for checking register. We ensure that password is validated with many constraints because the app involves money.
activity_home
This is the homepage of the application. Think of this as a Facebook application where the riders/drivers can get their notifications as and when they log in.This page stores the requests sent/received from users. A user does all interactions from this page; such as accept/reject requests, payments, and start/end trips. Requests are pulled from the data store every time a user is in the homepage.
activity_search
This screen allows the user to post and search for a ride. A user is allowed to enter destination, source, fare, date, number of seats, and deciding whether they are looking for a driver or passenger. A popup window will also appear if they user decides to post. Within this popup window, the user can enter a description (a personal message to the driver/rider) about the trip.

activity_result
This screen shows all the results from searching. Each result is displayed as an item in a List View. Users can sort the information by date, fare, and number of seats. This screen takes information from the activity_search screen and queries the datastore and pulls the result. The result information are stored in an arraylist which gets displayed by an Adapter. Also, if no results are returned, a post field will appear similar to the one in activity_search where users can post their search query.
activity_confirm
This screen displays all the search/post information. When a result is clicked on, this screen will show user information, trip information, and a description. We used this screen not only for requests but also to allow users to do payment and start/end trip in the homepage.
AdInfo and UserInfo
These two classes store information that is pulled from the data base. We used this class for the result and home screen. We have a list of AdInfo which contains an instance of UserInfo for the poster's information. The list of AdInfo is the internal representation of our adapter. In other words, you are essentially seeing the list of AdInfo within activity_result and activity_home.
XFormTask
These classes are used to handle datastore interaction.  They all extends Asynctask which allows the application to run network activities in the background thread. We make use of HTTP post/get to transfer/pull data from the datastore. Once the appropriate data is found, they are sent back to the screens that requires them.
XListAdapter
These adapter classes handles the display of the List View. A List of information such as AdInfo and UserInfo is sent to this class where they will use the appropriate data to display the list you see on activity_result and activity_home.
SortByXComparator
These classes are used to sort information on the activity_result screen. They all implement Comparator which contains the logic for sorting. Comparator is a fast and efficient way to sort Collections. In our case, we used this to sort our ArrayList.


