package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;
import concurrency.statements.Stmt;
import concurrency.statements.WaitStmt;
import java.util.HashSet;
import java.util.Set;

public class FewestWaitsScheduler extends AbstractSchedule implements Scheduler {

  public FewestWaitsScheduler() {
  }

  @Override
  public int chooseThread(ConcurrentProgram program) throws DeadlockException {
    Set<Integer> threads = program.getEnabledThreadIds();
    if (threads.isEmpty()) {
      throw new DeadlockException();
    }
    Set<Integer> fewestWaits = new HashSet<>();
    int minWait = Integer.MAX_VALUE;
    for (Integer i : threads) {
      int currWait = countWait(program, i);
      if (currWait < minWait) {
        minWait = currWait;
        fewestWaits = new HashSet<>();
        fewestWaits.add(i);
      } else if (currWait == minWait) {
        fewestWaits.add(i);
      }
    }
    return getMinThread(fewestWaits);
  }

  private static int countWait(ConcurrentProgram program, Integer next) {
    int counter = 0;
    for (Stmt s : program.remainingStatements(next)) {
      if (s instanceof WaitStmt) {
        counter++;
      }
    }
    return counter;
  }
}