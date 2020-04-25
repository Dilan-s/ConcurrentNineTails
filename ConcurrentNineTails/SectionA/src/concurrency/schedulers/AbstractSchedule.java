package concurrency.schedulers;

import java.util.Iterator;
import java.util.Set;

public abstract class AbstractSchedule implements Scheduler {

  protected static int getMinThread(Set<Integer> nums) {
    Iterator<Integer> iterator = nums.iterator();
    int finalVal = iterator.next();

    while (iterator.hasNext()) {
      int nextVal = iterator.next();
      if (finalVal > nextVal) {
        finalVal = nextVal;
      }
    }
    return finalVal;
  }
}