package dto;

public class Nutrients {
	
	private String unit, name;
	private double per_hundred, per_portion, per_day;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getPer_Hundred() {
		return per_hundred;
	}
	public void setPer_Hundred(double per_hundred) {
		this.per_hundred = per_hundred;
	}
	public double getPer_Portion() {
		return per_portion;
	}
	public void setPer_Portion(double per_portion) {
		this.per_portion = per_portion;
	}
	public double getPer_Day() {
		return per_day;
	}
	public void setPer_Day(double per_day) {
		this.per_day = per_day;
	}
	@Override
	public String toString() {
		return "Name: "+name+ "\n\t\t\tunit=+"+ unit + ", \n\t\t\tname=" + name + ", \n\t\t\tperHundred=" + 
				per_hundred + ", \n\t\t\tperPortion="+ per_portion + ", \n\t\t\tperDay=" + per_day;
	}
}
