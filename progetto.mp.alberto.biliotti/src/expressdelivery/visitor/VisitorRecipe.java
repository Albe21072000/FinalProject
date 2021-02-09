package expressdelivery.visitor;

import java.util.Iterator;

import expressdelivery.abstractclassesforentities.ObjectToBeShipped;
import expressdelivery.concreteentities.Box;
import expressdelivery.concreteentities.ElectronicDevice;
import expressdelivery.concreteentities.ShockProofPackagingFoam;

public final class VisitorRecipe implements VisitorObjectToBeShipped<String>{

	@Override
	public String visitBox(Box box) {
		String recipe="Box that wheigs: " +box.calculateTotalWeight()+"Kg and contains: ";
		Iterator<ObjectToBeShipped> iterator = box.iteatorForSons();
		while(iterator.hasNext()) {
			recipe+=iterator
					.next()
					.accept(this);
		}
		return recipe;
	}

	@Override
	public String visitElectronicDevice(ElectronicDevice electronicDevice) {
		return "Electronic device that is worth: "+electronicDevice.getPriceInEuro()+"€ and occupies: "+electronicDevice.getVolumeInCubeMeters()+" cube meters. ";
	}

	@Override
	public String visitShockProofPackagingFoam(ShockProofPackagingFoam shockProofPackagingFoam) {
		return "Shockproof Packaging Foam which occupies: " +shockProofPackagingFoam.getVolumeInCubeMeters()+" cube meters. ";
	}

}
