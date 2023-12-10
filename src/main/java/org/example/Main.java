package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        Menu menu = new Menu();

        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try (// Créer un serveur socket
                        ServerSocket serverSocket = new ServerSocket(1234)) {
                    System.out.println("Serveur en attente de connexion...");

                    while (true) {
                        // Accepter une connexion
                        Socket socket = serverSocket.accept();
                        System.out.println("Connexion acceptée : " + socket);

                        // Créer un ObjectInputStream pour recevoir les commandes
                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                        // Recevoir la commande
                        Commande commande = (Commande) inputStream.readObject();
                        System.out.println("Commande reçue : " + commande);

                        // Ajouter la commande à la liste des commandes pour le bar
                        try (Socket socketAffi = new Socket("localhost", 1235)) {
                            ObjectOutputStream outputStream = new ObjectOutputStream(socketAffi.getOutputStream());
                            outputStream.writeObject(commande);
                            outputStream.flush();
                        } catch (ConnectException connectException) {
                            System.out.println("Serveur du bar non connecté");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

                        // Ajouter la commande à la liste des commandes pour la cuisine
                        try (Socket socketAffi = new Socket("localhost", 1236)) {
                            ObjectOutputStream outputStream = new ObjectOutputStream(socketAffi.getOutputStream());
                            outputStream.writeObject(commande);
                            outputStream.flush();
                        } catch (ConnectException connectException) {
                            System.out.println("Serveur de cuisine non connecté");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

                        // Créer et démarrer les threads pour les actions en parallèle
                        Thread cuisinierThread = new Thread(new Runnable() {
                            public void run() {
                                Cuisinier cuisinier = new Cuisinier("Bob", "Leponge", 2, "Cuisinier");
                                cuisinier.cuisinerCommande(commande);
                            }
                        });

                        Thread barmanThread = new Thread(new Runnable() {
                            public void run() {
                                Barman barman = new Barman("Patrick", "LetoileDeMer", 2, "Barman");
                                barman.preparerBoisson(commande);
                            }
                        });

                        commande.setEnPreparation(true);

                        cuisinierThread.start();
                        barmanThread.start();

                        // Dès que les threads sont terminés, afficher la facture
                        try {
                            cuisinierThread.join();
                            barmanThread.join();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }

                        FileText f = new FileText();

                        f.writeCommandeToFile(commande);

                        commande.afficherFacture();

                        // Fermer la connexion
                        socket.close();
                    }
                } catch (ClassNotFoundException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });

        serverThread.start();

        // Créer une fenêtre
        JFrame frame = new JFrame("Gestion du restaurant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Créer un panneau
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Ajouter des boutons pour chaque écrans
        JButton button = new JButton("Serveur (Prise de commande)");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi Serveur");
                menu.afficherMenu();
            }
        });
        panel.add(button);

        button = new JButton("Barman (Préparation des boissons)");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi Barman");
                EcransComm.affichageCommandeBar();
            }
        });
        panel.add(button);

        button = new JButton("Cuisinier (Préparation des plats)");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi Cuisinier");
                EcransComm.affichageCommandeCuisine();
            }
        });
        panel.add(button);

        // bouton de séparation
        button = new JButton(" ");
        button.setEnabled(false);
        panel.add(button);


        button = new JButton("Manager (Perfomance du restaurant / Courses)");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi Manager");
                EcransComm.affichageManager();
            }
        });
        panel.add(button);

        button = new JButton("Administrateur (Gestion des employés et stock)");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Réagir au clic sur la boisson
                System.out.println("Vous avez choisi Administrateur");
                EcransComm.affichageAdmin();
            }
        });
        panel.add(button);

        // Ajouter le panneau à la fenêtre
        frame.getContentPane().add(panel);

        // Définir la taille de la fenêtre
        frame.setSize(800, 550);

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }
}
