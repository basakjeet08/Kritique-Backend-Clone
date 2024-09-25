package dev.anirban.kritique.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import dev.anirban.kritique.constants.NetworkStatusCodes;
import dev.anirban.kritique.dto.common.CustomResponse;
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

    private final ObjectMapper objectMapper = new ObjectMapper();

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

        Optional<FirebaseAuthenticationToken> optionalAuthentication = createAuthTokenWrapper(token);

        if (optionalAuthentication.isEmpty()) {
            invalidTokenResponse(response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(optionalAuthentication.get());

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

    private void invalidTokenResponse(HttpServletResponse response) throws IOException {
        CustomResponse<Object> customResponse = new CustomResponse<>(
                NetworkStatusCodes.INVALID_TOKEN,
                new InvalidToken().getMessage(),
                null
        );

        // Writing the Json Response
        response.getWriter().write(objectMapper.writeValueAsString(customResponse));
    }
}
