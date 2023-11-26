package com.apera.aperaserver.resource.security;

import com.apera.aperaserver.enterprise.ResourceNotFoundException;
import com.apera.aperaserver.enterprise.TokenRefreshException;
import com.apera.aperaserver.model.QWallet;
import com.apera.aperaserver.model.User;
import com.apera.aperaserver.model.Wallet;
import com.apera.aperaserver.model.security.ERole;
import com.apera.aperaserver.model.security.RefreshToken;
import com.apera.aperaserver.model.security.Role;
import com.apera.aperaserver.repository.UserRepository;
import com.apera.aperaserver.repository.WalletRepository;
import com.apera.aperaserver.repository.security.RefreshTokenService;
import com.apera.aperaserver.repository.security.RoleRepository;
import com.apera.aperaserver.resource.security.payload.request.LoginRequest;
import com.apera.aperaserver.resource.security.payload.request.SignupRequest;
import com.apera.aperaserver.resource.security.payload.request.TokenRefreshRequest;
import com.apera.aperaserver.resource.security.payload.response.JwtResponse;
import com.apera.aperaserver.resource.security.payload.response.MessageResponse;
import com.apera.aperaserver.resource.security.payload.response.TokenRefreshResponse;
import com.apera.aperaserver.security.jwt.JwtUtils;
import com.apera.aperaserver.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

            List<Wallet> wallet = walletRepository.findAll(QWallet.wallet.user.id.eq(userDetails.getId())).stream().toList();

            System.out.println("ai papai" + wallet);

            return ResponseEntity.ok(new JwtResponse(jwt,
                    refreshToken.getToken(),
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles, wallet));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha inválidos");
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        List<Role> all = roleRepository.findAll();
        if (all.isEmpty()) {
            Role role = new Role();
            role.setName(ERole.ROLE_ADMIN);
            roleRepository.save(role);
            Role role2 = new Role();
            role2.setName(ERole.ROLE_USER);
            roleRepository.save(role2);
            Role role3 = new Role();
            role3.setName(ERole.ROLE_MODERATOR);
            roleRepository.save(role3);
        }


        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}
