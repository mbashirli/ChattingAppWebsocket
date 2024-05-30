package dp.ms.chattingappwebsocket.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void connectUser(User user) {
        user.setUserStatus(UserStatus.ONLINE);
        userRepository.save(user);
    }

    public void disconnectUser(User user) {
        var savedUser = userRepository.findById(user.getUsername()).orElse(null);
        if (savedUser != null) {
            savedUser.setUserStatus(UserStatus.OFFLINE);
            userRepository.save(savedUser);
        }
    }

    public List<User> findConnectedUsers() {
        return userRepository.findAllByUserStatus(UserStatus.ONLINE);
    }
}
