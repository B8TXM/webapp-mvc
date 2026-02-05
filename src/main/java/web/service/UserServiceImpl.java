package web.service;


import web.dao.UserDao;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void save(String name, String email, Integer age) {

        User user = new User();
        user.setName(name.trim());
        user.setEmail(email != null ? email.trim() : null);
        user.setAge(age);
        userDao.save(user);
    }

    @Override
    public void update(Long id, String name, String email, Integer age) {

        User existingUser = userDao.findById(id);
        if (existingUser == null) {
            throw new IllegalArgumentException("User " + id + " not found");
        }
        if (name != null && !name.trim().isEmpty()) {
            existingUser.setName(name.trim());
        }
        if (email != null && !email.trim().isEmpty()) {
            existingUser.setEmail(email.trim());
        }
        if (age != null) {
            existingUser.setAge(age);
        }

        userDao.update(existingUser);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}

