package com.artlongs.webapp.service;

import com.artlongs.common.Enums;
import com.artlongs.webapp.dao.UserDao;
import com.artlongs.webapp.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class UserService  implements UserDetailsService {

    @Resource
    protected UserDao userDao;

	public long countUsers() {
		return userDao.count();
	}
	
	public User createUser(String name,String birthday) {
		return userDao.save(new User(name,birthday));
	}
	
	public Iterable<User> getAllUsers() {
		return userDao.findAll();
	}
	
	public User findWorldById(Long id) {
		return userDao.findOne(id);
	}
	
	public User findWorldByName(String name) {
		return userDao.findBySchemaPropertyValue("name", name);
	}
	
	public Iterable<User> findAllByAge(int age) {
		return userDao.findAllBySchemaPropertyValue("age", age);
	}
	
	public Collection<User> makeSomeUsers() {
		Collection<User> users = new ArrayList<User>();

        User leeton = createUser("leeton","1978-01-01");
        User neo = createUser("neo","1978-01-01");
        User alice = createUser("alice","1983-01-01");
        User jocky = createUser("jocky","1976-01-01");
        User eya = createUser("eya","1979-01-01");


        leeton.addFriend(eya);
        eya.addFriend(jocky);
        jocky.addFriend(eya);

        users.add(leeton);
        users.add(neo);
        users.add(alice);
        users.add(jocky);
        users.add(eya);

        leeton.setFindingType(Enums.Finding.FRIEND);

        // userDao.save(users);
        
        return users;
	}

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException, DataAccessException {
        final User user = findUser(account);
        if (user == null) throw new UsernameNotFoundException("Username not found");
        return new UserDetail(user);
    }

    public User findUser(String login) {
        return userDao.findBySchemaPropertyValue("login", login);
    }


    public User getUserFromSession() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetail) {
            UserDetail userDetail = (UserDetail) principal;
            return userDetail.getUser();
        }
        return null;
    }

    @Transactional
    public User register(String login, String name, String password) {
        User found = findUser(login);
        if (found!=null) throw new RuntimeException("Login already taken: "+login);
        if (name==null || name.isEmpty()) throw new RuntimeException("No name provided.");
        if (password==null || password.isEmpty()) throw new RuntimeException("No password provided.");
        User user=userDao.save(new User(login,name,password,User.Roles.ROLE_USER));
        setUserInSession(user);
        return user;
    }

    private void setUserInSession(User user) {
        SecurityContext context = SecurityContextHolder.getContext();
        UserDetail userDetail = new UserDetail(user);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, user.getPassword(),userDetail.getAuthorities());
        context.setAuthentication(authentication);

    }

    @Transactional
    public void addFriend(String account) {
        User friend = findUser(account);
        User user = getUserFromSession();
        if (!user.equals(friend)) {
            user.addFriend(friend);
        }
    }

}
