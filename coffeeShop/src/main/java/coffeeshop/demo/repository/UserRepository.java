package coffeeshop.demo.repository;

import coffeeshop.demo.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM User u ORDER BY SIZE(u.orders) DESC")
    List<User> findAllByOrdersCountDescending();

}
