package com.company.services;

import com.company.entities.User;
import com.company.exceptions.UserNotFoundException;
import com.company.repositories.UserRepository;
import com.company.requests.NewUserForm;
import com.company.requests.UpdateUserForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @user siti2017
 * @date 15/02/17
 */
@Service
@Qualifier("mysql")
class MysqlUserService implements UserService {
    private UserRepository userRepository;

    @Autowired
    EntityManager em;

    @Autowired
    public MysqlUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findOne(Long id) {
        if (! exists(id)) {
            throw new UserNotFoundException(id);
        }
        return userRepository.findOne(id);
    }

    @Override
    public User store(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(UpdateUserForm usr) {
        User user = this.validateUser(usr);

        BeanUtils.copyProperties(usr, user, "id", "password", "createdAt", "updatedAt");

        return userRepository.save(user);
    }

    @Override
    public void destroy(Long id) {
        this.validateUser(id);

        userRepository.delete(id);
    }

    private boolean exists(Long id) {
        return this.userRepository.exists(id);
    }

    private User validateUser(UpdateUserForm usr) {
        return validateUser(usr.getId());
    }

    /**
     * Validate that an user exists
     *
     * @param id of the user
     * @return an existing User
     */
    private User validateUser(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }
}
