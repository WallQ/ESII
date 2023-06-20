package es2_groupbf;

import es2_groupbf.entities.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@DisplayName("OpenCSV Test")
class OpenCSVTest {
    private static final String FILE_NAME = "dataset.csv";
    private static final String INVALID_FILE_NAME = "invalid.csv";
    private static final String EMPTY_FILE_NAME = "empty.csv";

    @Nested
    @DisplayName("Test loadData method")
    class LoadData {
        @Nested
        @DisplayName("When file is valid")
        class WhenFileIsValid {
            @Test
            @DisplayName("Should be equal")
            void shouldBeEqual() throws FileNotFoundException {
                List<Transaction> transactions = OpenCSV.loadData(FILE_NAME);
                Transaction transaction = transactions.get(0);

                Date date = null;
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                try {
                    date = dateFormat.parse("23/05/18");
                } catch (ParseException exception) {
                    exception.printStackTrace();
                }

                Date finalDate = date;

                Assertions.assertAll(
                        () -> Assertions.assertEquals(4, transactions.size()),
                        () -> Assertions.assertEquals(1, transaction.getId()),
                        () -> Assertions.assertEquals("PRT", transaction.getNationality()),
                        () -> Assertions.assertEquals(51, transaction.getAge()),
                        () -> Assertions.assertEquals(150, transaction.getDaysSinceCreation()),
                        () -> Assertions.assertEquals("0x8E0A7AF39B633D5EA25C3B7EF4DFC5464B36DB7AF375716EB065E29697CC071E", transaction.getNameHash()),
                        () -> Assertions.assertEquals("0x71568459B729F7A7ABBED6C781A84CA4274D571003ACC7A4A791C3350D924137", transaction.getDocIdHash()),
                        () -> Assertions.assertEquals(45, transaction.getAverageLeadTime()),
                        () -> Assertions.assertEquals(371.0, transaction.getLodgingRevenue()),
                        () -> Assertions.assertEquals(105.3, transaction.getOtherRevenue()),
                        () -> Assertions.assertEquals(1, transaction.getBookingsCanceled()),
                        () -> Assertions.assertEquals(3, transaction.getBookingsCheckedIn()),
                        () -> Assertions.assertEquals(8, transaction.getPersonsNights()),
                        () -> Assertions.assertEquals(5, transaction.getRoomNights()),
                        () -> Assertions.assertEquals(151, transaction.getDaysSinceLastStay()),
                        () -> Assertions.assertEquals(1074, transaction.getDaysSinceFirstStay()),
                        () -> Assertions.assertEquals("Corporate", transaction.getDistributionChannel()),
                        () -> Assertions.assertEquals("Corporate", transaction.getMarketSegment()),
                        () -> Assertions.assertEquals(finalDate, transaction.getPurchaseDate()),
                        () -> Assertions.assertEquals(1, transaction.getPaymentMethod())
                );
            }

            @Test
            @DisplayName("Should not throw an exception")
            void shouldNotThrowException() {
                Assertions.assertDoesNotThrow(() -> OpenCSV.loadData(FILE_NAME));
            }
        }

        @Nested
        @DisplayName("When file is invalid")
        class WhenFileIsInvalid {
            @Test
            @DisplayName("Should throw the correct exception")
            void shouldThrowCorrectException() {
                Assertions.assertThrows(FileNotFoundException.class, () -> OpenCSV.loadData(INVALID_FILE_NAME));
            }
        }

        @Nested
        @DisplayName("When file is empty")
        class WhenFileIsEmpty {
            @Test
            @DisplayName("Should be equal")
            void shouldBeEqual() throws FileNotFoundException {
                List<Transaction> transactions = OpenCSV.loadData(EMPTY_FILE_NAME);
                Assertions.assertEquals(0, transactions.size());
            }

            @Test
            @DisplayName("Should not throw an exception")
            void shouldNotThrowException() {
                Assertions.assertDoesNotThrow(() -> OpenCSV.loadData(EMPTY_FILE_NAME));
            }
        }

        @Nested
        @DisplayName("When file is null")
        class WhenFileIsNull {
            @Test
            @DisplayName("Should throw the correct exception")
            void shouldThrowCorrectException() {
                Assertions.assertThrows(NullPointerException.class, () -> OpenCSV.loadData(null));
            }
        }
    }
}