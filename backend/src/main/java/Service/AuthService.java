package Service;

import com.pathpulse.dto.auth.AuthResponse;
import com.pathpulse.dto.auth.LoginRequest;
import com.pathpulse.dto.auth.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest request);
}
