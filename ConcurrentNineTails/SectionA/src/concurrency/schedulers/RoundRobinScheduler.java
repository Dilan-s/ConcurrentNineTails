package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RoundRobinScheduler extends AbstractSchedule implements Scheduler {

  private int lastThread;
  private boolean isFirstChoice = true;

  public RoundRobinScheduler() {
  }

  @Override
  public int chooseThread(ConcurrentProgram program) throws DeadlockException {
    Set<Integer> enabledVals = program.getEnabledThreadIds();
    if (enabledVals.isEmpty()){
      throw new DeadlockException();
    }
    if (isFirstChoice){
      isFirstChoice = false;
      lastThread = getMinThread(enabledVals);
    } else {
      Set<Integer> remainingVals = new HashSet<>();
      for (Integer i : enabledVals){
        if (i > lastThread){
          remainingVals.add(i);
        }
      }
      if (remainingVals.isEmpty()){
        lastThread = getMinThread(enabledVals);
      } else {
        lastThread = getMinThread(remainingVals);
      }
    }
    return lastThread;
  }


}
