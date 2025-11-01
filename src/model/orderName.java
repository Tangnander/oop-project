package model;

public class orderName {



		public static String nameflipper(String fullName) {

			// Dela namnet på mellanslag
			String[] parts = fullName.trim().split("\\s+");

			String firstName = parts[1]; // "Marilyn"
			String lastName = parts[0]; // "Monroe"

			// Ta två första bokstäverna från varje del 
			String part1 = firstName;
			String part2 = lastName;

			// Kombinera och returnera, t.ex. "25MaMo01" för Marliyn Monroe
			return (part1 + " " + part2);
			
		
	}
}
