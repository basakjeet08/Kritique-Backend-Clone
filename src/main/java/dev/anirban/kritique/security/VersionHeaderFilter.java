package dev.anirban.kritique.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.anirban.kritique.constants.NetworkStatusCodes;
import dev.anirban.kritique.constants.VersionConstant;
import dev.anirban.kritique.dto.common.CustomResponse;
import dev.anirban.kritique.exception.AppVersionMismatchError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@Slf4j
public class VersionHeaderFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String versionHeader = request.getHeader("Version");

        if (!versionHeader.equals(VersionConstant.APP_VERSION)) {
            writeJsonResponse(response);
            return;
        }

        // Continue the request after successful token verification
        filterChain.doFilter(request, response);
    }

    private void writeJsonResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");

        // Create a CustomResponse object
        CustomResponse<Object> customResponse = new CustomResponse<>(
                NetworkStatusCodes.VERSION_MISMATCH,
                new AppVersionMismatchError().getMessage(),
                null
        );

        // Writing the Json Response
        response.getWriter().write(objectMapper.writeValueAsString(customResponse));
    }
}