package altcompetition;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JobAssignments extends CompetitionTest {

    @Override
    protected String runTest(List<String> args) {
        List<String> employees = List.of(args.get(0).split("\\s+"));

        List<Set<String>> jobs = args.stream().skip(1)
                .map(s -> Arrays.stream(s.split("\\s+"))
                        .collect(Collectors.toSet()))
                .collect(Collectors.toList());
        return String.valueOf(assignJobs(employees, jobs));
    }

    /**
     * You are provided with a list of employees and list of job tickets.
     * Each job contains a list of employees needed to complete the job.
     * Assuming jobs can't be executed in parallel if they share any employees,
     * What is the maximum number of jobs that can be performed concurrently?
     *
     * Number of employees <= 2^6
     * Name of each employee is 4-10 characters
     *
     * Number of jobs <= 2^6
     */
    public final int assignJobs(List<String> employees, List<Set<String>> jobs) {
        return -1;
    }

}
