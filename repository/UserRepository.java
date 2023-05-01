package am.itspace.taskmanagement.repository;

import am.itspace.taskmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    List<User> findTop10ByNameOrderByIdDesc(String name);

}
