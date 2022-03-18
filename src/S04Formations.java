import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class S04Formations {

	public static void main(String[] args) {

		HashMap<Integer, List<String>> programList = new HashMap<>();
		Scanner scan = new Scanner(System.in);
		int menuChoice = 0;
		boolean activity = true;
		int cartPrice = 0;

		initData(programList);
		buildArray(programList);
		while (activity = true) {
			System.out.println("Que souhaitez vous faire ? ");
			System.out.println(
					" 1/ Voir votre panier\n 2/ Ajouter une formation au panier\n 3/ Retirer une formation du panier\n 4/ Supprimer le panier");

			while (!scan.hasNextInt())
				scan.next();
			menuChoice = scan.nextInt();
			switch (menuChoice) {
			case 1:
				showShoppingCart(programList, cartPrice);
				break;
			case 2:
				addToCart(programList);
				break;
			case 3:
				removeToCart(programList);
				break;

			case 4:
				deleteCart(programList);
				break;
			case 0:
				activity = false;
				break;
			}
		}

	}

	public static void initData(HashMap<Integer, List<String>> programList) {
		List<String> programList1 = new ArrayList<String>();
		programList1.add("Java");
		programList1.add("20");
		programList1.add("Java SE 8 : Syntax & Poo");
		programList1.add("3000");
		// additionnal option
		programList1.add("0");

		List<String> programList2 = new ArrayList<String>();
		programList2.add("Java avanc�");
		programList2.add("20");
		programList2.add("Exceptions, files, Jbdc, thread...");
		programList2.add("5000");
		// additionnal option
		programList2.add("0");

		List<String> programList3 = new ArrayList<String>();
		programList3.add("Spring");
		programList3.add("20");
		programList3.add("Spring Core/Mvc/Security");
		programList3.add("5000");
		// additionnal option
		programList3.add("0");

		List<String> programList4 = new ArrayList<String>();
		programList4.add("Php frameworks");
		programList4.add("15");
		programList4.add("Symphony");
		programList4.add("2500");
		// additionnal option
		programList4.add("0");

		List<String> programList5 = new ArrayList<String>();
		programList5.add("C#");
		programList5.add("20");
		programList5.add("DotNet Core");
		programList5.add("5000");
		// additionnal option
		programList5.add("0");

		// build the list of program
		programList.put(1, programList1);
		programList.put(2, programList2);
		programList.put(3, programList3);
		programList.put(4, programList4);
		programList.put(5, programList5);

	}

	private static void buildArray(HashMap<Integer, List<String>> programList) {
		// String tableLine = "-".repeat(tableLength);
		System.out.println("\tBonjour et bienvenue dans mon application FullTrainings");
		System.out.println("Nous allons vous proposer une liste de formation actuellement disponnible.");
		System.out.println("----------------------------------------------------------------------------");
		System.out.printf("| %-14s | %-8s | %-37s | %-4s |", "COURS", "NB/JOURS", "DESCRIPTION", "PRIX");
		System.out.print("\n----------------------------------------------------------------------------");
		programList.entrySet().stream().forEach(e -> System.out.printf("\n| %-14s | %-8s | %-37s | %-4s |",
				e.getValue().get(0), e.getValue().get(1), e.getValue().get(2), e.getValue().get(3)));
		System.out.println("\n----------------------------------------------------------------------------");
	}

	private static void showShoppingCart(HashMap<Integer, List<String>> programList, int cartPrice) {
		System.out.println("Votre pannier :");
//		if(programList.get().get(4) != "0") System.out.println("Votre panier est vide.");
		System.out.println("---------------------------------");
		System.out.printf("| %-14s | %-3s | %-6s |", "Cours", "Qté", "Prix");
		System.out.println("\n---------------------------------");
		for (int i = 1; i < programList.size(); i++) {
			int quantity = Integer.parseInt(programList.get(i).get(4));
			int price = Integer.parseInt(programList.get(i).get(3));
			if (programList.get(i).get(4) != "0")
				System.out.printf("| %-14s | %-3s | %-6s |\n", programList.get(i).get(0), quantity, price);
			cartPrice += (quantity * price);
		}
		System.out.println("---------------------------------");
		System.out.printf("| %-14s | %-6s |", " PRIX TOTAL :", cartPrice);
		System.out.println("\n---------------------------");
	}

	private static void addToCart(HashMap<Integer, List<String>> programList) {
		System.out.println("Quelle formation souhaitez vous ajouter ?");
		System.out.println("[1 - Java] [2 - Java avanc�] [3 - Spring] [4 - Php framework] [5 - C#]");
		int cartChoice = 0;
		Scanner scanValue = new Scanner(System.in);
		while (!scanValue.hasNextInt())
			scanValue.next();
		cartChoice = scanValue.nextInt();
		int quantityChange;
		switch (cartChoice) {
		case 1:
			quantityChange = Integer.parseInt(programList.get(1).get(4));
			quantityChange++;
			programList.get(1).set(4, (Integer.toString(quantityChange)));
			break;
		case 2:
			quantityChange = Integer.parseInt(programList.get(2).get(4));
			quantityChange++;
			programList.get(2).set(4, Integer.toString(quantityChange++));
			break;
		case 3:
			quantityChange = Integer.parseInt(programList.get(3).get(4));
			quantityChange++;
			programList.get(3).set(4, Integer.toString(quantityChange));
			break;
		case 4:
			quantityChange = Integer.parseInt(programList.get(4).get(4));
			quantityChange++;
			programList.get(4).set(4, Integer.toString(quantityChange));
			break;
		case 5:
			quantityChange = Integer.parseInt(programList.get(5).get(4));
			quantityChange++;
			programList.get(5).set(4, Integer.toString(quantityChange));
			break;
		}
	}

	private static void removeToCart(HashMap<Integer, List<String>> programList) {
		System.out.println("Quelle formation souhaitez vous retirer ?");
		for (int i = 1; i < programList.size(); i++) {
			if (programList.get(i).get(4) != "0")
				System.out.print("[" + i + " - " + programList.get(i).get(0) + "]");
		}
		Scanner scanToDel = new Scanner(System.in);
		while (!scanToDel.hasNextInt())
			scanToDel.next();
		int deleteProgram = scanToDel.nextInt();
		if(programList.get(deleteProgram).get(4) != "0") {
		int quantityChange = Integer.parseInt(programList.get(deleteProgram).get(4));
		quantityChange--;
		programList.get(deleteProgram).set(4, Integer.toString(quantityChange));}
		else {System.out.print("La formation n'est pas dans votre panier.");}

	}

	private static void deleteCart(HashMap<Integer, List<String>> programList) {
		for (int i = 1; i < programList.size(); i++) {
			programList.get(i).set(4, "0");
		}

	}

}