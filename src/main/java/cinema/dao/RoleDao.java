package cinema.dao;

import cinema.model.Role;

public interface RoleDao {
    Role add(Role role);

    Role get(String name);
}
