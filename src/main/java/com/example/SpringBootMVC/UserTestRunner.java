//package com.example.SpringBootMVC;
//
//import com.example.SpringBootMVC.user.entity.User;
//import com.example.SpringBootMVC.user.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserTestRunner implements CommandLineRunner {
//    private final UserRepository userRepository;
//
//    public UserTestRunner(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//@Override
//    public void run (String... args)throws Exception{
//    User user = new User("Ali", "ali@example.com", "123456");
//    userRepository.save(user);
//
//    System.out.println("User saved: " + user.getId());
//}
//}
