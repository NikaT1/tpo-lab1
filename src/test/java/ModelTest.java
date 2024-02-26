import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tpo.task_3.*;
import tpo.task_3.disease.Disease;

import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    @Nested
    class ProcessOfDiseaseClassTest {
        private Entity entity;
        private Disease simpleDisease;
        private Disease deadlyDisease;
        @BeforeEach
        void init() {
            Language l1 = null;
            try {
                l1 = new Language("Бетельгейзе-5", 3);
            } catch (Exception ignored) {}
            entity = new Entity(new Name("Форд",l1), new Date());
            simpleDisease = new Disease("Простуда", TypeOfDisease.PASSING_WITHOUT_TREATMENT);
            deadlyDisease = new Disease("Стыд", TypeOfDisease.DEADLY);
        }

        @Test
        @DisplayName("Check health type of entity without disease")
        void testHealthTypeOfEntityWithoutDisease() {
            assertEquals(HealthStatus.HEALTHFUL, entity.getHealthStatus());
        }
        @ParameterizedTest
        @ArgumentsSource(ProcessOfDiseaseProvider.class)
        @DisplayName("Check health type of entity connect to disease")
        void testHealthTypeOfEntityWithDisease(Disease disease, DiseaseResult result, HealthStatus status) {
            ProcessOfDisease processOfDisease = new ProcessOfDisease(disease, entity);
            assertAll(
                    () -> {
                        assertEquals(HealthStatus.INFECTED, entity.getHealthStatus());
                    },
                    () -> {
                        processOfDisease.startDisease();
                        assertEquals(HealthStatus.SICK, entity.getHealthStatus());
                    },
                    () -> {
                        processOfDisease.finishDisease(result);
                        assertEquals(status, entity.getHealthStatus());
                    }
            );
        }
        @Test
        @DisplayName("Check not being able to recover after deadly disease")
        void testRecoverAfterDeadlyDisease() {
            ProcessOfDisease processOfDeadlyDisease = new ProcessOfDisease(deadlyDisease, entity);
            assertAll(
                    () -> {
                        processOfDeadlyDisease.startDisease();
                        assertEquals(HealthStatus.SICK, entity.getHealthStatus());
                    },
                    () -> {

                        Throwable exception = assertThrows(Exception.class, () ->  processOfDeadlyDisease.finishDisease(DiseaseResult.WITHOUT_TRACE));
                        assertEquals("Чуда не бывает, нельзя выжить после смертельной болезни!", exception.getMessage());
                    },
                    () -> {
                        Throwable exception = assertThrows(Exception.class, () ->  processOfDeadlyDisease.finishDisease(DiseaseResult.HEALTH_CONSEQUENCES));
                        assertEquals("Чуда не бывает, нельзя выжить после смертельной болезни!", exception.getMessage());
                    }
            );
        }

        @Test
        @DisplayName("Check not being able to die after simple disease")
        void testDieAfterSimpleDisease() {
            ProcessOfDisease processOfSimpleDisease = new ProcessOfDisease(simpleDisease, entity);
            assertAll(
                    () -> {
                        processOfSimpleDisease.startDisease();
                        assertEquals(HealthStatus.SICK, entity.getHealthStatus());
                    },
                    () -> {

                        Throwable exception = assertThrows(Exception.class, () ->  processOfSimpleDisease.finishDisease(DiseaseResult.DEATH));
                        assertEquals("Болезнь не вызывает смерть", exception.getMessage());
                    }
            );
        }
        static class ProcessOfDiseaseProvider implements ArgumentsProvider {

            @Override
            public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
                return Stream.of(
                        Arguments.of(new Disease("Простуда", TypeOfDisease.PASSING_WITHOUT_TREATMENT), DiseaseResult.WITHOUT_TRACE, HealthStatus.HEALTHFUL),
                        Arguments.of(new Disease("Простуда", TypeOfDisease.PASSING_WITHOUT_TREATMENT), DiseaseResult.HEALTH_CONSEQUENCES, HealthStatus.INJURY),
                        Arguments.of(new Disease("Корь", TypeOfDisease.CURABLE), DiseaseResult.HEALTH_CONSEQUENCES, HealthStatus.INJURY),
                        Arguments.of(new Disease("Корь", TypeOfDisease.CURABLE), DiseaseResult.WITHOUT_TRACE, HealthStatus.HEALTHFUL),
                        Arguments.of(new Disease("Корь", TypeOfDisease.CURABLE), DiseaseResult.DEATH, HealthStatus.DEAD),
                        Arguments.of(new Disease("Стыд", TypeOfDisease.DEADLY), DiseaseResult.DEATH, HealthStatus.DEAD)
                );
            }
        }
    }
    @Nested
    class TableOfNicknamesClassTest {
        private TableOfNicknames table;
        private Entity entity;
        private Language l1;
        @BeforeEach
        void init() {
            try {
                l1 = new Language("Бетельгейзе-5", 3);
            } catch (Exception ignored) {}
            table = new TableOfNicknames(NicknameFromPlaces.SCHOOL);
            entity = new Entity(new Name("Форд",l1), new Date());
        }
        @Test
        @DisplayName("Check taking names from table of nicknames")
        void testTakingNamesFromTableOfNicknames() {
            table.addNicknameToArrayList(new Nickname("abc", l1, "abcd"));
            table.addNicknameToArrayList(new Nickname("Форд", l1, "Ыкс"));
            table.addNicknameToArrayList(new Nickname("bcd", l1, "bcdf"));
            assertArrayEquals(new String[]{"Ыкс"}, table.getAllNicknamesOfEntity(entity));
        }
        @Test
        @DisplayName("Check taking names from empty table of nicknames")
        void testAddingNameWithIncorrectComplexity() {
            assertArrayEquals(new String[]{}, table.getAllNicknamesOfEntity(entity));
        }

        @Test
        @DisplayName("Check taking no names from table of nicknames")
        void testAddingLanguageWithIncorrectComplexity() {
            table.addNicknameToArrayList(new Nickname("abc", l1, "abcd"));
            table.addNicknameToArrayList(new Nickname("ggg", l1, "Ыкс"));
            table.addNicknameToArrayList(new Nickname("bcd", l1, "bcdf"));
            assertArrayEquals(new String[]{}, table.getAllNicknamesOfEntity(entity));
        }
    }
    @Nested
    class HumanClassTest {
        private Human h1;
        @BeforeEach
        void init() {
            Language l1 = null;
            try {
                l1 = new Language("Бетельгейзе-7", 6);
            } catch (Exception ignored) {}
            h1 = new Human(new Name("Форд", l1), new Date());
        }
        @Test
        @DisplayName("Check say name with different complexity")
        void testSayNameFunc() {
            assertAll(
                    () -> {
                        h1.getName().setComplexity(3);
                        assertEquals("Форд", h1.sayName());
                    },
                    () -> {
                        h1.getName().setComplexity(6);
                        Throwable exception = assertThrows(Exception.class, () ->  h1.sayName());
                        assertEquals("Имя не может быть произнесено, оно слишком сложное!", exception.getMessage());
                    }
            );
        }
    }
    @Nested
    class NameAndLanguageClassTest {
        private Language l1;
        private Language l2;
        @BeforeEach
        void init() {
            try {
                l1 = new Language("Бетельгейзе-5", 3);
                l2 = new Language("Бетельгейзе-7", 6);
            } catch (Exception ignored) {}
        }
        @Test
        @DisplayName("Check creation names with normal complexity")
        void testAddingNameWithNormalComplexity() {
            assertAll(
                    () -> {
                        Name name1 = new Name("Форд", l1);
                        assertEquals(5, name1.getComplexity());
                    },
                    () -> {
                        Name name1 = new Name("Форд", l1, 6);
                        assertEquals(6, name1.getComplexity());
                    },
                    () -> {
                        Name name1 = new Name("Форд", l2, 6);
                        assertEquals(9, name1.getComplexity());
                    },
                    () -> {
                        Name name1 = new Name("Форд", l2, 9);
                        assertEquals(10, name1.getComplexity());
                    }
            );
        }
        @Test
        @DisplayName("Check creation names with incorrect complexity")
        void testAddingNameWithIncorrectComplexity() {
            Throwable exception = assertThrows(Exception.class, () ->  new Name("Форд", l2, 12));
            assertEquals("Сложность имени должна быть в пределах от 0 до 10!", exception.getMessage());
        }

        @Test
        @DisplayName("Check creation language with incorrect complexity")
        void testAddingLanguageWithIncorrectComplexity() {
            Throwable exception = assertThrows(Exception.class, () ->  new Language("fffff", 12));
            assertEquals("Сложность языка должна быть в пределах от 0 до 10!", exception.getMessage());
        }
    }
    @Nested
    class EntityClassTest {
        private Entity h1;
        private Entity h2;
        @BeforeEach
        void init() {
            Language l1 = null;
            try {
                l1 = new Language("Бетельгейзе-7", 6);
            } catch (Exception ignored) {}
            h1 = new Entity(new Name("Форд", l1), new Date());
            h2 = new Entity(new Name("Джон", l1), new Date());
        }
        @Test
        @DisplayName("Check creation parents")
        void testAddingParentsToHuman() {
            h1.addParent(h2);
            assertTrue(h1.checkParent(h2));
            assertTrue(h2.checkChildren(h1));
            assertFalse(h2.checkParent(h1));
            assertFalse(h1.checkChildren(h2));
        }
        @Test
        @DisplayName("Check creation children")
        void testAddingChildrenToHuman() {
            h1.addChildren(h2);
            assertTrue(h2.checkParent(h1));
            assertTrue(h1.checkChildren(h2));
            assertFalse(h1.checkParent(h2));
            assertFalse(h2.checkChildren(h1));
        }
    }
}
