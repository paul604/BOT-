package bot.listener;

import bot.Bot;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

/**
 * Created by paul on 01/10/2016.
 */
public class Listener extends ListenerAdapter {

    private Bot bot;
    private id "";//votre id

    public Listener(Bot bot){
        this.bot=bot;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        String msg = event.getMessage().getContent();
        if(event.getAuthor().getUsername().equals("Phoenix")){
            return;
        }
        if(msg.contains("(╯°□°）╯︵ ┻━┻")){
            event.getChannel().sendTyping();
            Message m = new MessageBuilder()
                    .appendString("┬─┬ ノ( ゜-゜ノ)  ")
                    .appendString("mais enfin ")
                    .appendMention(event.getAuthor())
                    .appendString(" cette table ne ta rien fait!")
                    .build();
            event.getChannel().sendMessage(m);
        }else if(msg.equals("test")){
            event.getChannel().sendTyping();
            Message m = new MessageBuilder()
                    .appendMention(event.getAuthor())
                    .appendString(" test recue 5/5.")
                    .build();
            event.getChannel().sendMessage(m);
        }else if(msg.equals("@Phoenix stop") && event.getAuthor().getId().equals(id)){
            this.bot.botStop();
        }
    }
}
