package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;
import java.util.Iterator;
import java.util.Set;

public class AbstractSchedule implements Scheduler {

  @Override
  public int chooseThread(ConcurrentProgram program) throws DeadlockException {
    return 0;
  }

  protected static int getMinThread(Set<Integer> nums){
    Iterator<Integer> iterator = nums.iterator();
    int finalVal = iterator.next();

    while (iterator.hasNext()){
      int nextVal = iterator.next();
      if (finalVal > nextVal){
        finalVal = nextVal;
      }
    }
    return finalVal;
  }
}
