package es2_groupbf;

import es2_groupbf.entities.Client;
import es2_groupbf.entities.Transaction;
import org.junit.jupiter.api.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DisplayName("Indicators Test")
public class IndicatorsTest {
    private static final Client VALID_CLIENT = new Client();
    private static final Client INVALID_CLIENT = new Client();
    private static final Client EMPTY_CLIENT = new Client();

    @BeforeAll
    static void beforeAll() throws ParseException {
        Transaction transaction1 = new Transaction(1, "PRT", 20, 0, "Client 1", "123abc", 60, 120.0, 60.0, 0, 1, 2, 1, 0, 0, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/01/20"), 1);
        Transaction transaction2 = new Transaction(2, "PRT", 20, 365, "Client 1", "123abc", 120, 220.0, 40.0, 0, 2, 2, 1, 365, 365, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/01/21"), 2);
        Transaction transaction3 = new Transaction(3, "PRT", 20, 730, "Client 1", "123abc", 180, 260.0, 80.0, 0, 3, 2, 1, 730, 730, "Travel Agent/Operator", "Travel Agent/Operator", new SimpleDateFormat("dd/MM/yy").parse("01/01/22"), 3);
        Transaction transaction4 = new Transaction(4, "PRT", 20, 1095, "Client 1", "123abc", 240, 110.0, 30.0, 0, 4, 2, 1, 1095, 1095, "Other", "Other", new SimpleDateFormat("dd/MM/yy").parse("01/01/23"), 4);
        List<Transaction> transactions = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3, transaction4));

        VALID_CLIENT.setDocIdHash("VALID CLIENT");
        VALID_CLIENT.setTransactions(transactions);

        INVALID_CLIENT.setDocIdHash("INVALID CLIENT");
        INVALID_CLIENT.setTransactions(new ArrayList<>(List.of(new Transaction())));
    }

    @Nested
    @DisplayName("Test monetization indicator")
    class MonetizationIndicator {
        @Nested
        @DisplayName("Test calculateIndicator method")
        class CalculateIndicator {
            @Nested
            @DisplayName("When client is valid")
            class WhenClientIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    VALID_CLIENT.setMonetization(new es2_groupbf.segmentation.indicators.MonetizationIndicator().calculateIndicator(VALID_CLIENT.getTransactions()));
                    Assertions.assertEquals(920.0, VALID_CLIENT.getMonetization());
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.indicators.MonetizationIndicator().calculateIndicator(VALID_CLIENT.getTransactions()));
                }
            }

            @Nested
            @DisplayName("When client is invalid")
            class WhenClientIsInvalid {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> new es2_groupbf.segmentation.indicators.MonetizationIndicator().calculateIndicator(INVALID_CLIENT.getTransactions()));
                }
            }

            @Nested
            @DisplayName("When client is empty")
            class WhenClientIsEmpty {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> new es2_groupbf.segmentation.indicators.MonetizationIndicator().calculateIndicator(EMPTY_CLIENT.getTransactions()));
                }
            }

            @Nested
            @DisplayName("When client is null")
            class WhenClientIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> new es2_groupbf.segmentation.indicators.MonetizationIndicator().calculateIndicator(null));
                }
            }
        }
    }

    @Nested
    @DisplayName("Test regularity indicator")
    class RegularityIndicator {
        @Nested
        @DisplayName("Test calculateIndicator method")
        class CalculateIndicator {
            @Nested
            @DisplayName("When client is valid")
            class WhenClientIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    VALID_CLIENT.setRegularity(new es2_groupbf.segmentation.indicators.RegularityIndicator().calculateIndicator(VALID_CLIENT.getTransactions()));
                    Assertions.assertEquals(LocalDate.now().getDayOfMonth() - 1, VALID_CLIENT.getRegularity());
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.indicators.RegularityIndicator().calculateIndicator(VALID_CLIENT.getTransactions()));
                }
            }

            @Nested
            @DisplayName("When client is invalid")
            class WhenClientIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.indicators.RegularityIndicator().calculateIndicator(INVALID_CLIENT.getTransactions()));
                }
            }

            @Nested
            @DisplayName("When client is empty")
            class WhenClientIsEmpty {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Assertions.assertEquals(Integer.MAX_VALUE, new es2_groupbf.segmentation.indicators.RegularityIndicator().calculateIndicator(new ArrayList<>()));
                }

                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> new es2_groupbf.segmentation.indicators.RegularityIndicator().calculateIndicator(EMPTY_CLIENT.getTransactions()));
                }
            }

            @Nested
            @DisplayName("When client is null")
            class WhenClientIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> new es2_groupbf.segmentation.indicators.RegularityIndicator().calculateIndicator(null));
                }
            }
        }
    }

    @Nested
    @DisplayName("Test total purchases indicator")
    class TotalPurchasesIndicator {
        @Nested
        @DisplayName("Test calculateIndicator method")
        class CalculateIndicator {
            @Nested
            @DisplayName("When client is valid")
            class WhenClientIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    VALID_CLIENT.setTotalPurchases(new es2_groupbf.segmentation.indicators.TotalPurchasesIndicator().calculateIndicator(VALID_CLIENT.getTransactions()));
                    Assertions.assertEquals(4, VALID_CLIENT.getTotalPurchases());
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.indicators.TotalPurchasesIndicator().calculateIndicator(VALID_CLIENT.getTransactions()));
                }
            }

            @Nested
            @DisplayName("When client is invalid")
            class WhenClientIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.indicators.TotalPurchasesIndicator().calculateIndicator(INVALID_CLIENT.getTransactions()));
                }
            }

            @Nested
            @DisplayName("When client is empty")
            class WhenClientIsEmpty {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> new es2_groupbf.segmentation.indicators.TotalPurchasesIndicator().calculateIndicator(EMPTY_CLIENT.getTransactions()));
                }
            }

            @Nested
            @DisplayName("When client is null")
            class WhenClientIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> new es2_groupbf.segmentation.indicators.TotalPurchasesIndicator().calculateIndicator(null));
                }
            }
        }
    }
}
