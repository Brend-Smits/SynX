package net.kaikk.mc.synx;

import java.util.Calendar;
import java.util.Collection;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Utils {
	public static UUID toUUID(byte[] bytes) {
	    if (bytes.length != 16) {
	        throw new IllegalArgumentException();
	    }
	    int i = 0;
	    long msl = 0;
	    for (; i < 8; i++) {
	        msl = (msl << 8) | (bytes[i] & 0xFF);
	    }
	    long lsl = 0;
	    for (; i < 16; i++) {
	        lsl = (lsl << 8) | (bytes[i] & 0xFF);
	    }
	    return new UUID(msl, lsl);
	}
	
	public static String UUIDtoHexString(UUID uuid) {
		if (uuid==null) return "0x0";
		return "0x"+org.apache.commons.lang.StringUtils.leftPad(Long.toHexString(uuid.getMostSignificantBits()), 16, "0")+org.apache.commons.lang.StringUtils.leftPad(Long.toHexString(uuid.getLeastSignificantBits()), 16, "0");
	}
	
	public static boolean isFakePlayer(Player player) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if(player==p) {
				return false;
			}
		}
		return true;
	}
	
	public static String locationToString(Location location) {
		return "[" + location.getWorld().getName() + ", " + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + "]";
	}
	
	public static String mergeStringArrayFromIndex(String[] arrayString, int i) {
		StringBuilder sb = new StringBuilder();
		
		for(;i<arrayString.length;i++){
			sb.append(arrayString[i]);
			sb.append(' ');
		}
		
		if (sb.length()!=0) {
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	
	public static short getUtcYear() {
		return (short) Calendar.getInstance(TimeZone.getTimeZone("GMT")).get(Calendar.YEAR);
	}
	
	public static int epoch() {
		return (int) (System.currentTimeMillis()/1000);
	}
	
	private static Pattern alphanumeric = Pattern.compile("[a-zA-Z0-9]");
	public static boolean isAlphanumeric(String string) {
		return alphanumeric.matcher(string).find();
	}
	
	/** Returns true if they contain basically the same stuff, even with different orders */
	public static boolean compareCollections(Collection<?> c1, Collection<?> c2) {
		if (c1.size()!=c2.size()) {
			return false;
		}
		
		for (Object o : c1) {
			if (!c2.contains(o)) {
				return false;
			}
		}
		
		return true;
	}
}
