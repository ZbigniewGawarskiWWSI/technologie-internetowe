package pl.wwsi.gawarski.technologieinternetowe.util;

import java.util.UUID;

public class UuidFactory {
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
