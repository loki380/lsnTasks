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

	public static void resolveThirdTask() throws IOException {
		System.out.println("Enter input: ");
		int connections = Integer.parseInt(br.readLine());

		List<Pair<Integer, Integer>> pairs = new ArrayList<>();

		for (int i = 0; i < connections; i++) {
			String connection = br.readLine();

			String[] splited = connection.split(" ");

			List<Integer> inputAsListIntegers = Arrays.stream(splited)
					.map(Integer::valueOf)
					.collect(Collectors.toList());

			pairs.add(new ImmutablePair<>(inputAsListIntegers.get(0), inputAsListIntegers.get(1)));
		}

		List<List<Integer>> groupsOfVertices = new ArrayList<>();
		List<Pair<Integer, Integer>> tmpPairs = new ArrayList<>(pairs);

		pairs.forEach(pair -> {
			if (tmpPairs.contains(pair)) {
				List<Integer> groupOfVertices = new ArrayList<>(Arrays.asList(pair.getLeft(), pair.getRight()));
				tmpPairs.remove(pair);

				Pair<Integer, Integer> leftPair = searchPairForVerticle(pair.getLeft(), tmpPairs);
				if (leftPair != null) {
					tmpPairs.remove(leftPair);
					groupOfVertices.add(leftPair.getLeft());
					groupOfVertices.add(leftPair.getRight());
				}

				Pair<Integer, Integer> rightPair = searchPairForVerticle(pair.getRight(), tmpPairs);
				if (rightPair != null) {
					tmpPairs.remove(rightPair);
					groupOfVertices.add(rightPair.getLeft());
					groupOfVertices.add(rightPair.getRight());
				}

				groupsOfVertices.add(groupOfVertices.stream().distinct().collect(Collectors.toList()));
			}
		});

		System.out.println("Output: ");
		groupsOfVertices.forEach(group -> System.out.println(group.stream().map(v -> "[" + v.toString() + "]").collect(Collectors.joining("-"))));
	}

	private static Pair<Integer, Integer> searchPairForVerticle(int verticleValue, List<Pair<Integer, Integer>> pairs) {
		return pairs.stream().filter(p -> p.getLeft() == verticleValue || p.getRight() == verticleValue).findAny().orElse(null);

	}

}
