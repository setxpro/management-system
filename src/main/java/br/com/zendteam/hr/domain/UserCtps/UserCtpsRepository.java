package br.com.zendteam.hr.domain.UserCtps;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserCtpsRepository extends JpaRepository<UserCtps, UUID> {
    @Query(value = "SELECT * FROM user_ctps WHERE num_carteira = :ctps", nativeQuery = true)
    Optional<UserCtps> findOneCtps(@Param(value = "ctps") String ctps);

    @Query(value = "SELECT * FROM user_ctps WHERE user_id = :id", nativeQuery = true)
    Optional<UserCtps> findOneByUserId(@Param(value = "id") String id);
}
