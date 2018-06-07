package filesprocessing.orders;

import java.io.File;
import java.util.ArrayList;

/**
 * factory of order decorators, acts as singleton of orderers
 */
public class OrderFactory {

    private static final AbsOrder ABS = new AbsOrder();
    private static final SizeOrder SIZE = new SizeOrder();
    private static final TypeOrder TYPE = new TypeOrder();

    public static OrderDecorator createOrder(String type, boolean reverse) throws OrderWarningException {
        OrderDecorator decorator = new OrderDecorator(null, reverse);
        switch (type) {
            case "abs":
            case "":
                decorator.setOrder(ABS);
                break;
            case "type":
                decorator.setOrder(TYPE);
                break;
            case "size":
                decorator.setOrder(SIZE);
                break;
            default:
                throw new OrderWarningException();
        }
        return decorator;
    }
}
