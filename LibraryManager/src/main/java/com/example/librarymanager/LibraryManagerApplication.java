package com.example.librarymanager;

import com.example.librarymanager.model.entity.Book;
import com.example.librarymanager.model.entity.Customer;
import com.example.librarymanager.model.entity.Type;
import com.example.librarymanager.model.entity.Wallet;
import com.example.librarymanager.repository.BookRepository;
import com.example.librarymanager.repository.CustomerRepository;
import com.example.librarymanager.repository.TypeRepository;
import com.example.librarymanager.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class LibraryManagerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagerApplication.class, args);
    }


    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
//        Type type1 = new Type();
//        type1.setName("trinh thám");
//        Type type2 = new Type();
//        type2.setName("tiểu thuyết");
//        Type type3 = new Type();
//        type3.setName("truyện tiếu lâm");
//        Type type4 = new Type();
//        type4.setName("truyện thiếu nhi");
//        Type type5 = new Type();
//        type5.setName("truyện văn học");
//        Type type6 = new Type();
//        type6.setName("truyện ma");
//        Type type7 = new Type();
//        type7.setName("Fiction");
//        typeRepository.saveAll(Arrays.asList(type1, type2, type3, type4,type5,type6,type7));
//        Book book1 = new Book();
//        book1.setName("Lão hạc");
//        book1.setAuthor("Nam Cao");
//        book1.setPrice(50000);
//        book1.setQuantity(1000);
//        book1.setType(type5);
//        Book book2 = new Book();
//        book2.setName("Rế mèn phiêu lưu kí");
//        book2.setAuthor("Tô Hoài ");
//        book2.setPrice(30000);
//        book2.setQuantity(1000);
//        book2.setType(type4);
//        Book book3 = new Book();
//        book3.setName("cô nan");
//        book3.setAuthor("abc");
//        book3.setPrice(60000);
//        book3.setQuantity(1000);
//        book3.setType(type1);
//        Book book4 = new Book();
//        book4.setName("sách đen");
//        book4.setAuthor("Otegha Uwagba");
//        book4.setPrice(60000);
//        book4.setQuantity(1000);
//        book4.setType(type2);
//        Book book5 = new Book();
//        book5.setName("tấm cám");
//        book5.setAuthor("dân gian");
//        book5.setPrice(60000);
//        book5.setQuantity(1000);
//        book5.setType(type4);
//   Book book6 = new Book();
//        book6.setName("The Great Gatsby");
//        book6.setAuthor("F. Scott Fitzgerald");
//        book6.setPrice(70000);
//        book6.setQuantity(1000);
//        book6.setType(type7);
//   Book book7 = new Book();
//        book7.setName("To Kill a Mockingbird");
//        book7.setAuthor("Harper Lee");
//        book7.setPrice(70000);
//        book7.setQuantity(1000);
//        book7.setType(type7);
//
//        bookRepository.saveAll(Arrays.asList(book1, book2, book3,book4,book5,book6,book7));
//
//        final Wallet libaryCenter = new Wallet();
//        libaryCenter.setAccountNum("123123");
//        libaryCenter.setBalance(100000);
//
//
//        Wallet bWallet1 = new Wallet();
//        bWallet1.setAccountNum("A123456");
//        bWallet1.setBalance(500000);
//
//        Wallet bWallet2 = new Wallet();
//        bWallet2.setAccountNum("B123456");
//        bWallet2.setBalance(600000);
//
//        Wallet bWallet3 = new Wallet();
//        bWallet3.setAccountNum("C123456");
//        bWallet3.setBalance(700000);
//
//        Wallet bWallet4 = new Wallet();
//        bWallet4.setAccountNum("D123456");
//        bWallet4.setBalance(800000);
//        walletRepository.saveAll(Arrays.asList(libaryCenter, bWallet1, bWallet2, bWallet3, bWallet4));
//
//        Customer customer1 = new Customer();
//        customer1.setName("hungnh");
//        customer1.setPhone("0366333222");
//        customer1.setCccd("031091220111");
//        customer1.setWallet(bWallet1);
//        customerRepository.save(customer1);
//        Customer customer2 = new Customer();
//        customer2.setName("tiendq");
//        customer2.setPhone("0366333222");
//        customer2.setCccd("031091220123");
//        customer2.setWallet(bWallet2);
//        customerRepository.save(customer2);
//        Customer customer3 = new Customer();
//        customer3.setName("thaipq");
//        customer3.setPhone("0366123422");
//        customer3.setCccd("031091220222");
//        customer3.setWallet(bWallet3);
//        customerRepository.save(customer3);
//        Customer customer4 = new Customer();
//        customer4.setName("trungna");
//        customer4.setPhone("0366432122");
//        customer4.setCccd("031091220333");
//        customer4.setWallet(bWallet4);
//        customerRepository.save(customer4);
    }

}












