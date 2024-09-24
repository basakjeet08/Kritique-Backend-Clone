package dev.anirban.kritique.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import dev.anirban.kritique.exception.InvalidToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;


@Component
@Log4j2
public class FirebaseTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Fetching the Authorization header from the Request
        final String authHeader = request.getHeader("Authorization");

        // If the Authorization header is null or have something else then we pass it to the next filter
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Fetching the JWT Token from the Header ignoring the "Bearer " Text.
        String token = authHeader.substring(7);

        FirebaseAuthenticationToken authentication = createAuthTokenWrapper(token).orElseThrow(InvalidToken::new);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue the request after successful token verification
        filterChain.doFilter(request, response);
    }


    private Optional<FirebaseAuthenticationToken> createAuthTokenWrapper(String token) {
        try {

            // Verify the token using Firebase Admin SDK
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String uid = decodedToken.getUid();

            return Optional.of(new FirebaseAuthenticationToken(uid, decodedToken));

        } catch (FirebaseAuthException e) {
            log.error(e);
            return Optional.empty();
        }
    }
}
