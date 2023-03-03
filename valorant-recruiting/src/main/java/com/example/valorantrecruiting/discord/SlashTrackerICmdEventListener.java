package com.example.valorantrecruiting.discord;

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
        if (event instanceof SlashCommandInteractionEvent) {
            if (((SlashCommandInteractionEvent) event).getName().equals("tracker")) {
                Long userID = ((SlashCommandInteractionEvent) event).getUser().getIdLong();
                    String value = ((SlashCommandInteractionEvent) event).getOption("vlrtrackerlink").getAsString();
                    templateForLink.send("trackerlink", userID, value);
                    ((SlashCommandInteractionEvent) event).reply("success").queue();
            }
        }
    }
}
