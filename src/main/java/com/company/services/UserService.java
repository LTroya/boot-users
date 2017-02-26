package com.company.services;

import com.company.entities.User;
import com.company.requests.NewUserForm;
import com.company.requests.UpdateUserForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @user siti2017
 * @date 15/02/17
 */
public interface UserService {
    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    User findOne(Long id);

    User store(User user);

    User update(UpdateUserForm user);

    void destroy(Long id);
}
