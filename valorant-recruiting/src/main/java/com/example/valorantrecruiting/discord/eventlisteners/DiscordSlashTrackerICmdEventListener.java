package com.example.valorantrecruiting.discord.eventlisteners;

import com.example.valorantrecruiting.model.Applicant;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class DiscordSlashTrackerICmdEventListener implements EventListener {

    private final KafkaTemplate<String, Applicant> template;


    //method for passing ur valoranttracker link with a cmd command in basically any channel
    public void onEvent(GenericEvent event) {
        if (event instanceof SlashCommandInteractionEvent listenedEvent) {
            if (listenedEvent.getName().equals("tracker")) {
                Long userID = listenedEvent.getUser().getIdLong();
                String userTrackerLink = listenedEvent.getOption("vlrtrackerlink").getAsString();
                Applicant applicant = Applicant.builder().discordId(userID).valorantTrackerLink(userTrackerLink).build();
                template.send("applicant", applicant);
                listenedEvent.reply("success").queue();
            }
        }
    }
}
