package com.mg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class RestMatchesApplication {

	public static void main(String[] args) {
		/*Stream<Integer> numbersFromValues = Stream.of(1, 2, 3, 4);
		int[] numbers = {1, 2, 3, 4};
		IntStream numbersFromArray = Arrays.stream(numbers);

		Stream<Integer> numbers1 = Stream.iterate(0, n -> n + 10);
		numbers1.limit(5).forEach(System.out::println);*/

		SpringApplication.run(RestMatchesApplication.class, args);
	}
}
