package com.example.valorantrecruiting.discord;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class ReactionEventListener implements EventListener {
    private final KafkaTemplate<Long, String> template;


    //this method gets all reactions on specific message which should be used for registration
    //dont see any need in creating two ones because it is not gonna expand
    public void onEvent(GenericEvent event) {
        String messageIdForRank = "1072303482628997183";
        String messageIdForRole = "1072908287181336666";
        if (event instanceof MessageReactionAddEvent) {
            Long reactionUserId = ((MessageReactionAddEvent) event).getUserIdLong();
            String emojiName = ((MessageReactionAddEvent) event).getReaction().getEmoji().getName();
           if (((MessageReactionAddEvent) event).getMessageId().equals(messageIdForRank)) {
               template.send("rank", reactionUserId, emojiName);
          }else;
           if (((MessageReactionAddEvent) event).getMessageId().equals(messageIdForRole))
              template.send("roles",reactionUserId,emojiName);
           }
        }
    }


