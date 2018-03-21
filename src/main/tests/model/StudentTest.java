package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest{

    @Test
    void studentWalletShoudntBeOnMinus() {
        Team myTeam = new Team(10, "Trololo");
        Group myGroup = new Group(10, "Hehehe");

        Student student = new Student(100, "Kuba", "Mikołajczyk", "mojemail@gmail.com", "ukulele", -100, 100,
                myTeam, myGroup);
        int expected = 0;
        assertEquals(expected, student.getWallet());
    }

        }