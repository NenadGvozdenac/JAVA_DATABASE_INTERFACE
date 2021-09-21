package database_work;

// Declare a new class for all new classes
public class _classes {

	// Only new class we need is a Date class for database
	public static class Date {
			
		int day, month, year;
			
		public Date() {
			day = month = year = 0;
		}
		
		public Date(int dan, int mesec, int godina) {
			this.day = dan;
			this.month = mesec;
			this.year = godina;
		}
		public void output() {
			System.out.print(day + "/" + month + "/" + year);
		}
			
	};
}
