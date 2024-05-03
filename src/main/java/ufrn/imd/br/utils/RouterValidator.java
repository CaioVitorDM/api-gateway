package ufrn.imd.br.utils;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static final List<String> endpoits = List.of(
            "/api/auth-server/v1/auth/authenticate",
            "/api/auth-server/v1/users/register",
            "/api/auth-server/v1/auth/refresh-token",
            "/api/auth-server/v1/recover-password",
            "/api/auth-server/v1/recover-password/send-code",
            "/api/auth-server/v1/recover-password/change-password"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> endpoits
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
