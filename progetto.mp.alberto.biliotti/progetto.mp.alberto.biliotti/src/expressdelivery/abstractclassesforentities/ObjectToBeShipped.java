package expressdelivery.abstractclassesforentities;

import expressdelivery.visitor.VisitorObjectToBeShipped;

public abstract class ObjectToBeShipped {

	private double weight;

	public ObjectToBeShipped(double weight) {
		this.weight=weight;
	}


	protected double getWeightInKG() {
		return weight;
	}


	public abstract double calculateTotalWeight();


	public abstract <T>  T accept(VisitorObjectToBeShipped<T> visitor);

}