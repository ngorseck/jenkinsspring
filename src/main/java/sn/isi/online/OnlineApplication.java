package sn.isi.online;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sn.isi.online.dao.IProfesseur;
import sn.isi.online.dao.IRoles;
import sn.isi.online.entities.Professeur;
import sn.isi.online.entities.Roles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class OnlineApplication implements CommandLineRunner {

	@Autowired
	private IProfesseur professeurdao;

	@Autowired
	private IRoles rolesdao;

	public static void main(String[] args) {
		SpringApplication.run(OnlineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Roles 1
		Roles roles1 = new Roles();
		roles1.setNom("ROLE_USER");
		rolesdao.save(roles1);
		//Roles 2
		Roles roles2 = new Roles();
		roles2.setNom("ROLE_PROFESSEUR");
		rolesdao.save(roles2);

		//Liste 1 de roles
		List<Roles> rolesList1 = new ArrayList<>();
		rolesList1.add(roles1);
		rolesList1.add(roles2);
		//Liste 2 de roles
		List<Roles> rolesList2 = new ArrayList<>();
		rolesList2.add(roles1);

		// Prof 1
		Professeur professeur = new Professeur();
		professeur.setNom("Seck");
		professeur.setPrenom("Ngor");
		professeur.setTelephone("774339716");
		professeur.setEmail("ngorseck@groupeisi.com");
		//crypt password
		String password = "seckisi123";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		professeur.setPassword(hashedPassword);
		professeur.setEtat(1);
		professeur.setRoles(rolesList1);
		professeurdao.save(professeur);

		// Prof 2
		professeur = new Professeur();
		professeur.setNom("Kane");
		professeur.setPrenom("Maguette");
		professeur.setTelephone("774629716");
		professeur.setEmail("mkane@groupeisi.com");
		//cryp password
		String pwd = "kaneisi123";
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		String hashedPwd = pwdEncoder.encode(pwd);
		professeur.setPassword(hashedPwd);
		professeur.setEtat(1);
		professeur.setRoles(rolesList2);
		professeurdao.save(professeur);

		//Professeur p = professeurdao.chercher("ngorseck@groupeisi.com", "seckisi123");

		List<Professeur> listeprofesseurs =  professeurdao.findAll();

		listeprofesseurs.forEach(p->{
			System.out.println(p.getPrenom() + "  " + p.getNom());
		});

	}

}
