package es2_groupbf;

import es2_groupbf.entities.Statistics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GsonTest {
    private static final Statistics STATISTICS = new Statistics();
    private static final String FILE_NAME = "data.json";

    @Nested
    @DisplayName("Test exportData method")
    class ExportData {
        @Nested
        @DisplayName("When object and file name is valid")
        class WhenFileIsValid {
            @Test
            @DisplayName("Should not throw an exception")
            void shouldNotThrowException() {
                Assertions.assertDoesNotThrow(() -> Gson.exportData(STATISTICS, FILE_NAME));
            }
        }

        @Nested
        @DisplayName("When object and file name is invalid")
        class WhenFileIsInvalid {
            @Test
            @DisplayName("Should not throw an exception")
            void shouldNotThrowException() {
                Assertions.assertDoesNotThrow(() -> Gson.exportData(STATISTICS, FILE_NAME));
            }
        }

        @Nested
        @DisplayName("When object and file name is null")
        class WhenFileIsNull {
            @Test
            @DisplayName("Should throw the correct exception")
            void shouldThrowCorrectException() {
                Assertions.assertAll(
                        () -> Assertions.assertThrows(NullPointerException.class, () -> Gson.exportData(STATISTICS, null)),
                        () -> Assertions.assertDoesNotThrow(() -> Gson.exportData(null, FILE_NAME)),
                        () -> Assertions.assertThrows(NullPointerException.class, () -> Gson.exportData(null, null))
                );
            }
        }
    }
}