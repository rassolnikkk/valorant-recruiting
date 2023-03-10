package com.example.valorantrecruiting.discord.eventlisteners;

import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class SlashTrackerICmdEventListener implements EventListener {

    private final KafkaTemplate<Long, String> templateForLink;


    //method for passing ur valoranttracker link with a cmd command in basically any channel
    public void onEvent(GenericEvent event) {
        if (event instanceof SlashCommandInteractionEvent listenedEvent) {
            if ((listenedEvent).getName().equals("tracker")) {
                Long userID = (listenedEvent).getUser().getIdLong();
                    String value = (listenedEvent).getOption("vlrtrackerlink").getAsString();
                    templateForLink.send("trackerlink", userID, value);
                    (listenedEvent).reply("success").queue();
            }
        }
    }
}
