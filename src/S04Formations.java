import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class S04Formations {

	public static void main(String[] args) {

		HashMap<Integer, List<String>> programList = new HashMap<>();
		HashMap<Integer, List<String>> futurAddList = new HashMap<>();
		
		
		Scanner scan = new Scanner(System.in);
		int menuChoice = 0;
		
		int cartPrice = 0;
		

		initData(programList,futurAddList);
		buildArray(programList);
		for (boolean endOfCart = false; endOfCart != true;) {
			System.out.println("\nQue souhaitez vous faire ? ");
			System.out.println(
					" 1/ Voir votre panier\n 2/ Ajouter une formation au panier\n 3/ Retirer une formation du panier\n 4/ Supprimer le panier\n 5/ Passer à la caisse\n 6/ Voir les prochains cours\n 0/ Sortir");

			while (!scan.hasNextInt())
				scan.next();
			menuChoice = scan.nextInt();
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
				deleteCart(programList);
				break;
			case 5:
				endOfCart = true;
				showShoppingCart(programList, futurAddList, cartPrice, endOfCart);
				break;
			case 6:
				futurProgram(futurAddList);
				break;
			case 0:
				endOfCart = true;
				break;
			}
		}
	}

	public static void initData(HashMap<Integer, List<String>> programList, HashMap<Integer, List<String>> futurAddList) {
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
	
		List<String> futurAddList1 = new ArrayList<String>();
		futurAddList1.add("Angular"); futurAddList1.add("20"); futurAddList1.add("HTML, JS, TS & Css"); futurAddList1.add("4000"); futurAddList1.add("0");

		List<String> futurAddList2 = new ArrayList<String>();
		futurAddList2.add("Kotlin"); futurAddList2.add("15"); futurAddList2.add("Google, mobile, Java"); futurAddList2.add("4000"); futurAddList2.add("0");
		
		//Addons, futur list
		futurAddList.put(1, futurAddList1); futurAddList.put(2, futurAddList2);
	
	}

	private static void buildArray(HashMap<Integer, List<String>> programList) {
		System.out.println("\tBonjour et bienvenue dans mon application FullTrainings");
		System.out.println("Nous allons vous proposer une liste de formation actuellement disponnible.");
		System.out.println("----------------------------------------------------------------------------");
		System.out.printf("| %-14s | %-8s | %-37s | %-4s |", "COURS", "NB/JOURS", "DESCRIPTION", "PRIX");
		System.out.print("\n----------------------------------------------------------------------------");
		programList.entrySet().stream().forEach(e -> System.out.printf("\n| %-14s | %-8s | %-37s | %-4s |",
				e.getValue().get(0), e.getValue().get(1), e.getValue().get(2), e.getValue().get(3)));
		System.out.println("\n----------------------------------------------------------------------------");
	}

	private static void showShoppingCart(HashMap<Integer, List<String>> programList, HashMap<Integer, List<String>> futurAddList, int cartPrice,
			boolean endOfCart) {
		System.out.println("Votre pannier :");
		boolean emptyCart = true;
		System.out.println("---------------------------------");
		System.out.printf("| %-14s | %-3s | %-6s |", "Cours", "Qté", "Prix");
		System.out.println("\n---------------------------------");
		for (int i = 1; i < programList.size(); i++) {
			int quantity = Integer.parseInt(programList.get(i).get(4));
			int price = Integer.parseInt(programList.get(i).get(3));

			if (Integer.parseInt(programList.get(i).get(4)) > 0)

			{
				System.out.printf("| %-14s | %-3s | %-6s |\n", programList.get(i).get(0), quantity, price);
				cartPrice += (quantity * price);
				emptyCart = false;
			}

		}
		if (emptyCart) {
			System.out.printf("| %-29s |\n", "** Votre pannier est vide **");}
		System.out.println("---------------------------------");
		System.out.printf("\t| %-14s | %-6s |", " PRIX TOTAL :", cartPrice);
		System.out.println("\t\n------------------------");
		if (!emptyCart && endOfCart)
			System.out.println("\t| Facture : "+cartPrice +"e | \n\t-------------------");
	}

	private static void addToCart(HashMap<Integer, List<String>> programList) {
		System.out.println("Quelle formation souhaitez vous ajouter ?");
		System.out.println("[1 - Java] [2 - Java avancé] [3 - Spring] [4 - Php framework] [5 - C#]");
		int cartChoice = 0;
		int number = 0;
		Scanner scanValue = new Scanner(System.in);
		while (!scanValue.hasNextInt()) scanValue.next();
		cartChoice = scanValue.nextInt();
		System.out.println("Quelle quantité ?");
		while (!scanValue.hasNextInt()) scanValue.next();
		number = scanValue.nextInt();
		int quantityChange;
		switch (cartChoice) {
		case 1:
			quantityChange = Integer.parseInt(programList.get(1).get(4)); quantityChange+=number;
			programList.get(1).set(4, (Integer.toString(quantityChange)));
			break;
		case 2:
			quantityChange = Integer.parseInt(programList.get(2).get(4)); quantityChange+=number;
			programList.get(2).set(4, Integer.toString(quantityChange++));
			break;
		case 3:
			quantityChange = Integer.parseInt(programList.get(3).get(4)); quantityChange+=number;
			programList.get(3).set(4, Integer.toString(quantityChange));
			break;
		case 4:
			quantityChange = Integer.parseInt(programList.get(4).get(4)); quantityChange+=number;
			programList.get(4).set(4, Integer.toString(quantityChange));
			break;
		case 5:
			quantityChange = Integer.parseInt(programList.get(5).get(4)); quantityChange+=number;
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
		if (programList.get(deleteProgram).get(4) != "0") {
			int quantityChange = Integer.parseInt(programList.get(deleteProgram).get(4));
			quantityChange--;
			programList.get(deleteProgram).set(4, Integer.toString(quantityChange));
		} else {
			System.out.print("La formation n'est pas dans votre panier.");
		}
	}

	private static void deleteCart(HashMap<Integer, List<String>> programList) {
		for (int i = 1; i < programList.size(); i++) {
			programList.get(i).set(4, "0");
		}
	}
	
	private static void futurProgram(HashMap<Integer, List<String>> futurAddList) {
		System.out.println("Ces formations seront bientot disponnible :");
		System.out.println("------------------------------------------------------------------");
		System.out.printf("| %-2s | %-14s | %-8s | %-22s | %-4s |", "N°", "COURS", "NB/JOURS", "DESCRIPTION", "PRIX");
		System.out.print("\n------------------------------------------------------------------");
		futurAddList.entrySet().stream().forEach(e -> System.out.printf("\n| %-2s | %-14s | %-8s | %-22s | %-4s |",e.getKey(),
				e.getValue().get(0), e.getValue().get(1), e.getValue().get(2), e.getValue().get(3)));
		System.out.println("\n------------------------------------------------------------------\n");
		System.out.println("Souhaitez vous en reserver une ? (Elle ne sera pas facturée pour l'instant)\n *Si aucune, entrez 0");
		Scanner scanPreview = new Scanner(System.in);
		while (!scanPreview.hasNextInt())
			scanPreview.next();
		int quantityChange;
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
		}
		
		
	}
}