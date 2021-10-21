package com.evil.inc.icresco.service.impl;

import com.evil.inc.icresco.config.cache.properties.CacheNames;
import com.evil.inc.icresco.config.security.JwtTokenManager;
import com.evil.inc.icresco.domain.dto.AuthRequest;
import com.evil.inc.icresco.domain.dto.CreateUserRequest;
import com.evil.inc.icresco.domain.dto.UserView;
import com.evil.inc.icresco.domain.entity.Authority;
import com.evil.inc.icresco.domain.entity.Gender;
import com.evil.inc.icresco.domain.entity.User;
import com.evil.inc.icresco.domain.entity.UserAuthority;
import com.evil.inc.icresco.domain.exception.NotFoundException;
import com.evil.inc.icresco.domain.mapper.Mapper;
import com.evil.inc.icresco.repo.UserRepository;
import com.evil.inc.icresco.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.HashSet;

@Service
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = CacheNames.USERS_CACHE)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Mapper<User, UserView> userViewMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenManager jwtTokenManager;

    @Override
    @Transactional(readOnly = true)
    public Page<UserView> findAll(final Pageable pageable) {
        final Page<User> page = userRepository.findAll(pageable);
        return page.map(userViewMapper::map);
    }

    @Override
    @Cacheable(key = "#p0")
    @Transactional(readOnly = true)
    public UserView findById(final String id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, "id", id));
        return userViewMapper.map(user);
    }

    @Override
    @Caching(put = {@CachePut(key = "#p0.username")})
    @Transactional
    public UserView create(final CreateUserRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ValidationException("Username exists!");
        }
        if (request.getAuthorities() == null) {
            request.setAuthorities(new HashSet<>());
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .gender(Gender.valueOf(request.getGender()))
                .build();

        request.getAuthorities()
                .stream()
                .map(a -> new UserAuthority(Authority.valueOf(a)))
                .forEach(user::addAuthority);

        userRepository.save(user);

        return userViewMapper.map(user);
    }

    @Override
    @Cacheable(key = "#p0")
    @Transactional(readOnly = true)
    public UserView findByUsername(final String username) {
        final User user = userRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException(User.class, "username", username));
        return userViewMapper.map(user);
    }

    @Override
    @Transactional
    public UserView authenticate(final AuthRequest authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        User user = (User) authentication.getPrincipal();
        final String accessToken = jwtTokenManager.generateAccessToken(user);
        final UserView userView = userViewMapper.map(user);
        userView.setAccessToken(accessToken);
        return userView;
    }

    @Override
    @CacheEvict(key = "#p0")
    @Transactional
    public void delete(final String id) {
        userRepository.deleteById(id);
    }
}
