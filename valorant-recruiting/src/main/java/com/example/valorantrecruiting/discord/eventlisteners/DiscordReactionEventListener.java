package com.example.valorantrecruiting.discord.eventlisteners;
import com.example.valorantrecruiting.model.Applicant;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class DiscordReactionEventListener implements EventListener {

    private final KafkaTemplate<String, Applicant> template;



    //this method gets all reactions on specific message which should be used for registration
    //dont see any need in creating two ones because it is not gonna expand
    public void onEvent(GenericEvent event) {
        if (event instanceof MessageReactionAddEvent listenedEvent) {
            String messageIdForRank = "1072303482628997183";
            String messageIdForRole = "1072908287181336666";
            Long reactionUserId = listenedEvent.getUserIdLong();
            String emojiName = listenedEvent.getReaction().getEmoji().getName();
           if (listenedEvent.getMessageId().equals(messageIdForRank)) {
               template.send("applicant", Applicant.builder().discordId(reactionUserId).rank(emojiName).build());
          }
           if (listenedEvent.getMessageId().equals(messageIdForRole))
               template.send("applicant", Applicant.builder().discordId(reactionUserId).role(emojiName).build());
           }
        }
    }


