package cl.nava.springsecurityjwt.initializers;

import cl.nava.springsecurityjwt.models.RolesModel;
import cl.nava.springsecurityjwt.models.UsersModel;
import cl.nava.springsecurityjwt.repositories.IRolesRepository;
import cl.nava.springsecurityjwt.repositories.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Order(1) // Ensure it runs early in the app startup
public class DatabaseInitializer implements CommandLineRunner {

    private final IRolesRepository rolesRepository;
    private final IUsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseInitializer(IRolesRepository rolesRepository, IUsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.rolesRepository = rolesRepository;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        System.out.println("🚀 Database Initializer started...");

        // Ensure roles exist
        RolesModel adminRole = rolesRepository.findByName("ADMIN").orElseGet(() -> {
            System.out.println("✅ Creating role 'ADMIN'");
            RolesModel newRole = new RolesModel();
            newRole.setName("ADMIN");
            return rolesRepository.save(newRole);
        });

        RolesModel userRole = rolesRepository.findByName("USER").orElseGet(() -> {
            System.out.println("✅ Creating role 'USER'");
            RolesModel newRole = new RolesModel();
            newRole.setName("USER");
            return rolesRepository.save(newRole);
        });

        // Check if admin user already exists
        if (usersRepository.findByUserName("admin").isEmpty()) {
            System.out.println("⚠️ Admin user not found, creating new admin user...");

            UsersModel adminUser = new UsersModel();
            adminUser.setUserName("admin");
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setRoles(Collections.singletonList(adminRole));

            usersRepository.save(adminUser);
            System.out.println("🎉 Admin user created successfully!");
        } else {
            System.out.println("✅ Admin user already exists, skipping creation.");
        }
    }
}
