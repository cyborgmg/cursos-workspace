  @Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawChangeData {
    private Boolean withdrawEnabled;
    private Boolean changeEnabled;
    private List<Period> periods;
}
  
  if (periodExists && superimposedBand(customer.getWithdrawChangeData().getPeriods())) {
     throw new BusinessException(CUSTOMER_WITHDRAW_CHANGE_DATA_LIMIT_TIMES_CONFLICT);
  }

  private static boolean between(LocalTime start, LocalTime end, LocalTime time) {
    if (start.isAfter(end)) {
      return !time.isBefore(start) || !time.isAfter(end);
    } else {
      return !time.isBefore(start) && !time.isAfter(end);
    }
  }

  private static boolean superimposedBand(List<Period> periudos) {

    HashMap<Integer, Period> periudoHashMap = new HashMap<>();
    AtomicInteger i = new AtomicInteger();
    periudos.forEach(p -> periudoHashMap.put(i.getAndIncrement(), p));

    for (Map.Entry<Integer, Period> periudoFor : periudoHashMap.entrySet()) {

      for (Map.Entry<Integer, Period> periudoRang : periudoHashMap.entrySet()) {

        if (!periudoRang.getKey().equals(periudoFor.getKey())) {

          if (between(
              periudoRang.getValue().getLimitHourBegin(),
              periudoFor.getValue().getLimitHourBegin(),
              periudoFor.getValue().getLimitHourEnd())) {
            return true;
          }

          if (between(
              periudoRang.getValue().getLimitHourEnd(),
              periudoFor.getValue().getLimitHourBegin(),
              periudoFor.getValue().getLimitHourEnd())) {
            return true;
          }
        }
      }
    }
    return false;
  }