import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

/**
 * Complete solutions for the recursive algorithm and Big-O exercises.
 *
 * Run examples:
 *   java RecursiveBigOExercises.java
 *
 * Run the five-trial performance experiments for Questions 4 and 5:
 *   java RecursiveBigOExercises.java benchmark
 */
public class RecursiveBigOExercises {

    private static boolean showPairMessages = true;
    private static volatile long benchmarkChecksum = 0L;

    private RecursiveBigOExercises() {
        // Utility class: do not create instances.
    }

    // ---------------------------------------------------------------------
    // Question 1: Reverse a string
    // ---------------------------------------------------------------------

    /**
     * Recursive solution required by the exercise.
     * Base case: an empty or one-character string is already reversed.
     * Recursive case: last character + reverse(prefix).
     */
    static String reverseRecursive(String s) {
        if (s == null || s.length() <= 1) {
            return s == null ? "" : s;
        }
        int lastIndex = s.length() - 1;
        return s.charAt(lastIndex) + reverseRecursive(s.substring(0, lastIndex));
    }

    /** Iterative O(n) solution using StringBuilder. */
    static String reverseIterative(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        StringBuilder reversed = new StringBuilder(s.length());
        for (int index = s.length() - 1; index >= 0; index--) {
            reversed.append(s.charAt(index));
        }
        return reversed.toString();
    }

    // ---------------------------------------------------------------------
    // Question 2: Palindrome checking
    // ---------------------------------------------------------------------

    /**
     * Builds a normalized string and a manually reversed copy, then compares
     * them. Letter case, whitespace, and punctuation are ignored.
     */
    static boolean isPalindromeByReverse(String s) {
        String normalized = normalizeLettersAndDigits(s);
        StringBuilder reversed = new StringBuilder(normalized.length());
        for (int index = normalized.length() - 1; index >= 0; index--) {
            reversed.append(normalized.charAt(index));
        }
        return normalized.contentEquals(reversed);
    }

    /**
     * Recursive two-pointer solution. Non-alphanumeric characters are skipped
     * in-place, so no normalized copy is needed.
     *
     * Typical call:
     *   isPalindromeRecursive(s, 0, s == null ? -1 : s.length() - 1)
     */
    static boolean isPalindromeRecursive(String s, int left, int right) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        // Keep callers safe even if they provide out-of-range boundaries.
        left = Math.max(0, left);
        right = Math.min(s.length() - 1, right);

        // Base case: all relevant character pairs have matched.
        if (left >= right) {
            return true;
        }

        // Recursive cases for ignored spaces and punctuation.
        if (!Character.isLetterOrDigit(s.charAt(left))) {
            return isPalindromeRecursive(s, left + 1, right);
        }
        if (!Character.isLetterOrDigit(s.charAt(right))) {
            return isPalindromeRecursive(s, left, right - 1);
        }

        if (Character.toLowerCase(s.charAt(left))
                != Character.toLowerCase(s.charAt(right))) {
            return false; // Early termination on the first mismatch.
        }

        // Recursive case: check the next inner pair.
        return isPalindromeRecursive(s, left + 1, right - 1);
    }

    private static String normalizeLettersAndDigits(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        StringBuilder normalized = new StringBuilder(s.length());
        for (int index = 0; index < s.length(); index++) {
            char current = s.charAt(index);
            if (Character.isLetterOrDigit(current)) {
                normalized.append(Character.toLowerCase(current));
            }
        }
        return normalized.toString();
    }

    // ---------------------------------------------------------------------
    // Question 3: Compare vowels and consonants
    // ---------------------------------------------------------------------

    /** Result object used to display both counts in the examples. */
    static final class LetterCount {
        final int vowels;
        final int consonants;

        LetterCount(int vowels, int consonants) {
            this.vowels = vowels;
            this.consonants = consonants;
        }

        boolean hasMoreVowels() {
            return vowels > consonants;
        }
    }

    static boolean hasMoreVowelsRecursive(String s) {
        return countLettersRecursive(s, 0, 0, 0).hasMoreVowels();
    }

    /**
     * Carries the two counters into the next recursive call.
     * Base case: index reaches the end of the string.
     */
    private static LetterCount countLettersRecursive(
            String s, int index, int vowelCount, int consonantCount) {
        if (s == null || index >= s.length()) {
            return new LetterCount(vowelCount, consonantCount);
        }

        char current = Character.toLowerCase(s.charAt(index));
        if (current >= 'a' && current <= 'z') {
            if (isEnglishVowel(current)) {
                vowelCount++;
            } else {
                consonantCount++;
            }
        }

        return countLettersRecursive(s, index + 1, vowelCount, consonantCount);
    }

    static boolean hasMoreVowelsIterative(String s) {
        return countLettersIterative(s).hasMoreVowels();
    }

    static LetterCount countLettersIterative(String s) {
        int vowelCount = 0;
        int consonantCount = 0;

        if (s == null) {
            return new LetterCount(0, 0);
        }

        for (int index = 0; index < s.length(); index++) {
            char current = Character.toLowerCase(s.charAt(index));
            if (current >= 'a' && current <= 'z') {
                if (isEnglishVowel(current)) {
                    vowelCount++;
                } else {
                    consonantCount++;
                }
            }
        }
        return new LetterCount(vowelCount, consonantCount);
    }

    private static boolean isEnglishVowel(char character) {
        return character == 'a' || character == 'e' || character == 'i'
                || character == 'o' || character == 'u';
    }

    // ---------------------------------------------------------------------
    // Question 4: Put even numbers before odd numbers
    // ---------------------------------------------------------------------

    /** Recursive, in-place two-pointer solution. */
    static void rearrangeRecursive(int[] a, int left, int right) {
        if (a == null || left >= right) {
            return;
        }

        if (isEven(a[left])) {
            rearrangeRecursive(a, left + 1, right);
        } else if (!isEven(a[right])) {
            rearrangeRecursive(a, left, right - 1);
        } else {
            swap(a, left, right);
            rearrangeRecursive(a, left + 1, right - 1);
        }
    }

    /** Iterative, in-place two-pointer solution. */
    static void rearrangeTwoPointer(int[] a) {
        if (a == null || a.length < 2) {
            return;
        }

        int left = 0;
        int right = a.length - 1;

        while (left < right) {
            if (isEven(a[left])) {
                left++;
            } else if (!isEven(a[right])) {
                right--;
            } else {
                swap(a, left, right);
                left++;
                right--;
            }
        }
    }

    /** Stable solution that returns a new array and preserves the input. */
    static int[] rearrangeExtraArray(int[] a) {
        if (a == null) {
            return new int[0];
        }

        int[] result = new int[a.length];
        int writeIndex = 0;

        // First pass preserves the relative order of all even numbers.
        for (int value : a) {
            if (isEven(value)) {
                result[writeIndex++] = value;
            }
        }

        // Second pass preserves the relative order of all odd numbers.
        for (int value : a) {
            if (!isEven(value)) {
                result[writeIndex++] = value;
            }
        }
        return result;
    }

    private static boolean isEven(int value) {
        return value % 2 == 0;
    }

    // ---------------------------------------------------------------------
    // Question 5: Partition an array around k
    // ---------------------------------------------------------------------

    /** Recursive, in-place partition: values <= k are placed before values > k. */
    static void partitionRecursive(int[] a, int k, int left, int right) {
        if (a == null || left >= right) {
            return;
        }

        if (a[left] <= k) {
            partitionRecursive(a, k, left + 1, right);
        } else if (a[right] > k) {
            partitionRecursive(a, k, left, right - 1);
        } else {
            swap(a, left, right);
            partitionRecursive(a, k, left + 1, right - 1);
        }
    }

    /** Iterative, in-place partition. */
    static void partitionIterative(int[] a, int k) {
        if (a == null || a.length < 2) {
            return;
        }

        int left = 0;
        int right = a.length - 1;

        while (left < right) {
            if (a[left] <= k) {
                left++;
            } else if (a[right] > k) {
                right--;
            } else {
                swap(a, left, right);
                left++;
                right--;
            }
        }
    }

    /**
     * Sorting-based solution permitted by the exercise. Sorting is more work
     * than partitioning because it orders values inside both groups as well.
     */
    static void partitionBySorting(int[] a, int k) {
        if (a == null || a.length < 2) {
            return;
        }
        Arrays.sort(a);
        // The boundary can be obtained with lastIndexAtMostK(a, k) if needed.
    }

    /** Returns the last index containing a value <= k in a sorted array. */
    static int lastIndexAtMostK(int[] sorted, int k) {
        if (sorted == null || sorted.length == 0) {
            return -1;
        }

        int low = 0;
        int high = sorted.length - 1;
        int answer = -1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (sorted[middle] <= k) {
                answer = middle;
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return answer;
    }

    // ---------------------------------------------------------------------
    // Question 6: Find two sorted-array values whose sum equals k
    // ---------------------------------------------------------------------

    /** Checks every possible pair. */
    static boolean findPairBruteForce(int[] a, int k) {
        if (a == null || a.length < 2) {
            return reportNoPair();
        }

        for (int first = 0; first < a.length - 1; first++) {
            for (int second = first + 1; second < a.length; second++) {
                if ((long) a[first] + a[second] == k) {
                    return reportPair(a[first], a[second]);
                }
            }
        }
        return reportNoPair();
    }

    /** Recursive two-pointer method for an array sorted in ascending order. */
    static boolean findPairRecursive(int[] a, int k, int left, int right) {
        if (a == null || left < 0 || right >= a.length || left >= right) {
            return reportNoPair();
        }

        long sum = (long) a[left] + a[right];
        if (sum == k) {
            return reportPair(a[left], a[right]);
        }
        if (sum < k) {
            return findPairRecursive(a, k, left + 1, right);
        }
        return findPairRecursive(a, k, left, right - 1);
    }

    /** For each value, binary-searches for its complement in the remaining suffix. */
    static boolean findPairBinarySearch(int[] a, int k) {
        if (a == null || a.length < 2) {
            return reportNoPair();
        }

        for (int index = 0; index < a.length - 1; index++) {
            long complementLong = (long) k - a[index];
            if (complementLong < Integer.MIN_VALUE || complementLong > Integer.MAX_VALUE) {
                continue;
            }
            int complement = (int) complementLong;
            int foundIndex = binarySearch(a, index + 1, a.length - 1, complement);
            if (foundIndex >= 0) {
                return reportPair(a[index], a[foundIndex]);
            }
        }
        return reportNoPair();
    }

    private static int binarySearch(int[] a, int low, int high, int target) {
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (a[middle] == target) {
                return middle;
            }
            if (a[middle] < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return -1;
    }

    private static boolean reportPair(int first, int second) {
        if (showPairMessages) {
            System.out.println("Pair found: " + first + " and " + second);
        }
        return true;
    }

    private static boolean reportNoPair() {
        if (showPairMessages) {
            System.out.println("Pair not found");
        }
        return false;
    }

    private static void swap(int[] a, int first, int second) {
        int temporary = a[first];
        a[first] = a[second];
        a[second] = temporary;
    }

    // ---------------------------------------------------------------------
    // Demonstrations and special-case tests
    // ---------------------------------------------------------------------

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        if (args.length > 0 && "benchmark".equalsIgnoreCase(args[0])) {
            runPerformanceExperiments();
            return;
        }

        demonstrateAllQuestions();
        System.out.println();
        System.out.println("Run with argument 'benchmark' for the five-trial timing tables.");
    }

    private static void demonstrateAllQuestions() {
        System.out.println("QUESTION 1");
        String original = "pots&pans";
        System.out.println("Input: " + original);
        System.out.println("Recursive: " + reverseRecursive(original));
        System.out.println("Iterative: " + reverseIterative(original));
        System.out.println("Empty input: '" + reverseIterative("") + "'");

        System.out.println("\nQUESTION 2");
        String[] palindromeTests = {
            "racecar",
            "level",
            "algorithm",
            "gohangasalamiimalasagnahog",
            "A man, a plan, a canal: Panama",
            ""
        };
        for (String test : palindromeTests) {
            boolean byReverse = isPalindromeByReverse(test);
            boolean byRecursion = isPalindromeRecursive(test, 0, test.length() - 1);
            System.out.printf("%-38s reverse=%-5s recursive=%-5s%n",
                    '"' + test + '"', byReverse, byRecursion);
        }

        System.out.println("\nQUESTION 3");
        String letterInput = "education";
        LetterCount counts = countLettersIterative(letterInput);
        System.out.println("Input: " + letterInput);
        System.out.println("Vowels: " + counts.vowels);
        System.out.println("Consonants: " + counts.consonants);
        System.out.println("Recursive result: " + hasMoreVowelsRecursive(letterInput));
        System.out.println("Iterative result: " + hasMoreVowelsIterative(letterInput));
        System.out.println("Ignored symbols test: "
                + hasMoreVowelsIterative("AEI 123!? bc"));

        System.out.println("\nQUESTION 4");
        int[] parityInput = {7, 2, 9, 4, 1, 6, 3, 8};
        int[] recursiveParity = parityInput.clone();
        rearrangeRecursive(recursiveParity, 0, recursiveParity.length - 1);
        int[] iterativeParity = parityInput.clone();
        rearrangeTwoPointer(iterativeParity);
        int[] stableParity = rearrangeExtraArray(parityInput);
        System.out.println("Input:     " + Arrays.toString(parityInput));
        System.out.println("Recursive: " + Arrays.toString(recursiveParity));
        System.out.println("Iterative: " + Arrays.toString(iterativeParity));
        System.out.println("Stable:    " + Arrays.toString(stableParity));

        System.out.println("\nQUESTION 5");
        int[] partitionInput = {12, 4, 7, 15, 3, 10, 8};
        int partitionValue = 8;
        int[] recursivePartition = partitionInput.clone();
        partitionRecursive(recursivePartition, partitionValue, 0,
                recursivePartition.length - 1);
        int[] iterativePartition = partitionInput.clone();
        partitionIterative(iterativePartition, partitionValue);
        int[] sortedPartition = partitionInput.clone();
        partitionBySorting(sortedPartition, partitionValue);
        System.out.println("Input:     " + Arrays.toString(partitionInput) + ", k=" + partitionValue);
        System.out.println("Recursive: " + Arrays.toString(recursivePartition));
        System.out.println("Iterative: " + Arrays.toString(iterativePartition));
        System.out.println("Sorting:   " + Arrays.toString(sortedPartition));
        System.out.println("Last index <= k after sorting: "
                + lastIndexAtMostK(sortedPartition, partitionValue));

        System.out.println("\nQUESTION 6");
        int[] sortedInput = {2, 4, 7, 11, 15, 20};
        int target = 18;
        System.out.println("Input: " + Arrays.toString(sortedInput) + ", k=" + target);
        System.out.print("Brute force: ");
        findPairBruteForce(sortedInput, target);
        System.out.print("Recursive two-pointer: ");
        findPairRecursive(sortedInput, target, 0, sortedInput.length - 1);
        System.out.print("Binary search: ");
        findPairBinarySearch(sortedInput, target);

        runAssertions();
    }

    /** Lightweight correctness checks for normal and special cases. */
    private static void runAssertions() {
        require("".equals(reverseRecursive(null)), "Null reverse must be safe");
        require("a".equals(reverseRecursive("a")), "Single-character reverse failed");
        require(isPalindromeByReverse("!!!"), "Punctuation-only palindrome failed");
        require(isPalindromeRecursive("No 'x' in Nixon", 0, 14),
                "Recursive punctuation/case palindrome failed");
        require(!hasMoreVowelsIterative("123 !"), "Ignored-character count failed");
        require(rearrangeExtraArray(null).length == 0, "Null array must be safe");

        int[] parity = {5, 2, 7, 4, 9, 6};
        require(Arrays.equals(rearrangeExtraArray(parity),
                new int[] {2, 4, 6, 5, 7, 9}), "Stable parity order failed");

        int[] partition = {9, 2, 8, 1, 7, 3};
        partitionIterative(partition, 3);
        require(isPartitionedByK(partition, 3), "Partition invariant failed");
        System.out.println("\nAll correctness checks passed.");
    }

    private static void require(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }

    // ---------------------------------------------------------------------
    // Performance experiments: Questions 4 and 5, five trials per size
    // ---------------------------------------------------------------------

    private interface ArrayAlgorithm {
        int[] run(int[] input);
    }

    private static final class TimingResult {
        final boolean stackOverflow;
        final long averageNanoseconds;

        TimingResult(boolean stackOverflow, long averageNanoseconds) {
            this.stackOverflow = stackOverflow;
            this.averageNanoseconds = averageNanoseconds;
        }

        String display() {
            return stackOverflow ? "StackOverflow" : String.format("%,d", averageNanoseconds);
        }
    }

    static void runPerformanceExperiments() {
        final int[] sizes = {100, 1_000, 10_000, 100_000};
        final int trials = 5;
        showPairMessages = false;

        System.out.println("PERFORMANCE EXPERIMENTS");
        System.out.println("Each value is the average of " + trials
                + " measured runs in nanoseconds. Array cloning is excluded.");
        System.out.println("Recursive failures are reported as StackOverflow.");

        System.out.println("\nQUESTION 4: EVEN BEFORE ODD");
        System.out.printf("%10s | %16s | %16s | %16s%n",
                "n", "Recursive", "Two-pointer", "Extra array");
        System.out.println("-----------+------------------+------------------+------------------");
        for (int size : sizes) {
            int[] source = randomArray(size, 40_000L + size);
            TimingResult recursive = measure(source, trials, input -> {
                rearrangeRecursive(input, 0, input.length - 1);
                return input;
            });
            TimingResult iterative = measure(source, trials, input -> {
                rearrangeTwoPointer(input);
                return input;
            });
            TimingResult extra = measure(source, trials,
                    RecursiveBigOExercises::rearrangeExtraArray);

            System.out.printf("%,10d | %16s | %16s | %16s%n",
                    size, recursive.display(), iterative.display(), extra.display());
        }

        System.out.println("\nQUESTION 5: PARTITION AROUND k=0");
        System.out.printf("%10s | %16s | %16s | %16s%n",
                "n", "Recursive", "Iterative", "Sorting");
        System.out.println("-----------+------------------+------------------+------------------");
        for (int size : sizes) {
            int[] source = randomArray(size, 50_000L + size);
            TimingResult recursive = measure(source, trials, input -> {
                partitionRecursive(input, 0, 0, input.length - 1);
                return input;
            });
            TimingResult iterative = measure(source, trials, input -> {
                partitionIterative(input, 0);
                return input;
            });
            TimingResult sorting = measure(source, trials, input -> {
                partitionBySorting(input, 0);
                return input;
            });

            System.out.printf("%,10d | %16s | %16s | %16s%n",
                    size, recursive.display(), iterative.display(), sorting.display());
        }

        System.out.println("\nBenchmark checksum: " + benchmarkChecksum);
        System.out.println("Times vary with JVM warm-up, hardware, OS scheduling, and GC.");
    }

    private static TimingResult measure(int[] source, int trials, ArrayAlgorithm algorithm) {
        // Repeated warm-up calls reduce one-time interpreter/JIT effects.
        for (int warmUp = 0; warmUp < 30; warmUp++) {
            int[] warmInput = source.clone();
            try {
                int[] output = algorithm.run(warmInput);
                consume(output);
            } catch (StackOverflowError error) {
                return new TimingResult(true, 0L);
            }
        }

        long total = 0L;
        for (int trial = 0; trial < trials; trial++) {
            int[] input = source.clone(); // Excluded from the measured interval.
            long start = System.nanoTime();
            int[] output;
            try {
                output = algorithm.run(input);
            } catch (StackOverflowError error) {
                return new TimingResult(true, 0L);
            }
            long end = System.nanoTime();
            total += end - start;
            consume(output);
        }
        return new TimingResult(false, total / trials);
    }

    private static int[] randomArray(int size, long seed) {
        Random random = new Random(seed);
        int[] values = new int[size];
        for (int index = 0; index < size; index++) {
            values[index] = random.nextInt();
        }
        return values;
    }

    private static void consume(int[] values) {
        if (values != null && values.length > 0) {
            benchmarkChecksum += values[0];
            benchmarkChecksum += values[values.length - 1];
            benchmarkChecksum += values.length;
        }
    }

    private static boolean isPartitionedByK(int[] a, int k) {
        boolean seenGreater = false;
        for (int value : a) {
            if (value > k) {
                seenGreater = true;
            } else if (seenGreater) {
                return false;
            }
        }
        return true;
    }
}
