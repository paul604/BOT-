/*
 * La class Info de paul est mis à disposition selon les termes de la
 * licence Creative Commons Attribution - Pas d’Utilisation Commerciale - Partage dans les Mêmes Conditions 4.0 International.
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 */

package fr.paul.bot.listener.command;

import fr.paul.bot.Main;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IEmbed;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;

/**
 * <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/">
 * <img alt="Licence Creative Commons" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png">
 * </a>
 * <br>La class Info de paul est mis à disposition selon les termes de la <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/">
 * licence Creative Commons Attribution - Pas d’Utilisation Commerciale - Partage dans les Mêmes Conditions 4.0 International</a>.
 * <br>
 *
 * @author PAUL
 */
public class Info extends ACommand {

    public Info() {
        super("info", "les info du bot", null);
    }

    @Override
    public void run(IMessage message) {
        IUser client = Main.getClient().getOurUser();
        EmbedObject build = new EmbedBuilder()
                .withAuthorName(client.getName())
                .withAuthorIcon(client.getAvatarURL())
                .withThumbnail(client.getAvatarURL())

                .withDesc(client.mention()+" créer par Paul")
                .appendField("Link","[github](https://github.com/paul604/BOT-Discord)",false)

                .withColor(Color.RED)

                .build();
        message.getChannel().sendMessage(build);
    }
}
