package br.com.predio.discordbot.listeners;

import br.com.predio.discordbot.PropertiesRetriver;
import net.dv8tion.jda.api.entities.Message;
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
        System.out.println("ENTROU NO METODO");
        Message mensagem = event.getMessage();
        String conteudo = mensagem.getContentRaw();

        boolean contemNomeUsuario = nomesUsuarios.stream().anyMatch(conteudo::contains);
//        System.out.println("SISTEMA " + mensagem.getAuthor().isSystem());
//        System.out.println("WEBHOOK " + mensagem.isWebhookMessage());
//        System.out.println("CONTEM USUSARIO " + contemNomeUsuario);
//        System.out.println("CONTEUDO " + mensagem.getContentRaw());
//        System.out.println("NOME DO AUTOR " + mensagem.getAuthor().getName());

        if (contemNomeUsuario) {
            System.out.println("ENTROU NA CONDIÇÃO");
            event.getChannel().sendMessage(event.getAuthor().getAsMention() + " kita dessa porra").queue();

            nomesUsuarios.removeIf(conteudo::contains);
        }
    }
}
