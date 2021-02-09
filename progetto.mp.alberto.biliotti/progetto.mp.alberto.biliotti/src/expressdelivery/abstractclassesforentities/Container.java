package expressdelivery.abstractclassesforentities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public abstract class Container extends ObjectToBeShipped {

	private Collection<ObjectToBeShipped> boxContent = new ArrayList<>();

	public Container(double weightInKG) {
		super(weightInKG);
	}

	public boolean addObjectToBeShipped(ObjectToBeShipped objectToBeShipped) {
		Objects.requireNonNull(objectToBeShipped, "You can't put null element in a box!");
		return this.boxContent.add(objectToBeShipped);
	}

	public boolean removeObjectToBeShipped(ObjectToBeShipped objectToBeShipped) {
		return this.boxContent.remove(objectToBeShipped);
	}

	public Iterator<ObjectToBeShipped> iteatorForSons() {
		return boxContent.iterator();
	}

	protected Collection<ObjectToBeShipped> getBoxContent() {
		return boxContent;
	}

}