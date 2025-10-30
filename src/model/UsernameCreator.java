package model;

public class UsernameCreator {

	public static String createUsername(String fullName) {

		// Dela namnet på mellanslag
		String[] parts = fullName.trim().split("\\s+");

		String firstName = parts[1]; // "Marilyn"
		String lastName = parts[0]; // "Monroe"

		// Ta två första bokstäverna från varje del 
		String part1 = firstName.substring(0, Math.min(2, firstName.length()));
		String part2 = lastName.substring(0, Math.min(2, lastName.length()));

		// Kombinera och returnera, t.ex. "25MaMo01" för Marliyn Monroe
		return ("25" + part1 + part2 + "01");
		
	}
}
