package br.com.zendteam.hr.domain.UserRg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRgRepository extends JpaRepository<UserRg, UUID> {

    @Query(value = "SELECT * FROM user_rg WHERE num_rg = :rg", nativeQuery = true)
    Optional<UserRg> findOneRg(@Param(value = "rg") String rg);

    @Query(value = "SELECT * FROM user_rg WHERE user_id = :id", nativeQuery = true)
    Optional<UserRg> findOneRgByUserId(@Param(value = "id") String id);
}
