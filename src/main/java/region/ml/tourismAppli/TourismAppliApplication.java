package region.ml.tourismAppli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import region.ml.tourismAppli.Repo.RolesRepo;
import region.ml.tourismAppli.Repo.UtilisateursRepo;
import region.ml.tourismAppli.SecurityConfig.RsakeysConfig;
import region.ml.tourismAppli.modele.Roles;
import region.ml.tourismAppli.modele.Utilisateurs;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableConfigurationProperties(RsakeysConfig.class)
public class TourismAppliApplication{

	@Autowired
	private UtilisateursRepo utilisateursRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RolesRepo rolesRepo;



	public static void main(String[] args) {
		SpringApplication.run(TourismAppliApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


/*

	@Override
	public void run(String... args) throws Exception {

		Utilisateurs user = new Utilisateurs();
		user.setUsername("Diawara Karim");
		user.setEmail("karimdiawara@gmail.com");
		user.setPassword(passwordEncoder.encode("diawara"));

		String roleUser ="ADMIN";

		Roles role = rolesRepo.findByRole(roleUser);

		Set<Roles> rolee = new HashSet<>();
		rolee.add(role);
		user.setRole(rolee);
		user.setEmail("karimdiawara96@gmail.com");

		utilisateursRepo.save(user);

	}*/


}
