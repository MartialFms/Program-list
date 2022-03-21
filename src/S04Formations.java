import java.util.ArrayList;

/**
 * Application commerciale gerant un panier pour l'achat de formation.
 * Il est composé d'un Hashmap (avec un systeme de Id/valeur pour la liste des formations
 * avec gestion des quantités d'achat) et un Hashmap en extention pour gerer les préreservations
 * 
 * @author M.Derand - Mars 2022
 * @version 1.3
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class S04Formations {

	/**
	 * Methode principale gerant le menu, et son fonctionnement
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Liste des formations avec quantité et prix
		HashMap<Integer, List<String>> programList = new HashMap<>();
		// Liste des préreservations avec quantité
		HashMap<Integer, List<String>> futurAddList = new HashMap<>();
		
		Scanner scan = new Scanner(System.in);
		int menuChoice = 0;													// choix fait dans le menu
		int cartPrice = 0;													// montant total du caddie

		// Mise en place du menu principale, l'option 'endOfCart' permet d'ajouter la facture finale.
		initData(programList, futurAddList);
		buildArray(programList);
		for (boolean endOfCart = false; endOfCart != true;) {
			System.out.println("\nQue souhaitez vous faire ? ");
			System.out.println(" 1/ Voir votre panier\n 2/ Ajouter une formation au panier\n 3/ Retirer une formation du panier\n 4/ Supprimer le panier\n 5/ Passer à la caisse\n 6/ Voir les prochains cours\n 0/ Sortir");
			while (!scan.hasNextInt())
				scan.next(); menuChoice = scan.nextInt();
			switch (menuChoice) {
			case 1:
				endOfCart = false;
				showShoppingCart(programList, futurAddList, cartPrice, endOfCart);
				break;
			case 2:
				addToCart(programList);
				break;
			case 3:
				removeToCart(programList);
				break;
			case 4:
				deleteCart(programList, futurAddList);
				break;
			case 5:
				endOfCart = true;
				showShoppingCart(programList, futurAddList, cartPrice, endOfCart);
				break;
			case 6:
				futurProgram(futurAddList);
				break;
			case 0:
				// Exit
				endOfCart = true;
				break;
			default:
				System.out.println("\nMauvaise saisie.");
			}
		}
	}

	/**
	 * Methode initialisant les tableaux programList (commandes) et futuList (reservations)
	 * 
	 * @param programList, futurAddList
	 */
	public static void initData(HashMap<Integer, List<String>> programList, HashMap<Integer, List<String>> futurAddList) {
		// Liste des formations
		List<String> programList1 = new ArrayList<String>();
		programList1.add("Java"); programList1.add("20"); programList1.add("Java SE 8 : Syntax & Poo"); programList1.add("3000"); programList1.add("0");

		List<String> programList2 = new ArrayList<String>();
		programList2.add("Java avancé"); programList2.add("20"); programList2.add("Exceptions, files, Jbdc, thread..."); programList2.add("5000"); programList2.add("0");

		List<String> programList3 = new ArrayList<String>();
		programList3.add("Spring"); programList3.add("20"); programList3.add("Spring Core/Mvc/Security"); programList3.add("5000"); programList3.add("0");

		List<String> programList4 = new ArrayList<String>();
		programList4.add("Php frameworks"); programList4.add("15"); programList4.add("Symphony"); programList4.add("2500"); programList4.add("0");

		List<String> programList5 = new ArrayList<String>();
		programList5.add("C#"); programList5.add("20"); programList5.add("DotNet Core"); programList5.add("5000"); programList5.add("0");

		programList.put(1, programList1); programList.put(2, programList2); programList.put(3, programList3); programList.put(4, programList4); programList.put(5, programList5);

		// Précommandes
		List<String> futurAddList1 = new ArrayList<String>();
		futurAddList1.add("Angular"); futurAddList1.add("20"); futurAddList1.add("HTML, JS, TS & Css"); futurAddList1.add("4000"); futurAddList1.add("0");

		List<String> futurAddList2 = new ArrayList<String>();
		futurAddList2.add("Kotlin"); futurAddList2.add("15"); futurAddList2.add("Google, mobile, Java"); futurAddList2.add("4000"); futurAddList2.add("0");

		futurAddList.put(1, futurAddList1); futurAddList.put(2, futurAddList2);
	}

	/**
	 * Tableau principale des formations
	 * 
	 * @param programList
	 */
	private static void buildArray(HashMap<Integer, List<String>> programList) {
		System.out.println("\t☆ Bonjour et bienvenue dans mon application FullTrainings ☆");
		System.out.println("Nous allons vous proposer une liste de formation actuellement disponnible.");
		System.out.println("----------------------------------------------------------------------------");
		System.out.printf("| %-14s | %-8s | %-37s | %-4s |", "COURS", "NB/JOURS", "DESCRIPTION", "PRIX");
		System.out.print("\n----------------------------------------------------------------------------");
		programList.entrySet().stream().forEach(e -> System.out.printf("\n| %-14s | %-8s | %-37s | %-4s |",
				e.getValue().get(0), e.getValue().get(1), e.getValue().get(2), e.getValue().get(3)));
		System.out.println("\n----------------------------------------------------------------------------");
	}

	/**
	 * Affiche le contenu du caddie de facon dynamique
	 * L'option 'endOfCart' ajoute la facture finale et son paiement
	 * 
	 * @param programList, futurAddList, cartPrice, endOfCart
	 */
	private static void showShoppingCart(HashMap<Integer, List<String>> programList,
			HashMap<Integer, List<String>> futurAddList, int cartPrice, boolean endOfCart) {
		System.out.println("Votre pannier :");
		boolean emptyCart = true;														// gere le panier vide
		System.out.println("---------------------------------");
		System.out.printf("| %-14s | %-3s | %-6s |", "Cours", "Qté", "Prix");
		System.out.println("\n---------------------------------");
		for (int i = 1; i < programList.size(); i++) {
			int quantity = Integer.parseInt(programList.get(i).get(4));
			int price = Integer.parseInt(programList.get(i).get(3));

			if (Integer.parseInt(programList.get(i).get(4)) > 0)
			{
				System.out.printf("| %-14s | %-3s | %-6s |\n", programList.get(i).get(0), quantity, price); cartPrice += (quantity * price);
				emptyCart = false;
			}
		}
		if (emptyCart) {
			System.out.printf("| %-27s |\n", "☹ Votre pannier est vide ☹");			// affiche en cas de caddie vide
		}
		System.out.println("---------------------------------");
		System.out.printf("\t| %-11s | %-6s |", " PRIX TOTAL ", cartPrice);
		System.out.println("\n\t-------------------------");
		if (futurAddList.size() > 0) {
			System.out.println("\n★ Vos pré-commandes ★ : ");
			futurAddList.entrySet().stream().filter(num -> num.getValue().get(4) != "0").forEach(f -> System.out.print("[☛ " + f.getValue().get(0) + "] "));
		}
		// Paiement et facture finale
		if (!emptyCart && endOfCart)
			System.out.println("\n\t---------------------\n\t|  FACTURE  ⎙ " + cartPrice + "e | \n\t---------------------");
	}

	/**
	 * Ajout des formations au panier
	 * 
	 * @param programList
	 */
	private static void addToCart(HashMap<Integer, List<String>> programList) {
		System.out.println("Quelle formation souhaitez vous ajouter ?");
		System.out.println("[1 - Java] [2 - Java avancé] [3 - Spring] [4 - Php framework] [5 - C#]");
		int cartChoice = 0;
		int quantity = 0;
		Scanner scanValue = new Scanner(System.in);
		while (!scanValue.hasNextInt())
			scanValue.next(); cartChoice = scanValue.nextInt();
		// gestion des quantités
		if (cartChoice > 0 || cartChoice <= programList.size()) {
			System.out.println("Quelle quantité ?");
			while (!scanValue.hasNextInt())
				scanValue.next(); quantity = scanValue.nextInt();
		}
		int quantityChange = 5;													// variable temporaire de gestion des quantités
		switch (cartChoice) {
		case 1:
			quantityChange = Integer.parseInt(programList.get(1).get(4)); quantityChange += quantity;
			programList.get(1).set(4, (Integer.toString(quantityChange)));
			break;
		case 2:
			quantityChange = Integer.parseInt(programList.get(2).get(4)); quantityChange += quantity;
			programList.get(2).set(4, Integer.toString(quantityChange++));
			break;
		case 3:
			quantityChange = Integer.parseInt(programList.get(3).get(4)); quantityChange += quantity;
			programList.get(3).set(4, Integer.toString(quantityChange));
			break;
		case 4:
			quantityChange = Integer.parseInt(programList.get(4).get(4)); quantityChange += quantity;
			programList.get(4).set(4, Integer.toString(quantityChange));
			break;
		case 5:
			quantityChange = Integer.parseInt(programList.get(5).get(4)); quantityChange += quantity;
			programList.get(5).set(4, Integer.toString(quantityChange));
			break;
		default:
			System.out.println("⚠ Mauvaise saisie. ⚠");							// gere les saisies hors champ
		}
	}

	/**
	 * Retrait des formations du panier. Retire completement une formation du panier
	 * 
	 * @param programList
	 */
	private static void removeToCart(HashMap<Integer, List<String>> programList) {
		System.out.println("Quelle formation souhaitez vous retirer ?");
		for (int i = 1; i < programList.size(); i++) {
			if (programList.get(i).get(4) != "0")
				System.out.print("[" + i + "/ " + programList.get(i).get(0) + "]");
		}
		Scanner scanToDel = new Scanner(System.in);
		while (!scanToDel.hasNextInt())
			scanToDel.next();
		int deleteProgram = scanToDel.nextInt();
		if (programList.get(deleteProgram).get(4) != "0") {
			programList.get(deleteProgram).set(4, "0");
		} else {
			System.out.print("⚠ La formation n'est pas dans votre panier. ⚠");				// panier vide
		}
	}

	/**
	 * Vide le panier completement (commandes et reservations)
	 * 
	 * @param programList, futurAddList
	 */
	private static void deleteCart(HashMap<Integer, List<String>> programList, HashMap<Integer, List<String>> futurAddList) {
		programList.entrySet().stream().forEach(e -> e.getValue().set(4, "0"));
		futurAddList.entrySet().stream().forEach(e -> e.getValue().set(4, "0"));
	}

	/**
	 * Gestion des précommandes
	 * 
	 * @param futurAddList
	 */
	private static void futurProgram(HashMap<Integer, List<String>> futurAddList) {
		System.out.println("Ces formations seront bientot disponnible :");
		System.out.println("------------------------------------------------------------------");
		System.out.printf("| %-2s | %-14s | %-8s | %-22s | %-4s |", "N°", "COURS", "NB/JOURS", "DESCRIPTION", "PRIX");
		System.out.print("\n------------------------------------------------------------------");
		futurAddList.entrySet().stream().forEach(e -> System.out.printf("\n| %-2s | %-14s | %-8s | %-22s | %-4s |", e.getKey(), e.getValue().get(0), e.getValue().get(1), e.getValue().get(2), e.getValue().get(3)));
		System.out.println("\n------------------------------------------------------------------\n");
		// Ajout des reservations.
		System.out.println("Souhaitez vous en reserver une ?  (Elle ne sera pas facturée pour l'instant)\n *Si aucune, entrez 0");
		Scanner scanPreview = new Scanner(System.in);
		while (!scanPreview.hasNextInt())
			scanPreview.next(); int quantityChange;
		int choice = scanPreview.nextInt();
		switch (choice) {
		case 0:
			break;
		case 1:
			quantityChange = Integer.parseInt(futurAddList.get(1).get(4)); quantityChange++;
			futurAddList.get(1).set(4, (Integer.toString(quantityChange)));
			break;
		case 2:
			quantityChange = Integer.parseInt(futurAddList.get(2).get(4)); quantityChange++;
			futurAddList.get(2).set(4, Integer.toString(quantityChange++));
			break;
		default:
			System.out.println("⚠ Mauvaise saisie. ⚠");							// gere les erreurs de saisie
		}
	}
}