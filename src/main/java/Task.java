import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class Task {

	public static void resolveFirstTask() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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

	private static void resolveSecondTask() {

	}

	private static void resolveThirdTask() {

	}

}
