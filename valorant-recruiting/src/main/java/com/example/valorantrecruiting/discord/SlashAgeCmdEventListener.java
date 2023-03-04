package com.example.valorantrecruiting.discord;

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
        if (event instanceof SlashCommandInteractionEvent) {
            if (((SlashCommandInteractionEvent) event).getName().equals("age")) {
                Long userID = ((SlashCommandInteractionEvent) event).getUser().getIdLong();
                    String Myvalue = ((SlashCommandInteractionEvent) event).getOption("age").getAsString();
                    template.send("age", userID, Myvalue);
                    ((SlashCommandInteractionEvent) event).reply("success").queue();
            }
        }
    }


}
