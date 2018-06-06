package filesprocessing.orders;

import java.io.File;
import java.util.ArrayList;

public class OrderFactory {

	private static final AbsOrder ABS = new AbsOrder();
	private static final SizeOrder SIZE = new SizeOrder();
	private static final TypeOrder TYPE = new TypeOrder();

	public OrderDecorator createOrder(ArrayList<File> files, String type, boolean reverse) {
		OrderDecorator decorator = new OrderDecorator(null, reverse);
		switch(type) {
			case "abs":
				decorator.setOrder(ABS);
				break;
			case "type":
				decorator.setOrder(TYPE);
				break;
			case "size":
				decorator.setOrder(SIZE);
				break;
			default:
				// todo: add error
				break;
		}
		return decorator;
	}
}
