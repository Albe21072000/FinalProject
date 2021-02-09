package expressdelivery.deliverymanager;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Test;

import expressdelivery.abstractclassesforentities.Container;
import expressdelivery.concreteentities.Box;
import expressdelivery.concreteentities.ElectronicDevice;
import expressdelivery.concreteentities.ShockProofPackagingFoam;

public class TestDeliveryManager {

	@Test
	public void testCreateDeliveryWithOneContainer() {
		Container box=new Box(12);
		DeliveryManager manager=DeliveryManager.createDeliveryWithOneContainer(box, "");
		assertThat(manager.getDeliveryContainers()).containsExactly(box);
	}
	@Test
	public void testCreateEmptyDelivery() {
		DeliveryManager manager=DeliveryManager.createEmptyDelivery("");
		assertThat(manager.getDeliveryContainers()).isEmpty();
	}
	@Test
	public void testCreateDeliveryWithOneContainerPassingNull() {
		try {
			DeliveryManager.createDeliveryWithOneContainer(null, "");
			fail("This method should throw an exception when null is passed!");
		}catch (NullPointerException exception) {
			assertThat(exception).hasMessage("The container mustn't be null!");
		}
	}
	@Test
	public void testCreateDeliveryFromACollectionOfContainersPassingNull() {
		try {
			DeliveryManager.createDeliveryFromACollectionOfContainers(null, "");
			fail("This method should throw an exception when null is passed!");
		}catch (NullPointerException exception) {
			assertThat(exception).hasMessage("The collection mustn't be null!");
		}
	}
	@Test
	public void testAddContainerNull() {
		try {
			DeliveryManager manager=DeliveryManager.createDeliveryFromACollectionOfContainers(new ArrayList<>(),"Via Roma");
			manager.addContainer(null);
			fail("This method should throw an exception when null is passed!");
		}catch (NullPointerException exception) {
			assertThat(exception).hasMessage("Null shouldn't be passed at this method!");
		}
	}
	@Test
	public void testAddContainer() {
		DeliveryManager manager=DeliveryManager.createDeliveryFromACollectionOfContainers(new ArrayList<>(),"Via Roma");
		Container box=new Box(12);
		assertThat(manager.addContainer(box)).isTrue();
		assertThat(manager.getDeliveryContainers()).containsExactly(box);
	}
	@Test
	public void testRemoveContainer() {
		DeliveryManager manager=DeliveryManager.createDeliveryFromACollectionOfContainers(new ArrayList<>(),"Via Roma");
		Container box=new Box(12);
		manager.getDeliveryContainers().add(box);
		assertThat(manager.removeContainer(box)).isTrue();
		assertThat(manager.getDeliveryContainers()).isEmpty();
	}
	@Test 
	public void testDeliveryWeyght() {
		Container box=new Box(12);
		Container anotherBox=new Box(12);
		box.addObjectToBeShipped(new ElectronicDevice(34, 43, 43));
		DeliveryManager manager=DeliveryManager.createDeliveryFromACollectionOfContainers(new ArrayList<>(),"Via Roma");
		assertThat(manager.calculateDeliveryWheyght()).isEqualTo(0);
		manager.getDeliveryContainers().add(box);
		manager.getDeliveryContainers().add(anotherBox);
		assertThat(manager.calculateDeliveryWheyght()).isEqualTo(67);
	}
	@Test 
	public void testDeliveryValue() {
		Container box=new Box(12);
		Container anotherBox=new Box(12);
		anotherBox.addObjectToBeShipped(new ElectronicDevice(34, 43, 43));
		DeliveryManager manager=DeliveryManager.createDeliveryFromACollectionOfContainers(new ArrayList<>(),"Via Roma");
		assertThat(manager.calculateDeliveryWheyght()).isEqualTo(0);
		manager.getDeliveryContainers().add(box);
		manager.getDeliveryContainers().add(anotherBox);
		assertThat(manager.calculateDeliveryValue()).isEqualTo(43);
	}
	@Test 
	public void testDeliveryVolume() {
		Container box=new Box(12);
		Container anotherBox=new Box(12);
		anotherBox.addObjectToBeShipped(new ElectronicDevice(34, 43, 43));
		DeliveryManager manager=DeliveryManager.createDeliveryFromACollectionOfContainers(new ArrayList<>(),"Via Roma");
		assertThat(manager.calculateDeliveryWheyght()).isEqualTo(0);
		manager.getDeliveryContainers().add(box);
		manager.getDeliveryContainers().add(anotherBox);
		assertThat(manager.calculateVolumeOccupiedByDeliveryInCubeMeters()).isEqualTo(34);
	}
	@Test
	public void testIterator() {
		Container box=new Box(12);
		Container anotherBox=new Box(12);
		DeliveryManager manager=DeliveryManager.createDeliveryFromACollectionOfContainers(new ArrayList<>(),"Via Roma");
		manager.getDeliveryContainers().add(box);
		manager.getDeliveryContainers().add(anotherBox);
		assertThat(manager.iterator())
		.toIterable()
		.containsExactly(box, anotherBox);
	}
	@Test
	public void testDeliveryRecipe() {
		Container box=new Box(12);
		Container anotherBox=new Box(12);
		box.addObjectToBeShipped(new ElectronicDevice(34, 43, 43));
		box.addObjectToBeShipped(new ShockProofPackagingFoam(34, 43));
		DeliveryManager manager=DeliveryManager.createDeliveryFromACollectionOfContainers(new ArrayList<>(),"Via Roma");
		manager.getDeliveryContainers().add(box);
		manager.getDeliveryContainers().add(anotherBox);
		assertThat(manager.getRecipe()).isEqualTo("Delivery to Via Roma that contains: Box that wheigs: 98.0Kg and contains: Electronic device that is worth: 43€ and occupies: 34.0 cube meters. Shockproof Packaging Foam which occupies: 34.0 cube meters. Box that wheigs: 12.0Kg and contains: ");
	}
}
