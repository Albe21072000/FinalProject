package expressdelivery.visitor;

import java.util.Iterator;
import expressdelivery.abstractclassesforentities.ObjectToBeShipped;
import expressdelivery.concreteentities.Box;
import expressdelivery.concreteentities.ElectronicDevice;
import expressdelivery.concreteentities.ShockProofPackagingFoam;

public final class VisitorTotalPrice implements VisitorObjectToBeShipped<Integer>{

	@Override
	public Integer visitBox(Box box) {
		int totalPrice=0;
		Iterator<ObjectToBeShipped> iterator = box.iteatorForSons();
		while(iterator.hasNext()) {
			totalPrice+=iterator.next().accept(this);
		}
		return totalPrice;
	}

	@Override
	public Integer visitElectronicDevice(ElectronicDevice electronicDevice) {
		return electronicDevice.getPriceInEuro();
	}

	@Override
	public Integer visitShockProofPackagingFoam(ShockProofPackagingFoam shockProofPackagingFoam) {
		return 0;

	}
}
