package terziev;

import java.util.Arrays;
import java.util.List;
import static java.util.Comparator.comparing;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

public class TransactionExample1 {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        transactions.forEach(System.out::println);
        //System.out.println("Transactions: " + transactions.toString());
        System.out.println("========================================================");
        System.out.println("Find all transactions in the year 2011 and sort them by value (small to high)");
        List<Transaction> sorted = transactions
                .stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        System.out.println(sorted.toString());

        System.out.println("================================================");
        System.out.println("What are all the unique cities where the traders work?");
        List<String> cities = transactions
                .stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(toList());
        System.out.println(cities.toString());

        System.out.println("================================================");
        System.out.println("Find all traders from Cambridge and sort them by name");
        List<Trader> traders = transactions
                .stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        System.out.println(traders.toString());

        System.out.println("==================================================");
        System.out.println("Return a string of all traders’ names sorted alphabetically");
        String traderStr
                = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);//Combine each name one by one to form a String that concatenates all the names
        System.out.println(traderStr);

        System.out.println("================================================");
        System.out.println("Are any traders based in Milan?");
        boolean milanBased
                = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader()
                        .getCity()
                        .equals("Milan"));
        System.out.println(milanBased);

        System.out.println("================================================");
        System.out.println("Print all transactions’ values from the traders living in Cambridge");
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        System.out.println("=================================================");
        System.out.println("What’s the highest value of all the transactions?");
        Optional<Integer> highestValue
                = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        highestValue.ifPresent(System.out::println);

        System.out.println("=============================================");
        System.out.println("Find the transaction with the smallest value");
//        Optional<Transaction> smallestTransaction
//                = transactions
//                .stream()
//                .reduce(
//                        (t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2
//                );
        //Better approach >>>
        Optional<Transaction> smallestTransaction
                = transactions.stream()
                .min(comparing(Transaction::getValue));
        
        smallestTransaction.ifPresent(System.out::println);

        System.out.println("============================================");
        System.out.println("");
    }

}
