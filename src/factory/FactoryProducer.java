package factory;

public class FactoryProducer {

    public static AbstractFactory getFactory(String type) {
        switch(type) {
            case "ticket":
                return new TicketFactory();

            case "person":
                return new PersonFactory();

            default:
                return null;
        }
    }
}
