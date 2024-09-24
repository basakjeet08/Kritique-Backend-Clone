package dev.anirban.kritique.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.authentication.AbstractAuthenticationToken;


@Getter
@Setter
@Builder
@ToString
public class FirebaseAuthenticationToken extends AbstractAuthenticationToken {

    private final String uid; // Firebase UID
    private final Object principal;

    public FirebaseAuthenticationToken(String uid, Object principal) {
        super(null);
        this.uid = uid;
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}