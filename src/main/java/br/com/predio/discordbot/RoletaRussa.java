package br.com.predio.discordbot;

import br.com.predio.discordbot.listeners.JoinServerListener;
import br.com.predio.discordbot.listeners.TestingListener;
import br.com.predio.discordbot.utils.PropertiesRetriver;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class RoletaRussa {

    public static void main(String[] args) throws LoginException, InterruptedException {

        String token = PropertiesRetriver.getProperties("token");

        JDA jda = JDABuilder.createDefault(token)
                .setActivity(Activity.competing("?kita"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new JoinServerListener(), new TestingListener())
                .build();

        jda.awaitReady();
    }
}