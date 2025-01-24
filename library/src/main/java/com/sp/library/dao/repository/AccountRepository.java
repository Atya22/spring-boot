package com.sp.library.dao.repository;

import com.sp.library.dao.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT * from account WHERE balance = :balance LIMIT 1")
    Optional<AccountEntity> findByBalance(@Param("balance") Double balance);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE account SET balance = :balance WHERE id = :id")
    void updateAccountByNumber(@Param("id") Long id, @Param("balance") Double balance);
}
