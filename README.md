# Food Delivery Application
## Using Java, Spring and H2 integrated with Ola maps

Requirement was to create a food delivery application that can be fulfil the following requirements:

1. Register a user
2. Register a rider
3. Register a restaurant
4. Restaurant suggestion to user based on food and delivery time filter
5. Provide a menu for restaurant
6. Accept an order
7. Find a rider nearest to the restaurant to pick up order
8. Update rider location
9. Show history of orders placed by user
10. Show history of orders completed by rider
11. Estimate time it will take for order to be delivered
12. Provide coupons to users to get discount
13. Generate a rating for drivers and users

#

#### What has been Accomplished

Created a web app using JAVA, Spring, H2 that can do the following  
- User/Rider/Restaurants can sign up on the application (1, 2, 3)
- Restaurants can add dishes to their menu (5)
- User can get list of nearby restaurants with real-time delivery time information (using Ola Krutrim API integration) (4, 11)
- Users can place order and once order is placed, nearest rider is assingned to the order (6, 7, 8)
- Users and riders can view their order history (9, 10)

#



### Main entities
- User (userId, email, address)
- Rider (riderId, email, phoneNo, status, coordinates)
- Restaurant (name, email, phoneNo, address, status, coordinates)
- Dish (name, price)
- Order(user, restaurant, rider, status)

#

### API Endpoints

#### User /api/users
&ensp; POST  /create?email={email}&address={address}  <br/> 

#### Rider /api/rider
&ensp; POST  /create?email={email}&phoneNo={phoneNo} <br/> 
&ensp; POST  /updateLocation?riderId={riderId}&lat={lat}&long={long} <br/> 
&ensp; POST  /updateLocation?riderId={riderId}&status={status} <br/> 

#### Restaurant /api/restaurant
&ensp; POST  /register?name={name}&email={email}&phoneNo={phoneNo}&address={address}&status={status}&lat={lat}&long={long} <br/> 
&ensp; GET  /nearby?lat={lat}&long={long}&distance={distance} <br/> 
&ensp; POST  /add-dish?restaurantId={restaurantId}&name={name}&price={price} <br/> 
&ensp; GET  /{restaurantId}/dishes <br/> 

#### Order /api/order
&ensp; POST  /place-order?restaurantId={restaurantId}&userId={userId}&dishes={<Array>dishId} <br/> 
&ensp; GET  /user/all-orders/{userId} <br/> 
&ensp; GET  /rider/all-orders/{riderId} <br/> 
&ensp; GET  /info/{orderId} <br/> 

#

### Design Choices and thought process

#### Database
For an application like food delivery where data is well structed a relational database would suit the needs. We can also use a combinatio of SQL and NoSQL databases for differrent parts of the application depending on nature or data (structured / flexible). For this POC I have chosen H2 database which is a SQL based database.

#### Finding nearby restaurants/riders
In this POC, I have first used a mathematical function (haversine formula) to first find the nearest restaurants / riders with in a certain radius and then call Ola Maps API to find real-time time duration between user and restaurant. This minimized the number of APIs calls to third party reducing pricing. 

An efficient way of doing it would be to save the lat, long in geohased format. This would make retrieval of nearest restaurants exponentially faster (eliminating the need of haversine).

#### Dished - entity or json object?
One design choice that I made was to create a separate dishes entity that saves all the dishes for all the retaurants rather than adding dished in form of json object in restaurant table itself. This makes it easy to track items in cart, created orders and all other places. Where as saving them in json format would have meant sending json object around where it was needed.

This can also be used to track what dishes are ordered more frequently and be used for rating purposes of dishes as well.

#

### What more can be done
#### User/Rider/Restaurant verification
We can add email/sms based verification for users/riders/restaurants. Riders might be asked  to submit some gov/official documents. 

#### Payment gateway integration
Right now orders are placed right away without payment confirmation. We can integrate a payment gateway by calling third party API to generate a URL for completing payment (which will be sent to the user) and create a webhook on our end that the payment gateway can send confirmation on. And then confirm the order.

#### Notification for rider 
In our POC, there is not way rider is getting notified when a order is assigned to them. We can set up SSE to notify them on their application. But this is not possible when they are not on the application, in that case we can use Apple/Android third party notification services to send notification on their mobile phones.


