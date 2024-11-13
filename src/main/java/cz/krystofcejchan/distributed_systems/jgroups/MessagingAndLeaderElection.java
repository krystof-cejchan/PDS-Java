package cz.krystofcejchan.distributed_systems.jgroups;


import org.jgroups.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessagingAndLeaderElection implements Receiver {
    private JChannel channel;
    private Address leader;

    public void start() throws Exception {
        // Vytvoření kanálu s výchozí konfigurací
        channel = new JChannel();
        channel.setReceiver(this);
        // Připojení ke skupině
        channel.connect("Cluster");

        // Spuštění vlákna pro čtení vstupu z konzole
        eventLoop();

        // Zavření kanálu při ukončení
        channel.close();
    }

    private void eventLoop() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while (true) {
                System.out.print("> ");
                line = in.readLine();
                if (line == null || line.equalsIgnoreCase("exit")) {
                    break;
                }
                // Vytvoření a odeslání zprávy
                Message msg = new ObjectMessage(null, line);
                channel.send(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void viewAccepted(View newView) {
        System.out.println("** Nové zobrazení: " + newView);
        electLeader(newView);
    }

    private void electLeader(View view) {
        // Volba lídra - poslední adresa ve View (nejmladší člen)
        Address newLeader = view.getMembers().getLast();
        if (!newLeader.equals(leader)) {
            leader = newLeader;
            if (leader.equals(channel.getAddress())) {
                System.out.println("Jsem nový lídr.");
                try {
                    channel.send(new ObjectMessage(null, "System: %s je náš nový lídr!".formatted(leader)));
                } catch (Exception e) {
                    Logger.getAnonymousLogger().log(Level.WARNING, "Zpráva nebyla odeslána");
                }
                /*
                 * zde může lídr provést nějaké změny
                 */
            } else {
                System.out.println("Nový lídr je: " + leader);
            }
        }
    }

    @Override
    public void receive(Message msg) {
        String line = msg.getObject();
        System.out.println(msg.getSrc() + ": " + line);
        // Pokud je aktuální uzel lídrem, může zpracovat speciální zprávy
        if (!(msg.getSrc().equals(channel.getAddress())) && leader.equals(channel.getAddress())) {
            System.out.println("Dostali jsme zprávu od lídra!");
        }
    }

    public static void main(String... args) throws Exception {
        new MessagingAndLeaderElection().start();
    }
}
