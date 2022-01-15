//package com.project.app;
//
//import java.security.Principal;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import com.project.app.Entities.CategoryEntity;
//import com.project.app.Entities.RoleEntity;
//import com.project.app.Entities.UserEntity;
//import com.project.app.Entities.Enums.RoleEnum;
//import com.project.app.Repositories.CategoryRepository;
//import com.project.app.Repositories.OrderRepository;
//import com.project.app.Repositories.RoleRepository;
//import com.project.app.Repositories.UserRepository;
//
//public class Auth implements CommandLineRunner {
//
////    public static Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private RoleRepository roleRepository;
//	@Autowired
//	private CategoryRepository categoryRepository;
//	@Autowired
//	private OrderRepository orderRepository;
//	
//	@Override
//	public void run(String... args) throws Exception {
//		
//		
//		if (orderRepository.count() <= 0) {
//
//		}
//
//		if (categoryRepository.count() <= 0) {
//			CategoryEntity PC = new CategoryEntity();
//			PC.setCategoryurl("pc");
//			PC.setName("PC");
//			PC.setDescription("pc");
//
//			CategoryEntity Car = new CategoryEntity();
//			Car.setCategoryurl("car");
//			Car.setName("Car");
//			Car.setDescription("car");
//
//			CategoryEntity Phone = new CategoryEntity();
//			Phone.setCategoryurl("phone");
//			Phone.setName("Phone");
//			Phone.setDescription("phone");
//
//			categoryRepository.saveAll(List.of(PC, Car, Phone));
//		}
//
//		if (roleRepository.count() <= 0) {
//			roleRepository.saveAll(List.of(new RoleEntity(RoleEnum.ADMIN.toString(), "Admin"), new RoleEntity(RoleEnum.CLIENT.toString(), "Client")));
//		}
//
//		if (userRepository.count() <= 0) {
//			UserEntity user = new UserEntity();
//			user.setFirstname("lahcen");
//			user.setLastname("HAYANE");
//			user.setEmail("lahcenhayane@gmail.com");
//			user.setCity("Rabat");
//			user.setPhone("0628165941");
////			user.getEnabled()
//			user.setPassword(bCryptPasswordEncoder().encode("lahcenhayane"));
//			user.setUsername("lahcenhayane");
//			UserEntity userEntity = userRepository.save(user);
//
//			RoleEntity role = roleRepository.findByName(RoleEnum.ADMIN.name());
//			RoleEntity roleclient = roleRepository.findByName(RoleEnum.CLIENT.name());
//			userEntity.getRoles().addAll(List.of(roleclient, role));
//			userRepository.save(userEntity);
//
//
//			UserEntity userClient = new UserEntity();
//			userClient.setFirstname("lahcen");
//			userClient.setLastname("HAYANE");
//			userClient.setEmail("lahcenhayane1@gmail.com");
//			userClient.setCity("Rabat");
//			userClient.setPhone("0628165941");
////			user.getEnabled()
//			userClient.setPassword(bCryptPasswordEncoder().encode("lahcenhayane"));
//			userClient.setUsername("lahcenhayane1");
//			UserEntity userEntity1 = userRepository.save(userClient);
//
//			userEntity1.getRoles().addAll(List.of(roleclient));
//			userRepository.save(userEntity1);
//
//		}
//	}
//	
//}
