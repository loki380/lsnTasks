import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

		// Tworzymy nowe grupy lub dołączamy parę do istniejącej jeżeli istnieje dopasowanie
		pairs.forEach(pair -> {
			if (groupsOfVertices.isEmpty()) {
				groupsOfVertices.add(new ArrayList<>(Arrays.asList(pair.getLeft(), pair.getRight())));
			} else {
				groupsOfVertices.forEach(g -> {
					if (tmpPairs.contains(pair)) {
						Collections.sort(g);
						Integer leftSide = g.get(0);
						Integer rightSide = g.get(g.size() - 1);

						if (pair.getLeft().equals(leftSide) || pair.getLeft().equals(rightSide)) {
							g.add(pair.getRight());
							tmpPairs.remove(pair);
						} else if (pair.getRight().equals(leftSide) || pair.getRight().equals(rightSide)) {
							g.add(pair.getLeft());
							tmpPairs.remove(pair);
						}
					}
				});
				if (tmpPairs.contains(pair)) {
					groupsOfVertices.add(new ArrayList<>(Arrays.asList(pair.getLeft(), pair.getRight())));
				}
			}
		});

		// Łączymy grupy w przypadkach kiedy istnieją połączenia pomiędzy nimi
		List<List<Integer>> tmpGroups = new ArrayList<>(groupsOfVertices);
		List<List<Integer>> groupsToRemove = new ArrayList<>();
		groupsOfVertices.forEach(group -> {
			if (tmpGroups.contains(group)) {
				Integer leftSide = group.get(0);
				Integer rightSide = group.get(group.size() - 1);
				tmpGroups.remove(group);
				List<Integer> leftConnection = tmpGroups.stream().filter(g -> leftSide.equals(g.get(0)) || leftSide.equals(g.get(g.size() - 1))).findAny().orElse(null);
				List<Integer> rightConnection = tmpGroups.stream().filter(g -> rightSide.equals(g.get(0)) || rightSide.equals(g.get(g.size() - 1))).findAny().orElse(null);

				if (leftConnection != null) {
					group.remove(leftSide);
					group.addAll(leftConnection);
					tmpGroups.remove(leftConnection);
					groupsToRemove.add(leftConnection);
				} else if (rightConnection != null) {
					group.remove(rightSide);
					group.addAll(rightConnection);
					tmpGroups.remove(rightConnection);
					groupsToRemove.add(rightConnection);
				}
			}
		});
		groupsOfVertices.removeAll(groupsToRemove);

		// Wyświetlanie outputu
		System.out.println("Output: ");
		groupsOfVertices.forEach(group -> System.out.println(group.stream().

				map(v -> "[" + v.toString() + "]").

				collect(Collectors.joining("-"))));
	}
}
