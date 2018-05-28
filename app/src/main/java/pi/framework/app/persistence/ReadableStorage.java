package pi.framework.app.persistence;

import java.util.stream.Stream;

interface ReadableStorage<D> {
    Stream<D> getDataStream();

    D get(int id);

    int size();
}
