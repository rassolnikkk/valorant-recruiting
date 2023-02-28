package com.example.valorantrecruiting.discord;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
@RequiredArgsConstructor
public class ReactionEventListener implements EventListener {
    private final KafkaTemplate<Long, String> template;


    @Override
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


