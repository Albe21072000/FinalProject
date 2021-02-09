package expressdelivery.concreteentities;

import expressdelivery.abstractclassesforentities.Contained;
import expressdelivery.visitor.VisitorObjectToBeShipped;

public final class ElectronicDevice extends Contained {

	private int priceInEuro;

	public ElectronicDevice(double volume, double weight, int priceinEuro) {
		super(volume, weight);
		this.priceInEuro=priceinEuro;
	}

	public int getPriceInEuro() {
		return priceInEuro;
	}

	@Override
	public <T> T accept(VisitorObjectToBeShipped<T> visitor) {
		return visitor.visitElectronicDevice(this);
	}

}
