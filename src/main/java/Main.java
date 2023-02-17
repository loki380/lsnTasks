import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Choose task: ");
		Integer taskNumber = Integer.valueOf(br.readLine());

		if (taskNumber == 1) {
			Task.resolveFirstTask();
		} else if (taskNumber == 2) {
			Task.resolveSecondTask();
		} else if (taskNumber == 3) {
			Task.resolveThirdTask();
		}
	}
}