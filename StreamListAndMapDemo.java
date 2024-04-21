import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


class Book {

    int id;
    String name;
    String author;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    Double price;


    public Book(int id, String name, String author, Double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }
}

public class StreamListAndMapDemo {

    public static void main(String[] args) {

        HashMap<String, List<Integer>> map = new HashMap<>();

        // computIfAbsent - we can provide compute function
        map.computeIfAbsent("TEST1", s -> new ArrayList<>()).add(1);

        //only works when key is absent
        map.putIfAbsent("TEST1", new ArrayList<>());

        System.out.println(map);

        Book b1 = new Book(1, "Book1", "ABC", 100.0);
        Book b2 = new Book(2, "Book2", "PQR", 200.0);
        Book b3 = new Book(3, "Book3", "ABC", 300.0);
        Book b4 = new Book(1, "Book1", "ABC", 100.0);

        List<Book> list = new ArrayList<>();
        list.add(b1);
        list.add(b2);
        list.add(b3);

        //get BookName by bookId
        Map<Integer, String> bookbyIDMap = list.stream().collect(Collectors.toMap(Book::getId, Book::getName));
        System.out.println("BookName by bookId " + bookbyIDMap);


        //get Book by author , in case of same id take existing
        Map<String, Book> authorMap =
                list.stream().collect(Collectors.toMap(Book::getAuthor, Function.identity(), (o1, o2) -> o1));

        System.out.println("Book by author " + authorMap);


        //get count of Books per author
        Map<String, Long> countByAuthor = list.stream().collect(Collectors.groupingBy(Book::getAuthor, Collectors.counting()));

        System.out.println("count of Books per author " + countByAuthor);


        //get sum of price by author
        Map<String, Double> totalPriceByAuthor = list.stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.summingDouble(Book::getPrice)));

        System.out.println("sum of price by author " + totalPriceByAuthor);


        //get highest priced book
        Book b = list.stream().sorted(Comparator.comparing(Book::getPrice).reversed()).findFirst().get();
        System.out.println("Highest Priced Book  " + b);

        //get findAnyBook which is less than 300
        Book bookWhichIsLessThan300 = list.stream().filter(book -> book.price < 300).findAny().get();
        System.out.println("Book with less than 300 " + bookWhichIsLessThan300);

        //Avarage price of book
        Double avgPrice = list.stream().mapToDouble(Book::getPrice).average().getAsDouble();
        System.out.println("Avarage price of book " + avgPrice);

        //convert Book names into uppercase
        List<String> bookNamesUpperCase = list.stream().map(y -> y.getName().toUpperCase()).toList();
        System.out.println("Book names with upper case " + bookNamesUpperCase);

        //Convert String to uppercase and Join them with coma

        String bookNamesUpperCaseJoining = list.stream().map(y -> y.getName().toUpperCase()).collect(Collectors.joining(","));
        System.out.println("Book names with upper case joining with comma  " + bookNamesUpperCaseJoining);


        //remove duplicates from List
        List<Book> booksWithoutDuplicates = list.stream().distinct().toList();
        System.out.println("remove duplicates from List " + booksWithoutDuplicates);

    }
}
