package expressdelivery.concreteentities;

import expressdelivery.abstractclassesforentities.Contained;
import expressdelivery.visitor.VisitorObjectToBeShipped;

public final class ShockProofPackagingFoam extends Contained{

	public ShockProofPackagingFoam(double volume, double weight) {
		super(volume, weight);
	}

	@Override
	public <T> T accept(VisitorObjectToBeShipped<T> visitor) {
		return visitor.visitShockProofPackagingFoam(this);
	}
}
