package ru.meowhard.preproject23;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.meowhard.preproject23.model.User;
import ru.meowhard.preproject23.repository.UserRepository;

@SpringBootApplication
public class Preproject23Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Preproject23Application.class, args);
	}

//	@Autowired
//	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

//		User user1 = new User("Anton", 24, "anton.kleban.98@yandex.ru");
//		userRepository.save(user1);
//
//		User user2 = new User("Sergey", 35, "sergey@yandex.ru");
//		userRepository.save(user2);
//
//		User user3 = new User("Ivan", 46, "ivan@yandex.ru");
//		userRepository.save(user3);
	}
}