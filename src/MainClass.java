import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner (System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Enter rental data:");
		System.out.print("Car model: ");
		String model = sc.nextLine();
		Vehicle v = new Vehicle(model);
		
		System.out.print("Pego ás: ");
		Date pickup = sdf.parse(sc.nextLine());
		System.out.print("Retirado ás: ");
		Date retun = sdf.parse(sc.nextLine());
		CarRental cr = new CarRental(pickup, retun, v); 
		
		System.out.print("Enter price per hour: ");
		double priceHour = sc.nextDouble();
		System.out.print("Enter price per day: ");
		double priceDay = sc.nextDouble();
		
		RentalService rs = new RentalService(priceDay, priceHour, new BrasilTaxService());
		
		rs.processInvoice(cr);
		
		System.out.println("INVOICE:");
		System.out.println("Car model: "+ v.getModel());
		System.out.println("Basic payment: " +  String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Tax: " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Total payment: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
		sc.close();
	}

}
