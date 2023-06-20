package es2_groupbf;

import es2_groupbf.entities.Client;
import es2_groupbf.entities.Transaction;
import org.junit.jupiter.api.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@DisplayName("Scores Test")
public class ScoresTest {
    private static final List<Client> VALID_CLIENT_LIST = new ArrayList<>();
    private static final List<Client> INVALID_CLIENT_LIST = new ArrayList<>();
    private static final List<Client> EMPTY_CLIENT_LIST = new ArrayList<>();

    @BeforeAll
    static void beforeAll() throws ParseException {
        Transaction transaction1 = new Transaction(1, "PRT", 20, 0, "Client 1", "123abc", 60, 120.0, 60.0, 0, 1, 2, 1, 0, 0, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/01/22"), 1);
        Transaction transaction2 = new Transaction(2, "PRT", 20, 365, "Client 1", "123abc", 120, 220.0, 60.0, 0, 2, 2, 1, 365, 365, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/01/23"), 1);
        List<Transaction> client1Transactions = new ArrayList<>(Arrays.asList(transaction1, transaction2));

        Transaction transaction3 = new Transaction(3, "PRT", 40, 0, "Client 2", "abc123", 120, 260.0, 80.0, 0, 1, 2, 1, 0, 0, "Travel Agent/Operator", "Travel Agent/Operator", new SimpleDateFormat("dd/MM/yy").parse("01/01/21"), 2);
        Transaction transaction4 = new Transaction(4, "PRT", 40, 730, "Client 2", "abc123", 240, 110.0, 30.0, 0, 2, 3, 1, 730, 730, "Travel Agent/Operator", "Travel Agent/Operator", new SimpleDateFormat("dd/MM/yy").parse("01/01/23"), 2);
        List<Transaction> client2Transactions = new ArrayList<>(Arrays.asList(transaction3, transaction4));

        Transaction transaction5 = new Transaction(5, "FRA", 30, 0, "Client 3", "wasd", 60, 320.0, 60.0, 0, 1, 2, 1, 0, 0, "Direct", "Travel Agent/Operator", new SimpleDateFormat("dd/MM/yy").parse("01/01/20"), 3);
        List<Transaction> client3Transactions = new ArrayList<>(List.of(transaction5));

        Transaction transaction6 = new Transaction(6, "ESP", 60, 0, "Client 4", "xywz", 30, 80.0, 20.0, 0, 1, 2, 1, 0, 0, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/01/22"), 4);
        List<Transaction> client4Transactions = new ArrayList<>(List.of(transaction6));

        Client client1 = new Client("Client 1", client1Transactions);
        Client client2 = new Client("Client 2", client2Transactions);
        Client client3 = new Client("Client 3", client3Transactions);
        Client client4 = new Client("Client 4", client4Transactions);

        INVALID_CLIENT_LIST.add(client1);
        INVALID_CLIENT_LIST.add(client2);
        INVALID_CLIENT_LIST.add(client3);
        INVALID_CLIENT_LIST.add(client4);

        client1.setMonetization(460.0);
        client1.setRegularity(18);
        client1.setTotalPurchases(2);

        client2.setMonetization(480.0);
        client2.setRegularity(18);
        client2.setTotalPurchases(2);

        client3.setMonetization(380.0);
        client3.setRegularity(1114);
        client3.setTotalPurchases(1);

        client4.setMonetization(100.0);
        client4.setRegularity(383);
        client4.setTotalPurchases(1);

        VALID_CLIENT_LIST.add(client1);
        VALID_CLIENT_LIST.add(client2);
        VALID_CLIENT_LIST.add(client3);
        VALID_CLIENT_LIST.add(client4);
    }

    @Nested
    @DisplayName("Test individual score")
    class IndividualScore {
        @Nested
        @DisplayName("Test calculateScore method")
        class CalculateScore {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    for (Client client : VALID_CLIENT_LIST) {
                        client.setMonetizationScore(new Random().nextInt(4 - 1) + 1);
                        client.setRegularityScore(new Random().nextInt(4 - 1) + 1);
                        client.setTotalPurchasesScore(new Random().nextInt(4 - 1) + 1);
                    }

                    new es2_groupbf.segmentation.scores.IndividualScore().calculateScore(VALID_CLIENT_LIST);

                    for (Client client : VALID_CLIENT_LIST) {
                        Assertions.assertAll(
                                () -> Assertions.assertEquals((client.getMonetizationScore() + client.getRegularityScore() + client.getTotalPurchasesScore()), client.getGeneralScore()),
                                () -> Assertions.assertNotEquals(null, client.getGeneralScore())
                        );
                    }
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.scores.IndividualScore().calculateScore(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.scores.IndividualScore().calculateScore(INVALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.scores.IndividualScore().calculateScore(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> new es2_groupbf.segmentation.scores.IndividualScore().calculateScore(null));
                }
            }
        }
    }

    @Nested
    @DisplayName("Test monetization score")
    class MonetizationScore {
        @Nested
        @DisplayName("Test calculateScore method")
        class CalculateScore {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    new es2_groupbf.segmentation.scores.MonetizationScore().calculateScore(VALID_CLIENT_LIST);

                    for (Client client : VALID_CLIENT_LIST) {
                        Assertions.assertNotEquals(null, client.getMonetizationScore());
                    }
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.scores.MonetizationScore().calculateScore(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.scores.MonetizationScore().calculateScore(INVALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.scores.MonetizationScore().calculateScore(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> new es2_groupbf.segmentation.scores.MonetizationScore().calculateScore(null));
                }
            }
        }
    }

    @Nested
    @DisplayName("Test regularity score")
    class RegularityScore {
        @Nested
        @DisplayName("Test calculateScore method")
        class CalculateScore {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    new es2_groupbf.segmentation.scores.RegularityScore().calculateScore(VALID_CLIENT_LIST);

                    for (Client client : VALID_CLIENT_LIST) {
                        Assertions.assertNotEquals(null, client.getRegularityScore());
                    }
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.scores.RegularityScore().calculateScore(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.scores.RegularityScore().calculateScore(INVALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.scores.RegularityScore().calculateScore(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> new es2_groupbf.segmentation.scores.RegularityScore().calculateScore(null));
                }
            }
        }
    }

    @Nested
    @DisplayName("Test total purchases score")
    class TotalPurchasesScore {
        @Nested
        @DisplayName("Test calculateScore method")
        class CalculateScore {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    new es2_groupbf.segmentation.scores.TotalPurchasesScore().calculateScore(VALID_CLIENT_LIST);

                    for (Client client : VALID_CLIENT_LIST) {
                        Assertions.assertNotEquals(null, client.getTotalPurchasesScore());
                    }
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.scores.TotalPurchasesScore().calculateScore(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.scores.TotalPurchasesScore().calculateScore(INVALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> new es2_groupbf.segmentation.scores.TotalPurchasesScore().calculateScore(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> new es2_groupbf.segmentation.scores.TotalPurchasesScore().calculateScore(null));
                }
            }
        }
    }
}

