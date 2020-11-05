package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.*;
import ru.netology.exceptions.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductManagerTest {
    private ProductManager manager = new ProductManager();
    Smartphone first = new Smartphone(1, "Galaxy", 1000, "Samsung");
    Smartphone second = new Smartphone(2, "IPhone", 1000, "Apple");
    Smartphone third = new Smartphone(3, "Xiaomi1", 500, "Xiaomi");
    Book forth = new Book(4, "Throats", 50, "Garber");
    Book fifth = new Book(5, "Jumps", 100, "Warner");
    Book sixth = new Book(6, "Sprint", 150, "Johnson");


    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
        manager.add(fifth);
        manager.add(sixth);
    }

    @Test
    void shouldRemoveIfExists() {
        int idToRemove = 4;
        manager.removeById(idToRemove);
        Product[] expected = new Product[]{first, second, third, fifth, sixth};
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldGenerateExceptionWhenTryToRemoveMissingElement() {
        int idToRemove = 7;
        Exception e = assertThrows(NotFoundException.class, () -> manager.removeById(idToRemove));
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

    @Test
    void shouldSearchByBookAuthor() {
        String text = "Garber";
        Product[] expected = new Product[]{forth};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByBookName() {
        String text = "Jumps";
        Product[] expected = new Product[]{fifth};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBySmartphoneName() {
        String text = "Galaxy";
        Product[] expected = new Product[]{first};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBySmartphoneManufacturer() {
        String text = "Samsung";
        Product[] expected = new Product[]{first};
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByMissingString() {
        String text = "Honor";
        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy(text);
        assertArrayEquals(expected, actual);
        System.out.println("Not found");
    }

}
