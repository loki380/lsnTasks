import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class Task {

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void resolveFirstTask() throws IOException {
		System.out.println("Enter input: ");
		String input = br.readLine();

		String[] splited = input.split(" ");

		List<Integer> inputAsListIntegers = Arrays.stream(splited)
				.map(Integer::valueOf)
				.collect(Collectors.toList());

		List<Integer> inputSortedAndDistinctAsIntegers = inputAsListIntegers.stream()
				.distinct()
				.sorted()
				.collect(Collectors.toList());

		Integer min = inputSortedAndDistinctAsIntegers.stream().min(Integer::compare).get();
		Integer max = inputSortedAndDistinctAsIntegers.stream().max(Integer::compare).get();

		System.out.println("Output: ");
		System.out.println(StringUtils.join(inputSortedAndDistinctAsIntegers, " "));
		System.out.println("count: " + inputAsListIntegers.size());
		System.out.println("distinct: " + inputSortedAndDistinctAsIntegers.size());
		System.out.println("min: " + min);
		System.out.println("max: " + max);
	}

	public static void resolveSecondTask() throws IOException {
		System.out.println("Enter input: ");
		String input = br.readLine();

		String[] splited = input.split(" ");

		List<Integer> inputAsListIntegers = Arrays.stream(splited)
				.map(Integer::valueOf)
				.sorted()
				.collect(Collectors.toList());

		List<Integer> usedIntegers = new ArrayList<>();

		List<Pair<Integer, Integer>> map = inputAsListIntegers.stream()
				.map(integer -> {
					int searchInt = 13 - integer;
					if (inputAsListIntegers.contains(searchInt)) {
						usedIntegers.add(searchInt);
						return new ImmutablePair<>(integer, searchInt);
					} else {
						return null;
					}
				})
				.filter(Objects::nonNull)
				.filter(i -> !usedIntegers.contains(i.getKey()))
				.collect(Collectors.toList());

		map.forEach(pair -> System.out.println(pair.getLeft() + " " + pair.getRight()));

	}

	public static void resolveThirdTask() {

	}

}
