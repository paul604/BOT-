package bot;


import bot.listener.Listener;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;

import javax.security.auth.login.LoginException;
import java.util.Scanner;

/**
 * Created by paul on 01/10/2016.
 */
public class Bot {

    private JDA jda;
    private boolean stop;

    public Bot(String token){
        try {
            jda = new JDABuilder().setBotToken(token).setBulkDeleteSplittingEnabled(false).buildBlocking();

            jda.addEventListener(new Listener(this));

        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Une erreur est survenue vieulliez verifier le token ou votre connection internet");
            return;
        }
        System.out.println("Connecte avec: " + jda.getSelfInfo().getUsername());
        int i;
        System.out.println("Le Bot est autorisé sur " + (i = jda.getGuilds().size()) + " serveur" + (i > 1 ? "s" : ""));
        stop = false;
       /* while (!stop) {
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.next();
            if (cmd.equalsIgnoreCase("stop")) {
                botStop();
            }
        }*/
    }

    public void botStop(){
        jda.shutdown(true);
        stop = true;
    }

}
