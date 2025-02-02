package cl.nava.springsecurityjwt.factories;

import cl.nava.springsecurityjwt.models.RolesModel;

import java.util.Optional;

public interface IRoleFactory {
    Optional<RolesModel> findByName(String name);
}