import java.util.ArrayList;
import java.util.List;

// Classe principal (App)
public class App {
    public static void main(String[] args) {
        Client client = new Client("João", "joao@email.com");

        Order order = new Order(client);
        order.addItem(new Item("Notebook", 3500.0, 1));
        order.addItem(new Item("Mouse", 80.0, 2));

        Invoice invoice = order.generateInvoice();
        invoice.print();

        order.confirmOrder();
    }
}

// Representa um Cliente (Regra 5: Cliente como objeto próprio)
class Client {
    private String name;
    private String email;

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

// Representa um Item do pedido (Regra 2: primitivos substituídos)
class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        if (price <= 0 || quantity <= 0) {
            throw new IllegalArgumentException("Preço e quantidade devem ser positivos.");
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double totalPrice() {
        return price * quantity;
    }

    public String getDescription() {
        return quantity + "x " + name + " - R$" + price;
    }
}

// Representa um Pedido (Regra 1: listas encapsuladas)
class Order {
    private Client client;
    private List<Item> items;
    private static final double DISCOUNT_RATE = 0.1;

    public Order(Client client) {
        this.client = client;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    // Regra 4: cálculo do total movido para Order
    private double calculateTotal() {
        return items.stream()
                    .mapToDouble(Item::totalPrice)
                    .sum();
    }

    public Invoice generateInvoice() {
        double subtotal = calculateTotal();
        double discount = DiscountPolicy.calculateDiscount(subtotal, DISCOUNT_RATE);
        double finalTotal = subtotal - discount;
        return new Invoice(client, items, subtotal, discount, finalTotal);
    }

    // Regra 3: ocultando delegação do serviço de email
    public void confirmOrder() {
        EmailService.sendEmail(client.getEmail(), "Pedido recebido! Obrigado pela compra.");
    }
}

// Representa a Fatura gerada (Regra 4: responsabilidade de imprimir movida para cá)
class Invoice {
    private Client client;
    private List<Item> items;
    private double subtotal;
    private double discount;
    private double finalTotal;

    public Invoice(Client client, List<Item> items, double subtotal, double discount, double finalTotal) {
        this.client = client;
        this.items = items;
        this.subtotal = subtotal;
        this.discount = discount;
        this.finalTotal = finalTotal;
    }

    // Regra 6: impressão extraída para método próprio
    public void print() {
        System.out.println("Cliente: " + client.getName());
        for (Item item : items) {
            System.out.println(item.getDescription());
        }
        System.out.println("Subtotal: R$" + subtotal);
        System.out.println("Desconto: R$" + discount);
        System.out.println("Total final: R$" + finalTotal);
    }
}

// Política de desconto (não alterada pois já estava boa - Regra 4 aplicada)
class DiscountPolicy {
    public static double calculateDiscount(double amount, double rate) {
        return amount * rate;
    }
}

// Serviço de email permanece utilitário (Regra 3: ocultado pela Order)
class EmailService {
    public static void sendEmail(String to, String message) {
        System.out.println("Enviando e-mail para " + to + ": " + message);
    }
}
