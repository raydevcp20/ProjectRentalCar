
public class RentalService {
	
	private Double pricePerDay;
	private Double pricePerHour;
	
	private BrasilTaxService taxService; 
	
	public RentalService() {
	}
	
	public RentalService(Double pricePerDay, Double pricePerHour, BrasilTaxService taxService) {
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
		this.taxService = taxService;
	}
	
	public void processInvoice(CarRental carRental) {
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		
		double DPH = (double)(t2-t1)/1000/60/60;
		double resul;
		
		if(DPH <= 12) {
			resul = pricePerHour * Math.ceil(DPH);
		}else
			resul = pricePerDay * (Math.ceil(DPH/24));
		
		double tax = taxService.tax(resul);
		carRental.setInvoice(new Invoice(resul, tax));
		
	}
}
