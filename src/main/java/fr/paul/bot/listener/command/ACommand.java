/*
 * La class ACommand de paul est mis à disposition selon les termes de la
 * licence Creative Commons Attribution - Pas d’Utilisation Commerciale - Partage dans les Mêmes Conditions 4.0 International.
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 */

package fr.paul.bot.listener.command;

import sx.blah.discord.handle.obj.IMessage;

/**
 * <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/">
 * <img alt="Licence Creative Commons" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png">
 * </a>
 * <br>La class ACommand de paul est mis à disposition selon les termes de la <a href="http://creativecommons.org/licenses/by-nc-sa/4.0/">
 * licence Creative Commons Attribution - Pas d’Utilisation Commerciale - Partage dans les Mêmes Conditions 4.0 International</a>.
 * <br>
 *
 * @author PAUL
 */
public abstract class ACommand {

    private final String name;
    private String description;

    public ACommand(String name, String description, String ... alisas) {
        this.name = name;
        this.description = description;

        CommandManagement.registerCommand(this);
        CommandManagement.registerAlias(name, alisas);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void run(IMessage message);

}
