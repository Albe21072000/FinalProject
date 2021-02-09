package expressdelivery.abstractclassesforentities;

public abstract class Contained extends ObjectToBeShipped {

	private double volumeInCubeMeters;

	public Contained(double volume, double weight) {
		super(weight);
		this.volumeInCubeMeters = volume;
	}

	public double getVolumeInCubeMeters() {
		return volumeInCubeMeters;
	}

	@Override
	public double calculateTotalWeight() {
		return super.getWeightInKG();
	}
}