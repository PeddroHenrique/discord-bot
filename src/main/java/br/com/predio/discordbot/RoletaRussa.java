package br.com.predio.discordbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class RoletaRussa {

    public static void main(String[] args) throws LoginException, InterruptedException {
        String token = TokenRetriver.getProperties("token");
        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new PingPongListener())
                .build();

        jda.awaitReady();
    }
}