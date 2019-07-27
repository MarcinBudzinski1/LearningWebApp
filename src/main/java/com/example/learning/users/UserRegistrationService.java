package com.example.learning.users;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRegistrationService {

    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private UserRepository<Customer> userRepository;

    @Autowired
    public UserRegistrationService(PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository<Customer> userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public void registerUser(CustomerRegistrationDto customerRegistrationDto){
        Customer user = UserRegistrationDtoToUserBuilder.rewriteToCustomer(customerRegistrationDto, passwordEncoder);

        if (userRepository.existsByUsername(user.getUsername())){
            throw new UserExistsException("Użytkownik z nazwą :" + user.getUsername() + "już istnieje w naszej bazie");
        } else {
            addUser(user);
        }
    }

    private void addUser(Customer user) {
        Role role = Optional.ofNullable(roleRepository.findRoleByRoleName("ROLE_USER"))
                .orElseGet(()->roleRepository.save(new Role(RoleTypeEnum.USER.getRoleName())));
        user.setRoles(Sets.newHashSet(role));
        userRepository.save(user);
    }
}
