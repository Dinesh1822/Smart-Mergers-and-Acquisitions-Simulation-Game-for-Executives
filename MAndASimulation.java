import java.util.*;

class Company {
    private String name;
    private double value;
    private double growthRate;
    private double marketShare;

    public Company(String name, double value, double growthRate, double marketShare) {
        this.name = name;
        this.value = value;
        this.growthRate = growthRate;
        this.marketShare = marketShare;
    }

    public void simulateGrowth() {
        this.value *= (1 + this.growthRate);
        this.marketShare *= (1 + this.growthRate / 2);
    }

    public String display() {
        return String.format("%s: Value=$%.2fM, Growth=%.1f%%, Market Share=%.1f%%",
                this.name, this.value, this.growthRate * 100, this.marketShare * 100);
    }

    public String getName() {
        return this.name;
    }

    public double getValue() {
        return this.value;
    }
}

class Executive {
    private String name;
    private double budget;
    private List<Company> portfolio;

    public Executive(String name, double budget) {
        this.name = name;
        this.budget = budget;
        this.portfolio = new ArrayList<>();
    }

    public void acquireCompany(Company target) {
        if (target.getValue() <= budget) {
            budget -= target.getValue();
            portfolio.add(target);
            System.out.printf("Acquired %s for $%.2fM. Remaining budget: $%.2fM\n",
                    target.getName(), target.getValue(), budget);
        } else {
            System.out.printf("Not enough budget to acquire %s.\n", target.getName());
        }
    }

    public void simulatePortfolioGrowth() {
        for (Company c : portfolio) {
            c.simulateGrowth();
        }
    }

    public void portfolioSummary() {
        double totalValue = 0;
        System.out.println("\n" + name + "'s Portfolio Summary:");
        for (Company c : portfolio) {
            System.out.println(" - " + c.display());
            totalValue += c.getValue();
        }
        System.out.printf("Total Portfolio Value: $%.2fM\n", totalValue);
        System.out.printf("Remaining Budget: $%.2fM\n", budget);
    }
}

public class MAndASimulation {

    private static final String[] COMPANY_NAMES = {
        "TechNova", "FinCorp", "HealthPlus", "GreenEnergy", "RetailHub", "EduSmart"
    };

    private static Company generateRandomCompany(Random rand) {
        String name = COMPANY_NAMES[rand.nextInt(COMPANY_NAMES.length)];
        double value = 100 + (900 * rand.nextDouble()); // 100 to 1000
        double growthRate = 0.05 + (0.15 * rand.nextDouble()); // 0.05 to 0.20
        double marketShare = 0.01 + (0.14 * rand.nextDouble()); // 0.01 to 0.15
        return new Company(name, value, growthRate, marketShare);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Welcome to the Smart Mergers & Acquisitions Simulation Game for Executives!");
        System.out.print("Enter your executive name: ");
        String execName = scanner.nextLine();
        Executive player = new Executive(execName, 2000);

        for (int round = 1; round <= 3; round++) {
            System.out.println("\n--- Round " + round + " ---");

            List<Company> companies = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                companies.add(generateRandomCompany(rand));
            }

            for (int i = 0; i < companies.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + companies.get(i).display());
            }

            System.out.print("Choose a company to acquire (1-3): ");
            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (NumberFormatException ignored) {}

            if (choice >= 0 && choice < companies.size()) {
                player.acquireCompany(companies.get(choice));
            } else {
                System.out.println("Invalid choice. Skipping acquisition.");
            }

            player.simulatePortfolioGrowth();

            try {
                Thread.sleep(1000); // Simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\nGame Over. Final Results:");
        player.portfolioSummary();
    }
}
