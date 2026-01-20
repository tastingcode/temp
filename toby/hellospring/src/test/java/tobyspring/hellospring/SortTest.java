package tobyspring.hellospring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SortTest {

	Sort sort;

	@BeforeEach
	void beforeEach() {
		sort = new Sort();
		System.out.println(this);
	}


	@Test
	void sort() {
		// arrange

		// act
		List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

		// assert
		Assertions.assertThat(list).isEqualTo(List.of("b", "aa"));

	}

	@Test
	void sort3Items() {
		// arrange

		// act
		List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));

		// assert
		Assertions.assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));

	}

	@Test
	void sortAlreadySorted() {
		// arrange

		// act
		List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));

		// assert
		Assertions.assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));

	}
}
