package huh.enterprise.alpha.component.oura;

import huh.enterprise.alpha.component.oura.model.OuraQuery;
import huh.enterprise.alpha.component.oura.model.OuraSleepRecords;

public interface OuraService {

    OuraSleepRecords selectSleepPeriods(OuraQuery query);

    String selectActivitySummaries(String token);

    String selectUserInfo(String token);
}
