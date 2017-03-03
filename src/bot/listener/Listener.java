package bot.listener;

import bot.Bot;
import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.Permission;
import net.dv8tion.jda.entities.*;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 01/10/2016.
 */
public class Listener extends ListenerAdapter {

    private Bot bot;
    private boolean jdrOn = false;
    private List<Permission> permeOk = new ArrayList<Permission>();

    public Listener(Bot bot){
        this.bot=bot;
        permeOk.add(Permission.VOICE_CONNECT );
        permeOk.add(Permission.VOICE_SPEAK );
      //  System.out.println(permeOk.);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        String msg = event.getMessage().getContent();

        if(msg.charAt(0) != '!'){return;}
        msg=msg.substring(1);

        if( !( ((Channel)event.getChannel()).getName().equalsIgnoreCase("jdr") ) ){ return;}

        boolean roleOk=false;
        for(Role r : event.getGuild().getRolesForUser(event.getAuthor())){
            if(r.getName().equals("jdr_MJ")){
                roleOk=true;
            }
        }
        if(!roleOk && !(event.getAuthor().getId().equals("144846373957664769")) ){return;}

        //--------comand-----
        if(msg.equals("stop")){
            this.bot.botStop();

        }else if(msg.equals("jdr")){
            if(jdrOn){
                event.getChannel().sendMessage(new MessageBuilder()
                    .appendString("fin du jdr")
                    .build());
                jdrOn=false;
            }else {
                event.getChannel().sendMessage(new MessageBuilder()
                        .appendString("début du jdr")
                        .build());
                jdrOn = true;
            }

        }else if(msg.equalsIgnoreCase("a")){
            event.getGuild().getVoiceChannels().stream().filter(chanel -> chanel.getName().equalsIgnoreCase("jdr")).forEach(chanel -> {

                PermissionOverride permA = chanel.getOverrideForRole(event.getGuild().getRolesByName("jdr_a").get(0));
                permA.getManager().grant(this.permeOk.toArray(new Permission[this.permeOk.size()])).update();

                PermissionOverride permB = chanel.getOverrideForRole(event.getGuild().getRolesByName("jdr_b").get(0));
                permB.getManager().deny(this.permeOk.toArray(new Permission[this.permeOk.size()])).update();

            });
            event.getChannel().sendMessage("jdr ouvert au groupe a");
        }else if(msg.equalsIgnoreCase("b")){
            event.getGuild().getVoiceChannels().stream().filter(chanel -> chanel.getName().equalsIgnoreCase("jdr")).forEach(chanel -> {

                PermissionOverride permB = chanel.getOverrideForRole(event.getGuild().getRolesByName("jdr_b").get(0));
                permB.getManager().grant(this.permeOk.toArray(new Permission[this.permeOk.size()])).update();

                PermissionOverride permA = chanel.getOverrideForRole(event.getGuild().getRolesByName("jdr_a").get(0));
                permA.getManager().deny(this.permeOk.toArray(new Permission[this.permeOk.size()])).update();

            });
            event.getChannel().sendMessage("jdr ouvert au groupe b");
        }
    }
}
