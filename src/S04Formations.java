import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class S04Formations {

	public static void main(String[] args) {

		HashMap<Integer, List<String>> programList = new HashMap<>();
		HashMap<Integer, List<String>> shoppingCart = new HashMap<>();
		Scanner scan = new Scanner(System.in);
		int menuChoice = 0;
		boolean activity = true;

		initData(programList);
		buildArray(programList);
		while(activity = true) {
		System.out.println("Votre panier actuel : ");
		if (shoppingCart.isEmpty()) {
			System.out.println(" -- Votre panier est actuellement vide -- ");
		} else {
			showShoppingCart(shoppingCart);
		}
		
		System.out.println("Que souhaitez vous faire ? ");
		System.out.println(" 1/ Voir votre panier\n 2/ Ajouter une formation au panier\n 3/ Retirer une formation du panier\n 4/ Supprimer le panier");
		while (!scan.hasNextInt())
			scan.next();
		menuChoice = scan.nextInt();
		switch (menuChoice) {
		case 1:
			showShoppingCart(shoppingCart);
			break;
		case 2:
			addToCart(shoppingCart);
			break;
		case 3:
			removeToCart(shoppingCart);
			break;
			
		case 4:
			deleteCart(shoppingCart);
			break;
		case 0:
			activity=false;
			break;
		}
		}
		if (shoppingCart.isEmpty()) {
			System.out.println("A bientot...");
		} else {
			System.out.println("Hop là tu vas où ? On part pas sans payer !!!");}
	}

	public static void initData(HashMap<Integer, List<String>> programList) {
		List<String> programList1 = new ArrayList<String>();
		programList1.add("Java");
		programList1.add("20");
		programList1.add("Java SE 8 : Syntax & Poo");
		programList1.add("3000");

		List<String> programList2 = new ArrayList<String>();
		programList2.add("Java avancé");
		programList2.add("20");
		programList2.add("Exceptions, files, Jbdc, thread...");
		programList2.add("5000");

		List<String> programList3 = new ArrayList<String>();
		programList3.add("Spring");
		programList3.add("20");
		programList3.add("Spring Core/Mvc/Security");
		programList3.add("5000");

		List<String> programList4 = new ArrayList<String>();
		programList4.add("Php frameworks");
		programList4.add("15");
		programList4.add("Symphony");
		programList4.add("2500");

		List<String> programList5 = new ArrayList<String>();
		programList5.add("C#");
		programList5.add("20");
		programList5.add("DotNet Core");
		programList5.add("5000");

		// build the list
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

	private static void showShoppingCart(HashMap<Integer, List<String>> shoppingCart) {
		System.out.println(shoppingCart);
	}
	private static void addToCart(HashMap<Integer, List<String>> shoppingCart) {
	}
	private static void removeToCart(HashMap<Integer, List<String>> shoppingCart) {
	}
	private static void deleteCart(HashMap<Integer, List<String>> shoppingCart) {
	}
	
}
