package domain;

import java.util.LinkedList;
import java.util.List;
import presentation.Subscriber;

public interface Publisher {
    List<Subscriber> subscribers = new LinkedList<Subscriber>();

	void subscribe(Subscriber subscriber);
	void unsubscribe(Subscriber subscriber);
	void notifySubscribers();
}