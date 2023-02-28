package com.example.valorantrecruiting.discord;


import com.example.valorantrecruiting.kafka.KafkaProducerConfig;

import com.example.valorantrecruiting.repository.ApplicantRepo;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import javax.security.auth.login.LoginException;
@Configuration
public class BotBuilder {


     public JDA botEntity;

    @EventListener(ApplicationReadyEvent.class)
    public JDA botCreator() throws LoginException {
        JDABuilder bot = JDABuilder.createDefault("MTA3MTAzNjM4MTEyOTg3OTY1Mg.G5iPuf.-_IBUuIfVO_JazEk5GAAmw7C2QFgRZACtEqKss" );
        bot.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGE_REACTIONS, GatewayIntent.GUILD_MESSAGE_REACTIONS,GatewayIntent.DIRECT_MESSAGES);
        bot.addEventListeners(new BotReadyEventListener(),
                new SlashTrackerICmdEventListener(new KafkaProducerConfig().kafkaTemplate()),
                new ReactionEventListener(new KafkaProducerConfig().kafkaTemplate()),
                new SlashAgeCmdEventListener(new KafkaProducerConfig().kafkaTemplate()));
        botEntity = bot.build();
        this.slashCommands();
        return this.botEntity;
    }

    public void slashCommands(){
        JDA slashCmdsBotObject = this.botEntity;
        slashCmdsBotObject.upsertCommand("tracker", "pass ur vlrtracker link here")
                .setGuildOnly(false)
                .addOption(OptionType.STRING,"vlrtrackerlink", "pass here ur vlrtracker link",true)
                .queue();
        slashCmdsBotObject.upsertCommand("age", "pass ur age here")
                .setGuildOnly(false)
                .addOption(OptionType.INTEGER,"age", "pass here ur age",true)
                .queue();
    }
}