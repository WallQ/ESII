package es2_groupbf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

@DisplayName("Converters Test")
public class ConvertersTest {
    @Nested
    @DisplayName("Test date converter")
    class DateConverter {
        @Nested
        @DisplayName("When input is valid")
        class WhenInputIsValid {
            @Test
            @DisplayName("Should be equal")
            void shouldBeEqual() {
                Assertions.assertAll(
                        () -> Assertions.assertEquals(new SimpleDateFormat("dd/MM/yy").parse("01/01/1970"), new es2_groupbf.converters.DateConverter<>().convert("NULL")),
                        () -> Assertions.assertEquals(new SimpleDateFormat("dd/MM/yy").parse("01/01/2023"), new es2_groupbf.converters.DateConverter<>().convert("01/01/2023"))
                );
            }
        }

        @Nested
        @DisplayName("When input is invalid")
        class WhenInputIsInvalid {
            @Test
            @DisplayName("Should throw the correct exception")
            void shouldThrowCorrectException() {
                Assertions.assertThrows(RuntimeException.class, () -> new es2_groupbf.converters.DateConverter<>().convert("Invalid date"));
            }
        }

        @Nested
        @DisplayName("When input is null")
        class WhenInputIsNull {
            @Test
            @DisplayName("Should throw the correct exception")
            void shouldThrowCorrectException() {
                Assertions.assertThrows(RuntimeException.class, () -> new es2_groupbf.converters.DateConverter<>().convert(null));
            }
        }
    }

    @Nested
    @DisplayName("Test double converter")
    class DoubleConverter {
        @Nested
        @DisplayName("When input is valid")
        class WhenInputIsValid {
            @Test
            @DisplayName("Should be equal")
            void shouldBeEqual() {
                Assertions.assertAll(
                        () -> Assertions.assertEquals(0.0, new es2_groupbf.converters.DoubleConverter<>().convert("NULL")),
                        () -> Assertions.assertEquals(3.5, new es2_groupbf.converters.DoubleConverter<>().convert(String.valueOf(3.5)))
                );
            }
        }

        @Nested
        @DisplayName("When input is invalid")
        class WhenInputIsInvalid {
            @Test
            @DisplayName("Should throw the correct exception")
            void shouldThrowCorrectException() {
                Assertions.assertThrows(RuntimeException.class, () -> new es2_groupbf.converters.DoubleConverter<>().convert("Invalid double"));
            }
        }

        @Nested
        @DisplayName("When input is null")
        class WhenInputIsNull {
            @Test
            @DisplayName("Should throw the correct exception")
            void shouldThrowCorrectException() {
                Assertions.assertThrows(RuntimeException.class, () -> new es2_groupbf.converters.DoubleConverter<>().convert(null));
            }
        }
    }

    @Nested
    @DisplayName("Test null converter")
    class NullConverter {
        @Nested
        @DisplayName("When input is valid")
        class WhenInputIsValid {
            @Test
            @DisplayName("Should be equal")
            void shouldBeEqual() {
                Assertions.assertAll(
                        () -> Assertions.assertEquals(0, new es2_groupbf.converters.NullConverter<>().convert("NULL")),
                        () -> Assertions.assertEquals(1, new es2_groupbf.converters.NullConverter<>().convert(String.valueOf(1)))
                );
            }
        }

        @Nested
        @DisplayName("When input is invalid")
        class WhenInputIsInvalid {
            @Test
            @DisplayName("Should throw the correct exception")
            void shouldThrowCorrectException() {
                Assertions.assertThrows(RuntimeException.class, () -> new es2_groupbf.converters.NullConverter<>().convert("Invalid null"));
            }
        }

        @Nested
        @DisplayName("When input is null")
        class WhenInputIsNull {
            @Test
            @DisplayName("Should throw the correct exception")
            void shouldThrowCorrectException() {
                Assertions.assertThrows(RuntimeException.class, () -> new es2_groupbf.converters.NullConverter<>().convert(null));
            }
        }
    }

    @Nested
    @DisplayName("Test payment method converter")
    class PaymentMethodConverter {
        @Nested
        @DisplayName("When input is valid")
        class WhenInputIsValid {
            @Test
            @DisplayName("Should be equal")
            void shouldBeEqual() {
                Assertions.assertAll(
                        () -> Assertions.assertEquals(0, new es2_groupbf.converters.PaymentMethodConverter<>().convert("NULL")),
                        () -> Assertions.assertEquals(1, new es2_groupbf.converters.PaymentMethodConverter<>().convert(String.valueOf(1))),
                        () -> Assertions.assertEquals(0, new es2_groupbf.converters.PaymentMethodConverter<>().convert(String.valueOf(0))),
                        () -> Assertions.assertEquals(0, new es2_groupbf.converters.PaymentMethodConverter<>().convert(String.valueOf(5)))
                );
            }
        }

        @Nested
        @DisplayName("When input is invalid")
        class WhenInputIsInvalid {
            @Test
            @DisplayName("Should throw the correct exception")
            void shouldThrowCorrectException() {
                Assertions.assertThrows(RuntimeException.class, () -> new es2_groupbf.converters.PaymentMethodConverter<>().convert("Invalid payment method"));
            }
        }

        @Nested
        @DisplayName("When input is null")
        class WhenInputIsNull {
            @Test
            @DisplayName("Should throw the correct exception")
            void shouldThrowCorrectException() {
                Assertions.assertThrows(RuntimeException.class, () -> new es2_groupbf.converters.PaymentMethodConverter<>().convert(null));
            }
        }
    }
}
