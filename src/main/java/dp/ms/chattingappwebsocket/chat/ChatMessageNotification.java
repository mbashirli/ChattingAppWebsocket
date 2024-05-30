package dp.ms.chattingappwebsocket.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageNotification {

    private String id;
    private String senderId;
    private String recipientId;
    private String content;
}
