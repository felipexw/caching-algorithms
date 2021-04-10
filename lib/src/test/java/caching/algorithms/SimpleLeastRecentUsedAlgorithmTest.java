package caching.algorithms;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

@DisplayName("LRU algorithm test")
public class SimpleLeastRecentUsedAlgorithmTest {

	private SimpleLeastRecentUsedAlgorithm<Integer> lruAlgorithm;
	private TestInfo testInfo;
	private TestReporter testReporter;

	@Test
	@Tag("dev")
	@DisplayName("Running selectively if you want to")
	public void run_selectively_with_tag() {
		assertThat(1).isEqualTo(1);
	}

	@BeforeEach
	void setUpLogger(TestInfo testInfo, TestReporter testReporter) {
		lruAlgorithm = new SimpleLeastRecentUsedAlgorithm<>(5);
		this.testInfo = testInfo;
		this.testReporter = testReporter;

		// Display this when every test its executed and it transmits to the stout.
		testReporter.publishEntry("Running " + testInfo.getDisplayName() + " TAG " + testInfo.getTags());
	}

	@Nested
	@DisplayName("show list recent used (nested)")
	class TestCase {

		@Test
		@DisplayName("nested method test")
		void list_recented_used_test() {
			lruAlgorithm.add(10123);
			lruAlgorithm.add(577);
			lruAlgorithm.add(423);
			lruAlgorithm.add(664);
			lruAlgorithm.add(662);
			lruAlgorithm.add(-510);
			lruAlgorithm.add(1000);

			final String expectedResult = "1000  -510  662  664  423";

			Assertions.assertThat(lruAlgorithm.lru()).isEqualTo(expectedResult);
		}

		@Test
		@DisplayName("nested method with all assertions")
		void all_assertions_test() {
			lruAlgorithm.add(5);
			lruAlgorithm.add(10);
			lruAlgorithm.add(10123);
			lruAlgorithm.add(577);
			lruAlgorithm.add(423);
			lruAlgorithm.add(664);
			lruAlgorithm.add(662);
			lruAlgorithm.add(-510);
			lruAlgorithm.add(1000);

			final String expectedResult = "1000  -510  662  664  423";
			final String expectedResult2 = "423  -510  662  664  1000";

			assertAll(() -> assertThat(expectedResult).isEqualTo(lruAlgorithm.lru()),
					() -> assertThat(expectedResult2).isNotEqualTo(lruAlgorithm.lru()));
		}

		@Test
		@DisplayName("repeated test")
		@RepeatedTest(3)
		@Disabled
		void repeated_test(RepetitionInfo repetitionInfo) {
			// repeated tests with the current repetition
			System.out.println("current repetition" + repetitionInfo.getCurrentRepetition());
			lruAlgorithm.setCapacity(3);
			lruAlgorithm.add(423);
			lruAlgorithm.add(664);
			lruAlgorithm.add(662);
			lruAlgorithm.add(-510);
			lruAlgorithm.add(1000);

			final String expectedResult = "1000  -510  662";

			assertEquals(expectedResult, lruAlgorithm.lru(), () -> "it should fail and show this message");
		}

		@Test
		@DisplayName("[supplier] nested method with all assertions")
		void it_should_fail_testt() {
			lruAlgorithm.setCapacity(3);
			lruAlgorithm.add(3);
			lruAlgorithm.add(10);
			lruAlgorithm.add(10123);
			lruAlgorithm.add(577);
			lruAlgorithm.add(423);
			lruAlgorithm.add(664);
			lruAlgorithm.add(662);
			lruAlgorithm.add(-510);
			lruAlgorithm.add(1000);

			final String expectedResult = "1000  -510  662";

			assertEquals(expectedResult, lruAlgorithm.lru(), () -> "it should fail and show this message");
		}

		@Test
		@DisplayName("(doesn't run) nested method with assume assertions")
		void assume_that_test() {
			final Boolean isPrd = System.getenv("env") != null && System.getenv("env").equals("windows");
			assumeTrue(isPrd);

			lruAlgorithm.add(10);
			lruAlgorithm.add(10123);
			lruAlgorithm.add(577);
			lruAlgorithm.add(423);
			lruAlgorithm.add(664);
			lruAlgorithm.add(662);
			lruAlgorithm.add(-510);
			lruAlgorithm.add(1000);

			final String expectedResult = "1000  -510  662  664  423";
			final String expectedResult2 = "423  -510  662  664  1000";

			assertAll(() -> assertThat(expectedResult).isEqualTo(lruAlgorithm.lru()),
					() -> assertThat(expectedResult2).isNotEqualTo(lruAlgorithm.lru()));
		}

	}
}
