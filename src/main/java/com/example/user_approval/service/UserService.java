
import com.example.user_approval.dto.request.UserRegistrationRequest;
import com.example.user_approval.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse registerUser(UserRegistrationRequest request);
    UserResponse getUserById(UUID userId);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(UUID userId, UserRegistrationRequest request);
    void deleteUser(UUID userId);
}