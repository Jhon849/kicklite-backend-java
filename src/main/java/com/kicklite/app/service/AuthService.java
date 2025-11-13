@Service
public class AuthService {

    private final UserRepository users;
    private final RoleRepository roles;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwt;

    public AuthService(UserRepository users, RoleRepository roles,
                       PasswordEncoder encoder, AuthenticationManager authManager,
                       JwtUtil jwt) {
        this.users = users;
        this.roles = roles;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwt = jwt;
    }

    public JwtResponse login(LoginRequest req) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password())
        );

        var user = users.findByEmail(req.email()).orElseThrow();

        String token = jwt.generate(user.getEmail());

        return new JwtResponse(token, user.getEmail());
    }
}



