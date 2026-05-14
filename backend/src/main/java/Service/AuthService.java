package Service;

import com.pathpulse.dto.auth.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest registerRequest);
}
