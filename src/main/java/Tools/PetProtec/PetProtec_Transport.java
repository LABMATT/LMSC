package Tools.PetProtec;

import java.util.HashSet;
import java.util.Set;

class PetProtec_Transport {

    private static Set<String> removeName = new HashSet<>();

    void addName(String uuid)
    {
        removeName.add(uuid);
    }

    void removeName(String uuid)
    {
        removeName.remove(uuid);
    }

    Set<String> getSet()
    {
        return removeName;
    }
}
