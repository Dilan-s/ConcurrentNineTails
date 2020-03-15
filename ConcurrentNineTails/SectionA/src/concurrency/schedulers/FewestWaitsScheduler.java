package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;
import concurrency.statements.Stmt;
import concurrency.statements.WaitStmt;
import java.util.HashSet;
import java.util.Set;

public class FewestWaitsScheduler extends AbstractSchedule implements Scheduler {

  private int lastThread;
  private boolean isFirstChoice = true;

  public FewestWaitsScheduler() {
  }

  @Override
  public int chooseThread(ConcurrentProgram program) throws DeadlockException {
    Set<Integer> enabledVals = program.getEnabledThreadIds();
    if (enabledVals.isEmpty()) {
      throw new DeadlockException();
    }
    Set<Integer> minWaitThread = new HashSet<>();
    int minNumWait = Integer.MAX_VALUE;
    for (Integer i : enabledVals) {
      int noWait = countWait(program, i);
      if (noWait < minNumWait) {
        minWaitThread = new HashSet<>();
        minNumWait = noWait;
        minWaitThread.add(i);
      }
      if (noWait == minNumWait) {
        minWaitThread.add(i);
      }
    }
    return getMinThread(minWaitThread);
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