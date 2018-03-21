package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

 class UserTest {

    @Test
    void getEmail_ShouldReturnProperlyConstructedEmailAdress() {
        User user = new Student("John", "Doe", "123");
        String expected = "john.doe@cc.com";
        assertEquals(expected, user.getEmail());
    }

    @Test
    void constuctor_numbersInNameShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()-> {
        new Student("Jo4n", "Doe", "123");});
    }

    @Test
    void constructor_numbersInSurnameShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()-> {
            new Student("John", "Do5e", "123");});
    }

}