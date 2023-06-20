package es2_groupbf;

import es2_groupbf.entities.Client;
import es2_groupbf.entities.Transaction;
import org.junit.jupiter.api.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@DisplayName("Statistics Test")
public class StatisticsTest {
    private static final List<Client> VALID_CLIENT_LIST = new ArrayList<>();
    private static final List<Client> INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES = new ArrayList<>();
    private static final List<Client> INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY = new ArrayList<>();
    private static final List<Client> EMPTY_CLIENT_LIST = new ArrayList<>();

    @BeforeAll
    static void beforeAll() throws ParseException {
        Transaction transaction1 = new Transaction(1, "PRT", 20, 0, "Client 1", "123abc", 60, 120.0, 60.0, 0, 1, 2, 1, 0, 0, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/01/22"), 1);
        Transaction transaction2 = new Transaction(2, "PRT", 20, 365, "Client 1", "123abc", 120, 220.0, 60.0, 0, 2, 2, 1, 365, 365, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/01/23"), 1);
        List<Transaction> client1Transactions = new ArrayList<>();
        client1Transactions.add(transaction1);
        client1Transactions.add(transaction2);

        Transaction transaction3 = new Transaction(3, "PRT", 40, 0, "Client 2", "abc123", 120, 260.0, 80.0, 0, 1, 2, 1, 0, 0, "Travel Agent/Operator", "Travel Agent/Operator", new SimpleDateFormat("dd/MM/yy").parse("01/01/21"), 2);
        Transaction transaction4 = new Transaction(4, "PRT", 40, 730, "Client 2", "abc123", 240, 110.0, 30.0, 0, 2, 3, 1, 730, 730, "Travel Agent/Operator", "Travel Agent/Operator", new SimpleDateFormat("dd/MM/yy").parse("01/01/23"), 2);
        List<Transaction> client2Transactions = new ArrayList<>();
        client2Transactions.add(transaction3);
        client2Transactions.add(transaction4);

        Transaction transaction5 = new Transaction(5, "FRA", 30, 0, "Client 3", "wasd", 60, 320.0, 60.0, 0, 1, 2, 1, 0, 0, "Direct", "Travel Agent/Operator", new SimpleDateFormat("dd/MM/yy").parse("01/01/23"), 3);
        List<Transaction> client3Transactions = new ArrayList<>();
        client3Transactions.add(transaction5);

        Transaction transaction6 = new Transaction(6, "ESP", 60, 0, "Client 4", "xywz", 30, 80.0, 20.0, 0, 1, 2, 1, 0, 0, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/01/22"), 4);
        List<Transaction> client4Transactions = new ArrayList<>();
        client4Transactions.add(transaction6);

        Client client1 = new Client("Client 1", client1Transactions);
        Client client2 = new Client("Client 2", client2Transactions);
        Client client3 = new Client("Client 3", client3Transactions);
        Client client4 = new Client("Client 4", client4Transactions);

        INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES.add(client1);
        INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES.add(client2);
        INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES.add(client3);
        INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES.add(client4);

        client1.setMonetization(460.0);
        client1.setRegularity(18);
        client1.setTotalPurchases(2);
        client1.setMonetizationScore(3);
        client1.setRegularityScore(3);
        client1.setTotalPurchasesScore(3);
        client1.setGeneralScore(9);

        client2.setMonetization(480.0);
        client2.setRegularity(18);
        client2.setTotalPurchases(2);
        client2.setMonetizationScore(4);
        client2.setRegularityScore(4);
        client2.setTotalPurchasesScore(4);
        client2.setGeneralScore(12);

        client3.setMonetization(380.0);
        client3.setRegularity(1114);
        client3.setTotalPurchases(1);
        client3.setMonetizationScore(2);
        client3.setRegularityScore(1);
        client3.setTotalPurchasesScore(1);
        client3.setGeneralScore(4);

        client4.setMonetization(100.0);
        client4.setRegularity(383);
        client4.setTotalPurchases(1);
        client4.setMonetizationScore(1);
        client4.setRegularityScore(2);
        client4.setTotalPurchasesScore(2);
        client4.setGeneralScore(5);

        INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY.add(client3);
        INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY.add(client4);

        VALID_CLIENT_LIST.add(client1);
        VALID_CLIENT_LIST.add(client2);
        VALID_CLIENT_LIST.add(client3);
        VALID_CLIENT_LIST.add(client4);
    }

    @Nested
    @DisplayName("Test client score statistics")
    class ClientScore {
        @Nested
        @DisplayName("Test getClientWithMinimumOfEachScore method")
        class GetClientWithMinimumOfEachScore {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Map<String, Client> expected = new HashMap<>();
                    Client client1 = VALID_CLIENT_LIST.get(3);
                    Client client2 = VALID_CLIENT_LIST.get(2);
                    expected.put("monetizationScore", client1);
                    expected.put("regularityScore", client2);
                    expected.put("totalPurchasesScore", client2);

                    Assertions.assertEquals(expected, es2_groupbf.statistics.ClientScore.getClientWithMinimumOfEachScore(VALID_CLIENT_LIST));
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.ClientScore.getClientWithMinimumOfEachScore(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.ClientScore.getClientWithMinimumOfEachScore(INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES));
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.ClientScore.getClientWithMinimumOfEachScore(INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.ClientScore.getClientWithMinimumOfEachScore(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> es2_groupbf.statistics.ClientScore.getClientWithMinimumOfEachScore(null));
                }
            }
        }

        @Nested
        @DisplayName("Test getClientWithMaximumOfEachScore method")
        class GetClientWithMaximumOfEachScore {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Map<String, Client> expected = new HashMap<>();
                    Client client1 = VALID_CLIENT_LIST.get(1);
                    expected.put("monetizationScore", client1);
                    expected.put("regularityScore", client1);
                    expected.put("totalPurchasesScore", client1);

                    Assertions.assertEquals(expected, es2_groupbf.statistics.ClientScore.getClientWithMaximumOfEachScore(VALID_CLIENT_LIST));
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.ClientScore.getClientWithMaximumOfEachScore(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.ClientScore.getClientWithMaximumOfEachScore(INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES));
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.ClientScore.getClientWithMaximumOfEachScore(INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.ClientScore.getClientWithMaximumOfEachScore(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> es2_groupbf.statistics.ClientScore.getClientWithMaximumOfEachScore(null));
                }
            }
        }

        @Nested
        @DisplayName("Test getClientWithWorstAverageScore method")
        class GetClientWithWorstAverageScore {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Assertions.assertEquals(VALID_CLIENT_LIST.get(3), es2_groupbf.statistics.ClientScore.getClientWithWorstAverageScore(VALID_CLIENT_LIST));
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.ClientScore.getClientWithWorstAverageScore(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.ClientScore.getClientWithWorstAverageScore(INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES));
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.ClientScore.getClientWithWorstAverageScore(INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.ClientScore.getClientWithWorstAverageScore(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> es2_groupbf.statistics.ClientScore.getClientWithWorstAverageScore(null));
                }
            }
        }

        @Nested
        @DisplayName("Test getClientWithBestAverageScore method")
        class GetClientWithBestAverageScore {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Assertions.assertEquals(VALID_CLIENT_LIST.get(1), es2_groupbf.statistics.ClientScore.getClientWithBestAverageScore(VALID_CLIENT_LIST));
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.ClientScore.getClientWithWorstAverageScore(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.ClientScore.getClientWithBestAverageScore(INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES));
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.ClientScore.getClientWithBestAverageScore(INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.ClientScore.getClientWithBestAverageScore(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> es2_groupbf.statistics.ClientScore.getClientWithBestAverageScore(null));
                }
            }
        }
    }

    @Nested
    @DisplayName("Test communication channels statistics")
    class CommunicationChannels {
        @Nested
        @DisplayName("Test getMostUsedCommunicationChannel method")
        class GetMostUsedCommunicationChannel {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Assertions.assertEquals("Corporate", es2_groupbf.statistics.CommunicationChannels.getMostUsedCommunicationChannel(VALID_CLIENT_LIST));
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.CommunicationChannels.getMostUsedCommunicationChannel(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertAll(
                            () -> Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.CommunicationChannels.getMostUsedCommunicationChannel(INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES)),
                            () -> Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.CommunicationChannels.getMostUsedCommunicationChannel(INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY))
                    );
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.CommunicationChannels.getMostUsedCommunicationChannel(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> es2_groupbf.statistics.CommunicationChannels.getMostUsedCommunicationChannel(null));
                }
            }
        }
    }

    @Nested
    @DisplayName("Test payment method statistics")
    class PaymentMethod {
        @Nested
        @DisplayName("Test getPredominantPaymentMethodPerClient method")
        class GetPredominantPaymentMethodPerClient {
            private static final Client VALID_CLIENT = new Client();
            private static final Client INVALID_CLIENT_NO_INDICATORS_AND_SCORES = new Client();
            private static final Client INVALID_CLIENT_ONE_TRANSACTION_ONLY = new Client();
            private static final Client EMPTY_CLIENT = new Client();

            @BeforeAll
            static void beforeAll() throws ParseException {
                Transaction transaction1 = new Transaction(1, "PRT", 20, 0, "Client 1", "123abc", 60, 120.0, 60.0, 0, 1, 2, 1, 0, 0, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/01/20"), 1);
                Transaction transaction2 = new Transaction(2, "PRT", 20, 365, "Client 1", "123abc", 120, 220.0, 40.0, 0, 2, 2, 1, 365, 365, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/02/21"), 2);
                Transaction transaction3 = new Transaction(3, "PRT", 20, 730, "Client 1", "123abc", 180, 260.0, 80.0, 0, 3, 2, 1, 730, 730, "Travel Agent/Operator", "Travel Agent/Operator", new SimpleDateFormat("dd/MM/yy").parse("01/03/22"), 3);
                Transaction transaction4 = new Transaction(4, "PRT", 20, 1095, "Client 1", "123abc", 240, 110.0, 30.0, 0, 4, 2, 1, 1095, 1095, "Other", "Other", new SimpleDateFormat("dd/MM/yy").parse("01/04/23"), 4);
                Transaction transaction5 = new Transaction(5, "PRT", 20, 1095, "Client 1", "123abc", 240, 110.0, 30.0, 0, 4, 2, 1, 1095, 1095, "Other", "Other", new SimpleDateFormat("dd/MM/yy").parse("01/04/23"), 3);
                Transaction transaction6 = new Transaction(6, "PRT", 20, 0, "Client 1", "123abc", 60, 120.0, 60.0, 0, 1, 2, 1, 0, 0, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/05/20"), 0);
                List<Transaction> transactions = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3, transaction4, transaction5, transaction6));

                VALID_CLIENT.setDocIdHash("VALID CLIENT");
                VALID_CLIENT.setTransactions(transactions);
                VALID_CLIENT.setMonetization(920.0);
                VALID_CLIENT.setRegularity(18);
                VALID_CLIENT.setTotalPurchases(4);
                VALID_CLIENT.setMonetizationScore(4);
                VALID_CLIENT.setRegularityScore(4);
                VALID_CLIENT.setTotalPurchasesScore(4);
                VALID_CLIENT.setGeneralScore(12);

                INVALID_CLIENT_NO_INDICATORS_AND_SCORES.setDocIdHash("INVALID CLIENT NO INDICATORS AND SCORES");
                INVALID_CLIENT_NO_INDICATORS_AND_SCORES.setTransactions(transactions);

                INVALID_CLIENT_ONE_TRANSACTION_ONLY.setDocIdHash("INVALID CLIENT ONE TRANSACTION ONLY");
                INVALID_CLIENT_ONE_TRANSACTION_ONLY.setTransactions(new ArrayList<>(List.of(transaction1)));

                EMPTY_CLIENT.setTransactions(new ArrayList<>());
            }

            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Assertions.assertEquals("Credit Card", es2_groupbf.statistics.PaymentMethod.getPredominantPaymentMethodPerClient(VALID_CLIENT));
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.PaymentMethod.getPredominantPaymentMethodPerClient(VALID_CLIENT));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.PaymentMethod.getPredominantPaymentMethodPerClient(INVALID_CLIENT_NO_INDICATORS_AND_SCORES));
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.PaymentMethod.getPredominantPaymentMethodPerClient(INVALID_CLIENT_ONE_TRANSACTION_ONLY));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NoSuchElementException.class, () -> es2_groupbf.statistics.PaymentMethod.getPredominantPaymentMethodPerClient(EMPTY_CLIENT));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> es2_groupbf.statistics.PaymentMethod.getPredominantPaymentMethodPerClient(null));
                }
            }
        }

        @Nested
        @DisplayName("Test getPredominantPaymentMethodInGeneral method")
        class GetPredominantPaymentMethodInGeneral {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Assertions.assertEquals("Payment Provider", es2_groupbf.statistics.PaymentMethod.getPredominantPaymentMethodInGeneral(VALID_CLIENT_LIST));
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.PaymentMethod.getPredominantPaymentMethodInGeneral(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.PaymentMethod.getPredominantPaymentMethodInGeneral(INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES));
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.PaymentMethod.getPredominantPaymentMethodInGeneral(INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NoSuchElementException.class, () -> es2_groupbf.statistics.PaymentMethod.getPredominantPaymentMethodInGeneral(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> es2_groupbf.statistics.PaymentMethod.getPredominantPaymentMethodInGeneral(null));
                }
            }
        }
    }

    @Nested
    @DisplayName("Test purchases statistics")
    class Purchases {
        @Nested
        @DisplayName("Test getMinimumPurchaseValue method")
        class GetMinimumPurchaseValue {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Assertions.assertEquals(100.0, es2_groupbf.statistics.Purchases.getMinimumPurchaseValue(VALID_CLIENT_LIST));
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getMinimumPurchaseValue(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getMinimumPurchaseValue(INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES));
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getMinimumPurchaseValue(INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getMinimumPurchaseValue(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> es2_groupbf.statistics.Purchases.getMinimumPurchaseValue(null));
                }
            }
        }

        @Nested
        @DisplayName("Test getMaximumPurchaseValue method")
        class GetMaximumPurchaseValue {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Assertions.assertEquals(380.0, es2_groupbf.statistics.Purchases.getMaximumPurchaseValue(VALID_CLIENT_LIST));
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getMaximumPurchaseValue(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getMaximumPurchaseValue(INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES));
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getMaximumPurchaseValue(INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getMaximumPurchaseValue(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> es2_groupbf.statistics.Purchases.getMaximumPurchaseValue(null));
                }
            }
        }

        @Nested
        @DisplayName("Test getAveragePurchasesValue method")
        class GetAveragePurchasesValue {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Assertions.assertEquals(236.66666666666666, es2_groupbf.statistics.Purchases.getAveragePurchasesValue(VALID_CLIENT_LIST));
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getAveragePurchasesValue(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getAveragePurchasesValue(INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES));
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getAveragePurchasesValue(INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getAveragePurchasesValue(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> es2_groupbf.statistics.Purchases.getAveragePurchasesValue(null));
                }
            }
        }

        @Nested
        @DisplayName("Test getAverageTimeIntervalBetweenPurchasesPerClient method")
        class GetAverageTimeIntervalBetweenPurchasesPerClient {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Map<String, Double> expected = new HashMap<>();
                    expected.put("Client 2", 730.0);
                    expected.put("Client 1", 365.0);

                    Assertions.assertEquals(expected, es2_groupbf.statistics.Purchases.getAverageTimeIntervalBetweenPurchasesPerClient(VALID_CLIENT_LIST));
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getAverageTimeIntervalBetweenPurchasesPerClient(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getAverageTimeIntervalBetweenPurchasesPerClient(INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES));
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getAverageTimeIntervalBetweenPurchasesPerClient(INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getAverageTimeIntervalBetweenPurchasesPerClient(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> es2_groupbf.statistics.Purchases.getAverageTimeIntervalBetweenPurchasesPerClient(null));
                }
            }
        }

        @Nested
        @DisplayName("Test getMinimumTimeIntervalBetweenPurchasesInGeneral method")
        class GetMinimumTimeIntervalBetweenPurchasesInGeneral {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Assertions.assertEquals(365.0, es2_groupbf.statistics.Purchases.getMinimumTimeIntervalBetweenPurchasesInGeneral(VALID_CLIENT_LIST));
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getMinimumTimeIntervalBetweenPurchasesInGeneral(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getMinimumTimeIntervalBetweenPurchasesInGeneral(INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES));
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getMinimumTimeIntervalBetweenPurchasesInGeneral(INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getMinimumTimeIntervalBetweenPurchasesInGeneral(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> es2_groupbf.statistics.Purchases.getMinimumTimeIntervalBetweenPurchasesInGeneral(null));
                }
            }
        }

        @Nested
        @DisplayName("Test getMaximumTimeIntervalBetweenPurchasesInGeneral method")
        class GetMaximumTimeIntervalBetweenPurchasesInGeneral {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Assertions.assertEquals(730.0, es2_groupbf.statistics.Purchases.getMaximumTimeIntervalBetweenPurchasesInGeneral(VALID_CLIENT_LIST));
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getMaximumTimeIntervalBetweenPurchasesInGeneral(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getMaximumTimeIntervalBetweenPurchasesInGeneral(INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES));
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getMaximumTimeIntervalBetweenPurchasesInGeneral(INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getMaximumTimeIntervalBetweenPurchasesInGeneral(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> es2_groupbf.statistics.Purchases.getMaximumTimeIntervalBetweenPurchasesInGeneral(null));
                }
            }
        }

        @Nested
        @DisplayName("Test getAverageTimeIntervalBetweenPurchasesInGeneral method")
        class GetAverageTimeIntervalBetweenPurchasesInGeneral {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Assertions.assertEquals(547.5, es2_groupbf.statistics.Purchases.getAverageTimeIntervalBetweenPurchasesInGeneral(VALID_CLIENT_LIST));
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getAverageTimeIntervalBetweenPurchasesInGeneral(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getAverageTimeIntervalBetweenPurchasesInGeneral(INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES));
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getAverageTimeIntervalBetweenPurchasesInGeneral(INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Purchases.getAverageTimeIntervalBetweenPurchasesInGeneral(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> es2_groupbf.statistics.Purchases.getAverageTimeIntervalBetweenPurchasesInGeneral(null));
                }
            }
        }
    }

    @Nested
    @DisplayName("Test seasonality statistics")
    class Seasonality {
        @Nested
        @DisplayName("Test getMostInterestingSeasonPerClient method")
        class GetMostInterestingSeasonPerClient {
            private static final Client VALID_CLIENT = new Client();
            private static final Client INVALID_CLIENT_NO_INDICATORS_AND_SCORES = new Client();
            private static final Client INVALID_CLIENT_ONE_TRANSACTION_ONLY = new Client();
            private static final Client EMPTY_CLIENT = new Client();

            @BeforeAll
            static void beforeAll() throws ParseException {
                Transaction transaction1 = new Transaction(1, "PRT", 20, 0, "Client 1", "123abc", 60, 120.0, 60.0, 0, 1, 2, 1, 0, 0, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/01/20"), 1);
                Transaction transaction2 = new Transaction(2, "PRT", 20, 365, "Client 1", "123abc", 120, 220.0, 40.0, 0, 2, 2, 1, 365, 365, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/02/21"), 2);
                Transaction transaction3 = new Transaction(3, "PRT", 20, 730, "Client 1", "123abc", 180, 260.0, 80.0, 0, 3, 2, 1, 730, 730, "Travel Agent/Operator", "Travel Agent/Operator", new SimpleDateFormat("dd/MM/yy").parse("01/03/22"), 3);
                Transaction transaction4 = new Transaction(4, "PRT", 20, 1095, "Client 1", "123abc", 240, 110.0, 30.0, 0, 4, 2, 1, 1095, 1095, "Other", "Other", new SimpleDateFormat("dd/MM/yy").parse("01/04/23"), 4);
                Transaction transaction5 = new Transaction(5, "PRT", 20, 0, "Client 1", "123abc", 60, 120.0, 60.0, 0, 1, 2, 1, 0, 0, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/05/20"), 1);
                Transaction transaction6 = new Transaction(6, "PRT", 20, 365, "Client 1", "123abc", 120, 220.0, 40.0, 0, 2, 2, 1, 365, 365, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/06/21"), 2);
                Transaction transaction7 = new Transaction(7, "PRT", 20, 730, "Client 1", "123abc", 180, 260.0, 80.0, 0, 3, 2, 1, 730, 730, "Travel Agent/Operator", "Travel Agent/Operator", new SimpleDateFormat("dd/MM/yy").parse("01/07/22"), 3);
                Transaction transaction8 = new Transaction(8, "PRT", 20, 1095, "Client 1", "123abc", 240, 110.0, 30.0, 0, 4, 2, 1, 1095, 1095, "Other", "Other", new SimpleDateFormat("dd/MM/yy").parse("01/08/23"), 4);
                Transaction transaction9 = new Transaction(9, "PRT", 20, 0, "Client 1", "123abc", 60, 120.0, 60.0, 0, 1, 2, 1, 0, 0, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/09/20"), 1);
                Transaction transaction10 = new Transaction(10, "PRT", 20, 365, "Client 1", "123abc", 120, 220.0, 40.0, 0, 2, 2, 1, 365, 365, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("01/10/21"), 2);
                Transaction transaction11 = new Transaction(11, "PRT", 20, 730, "Client 1", "123abc", 180, 260.0, 80.0, 0, 3, 2, 1, 730, 730, "Travel Agent/Operator", "Travel Agent/Operator", new SimpleDateFormat("dd/MM/yy").parse("01/11/22"), 3);
                Transaction transaction12 = new Transaction(12, "PRT", 20, 1095, "Client 1", "123abc", 240, 110.0, 30.0, 0, 4, 2, 1, 1095, 1095, "Other", "Other", new SimpleDateFormat("dd/MM/yy").parse("01/12/23"), 4);
                Transaction transaction13 = new Transaction(13, "PRT", 20, 730, "Client 1", "123abc", 180, 260.0, 80.0, 0, 3, 2, 1, 730, 730, "Travel Agent/Operator", "Travel Agent/Operator", new SimpleDateFormat("dd/MM/yy").parse("21/03/22"), 3);
                Transaction transaction14 = new Transaction(14, "PRT", 20, 365, "Client 1", "123abc", 120, 220.0, 40.0, 0, 2, 2, 1, 365, 365, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("21/06/21"), 2);
                Transaction transaction15 = new Transaction(15, "PRT", 20, 0, "Client 1", "123abc", 60, 120.0, 60.0, 0, 1, 2, 1, 0, 0, "Corporate", "Corporate", new SimpleDateFormat("dd/MM/yy").parse("21/09/20"), 1);
                Transaction transaction16 = new Transaction(16, "PRT", 20, 1095, "Client 1", "123abc", 240, 110.0, 30.0, 0, 4, 2, 1, 1095, 1095, "Other", "Other", new SimpleDateFormat("dd/MM/yy").parse("21/12/23"), 4);
                List<Transaction> transactions = new ArrayList<>(Arrays.asList(transaction1, transaction2, transaction3, transaction4, transaction5, transaction6, transaction7, transaction8, transaction9, transaction10, transaction11, transaction12, transaction13, transaction14, transaction15, transaction16));

                VALID_CLIENT.setDocIdHash("VALID CLIENT");
                VALID_CLIENT.setTransactions(transactions);
                VALID_CLIENT.setMonetization(920.0);
                VALID_CLIENT.setRegularity(18);
                VALID_CLIENT.setTotalPurchases(4);
                VALID_CLIENT.setMonetizationScore(4);
                VALID_CLIENT.setRegularityScore(4);
                VALID_CLIENT.setTotalPurchasesScore(4);
                VALID_CLIENT.setGeneralScore(12);

                INVALID_CLIENT_NO_INDICATORS_AND_SCORES.setDocIdHash("INVALID CLIENT NO INDICATORS AND SCORES");
                INVALID_CLIENT_NO_INDICATORS_AND_SCORES.setTransactions(transactions);

                INVALID_CLIENT_ONE_TRANSACTION_ONLY.setDocIdHash("INVALID CLIENT ONE TRANSACTION ONLY");
                INVALID_CLIENT_ONE_TRANSACTION_ONLY.setTransactions(new ArrayList<>(List.of(transaction1)));

                EMPTY_CLIENT.setTransactions(new ArrayList<>());
            }

            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Assertions.assertEquals("Winter", es2_groupbf.statistics.Seasonality.getMostInterestingSeasonPerClient(VALID_CLIENT));
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Seasonality.getMostInterestingSeasonPerClient(VALID_CLIENT));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Seasonality.getMostInterestingSeasonPerClient(INVALID_CLIENT_NO_INDICATORS_AND_SCORES));
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Seasonality.getMostInterestingSeasonPerClient(INVALID_CLIENT_ONE_TRANSACTION_ONLY));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NoSuchElementException.class, () -> es2_groupbf.statistics.Seasonality.getMostInterestingSeasonPerClient(EMPTY_CLIENT));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> es2_groupbf.statistics.Seasonality.getMostInterestingSeasonPerClient(null));
                }
            }
        }

        @Nested
        @DisplayName("Test getMostInterestingSeasonInGeneral method")
        class GetMostInterestingSeasonInGeneral {
            @Nested
            @DisplayName("When client list is valid")
            class WhenClientListIsValid {
                @Test
                @DisplayName("Should be equal")
                void shouldBeEqual() {
                    Assertions.assertEquals("Winter", es2_groupbf.statistics.Seasonality.getMostInterestingSeasonInGeneral(VALID_CLIENT_LIST));
                }

                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Seasonality.getMostInterestingSeasonInGeneral(VALID_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is invalid")
            class WhenClientListIsInvalid {
                @Test
                @DisplayName("Should not throw an exception")
                void shouldNotThrowException() {
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Seasonality.getMostInterestingSeasonInGeneral(INVALID_CLIENT_LIST_NO_INDICATORS_AND_SCORES));
                    Assertions.assertDoesNotThrow(() -> es2_groupbf.statistics.Seasonality.getMostInterestingSeasonInGeneral(INVALID_CLIENT_LIST_ONE_TRANSACTION_ONLY));
                }
            }

            @Nested
            @DisplayName("When client list is empty")
            class WhenClientListIsEmpty {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NoSuchElementException.class, () -> es2_groupbf.statistics.Seasonality.getMostInterestingSeasonInGeneral(EMPTY_CLIENT_LIST));
                }
            }

            @Nested
            @DisplayName("When client list is null")
            class WhenClientListIsNull {
                @Test
                @DisplayName("Should throw the correct exception")
                void shouldThrowCorrectException() {
                    Assertions.assertThrows(NullPointerException.class, () -> es2_groupbf.statistics.Seasonality.getMostInterestingSeasonInGeneral(null));
                }
            }
        }
    }
}
