package org.example;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EcransComm {

    public static void affichageCommandeBar() {

        // Modèle de tableau pour stocker les données
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Commande BAR");

        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(1235)) {
                    System.out.println("Serveur en attente de connexion...");

                    while (true) {
                        Socket socket = serverSocket.accept();
                        System.out.println("Connexion acceptée : " + socket);

                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                        Commande commande = (Commande) inputStream.readObject();
                        System.out.println("Commande reçue : " + commande);

                        // Ajouter la commande au modèle du tableau
                        tableModel.addRow(new Object[] { commande.getBoissonsAffi() });
                    }
                } catch (EOFException e) {
                    // Fin du flux atteinte
                    e.printStackTrace();
                } catch (SocketException e) {
                    // Connexion fermée par l'hôte distant
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        serverThread.start();

        // Interface graphique
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Commandes");
        fenetre.setSize(500, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        // Tableau pour afficher les commandes
        JTable tableau = new JTable();
        tableau.setModel(tableModel);

        // Ajouter le tableau à un JScrollPane pour permettre le défilement si
        // nécessaire
        JScrollPane scrollPane = new JScrollPane(tableau);
        panel.add(scrollPane);

        // Bouton pour fermer l'application
        JButton buttonFermer = new JButton("Fermer");
        buttonFermer.setFont(new Font("Arial", Font.BOLD, 14));
        buttonFermer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fermer la fenêtre
                fenetre.dispose();
            }
        });
        panel.add(buttonFermer);

        fenetre.setContentPane(panel);
        fenetre.setVisible(true);
    }


    public static void affichageCommandeCuisine() {

        // Modèle de tableau pour stocker les données
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Commande CUISINE");

        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(1236)) {
                    System.out.println("Serveur en attente de connexion...");

                    while (true) {
                        Socket socket = serverSocket.accept();
                        System.out.println("Connexion acceptée : " + socket);

                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                        Commande commande = (Commande) inputStream.readObject();
                        System.out.println("Commande reçue : " + commande);

                        // Ajouter la commande au modèle du tableau
                        tableModel.addRow(new Object[] { commande.getRepasAffi() });
                    }
                } catch (EOFException e) {
                    // Fin du flux atteinte
                    e.printStackTrace();
                } catch (SocketException e) {
                    // Connexion fermée par l'hôte distant
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        serverThread.start();

        // Interface graphique
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Commandes");
        fenetre.setSize(500, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        // Tableau pour afficher les commandes
        JTable tableau = new JTable();
        tableau.setModel(tableModel);

        // Ajouter le tableau à un JScrollPane pour permettre le défilement si
        // nécessaire
        JScrollPane scrollPane = new JScrollPane(tableau);
        panel.add(scrollPane);

        // Bouton pour fermer l'application
        JButton buttonFermer = new JButton("Fermer");
        buttonFermer.setFont(new Font("Arial", Font.BOLD, 14));
        buttonFermer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fermer la fenêtre
                fenetre.dispose();
            }
        });
        panel.add(buttonFermer);

        fenetre.setContentPane(panel);
        fenetre.setVisible(true);
    }

}
