package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exceptions.NotFoundException;
import ru.netology.manager.ProductManager;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    Smartphone first = new Smartphone(1, "Galaxy", 1000, "Samsung");
    Smartphone second = new Smartphone(2, "IPhone", 1000, "Apple");
    Smartphone third = new Smartphone(3, "Xiaomi1", 500, "Xiaomi");
    Book forth = new Book(4, "Throats", 50, "Garber");
    Book fifth = new Book(5, "Jumps", 100, "Warner");
    Book sixth = new Book(6, "Sprint", 150, "Johnson");

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(forth);
        repository.save(fifth);
        repository.save(sixth);
    }

    @Test
    void shouldRemoveByIdIfExists() {
        int idToRemove = 4;
        repository.removeById(idToRemove);
        Product[] expected = new Product[]{first, second, third, fifth, sixth};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldGenerateExceptionWhenTryToRemoveMissingElement() {
        int idToRemove = 7;
        Exception e = assertThrows(NotFoundException.class, () -> repository.removeById(idToRemove));
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
    }
