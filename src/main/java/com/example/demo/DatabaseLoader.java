package com.example.demo;

import com.example.demo.entity.Dish;
import com.example.demo.entity.Restaurant;
import com.example.demo.entity.Rider;
import com.example.demo.entity.Users;
import com.example.demo.enums.RestaurantStatus;
import com.example.demo.enums.RiderStatus;
import com.example.demo.repository.DishRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.RiderRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Configuration
public class DatabaseLoader {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RiderRepository riderRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            // Create sample restaurants
            Restaurant restaurant1 = new Restaurant("Restaurant A", "restaurantA@example.com", "123-456-7890", "Address 1", RestaurantStatus.OPEN, 28.6139, 77.2090);
            Restaurant restaurant2 = new Restaurant("Restaurant B", "restaurantB@example.com", "234-567-8901", "Address 2", RestaurantStatus.OPEN, 28.7041, 77.1025);
            Restaurant restaurant3 = new Restaurant("Restaurant C", "restaurantC@example.com", "345-678-9012", "Address 3", RestaurantStatus.CLOSED, 28.5355, 77.3910);
            Restaurant restaurant4 = new Restaurant("Restaurant D", "restaurantD@example.com", "456-789-0123", "Address 4", RestaurantStatus.OPEN, 28.4595, 77.0266);
            Restaurant restaurant5 = new Restaurant("Restaurant E", "restaurantE@example.com", "567-890-1234", "Address 5", RestaurantStatus.OPEN, 28.4089, 77.3178);
            Restaurant restaurant6 = new Restaurant("Restaurant F", "restaurantF@example.com", "678-901-2345", "Address 6", RestaurantStatus.CLOSED, 28.6692, 77.4538);
            Restaurant restaurant7 = new Restaurant("Restaurant G", "restaurantG@example.com", "789-012-3456", "Address 7", RestaurantStatus.OPEN, 28.4085, 77.3183);
            Restaurant restaurant8 = new Restaurant("Restaurant H", "restaurantH@example.com", "890-123-4567", "Address 8", RestaurantStatus.OPEN, 28.5246, 77.2066);
            Restaurant restaurant9 = new Restaurant("Restaurant I", "restaurantI@example.com", "901-234-5678", "Address 9", RestaurantStatus.CLOSED, 28.4593, 77.0260);
            Restaurant restaurant10 = new Restaurant("Restaurant J", "restaurantJ@example.com", "012-345-6789", "Address 10", RestaurantStatus.OPEN, 28.6693, 77.3547);
            Restaurant restaurant11 = new Restaurant("Restaurant K", "restaurantK@example.com", "123-456-7890", "Address 11", RestaurantStatus.OPEN, 28.7046, 77.1035);

            Dish dish1 = new Dish("Pasta Alfredo", 12.99, restaurant1);
            Dish dish2 = new Dish("Margherita Pizza", 9.99, restaurant1);
            Dish dish3 = new Dish("Caesar Salad", 7.99, restaurant1);
            Dish dish4 = new Dish("Garlic Bread", 4.99, restaurant1);
            Dish dish5 = new Dish("Tiramisu", 5.99, restaurant1);

            Dish dish6 = new Dish("Chicken Tikka", 13.99, restaurant2);
            Dish dish7 = new Dish("Naan Bread", 2.99, restaurant2);
            Dish dish8 = new Dish("Paneer Butter Masala", 11.99, restaurant2);
            Dish dish9 = new Dish("Biryani", 14.99, restaurant2);
            Dish dish10 = new Dish("Gulab Jamun", 3.99, restaurant2);

            Dish dish11 = new Dish("Cheeseburger", 8.99, restaurant3);
            Dish dish12 = new Dish("French Fries", 3.99, restaurant3);
            Dish dish13 = new Dish("Chicken Nuggets", 6.99, restaurant3);
            Dish dish14 = new Dish("Milkshake", 4.99, restaurant3);
            Dish dish15 = new Dish("Apple Pie", 5.99, restaurant3);

            Dish dish16 = new Dish("Sushi Roll", 12.99, restaurant4);
            Dish dish17 = new Dish("Miso Soup", 4.99, restaurant4);
            Dish dish18 = new Dish("Tempura", 10.99, restaurant4);
            Dish dish19 = new Dish("Sashimi", 14.99, restaurant4);
            Dish dish20 = new Dish("Green Tea Ice Cream", 5.99, restaurant4);

            Dish dish21 = new Dish("Tacos", 9.99, restaurant5);
            Dish dish22 = new Dish("Burrito", 10.99, restaurant5);
            Dish dish23 = new Dish("Quesadilla", 8.99, restaurant5);
            Dish dish24 = new Dish("Nachos", 7.99, restaurant5);
            Dish dish25 = new Dish("Churros", 4.99, restaurant5);

            Dish dish26 = new Dish("Pad Thai", 11.99, restaurant6);
            Dish dish27 = new Dish("Green Curry", 12.99, restaurant6);
            Dish dish28 = new Dish("Spring Rolls", 5.99, restaurant6);
            Dish dish29 = new Dish("Mango Sticky Rice", 6.99, restaurant6);
            Dish dish30 = new Dish("Tom Yum Soup", 7.99, restaurant6);

            Dish dish31 = new Dish("BBQ Ribs", 16.99, restaurant7);
            Dish dish32 = new Dish("Mac and Cheese", 6.99, restaurant7);
            Dish dish33 = new Dish("Cornbread", 4.99, restaurant7);
            Dish dish34 = new Dish("Coleslaw", 3.99, restaurant7);
            Dish dish35 = new Dish("Pecan Pie", 5.99, restaurant7);

            Dish dish36 = new Dish("Falafel Wrap", 9.99, restaurant8);
            Dish dish37 = new Dish("Shawarma Plate", 12.99, restaurant8);
            Dish dish38 = new Dish("Hummus with Pita", 5.99, restaurant8);
            Dish dish39 = new Dish("Tabbouleh Salad", 6.99, restaurant8);
            Dish dish40 = new Dish("Baklava", 4.99, restaurant8);

            Dish dish41 = new Dish("Peking Duck", 18.99, restaurant9);
            Dish dish42 = new Dish("Sweet and Sour Pork", 13.99, restaurant9);
            Dish dish43 = new Dish("Kung Pao Chicken", 12.99, restaurant9);
            Dish dish44 = new Dish("Dumplings", 7.99, restaurant9);
            Dish dish45 = new Dish("Fried Rice", 9.99, restaurant9);

            Dish dish46 = new Dish("Spaghetti Bolognese", 12.99, restaurant10);
            Dish dish47 = new Dish("Lasagna", 13.99, restaurant10);
            Dish dish48 = new Dish("Bruschetta", 6.99, restaurant10);
            Dish dish49 = new Dish("Risotto", 14.99, restaurant10);
            Dish dish50 = new Dish("Gelato", 5.99, restaurant10);

            Users user = new Users("abc@abc.com", "123 Yemen Road, Yemen");

            Rider rider1 = new Rider("rider1@example.com", "111-222-3333", RiderStatus.AVAILABLE, 28.6200, 77.2100);
            Rider rider2 = new Rider("rider2@example.com", "222-333-4444", RiderStatus.AVAILABLE, 28.6900, 77.1200);
            Rider rider3 = new Rider("rider3@example.com", "333-444-5555", RiderStatus.AVAILABLE, 28.5400, 77.3800);
            Rider rider4 = new Rider("rider4@example.com", "444-555-6666", RiderStatus.AVAILABLE, 28.4700, 77.0300);
            Rider rider5 = new Rider("rider5@example.com", "555-666-7777", RiderStatus.AVAILABLE, 28.4100, 77.3100);
            Rider rider6 = new Rider("rider6@example.com", "666-777-8888", RiderStatus.AVAILABLE, 28.6800, 77.4600);
            Rider rider7 = new Rider("rider7@example.com", "777-888-9999", RiderStatus.AVAILABLE, 28.4200, 77.3200);
            Rider rider8 = new Rider("rider8@example.com", "888-999-0000", RiderStatus.AVAILABLE, 28.5300, 77.2100);
            Rider rider9 = new Rider("rider9@example.com", "999-000-1111", RiderStatus.AVAILABLE, 28.4700, 77.0300);
            Rider rider10 = new Rider("rider10@example.com", "000-111-2222", RiderStatus.AVAILABLE, 28.6900, 77.3600);
            Rider rider11 = new Rider("rider11@example.com", "111-222-3334", RiderStatus.AVAILABLE, 28.7100, 77.1000);
            Rider rider12 = new Rider("rider12@example.com", "222-333-4445", RiderStatus.AVAILABLE, 28.6200, 77.2000);
            Rider rider13 = new Rider("rider13@example.com", "333-444-5556", RiderStatus.AVAILABLE, 28.6800, 77.1500);
            Rider rider14 = new Rider("rider14@example.com", "444-555-6667", RiderStatus.AVAILABLE, 28.5500, 77.4000);
            Rider rider15 = new Rider("rider15@example.com", "555-666-7778", RiderStatus.AVAILABLE, 28.4600, 77.0200);
            Rider rider16 = new Rider("rider16@example.com", "666-777-8889", RiderStatus.AVAILABLE, 28.4200, 77.3200);
            Rider rider17 = new Rider("rider17@example.com", "777-888-9990", RiderStatus.AVAILABLE, 28.5200, 77.2200);
            Rider rider18 = new Rider("rider18@example.com", "888-999-0001", RiderStatus.AVAILABLE, 28.6700, 77.4400);
            Rider rider19 = new Rider("rider19@example.com", "999-000-1112", RiderStatus.AVAILABLE, 28.4400, 77.0200);
            Rider rider20 = new Rider("rider20@example.com", "000-111-2223", RiderStatus.AVAILABLE, 28.6700, 77.3600);
            Rider rider21 = new Rider("rider21@example.com", "111-222-3335", RiderStatus.AVAILABLE, 28.7200, 77.1100);
            Rider rider22 = new Rider("rider22@example.com", "222-333-4446", RiderStatus.AVAILABLE, 28.6300, 77.1900);
            Rider rider23 = new Rider("rider23@example.com", "333-444-5557", RiderStatus.AVAILABLE, 28.6900, 77.1300);
            Rider rider24 = new Rider("rider24@example.com", "444-555-6668", RiderStatus.AVAILABLE, 28.5600, 77.3900);
            Rider rider25 = new Rider("rider25@example.com", "555-666-7779", RiderStatus.AVAILABLE, 28.4700, 77.0300);
            Rider rider26 = new Rider("rider26@example.com", "666-777-8880", RiderStatus.AVAILABLE, 28.4300, 77.3100);
            Rider rider27 = new Rider("rider27@example.com", "777-888-9991", RiderStatus.AVAILABLE, 28.5300, 77.2200);
            Rider rider28 = new Rider("rider28@example.com", "888-999-0002", RiderStatus.AVAILABLE, 28.6800, 77.4500);
            Rider rider29 = new Rider("rider29@example.com", "999-000-1113", RiderStatus.AVAILABLE, 28.4500, 77.0300);
            Rider rider30 = new Rider("rider30@example.com", "000-111-2224", RiderStatus.AVAILABLE, 28.6900, 77.3600);
            Rider rider31 = new Rider("rider31@example.com", "111-222-3336", RiderStatus.AVAILABLE, 28.7300, 77.1200);
            Rider rider32 = new Rider("rider32@example.com", "222-333-4447", RiderStatus.AVAILABLE, 28.6400, 77.1800);
            Rider rider33 = new Rider("rider33@example.com", "333-444-5558", RiderStatus.AVAILABLE, 28.7000, 77.1400);
            Rider rider34 = new Rider("rider34@example.com", "444-555-6669", RiderStatus.AVAILABLE, 28.5700, 77.4000);
            Rider rider35 = new Rider("rider35@example.com", "555-666-7780", RiderStatus.AVAILABLE, 28.4800, 77.0200);
            Rider rider36 = new Rider("rider36@example.com", "666-777-8881", RiderStatus.AVAILABLE, 28.4400, 77.3200);
            Rider rider37 = new Rider("rider37@example.com", "777-888-9992", RiderStatus.AVAILABLE, 28.5400, 77.2300);
            Rider rider38 = new Rider("rider38@example.com", "888-999-0003", RiderStatus.AVAILABLE, 28.6900, 77.4600);
            Rider rider39 = new Rider("rider39@example.com", "999-000-1114", RiderStatus.AVAILABLE, 28.4600, 77.0300);
            Rider rider40 = new Rider("rider40@example.com", "000-111-2225", RiderStatus.AVAILABLE, 28.7100, 77.3700);
            Rider rider41 = new Rider("rider41@example.com", "111-222-3337", RiderStatus.AVAILABLE, 28.7400, 77.1300);
            Rider rider42 = new Rider("rider42@example.com", "222-333-4448", RiderStatus.AVAILABLE, 28.6500, 77.1900);
            Rider rider43 = new Rider("rider43@example.com", "333-444-5559", RiderStatus.AVAILABLE, 28.7100, 77.1500);
            Rider rider44 = new Rider("rider44@example.com", "444-555-6670", RiderStatus.AVAILABLE, 28.5800, 77.4000);
            Rider rider45 = new Rider("rider45@example.com", "555-666-7781", RiderStatus.AVAILABLE, 28.4900, 77.0200);
            Rider rider46 = new Rider("rider46@example.com", "666-777-8882", RiderStatus.AVAILABLE, 28.4500, 77.3300);
            Rider rider47 = new Rider("rider47@example.com", "777-888-9993", RiderStatus.AVAILABLE, 28.5500, 77.2400);
            Rider rider48 = new Rider("rider48@example.com", "888-999-0004", RiderStatus.AVAILABLE, 28.7000, 77.4700);
            Rider rider49 = new Rider("rider49@example.com", "999-000-1115", RiderStatus.AVAILABLE, 28.4700, 77.0400);
            Rider rider50 = new Rider("rider50@example.com", "000-111-2226", RiderStatus.AVAILABLE, 28.7300, 77.3800);



            // Add them to the database
            restaurantRepository.saveAll(List.of(restaurant1, restaurant2, restaurant3, restaurant4, restaurant5, restaurant6, restaurant7, restaurant8, restaurant9, restaurant10, restaurant11));
            dishRepository.saveAll(List.of(dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8, dish9, dish10, dish11, dish12, dish13, dish14, dish15, dish16, dish17, dish18, dish19, dish20, dish21, dish22, dish23, dish24, dish25, dish26, dish27, dish28, dish29, dish30, dish31, dish32, dish33, dish34, dish35, dish36, dish37, dish38, dish39, dish40, dish41, dish42, dish43, dish44, dish45, dish46, dish47, dish48, dish49, dish50));
            riderRepository.saveAll(List.of(rider1, rider2, rider3, rider4, rider5, rider6, rider7, rider8, rider9, rider10, rider11, rider12, rider13, rider14, rider15, rider16, rider17, rider18, rider19, rider20, rider21, rider22, rider23, rider24, rider25, rider26, rider27, rider28, rider29, rider30, rider31, rider32, rider33, rider34, rider35, rider36, rider37, rider38, rider39, rider40, rider41, rider42, rider43, rider44, rider45, rider46, rider47, rider48, rider49, rider50));
            userRepository.save(user);
            System.out.println("Restaurants and dishes added to database!");
        };
    }
}

