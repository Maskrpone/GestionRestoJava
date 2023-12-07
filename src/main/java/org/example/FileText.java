package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class FileText {
    public void writeCommandeToFile(Commande commande) {
        // Générer le nom de fichier en fonction de la date et de l'heure actuelles
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileName = "commande_table_" + commande.getTable() + "_" + dateFormat.format(new Date()) + ".txt";

        // Combiner le chemin du dossier et le nom du fichier
        Path filePath = Paths.get("facture/additionClient/", fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            // Obtenir la date et l'heure actuelles
            SimpleDateFormat dateFormatForMetadata = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = dateFormatForMetadata.format(new Date());

            // Écrire la date, l'heure et le numéro de la table
            writer.write("Commande table n° " + commande.getTable() + " - " + dateStr);
            writer.newLine();
            writer.newLine(); // Ligne vide pour séparer la métadonnée du reste du contenu

            // Écrire le titre "Boisson"
            writer.write("Boisson:");
            writer.newLine();

            // Écrire le nom et le prix de chaque Boisson avec une indentation
            for (Boisson boisson : commande.getBoissons()) {
                writer.write("\t" + boisson.getName() + " - " + boisson.getPrice());
                writer.newLine();
            }

            // Écrire une ligne vide pour séparer les boissons des plats
            writer.newLine();

            // Écrire le titre "Plat"
            writer.write("Plat:");
            writer.newLine();

            // Écrire le nom et le prix de chaque Produit avec une indentation
            for (Produits produit : commande.getRepas()) {
                writer.write("\t" + produit.getNom() + " - " + produit.getPrice());
                writer.newLine();
            }

            // Écrire une ligne vide avant la section "Total"
            writer.newLine();

            // Calculer la somme des prix de la commande
            double total = commande.calculTotal();

            // Écrire la section "Total"
            writer.write("Total: " + total);

            System.out.println("La commande pour la table " + commande.getTable() + " a été écrite dans le fichier " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeStockToFile(Map<Ingredients, Integer> map, Map<Boisson, Integer> mapBoisson){
        // Générer le nom de fichier en fonction de la date et de l'heure actuelles
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileName = "facture" + dateFormat.format(new Date()) + ".txt";

        // Combiner le chemin du dossier et le nom du fichier
        Path filePath = Paths.get("facture/factureStock/", fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            // Écrire l'en-tête de la facture
            writer.write("****************************************");
            writer.newLine();
            writer.write("                FACTURE                ");
            writer.newLine();
            writer.write("****************************************");
            writer.newLine();
            writer.newLine();

            writer.newLine();

            // Écrire la légende des ingrédients
            writer.write(String.format("%-20s%-10s%-10s", "Nom", "Prix", "Quantité"));
            writer.newLine();
            writer.newLine();

            writer.write("Ingrédient");
            writer.newLine();

            // Écrire les détails de la facture
            for (Map.Entry<Ingredients, Integer> entry : map.entrySet()) {
                Ingredients ingredient = entry.getKey();
                int quantity = entry.getValue();
                writer.write(String.format("%-20s%-10s%-10s", ingredient.getNom(), ingredient.getPrice(), quantity));
                writer.newLine();
            }

            writer.newLine();
            writer.write("Boisson");
            writer.newLine();
            writer.newLine();

            for (Map.Entry<Boisson, Integer> entry : mapBoisson.entrySet()) {
                Boisson boisson = entry.getKey();
                int quantity = entry.getValue();
                writer.write(String.format("%-20s%-10s%-10s", boisson.getName(), boisson.getPrice(), quantity));
                writer.newLine();
            }

            int total = 0;
            // Ajouter les prix des ingrédients avec leurs quantités respectives
            for (Map.Entry<Ingredients, Integer> entry : map.entrySet()) {
                Ingredients ingredient = entry.getKey();
                int quantity = entry.getValue();
                total += ingredient.getPrice() * quantity;
            }
            // Ajouter les prix des Boissons avec leurs quantités respectives
            for (Map.Entry<Boisson, Integer> entry : mapBoisson.entrySet()) {
                Boisson boisson = entry.getKey();
                int quantity = entry.getValue();
                total += boisson.getPrice() * quantity;
            }

            // Écrire le total
            writer.write(String.format("%-40s%-10s", "Total Ingrédients:", total));
            writer.newLine();
            writer.newLine();

            // Ligne de séparation
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
