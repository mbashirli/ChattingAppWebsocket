package dp.ms.chattingappwebsocket.chat;

import dp.ms.chattingappwebsocket.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatRoomService chatRoomService;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;

    public void processMessage(ChatMessage chatMessage) {
        Optional<String> chatRoomId = chatRoomService
                .getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);

        chatRoomId.ifPresent(chatMessage::setChatId);
        chatMessageRepository.save(chatMessage);

        messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(), "/queue/messages",
                new ChatMessageNotification(chatMessage.getChatId(), chatMessage.getSenderId(), chatMessage.getRecipientId(), chatMessage.getContent()));
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId){
        var chatRoomId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatRoomId.map(chatMessageRepository::findAllByChatId).orElse(new ArrayList<>());
    }
}
