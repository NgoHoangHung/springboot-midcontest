package com.example.librarymanager.service;

import com.example.librarymanager.model.dto.BookManagerDTO;
import com.example.librarymanager.model.dto.TicketBookDTO;
import com.example.librarymanager.model.entity.*;
import com.example.librarymanager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TicketBookServiceImpl implements TicketBookService {

    @Autowired
    private TicketBookRepository ticketBookRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private WalletService walletService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private BookManagerRepository bookManagerRepository;


    @Override
    public TicketBook getById(int id) {
        return ticketBookRepository.findById(id);
    }

    @Override
    public List<TicketBook> getAll1() {

        List<TicketBook> list = ticketBookRepository.findAll();
        for (TicketBook ticketBook : list) {
            bookRepository.findBooksByTicketBookId(ticketBook.getId());
        }
        return list;
    }

    @Override
    public List<TicketBook> getAll() {
        return null;
    }

    @Override
    public String lostBookTicket(TicketBookDTO dto) {
//        TicketBook ticketBook = ticketBookRepository.findById(dto.getId());
//        List<BookManager> bookManagers = ticketBook.getBookManagers();
//        List<BookManagerDTO>
//
//    }
        return null;
    }

    public double totalprice(TicketBookDTO dto) {
        double sum = 0;
        if (dto.getServicez() == Servicez.RENT) {
            sum += dto.getReturnDay() * 10000;
        } else if (dto.getServicez() == Servicez.BUY) {

            for (BookManagerDTO bookManagerDTO : dto.getBookManagerDTOS()) {
                sum += bookManagerDTO.getQuantity() * bookManagerDTO.getBookDTO().getPrice();
            }
        }
        return sum;
    }

    @Transactional
    @Override
    public String buyBook(TicketBookDTO dto) {
        if (dto.getId() != 0) return "ticket ???? t???n t???i trong h??? th???ng";
        //t???o h??a ????n
        TicketBook newTicket = new TicketBook();
        LocalDate currentDate = LocalDate.now();
        newTicket.setCreatAt(currentDate);
        newTicket.setServicez(Servicez.BUY);
        newTicket.setNote("ng?????i d??ng mua v??o ng??y " + currentDate);
        ticketBookRepository.save(newTicket);

        List<BookManager> bookManager = new ArrayList<>();
        List<BookManagerDTO> bookManagerDTOS = dto.getBookManagerDTOS();

        // nghi???p v??? mua s??ch
        //n???u s??? l?????ng s??ch trong kho l???n h??n s??? l?????ng thu?? th?? b??n cho kh??ch.
        // c??n kh??ng th?? b??n t???m nh???ng quy???n trong dto truy???n v??o
        // kh??ng c?? th?? ko l??m g?? c???.
        for (BookManagerDTO bookManagerDTO : bookManagerDTOS) {
            BookManager bookManagerInput = new BookManager();
            Book book = bookRepository.findById(bookManagerDTO.getBookDTO().getId());

            if (book.getQuantity() > bookManagerDTO.getQuantity()) {
                book.setQuantity(book.getQuantity() - bookManagerDTO.getQuantity());

                bookManagerInput.setQuantity(bookManagerDTO.getQuantity());
                bookManagerInput.setBook(book);
                bookManagerInput.setTicketBook(newTicket);

                newTicket.setTotalPrice(newTicket.getTotalPrice() +
                        bookManagerInput.getQuantity() * book.getPrice());

                bookManagerRepository.save(bookManagerInput);
                bookManager.add(bookManagerInput);
            } else if (book.getStatus() == Status.available &&
                    book.getQuantity() <= bookManagerDTO.getQuantity()) {
                bookManagerInput.setQuantity(book.getQuantity());

                bookManagerInput.setBook(book);
                bookManagerInput.setTicketBook(newTicket);
                bookManagerRepository.save(bookManagerInput);
                newTicket.setTotalPrice(newTicket.getTotalPrice() +
                        book.getPrice() * book.getQuantity());
                book.setQuantity(0);
                book.setStatus(Status.not_available);
                bookManager.add(bookManagerInput);
            } else if (book.getQuantity() == 0) {
                ;
            }
        }
        newTicket.setBookManagers(bookManager);


        if (!customerRepository.existsByPhone(dto.getCustomerDTO().getPhone())) {
            //logic l??n ng?????i thu?? ch??a t???n t???i trong h??? th???ng. thanh to??n
            Customer newCustomer = customerService.creatCustomer(dto.getCustomerDTO());
            //n???p ti???n cho tr?????ng h???p kh??ng ?????
            if (newCustomer.getWallet().getBalance() == 0 ||
                    newCustomer.getWallet().getBalance() < newTicket.getTotalPrice()) {
//                ChargeMoney chargeMoney = new ChargeMoney();
//                chargeMoney.setWallet(newCustomer.getWallet());
//                chargeMoney.setDeposit(newTicket.getTotalPrice() -
//                        newCustomer.getWallet().getBalance());
//                walletService.chargeWallet(chargeMoney);

                return "h??y n???p ????? ti???n ????? thanh to??n";
            } else {

                //thanh to??n
                Wallet walletCenter = walletRepository.findByAccountNum("123123");
                walletCenter.setBalance(walletCenter.getBalance() +
                        newTicket.getTotalPrice());
                newCustomer.getWallet().setBalance(newCustomer.getWallet().getBalance() -
                        newTicket.getTotalPrice());
                walletRepository.save(walletCenter);
                customerRepository.save(newCustomer);
                newTicket.setCustomer(newCustomer);
            }
        } else {
            //logic v???i ng?????i ???? t???n t???i trong h??? th???ng
            Customer customer = customerRepository.findByPhone(dto.getCustomerDTO().getPhone());
            if (customer.getWallet().getBalance() == 0 ||
                    customer.getWallet().getBalance() < newTicket.getTotalPrice()) {
//                ChargeMoney chargeMoney = new ChargeMoney();
//                chargeMoney.setWallet(customer.getWallet());
//                chargeMoney.setDeposit(newTicket.getTotalPrice() -
//                        customer.getWallet().getBalance());
//                walletService.chargeWallet(chargeMoney);
//                customer.getWallet().setBalance(customer.getWallet().getBalance() + totalPrice);
                return "h??y n???p ????? ti???n ????? thanh to??n";
            } else {

                customer.getWallet().setBalance(customer.getWallet().getBalance() -
                        newTicket.getTotalPrice());
                Wallet walletCenter = walletRepository.findByAccountNum("123123");
                walletCenter.setBalance(walletCenter.getBalance() + newTicket.getTotalPrice());

                walletRepository.saveAll(Arrays.asList(walletCenter, customer.getWallet()));

                customerRepository.save(customer);
                newTicket.setCustomer(customer);
            }
        }
        return "mua th??nh c??ng";


    }


    @Transactional
    @Override
    public String rentBook(TicketBookDTO dto) {
        if (dto.getId() != 0) return "ticket ???? t???n t???i trong h??? th???ng";

        TicketBook newTicket = new TicketBook();
        ticketBookRepository.save(newTicket);

        LocalDate currentDate = LocalDate.now();
        newTicket.setCreatAt(currentDate);

        LocalDate returnDate = currentDate.plusDays(dto.getReturnDay());
        newTicket.setReturnDay(returnDate);

        List<BookManager> bookManager = new ArrayList<>();
        List<BookManagerDTO> bookManagerDTOS = dto.getBookManagerDTOS();

        // nghi???p v??? mu???n s??ch
        //n???u s??? l?????ng s??ch trong kho l???n h??n s??? l?????ng thu?? th?? cho thu??.
        // c??n kh??ng th?? thu?? t???m nh???ng quy???n trong dto truy???n v??o
        // kh??ng c?? th?? ko l??m g?? c???.
        for (BookManagerDTO bookManagerDTO : bookManagerDTOS) {
            BookManager bookManagerInput = new BookManager();
            Book book = bookRepository.findById(bookManagerDTO.getBookDTO().getId());

            if (book.getQuantity() > bookManagerDTO.getQuantity()) {
                book.setQuantity(book.getQuantity() - bookManagerDTO.getQuantity());
                bookManagerInput.setQuantity(bookManagerDTO.getQuantity());
                bookManagerInput.setBook(book);
                bookManagerInput.setTicketBook(newTicket);
                bookManagerRepository.save(bookManagerInput);
                bookManager.add(bookManagerInput);
            } else if (book.getStatus() == Status.available &&
                    book.getQuantity() <= bookManagerDTO.getQuantity()) {
                bookManagerInput.setQuantity(book.getQuantity());
                book.setQuantity(0);
                book.setStatus(Status.not_available);
                bookManagerInput.setBook(book);
                bookManagerInput.setTicketBook(newTicket);
                bookManagerRepository.save(bookManagerInput);
                bookManager.add(bookManagerInput);
            } else if (book.getQuantity() == 0) {
                ;
            }

        }
        newTicket.setBookManagers(bookManager);
        newTicket.setServicez(Servicez.RENT);
        newTicket.setCreatAt(currentDate);
        newTicket.setNote("ng?????i d??ng m?????n t??? ng??y " + currentDate +
                "\nD??? ki???ntr??? v??o ng??y + " + returnDate);

        if (!customerRepository.existsByPhone(dto.getCustomerDTO().getPhone())) {
            //logic l??n ng?????i thu?? ch??a t???n t???i trong h??? th???ng
            Customer newCustomer = customerService.creatCustomer(dto.getCustomerDTO());
            customerRepository.save(newCustomer);
            newTicket.setCustomer(newCustomer);
        } else {
            //logic v???i ng?????i ???? t???n t???i trong h??? th???ng
            Customer customer = customerRepository.findByPhone(dto.getCustomerDTO().getPhone());
            customerRepository.save(customer);
            newTicket.setCustomer(customer);
        }

        return "th??m phi???u th??nh c??ng v??o h??? th???ng";

    }

    @Transactional
    @Override
    public String returnBook(TicketBookDTO dto) {
        //t??nh ti???n

        TicketBook ticketBook = ticketBookRepository.findById(dto.getId());
        LocalDate returnDate = LocalDate.now();
        String note = "tr??? s??ch v??o ng??y: " + returnDate;
        long dates = ChronoUnit.DAYS.between(ticketBook.getCreatAt(), returnDate);
        ticketBook.setTotalPrice(dates * 10000);

        //xem s??ch c?? m???t kh??ng
        List<BookManagerDTO> bookManagerDTOS = dto.getBookManagerDTOS(); //xem s??? l?????ng s??ch l??c tr???

        for (BookManagerDTO bookManagerDTO : bookManagerDTOS) {
            BookManager bookManager = bookManagerRepository.findById(bookManagerDTO.getId());//xem s??? l?????ng s??ch ban ?????u
            Book book = bookManager.getBook();//kho s??ch

            if (bookManager.getQuantity() > bookManagerDTO.getQuantity()) {
                int lostQuantity = bookManager.getQuantity() - bookManagerDTO.getQuantity();
                ticketBook.setTotalPrice(ticketBook.getTotalPrice() + lostQuantity * book.getPrice() * 0.85);
                note += "\nquy???n s??ch mang Id s???: " + book.getId() + "\nthi???u " + lostQuantity + "quy???n.";
                book.setQuantity(book.getQuantity() + bookManagerDTO.getQuantity());
            }
            bookRepository.save(book);
        }
        Customer customer = ticketBook.getCustomer();
        //n???p ti???n cho tr?????ng h???p kh??ng ?????
        if (customer.getWallet().getBalance() == 0 ||
                customer.getWallet().getBalance() < ticketBook.getTotalPrice()) {
            return "h??y n???p ????? ti???n ????? thanh to??n";
        } else {

            //th???c hi???n giao d???ch khi ???? ????? ti???n
            customer.getWallet().setBalance(customer.getWallet().getBalance() -
                    ticketBook.getTotalPrice());

            Wallet walletCenter = walletRepository.findByAccountNum("123123");
            walletCenter.setBalance(walletCenter.getBalance() + ticketBook.getTotalPrice());
            walletRepository.saveAll(Arrays.asList(walletCenter, customer.getWallet()));

            ticketBook.setNote(ticketBook.getNote() + "\n ???? tr??? s??ch v??o " + returnDate);
            ticketBookRepository.save(ticketBook);
            customerRepository.save(customer);
            return ticketBook.getNote().concat(note);
        }
    }


    @Override
    public String updateTicket(TicketBookDTO dto) {
        return null;
    }

    @Override
    public String deleteTicket(int id) {
        return null;
    }


}
