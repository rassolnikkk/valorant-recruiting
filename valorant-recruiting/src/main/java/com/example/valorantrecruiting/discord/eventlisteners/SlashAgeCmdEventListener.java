package com.example.valorantrecruiting.discord.eventlisteners;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
@RequiredArgsConstructor
public class SlashAgeCmdEventListener implements EventListener {
    private final KafkaTemplate<Long, String> template;
    //method for passing ur age with a cmd command in basically any channel
    public void onEvent(GenericEvent event) {
        if (event instanceof SlashCommandInteractionEvent listenedEvent) {
            if ((listenedEvent).getName().equals("age")) {
                Long userID = (listenedEvent).getUser().getIdLong();
                    String Myvalue = (listenedEvent).getOption("age").getAsString();
                    template.send("age", userID, Myvalue);
                    (listenedEvent).reply("success").queue();
            }
        }
    }


}
