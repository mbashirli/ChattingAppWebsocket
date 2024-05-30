package dp.ms.chattingappwebsocket.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByUserStatus(UserStatus userStatus);
}
