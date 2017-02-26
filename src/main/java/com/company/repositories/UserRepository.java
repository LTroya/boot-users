package com.company.repositories;

import com.company.entities.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @user siti2017
 * @date 14/02/17
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String name, PageRequest pageRequest);
}
