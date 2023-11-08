package bookstore.repository;

import bookstore.entity.role.Role;
import bookstore.entity.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(RoleName name);
}
