package dev.anirban.kritique.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import dev.anirban.kritique.dto.common.AccessTokenBody;
import dev.anirban.kritique.dto.user.UserDTO;
import dev.anirban.kritique.entity.User;
import dev.anirban.kritique.enums.Role;
import dev.anirban.kritique.exception.InvalidToken;
import dev.anirban.kritique.exception.KIITEmailNotFound;
import dev.anirban.kritique.exception.TokenNotFound;
import dev.anirban.kritique.exception.UserNotFound;
import dev.anirban.kritique.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Log4j2
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final RandomNameGenerator randomNameGenerator;

    public UserDTO loginUser(AccessTokenBody tokenBody) {

        // Checking authHeader is present or not
        String authHeader = tokenBody.getToken();
        if (authHeader == null || authHeader.isBlank() || !authHeader.startsWith("Bearer"))
            throw new TokenNotFound();

        String token = authHeader.substring(7);

        // Fetching the firebase user
        UserRecord firebaseUser = fetchFirebaseUserFromToken(token).orElseThrow(InvalidToken::new);

        // If the user has kiit email id or not
        if (!hasKIITEmail(firebaseUser))
            throw new KIITEmailNotFound(firebaseUser.getEmail());

        // If user not present then create user.
        if (isUserPresent(firebaseUser))
            return fetchUserById(firebaseUser).toUserDTO();
        else
            return createUser(firebaseUser).toUserDTO();
    }

    private Optional<UserRecord> fetchFirebaseUserFromToken(String token) {
        try {

            final FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            final String uid = decodedToken.getUid();

            return Optional.of(FirebaseAuth.getInstance().getUser(uid));
        } catch (FirebaseAuthException e) {
            log.error(e);
            return Optional.empty();
        }
    }

    private boolean hasKIITEmail(UserRecord firebaseUser) {
        return firebaseUser.getEmail().contains("@kiit.ac.in");
    }

    private boolean isUserPresent(UserRecord firebaseUser) {
        return userRepo.existsById(firebaseUser.getUid());
    }

    private User fetchUserById(UserRecord firebaseUser) {
        return userRepo
                .findById(firebaseUser.getUid())
                .orElseThrow(() -> new UserNotFound(firebaseUser.getUid()));
    }

    private User createUser(UserRecord firebaseUser) {

        // Creating a User Object using the firebase User
        User user = User
                .builder()
                .uid(firebaseUser.getUid())
                .name(firebaseUser.getDisplayName())
                .anonymousName(randomNameGenerator.generateRandomName())
                .email(firebaseUser.getEmail())
                .photoUrl(firebaseUser.getPhotoUrl())
                .role(Role.USER)
                .build();

        return userRepo.save(user);
    }
}

