package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }


    @Override
    public void run(String... args) throws Exception {


        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "232325");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        authorRepository.save(rod);
        bookRepository.save(noEJB);

        Publisher greatAukPubs = new Publisher();
        greatAukPubs.setName("Great Auk Pubs");
        //persisting ONE of one:many after relationship established via setPub and getBooks.Add:
        // publisherRepository.save(greatAukPubs) moved to bottom to fix persistence issue

        Publisher dodoPubs = new Publisher();
        dodoPubs.setName("Dodo Pubs");
        //publisherRepository.save(dodoPubs) moved to bottom to fix persistence issue

        ddd.setPublisher(greatAukPubs);
        noEJB.setPublisher(greatAukPubs);
        greatAukPubs.getBooks().add(ddd);
        greatAukPubs.getBooks().add(noEJB);

        publisherRepository.save(greatAukPubs);
        publisherRepository.save(dodoPubs);



        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publishers: " + publisherRepository.count());


    }

}
