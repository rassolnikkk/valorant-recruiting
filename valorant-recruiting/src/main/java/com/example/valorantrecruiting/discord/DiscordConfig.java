package com.example.valorantrecruiting.discord;

import com.example.valorantrecruiting.discord.eventlisteners.ReactionEventListener;
import com.example.valorantrecruiting.discord.eventlisteners.SlashAgeCmdEventListener;
import com.example.valorantrecruiting.discord.eventlisteners.SlashTrackerICmdEventListener;
import com.example.valorantrecruiting.kafka.producers.ApplicantKafkaProducer;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import javax.security.auth.login.LoginException;
@RequiredArgsConstructor
@Configuration
public class DiscordConfig {

    private final KafkaTemplate template;
    private final ApplicantKafkaProducer producer;
    private JDA botEntity;
    //this method is creating a bot instance with all eventlisteners and intentions,
    @Bean
    public JDA botCreator() throws LoginException {
        JDABuilder bot = JDABuilder.createDefault("MTA3MTAzNjM4MTEyOTg3OTY1Mg.GMI93l.Kh5HN1wHUoagFsA_tbQsGyXUCuVcJyrEYl4zu8");
        bot.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGE_REACTIONS, GatewayIntent.GUILD_MESSAGE_REACTIONS,GatewayIntent.DIRECT_MESSAGES);
        bot.addEventListeners(
                new SlashTrackerICmdEventListener(template, producer),
                new ReactionEventListener(template, producer),
                new SlashAgeCmdEventListener(template, producer));
        botEntity = bot.build();
        this.slashCommands();
        return this.botEntity;
    }

    //adding slashcmds support to bot instance
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
