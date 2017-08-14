/*
 * La class CommandManagement de paul est mis à disposition selon les termes de la
 * licence Creative Commons Attribution - Pas d’Utilisation Commerciale - Partage dans les Mêmes Conditions 4.0 International.
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 */

package fr.paul.bot.listener.command;

import sx.blah.discord.handle.obj.IMessage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/">
 * <img alt="Licence Creative Commons" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png">
 * </a>
 * <br>La class CommandManagement de paul est mis à disposition selon les termes de la <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/">
 * licence Creative Commons Attribution - Pas d’Utilisation Commerciale - Partage dans les Mêmes Conditions 4.0 International</a>.
 * <br>
 *
 * @author PAUL
 */
public class CommandManagement {

    private static Map<String, ACommand> commands = new HashMap<>();
    private static Map<String, String> aliases = new HashMap<>();

    public static void registerCommand(ACommand command) {
        commands.put(command.getName().toLowerCase(), command);
    }

    public static void registerAlias(String command, String ... alias) {
        if(alias != null && alias.length>0){
            for (String s : alias) {
                if(!command.equalsIgnoreCase(s))
                    aliases.put(s, command);
            }
        }
    }

    public static ACommand getCommand(String arg) {
        if (aliases.containsKey(arg))
            return getCommand(aliases.get(arg));
        return commands.get(arg.toLowerCase());
    }

    public static void run(IMessage message){
        if (message.getAuthor().isBot())
            return;

        String[] msgContent = message.getContent().split(" ");
        if (msgContent[0].length() == 1) {
            return;
        }
        ACommand command = getCommand(msgContent[0].substring(1));
        if (command != null) {
            try {
                command.run(message);
            } catch (Exception e) {
                message.getChannel().sendMessage("L'exécution de la commande : " + e.getClass().getName() + "a subit une erreur");
                e.printStackTrace();
            }
        }
    }

}
