package expressdelivery.abstractclassesforentities;

import expressdelivery.visitor.VisitorObjectToBeShipped;

public abstract class ObjectToBeShipped {

	private double weightInKG;

	public ObjectToBeShipped(double weight) {
		this.weightInKG=weight;
	}


	protected double getWeightInKG() {
		return weightInKG;
	}


	public abstract double calculateTotalWeight();


	public abstract <T>  T accept(VisitorObjectToBeShipped<T> visitor);

}