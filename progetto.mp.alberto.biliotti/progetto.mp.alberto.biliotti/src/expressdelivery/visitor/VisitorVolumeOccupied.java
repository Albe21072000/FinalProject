package expressdelivery.visitor;

import java.util.Iterator;

import expressdelivery.abstractclassesforentities.ObjectToBeShipped;
import expressdelivery.concreteentities.Box;
import expressdelivery.concreteentities.ElectronicDevice;
import expressdelivery.concreteentities.ShockProofPackagingFoam;

public class VisitorVolumeOccupied implements VisitorObjectToBeShipped<Double>{

	@Override
	public Double visitBox(Box box) {
		double volumeOccupied=0;
		Iterator<ObjectToBeShipped> iterator = box.iteatorForSons();
		while(iterator.hasNext()) {
			volumeOccupied+=iterator
					.next()
					.accept(this);
		}
		return volumeOccupied;
	}

	@Override
	public Double visitElectronicDevice(ElectronicDevice electronicDevice) {
		return electronicDevice.getVolumeInCubeMeters();
	}

	@Override
	public Double visitShockProofPackagingFoam(ShockProofPackagingFoam shockProofPackagingFoam) {
		return shockProofPackagingFoam.getVolumeInCubeMeters();
	}

}
