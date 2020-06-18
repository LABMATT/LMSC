package Tools.Lock;

import org.bukkit.Bukkit;
import org.bukkit.Location;

class Lock_Raw_Data {

    //Returns the location of the block.
    Location getData(String LockRule){
        try {
            String[] LockedSplit = LockRule.split(" ");

            double lockX = Double.parseDouble(LockedSplit[0]);
            double lockY = Double.parseDouble(LockedSplit[1]);
            double lockZ = Double.parseDouble(LockedSplit[2]);

            return new Location(Bukkit.getWorld(LockedSplit[4]), lockX, lockY, lockZ);
        } catch (NullPointerException ignore)
        {
            return null;
        }
    }

}
