//package com.example.demo.jobs;
//
//import com.example.demo.model.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//@EnableScheduling
//public class UserJob {
//
//    private final UserRepository userRepository;
//
//    @Scheduled(cron = "${timer.duration.value}")
////    @Scheduled(fixedDelay = 5000)
//    public void deleteInactiveUsers () {
//
////        List<User> users = userRepository.findAllByStatus(UserStatus.DELETED);
////        userRepository.deleteAll(users);
//
//        log.info("Scheduler is working");
//
//    }
//
//
//}
