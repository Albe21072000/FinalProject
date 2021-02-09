package expressdelivery.concreteentities;

import java.util.Collection;
import java.util.Iterator;

import expressdelivery.abstractclassesforentities.Container;
import expressdelivery.abstractclassesforentities.ObjectToBeShipped;
import expressdelivery.visitor.VisitorObjectToBeShipped;

public final class Box extends Container{

	public Box(double weight) {
		super(weight);
	}

	@Override
	public <T> T accept(VisitorObjectToBeShipped<T> visitor) {
		return visitor.visitBox(this);
	}

	@Override
	public double calculateTotalWeight() {
		Iterator<ObjectToBeShipped> iterator=super.iteatorForSons();
		double totalweight=super.getWeightInKG();
		while(iterator.hasNext()) {
			totalweight+=iterator
					.next()
					.calculateTotalWeight();
		}
		return totalweight;
	}

	//for test
	protected Collection<ObjectToBeShipped> getBoxContent() {
		return super.getBoxContent();
	}
}
