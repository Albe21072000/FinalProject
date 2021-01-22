package expressdelivery.deliverymanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import expressdelivery.abstractclassesforentities.Container;
import expressdelivery.visitor.VisitorRecipe;
import expressdelivery.visitor.VisitorTotalPrice;
import expressdelivery.visitor.VisitorVolumeOccupied;


public class DeliveryManager {

	private Collection<Container> deliveryContainers;
	private String deliveryAddress;

	private DeliveryManager(Collection<Container> deliveryContainers, String deliveryAddress) {
		this.deliveryContainers = deliveryContainers;
		this.deliveryAddress = deliveryAddress;
	}

	public static DeliveryManager createEmptyDelivery(String deliveryAddress) {
		return new DeliveryManager(new ArrayList<>(), deliveryAddress);
	}

	public static DeliveryManager createDeliveryWithOneContainer(Container container, String deliveryAddress) {
		Objects.requireNonNull(container, "The container mustn't be null!");
		List<Container> containers=new ArrayList<>();
		containers.add(container);
		return new DeliveryManager(containers, deliveryAddress);
	}

	public static DeliveryManager createDeliveryFromACollectionOfContainers(Collection<Container> deliveryContainers, String deliveryAddress) {
		Objects.requireNonNull(deliveryContainers, "The collection mustn't be null!");
		return new DeliveryManager(deliveryContainers, deliveryAddress);
	}

	public boolean addContainer(Container container) {
		Objects.requireNonNull(container, "Null shouldn't be passed at this method!");
		return deliveryContainers.add(container);
	}

	public boolean removeContainer(Container container) {
		return deliveryContainers.remove(container);
	}

	public Iterator<Container> iterator(){
		return deliveryContainers.iterator();
	}

	public double calculateDeliveryWheyght() {
		double totalWheight=0;
		Iterator<Container> iterator=this.iterator();
		while (iterator.hasNext()) {
			totalWheight+=iterator.next().calculateTotalWeight();

		}
		return totalWheight;
	}

	public double calculateDeliveryValue() {
		double totalvalue=0;
		Iterator<Container> iterator=this.iterator();
		while (iterator.hasNext()) {
			totalvalue+=iterator.next().accept(new VisitorTotalPrice());
		}
		return totalvalue;
	}

	public double calculateVolumeOccupiedByDeliveryInCubeMeters() {
		double totalVolume=0;
		Iterator<Container> iterator=this.iterator();
		while (iterator.hasNext()) {
			totalVolume+=iterator.next().accept(new VisitorVolumeOccupied());
		}
		return totalVolume;
	}

	public String getRecipe() {
		String recipe="Delivery to "+ deliveryAddress+" that contains: ";
		Iterator<Container> iterator=this.iterator();
		while (iterator.hasNext()) {
			recipe+=iterator.next().accept(new VisitorRecipe());
		}
		return recipe;
	}

	//For Test
	Collection<Container> getDeliveryContainers() {
		return deliveryContainers;
	}
}
