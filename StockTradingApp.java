import java.util.HashMap;
import java.util.Scanner;

class StockMarket {
    private HashMap<String, Double> marketPrices;

    public StockMarket() {
        marketPrices = new HashMap<>();
        // Simulated stock prices
        marketPrices.put("AAPL", 150.0);
        marketPrices.put("GOOGL", 2800.0);
        marketPrices.put("TSLA", 700.0);
        marketPrices.put("AMZN", 3400.0);
    }

    public double getStockPrice(String stock) {
        return marketPrices.getOrDefault(stock, -1.0);
    }

    public void displayMarketPrices() {
        System.out.println("\n Market Prices:");
        for (String stock : marketPrices.keySet()) {
            System.out.println(stock + " - $" + marketPrices.get(stock));
        }
    }
}

class TradingAccount {
    private double balance;
    private HashMap<String, Integer> portfolio;

    public TradingAccount(double initialBalance) {
        this.balance = initialBalance;
        this.portfolio = new HashMap<>();
    }

    public void buyStock(String stock, int quantity, double price) {
        double totalCost = quantity * price;
        if (totalCost > balance) {
            System.out.println(" Insufficient balance.");
        } else {
            balance -= totalCost;
            portfolio.put(stock, portfolio.getOrDefault(stock, 0) + quantity);
            System.out.println(" Bought " + quantity + " shares of " + stock + " at $" + price + " each.");
        }
    }

    public void sellStock(String stock, int quantity, double price) {
        if (!portfolio.containsKey(stock) || portfolio.get(stock) < quantity) {
            System.out.println(" Not enough shares to sell.");
        } else {
            balance += quantity * price;
            portfolio.put(stock, portfolio.get(stock) - quantity);
            if (portfolio.get(stock) == 0) portfolio.remove(stock);
            System.out.println(" Sold " + quantity + " shares of " + stock + " at $" + price + " each.");
        }
    }

    public void viewPortfolio() {
        System.out.println("\n Your Portfolio:");
        if (portfolio.isEmpty()) {
            System.out.println("No stocks owned.");
        } else {
            for (String stock : portfolio.keySet()) {
                System.out.println(stock + ": " + portfolio.get(stock) + " shares");
            }
        }
        System.out.println(" Balance: $" + balance);
    }

    public double getBalance() {
        return balance;
    }
}

public class StockTradingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StockMarket market = new StockMarket();
        TradingAccount account = new TradingAccount(1000.0); // Starting balance

        while (true) {
            System.out.println("\n Stock Trading Platform");
            System.out.println("5 Exit");
            System.out.println("1 View Market Prices");
            System.out.println("2 Buy Stock");
            System.out.println("3 Sell Stock");
            System.out.println("4 View Portfolio");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    market.displayMarketPrices();
                    break;

                case 2:
                    System.out.print("Enter stock name to buy: ");
                    String buyStock = scanner.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int buyQuantity = scanner.nextInt();
                    double buyPrice = market.getStockPrice(buyStock);
                    if (buyPrice == -1.0) {
                        System.out.println(" Invalid stock name.");
                    } else {
                        account.buyStock(buyStock, buyQuantity, buyPrice);
                    }
                    break;

                case 3:
                    System.out.print("Enter stock name to sell: ");
                    String sellStock = scanner.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int sellQuantity = scanner.nextInt();
                    double sellPrice = market.getStockPrice(sellStock);
                    if (sellPrice == -1.0) {
                        System.out.println(" Invalid stock name.");
                    } else {
                        account.sellStock(sellStock, sellQuantity, sellPrice);
                    }
                    break;

                case 4:
                    account.viewPortfolio();
                    break;

                case 5:
                    System.out.println(" Exiting. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println(" Invalid choice.");
            }
        }
    }
}