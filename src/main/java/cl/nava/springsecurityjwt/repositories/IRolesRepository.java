package cl.nava.springsecurityjwt.repositories;

import cl.nava.springsecurityjwt.models.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolesRepository extends JpaRepository<RolesModel, Long> {
    // Method to search for a role by name in our database
    Optional<RolesModel> findByName(String name);
}
