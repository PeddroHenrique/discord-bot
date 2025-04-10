package br.com.predio.discordbot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MyListeners extends ListenerAdapter {

    private static String canalId;

    static {
        canalId = PropertiesRetriver.getProperties("discord.canal-id");
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        TextChannel mensagem = event.getGuild().getTextChannelById(canalId);
        if (mensagem != null) {
            mensagem.sendMessage(event.getUser().getAsMention() + " kita dessa porra").queue();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        String content = message.getContentRaw();
        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        if (content.equals("testando")) {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("testando").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        }
    }
}
