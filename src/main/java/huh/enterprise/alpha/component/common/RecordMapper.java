package huh.enterprise.alpha.component.common;

import org.jooq.Record;

public interface RecordMapper<V> {
    V map(Record r);
}
