package com.example.valorantrecruiting.discord.eventlisteners;

import com.example.valorantrecruiting.model.Applicant;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class DiscordSlashAgeCmdEventListener implements EventListener {

    private final KafkaTemplate<String, Applicant> template;

    //method for passing ur age with a cmd command in basically any channel
    public void onEvent(GenericEvent event) {
        if (event instanceof SlashCommandInteractionEvent listenedEvent) {
            if (listenedEvent.getName().equals("age")) {
                Long userID = listenedEvent.getUser().getIdLong();
                Integer userAge = listenedEvent.getOption("age").getAsInt();
                template.send("applicant", Applicant.builder().discordId(userID).age(userAge).build());
                listenedEvent.reply("success").queue();
            }
        }
    }


}
