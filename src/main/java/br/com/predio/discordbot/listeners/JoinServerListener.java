package br.com.predio.discordbot.listeners;

import br.com.predio.discordbot.utils.PropertiesRetriver;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class JoinServerListener extends ListenerAdapter {

    private final List<String> nomesUsuarios = new ArrayList<>();
    private final String idChat = PropertiesRetriver.getProperties("discord.chat-principal-id");

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        String nomeUsuario = event.getUser().getName();
        nomesUsuarios.add(nomeUsuario);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel().getId().equals(PropertiesRetriver.getProperties("discord.chat-principal-id"))) {

            if (event.getMessage().getAuthor().isBot()) return;

            String nomeUsuario = event.getMessage().getAuthor().getName();
            boolean contemNomeUsuario = nomesUsuarios.stream().anyMatch(nomeUsuario::contains);

            if (contemNomeUsuario) {
                event.getChannel().sendMessage(event.getAuthor().getAsMention() + " kita dessa porra").queue();
                nomesUsuarios.removeIf(nomeUsuario::contains);
            }
        }
    }
}
