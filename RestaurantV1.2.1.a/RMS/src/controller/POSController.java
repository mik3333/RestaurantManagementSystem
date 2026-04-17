package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class POSController {

    private final Map<String, Product> productCatalog;
    private final List<OrderItem> currentOrder;
    private final List<KitchenTicket> kitchenTickets;

    public POSController() {
        this.productCatalog = initializeProductCatalog();
        this.currentOrder = new ArrayList<>();
        this.kitchenTickets = new ArrayList<>();
    }

    private Map<String, Product> initializeProductCatalog() {
        Map<String, Product> catalog = new HashMap<>();
        catalog.put("Zinger Burger", new Product("Zinger Burger", 250.0, "Burger"));
        catalog.put("Pizza", new Product("Pizza", 500.0, "Pizza"));
        catalog.put("Sprite", new Product("Sprite", 80.0, "Drinks"));
        catalog.put("Cheeze Lover", new Product("Cheeze Lover", 380.0, "Burger"));
        catalog.put("Double Zinger", new Product("Double Zinger", 320.0, "Burger"));
        catalog.put("Chicken Burger", new Product("Chicken Burger", 280.0, "Burger"));
        catalog.put("Double Chicken", new Product("Double Chicken", 400.0, "Burger"));
        catalog.put("Beef Burger", new Product("Beef Burger", 350.0, "Burger"));
        catalog.put("Chicken Wings", new Product("Chicken Wings", 220.0, "BBQ"));
        catalog.put("Chicken Kebab", new Product("Chicken Kebab", 300.0, "BBQ"));
        catalog.put("Samosa", new Product("Samosa", 50.0, "Snacks"));
        catalog.put("Korean Chicken BBQ", new Product("Korean Chicken BBQ", 280.0, "BBQ"));
        return catalog;
    }

    public void addToOrder(String productName, int quantity) {
        if (productCatalog.containsKey(productName)) {
            Product product = productCatalog.get(productName);
            OrderItem item = new OrderItem(productName, quantity, product.getPrice());
            currentOrder.add(item);
        }
    }

    public void removeFromOrder(int index) {
        if (index >= 0 && index < currentOrder.size()) {
            currentOrder.remove(index);
        }
    }

    public void clearOrder() {
        currentOrder.clear();
    }

    public List<OrderItem> getCurrentOrder() {
        return new ArrayList<>(currentOrder);
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : currentOrder) {
            total += item.getTotalAmount();
        }
        return total;
    }

    public Product getProduct(String productName) {
        return productCatalog.get(productName);
    }

    public KitchenTicket sendToKitchen() {
        if (currentOrder.isEmpty()) {
            return null;
        }
        KitchenTicket ticket = new KitchenTicket(new ArrayList<>(currentOrder));
        kitchenTickets.add(ticket);
        currentOrder.clear();
        return ticket;
    }

    public List<KitchenTicket> getKitchenTickets() {
        return new ArrayList<>(kitchenTickets);
    }

    public Map<String, Product> getProductCatalog() {
        return new HashMap<>(productCatalog);
    }

    public static class Product {
        private final String name;
        private final double price;
        private final String category;

        public Product(String name, double price, String category) {
            this.name = name;
            this.price = price;
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getCategory() {
            return category;
        }
    }

    public static class OrderItem {
        private final String productName;
        private int quantity;
        private final double unitPrice;

        public OrderItem(String productName, int quantity, double unitPrice) {
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public double getTotalAmount() {
            return quantity * unitPrice;
        }
    }

    // KOT (Kitchen Order Ticket) - represents an order sent to the kitchen
    public static class KitchenTicket {
        private final String ticketId;
        private final List<OrderItem> items;
        private final long timestamp;
        private boolean completed;

        public KitchenTicket(List<OrderItem> items) {
            this.ticketId = "KOT-" + System.currentTimeMillis();
            this.items = new ArrayList<>(items);
            this.timestamp = System.currentTimeMillis();
            this.completed = false;
        }

        public String getTicketId() {
            return ticketId;
        }

        public List<OrderItem> getItems() {
            return new ArrayList<>(items);
        }

        public long getTimestamp() {
            return timestamp;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void markCompleted() {
            this.completed = true;
        }
    }
}
